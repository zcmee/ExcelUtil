package com.github.zcmee.excelutil.controllers;

import com.github.zcmee.excelutil.api.ExcelReaderTemplate;
import com.github.zcmee.excelutil.components.XlsReadersFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("textxls")
public class TestController {
    private final XlsReadersFactory xlsReadersFactory;

    //@TODO co zrobić z utilasmi gdy komponent ma być użyty klasie nie springowej
    public TestController(XlsReadersFactory xlsReadersFactory) {
        this.xlsReadersFactory = xlsReadersFactory;
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("upload")
    @ResponseBody
    public List<ExcelReaderTemplate> upload(@RequestParam("file") MultipartFile file,
                                            @RequestParam("channel") String channel) {

        ExcelReaderTemplate excelReaderTemplate = xlsReadersFactory.createXlsReader(channel, file);
        return excelReaderTemplate.generateComplaint();
    }

}
