package com.github.zcmee.excelutil.components;

import com.github.zcmee.excelutil.api.Converter;
import com.github.zcmee.excelutil.exceptions.ConvertException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class MultipartFileToWorkbookConverter implements Converter<MultipartFile, Workbook> {

    @Override
    public Workbook convert(MultipartFile multipartFile) {
        try (Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream())) {
            return workbook;
        } catch (InvalidFormatException | IOException e) {
            throw new ConvertException(e);
        }
    }
}
