package com.github.zcmee.excelutil.components;

import com.github.zcmee.excelutil.api.Converter;
import com.github.zcmee.excelutil.api.ExcelReaderTemplate;
import com.github.zcmee.excelutil.headers.B2BHeaders;
import com.github.zcmee.excelutil.readers.B2BReader;
import com.github.zcmee.excelutil.readers.UserReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class XlsReadersFactory {
    private final Converter<MultipartFile, Workbook> multipartFileToWorkbookConverter;

    public XlsReadersFactory(Converter<MultipartFile, Workbook> multipartFileToWorkbookConverter) {
        this.multipartFileToWorkbookConverter = multipartFileToWorkbookConverter;
    }

    public ExcelReaderTemplate createXlsReader(String name, MultipartFile multipartFile) {
        ExcelReaderTemplate excelReaderTemplate;
        Workbook workbook = multipartFileToWorkbookConverter.convert(multipartFile);
        Sheet sheet = workbook.getSheetAt(0);

        switch (name) {
            case "B2B":
                excelReaderTemplate = new B2BReader(sheet);
                excelReaderTemplate.setHeaderToValidation(new B2BHeaders());
            break;
            case "USER":
                excelReaderTemplate = new UserReader(sheet);
            break;
            default: throw new IllegalArgumentException("Wrong name of reader");
        }

        return excelReaderTemplate;
    }
}
