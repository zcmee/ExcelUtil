package com.github.zcmee.excelutil.utils;

import com.github.zcmee.excelutil.exceptions.ConvertException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;

public class ExcelOperations {
    private static final ExcelOperations instance = new ExcelOperations();

    private ExcelOperations() {

    }

    public static ExcelOperations getInstance() {
        return instance;
    }

    public Sheet getSheet(File file, int sheetNumber)  {
        try(Workbook workbook = WorkbookFactory.create(file)) {
            return workbook.getSheetAt(sheetNumber);
        } catch(Exception ex) {
            throw new ConvertException(ex);
        }
    }

}
