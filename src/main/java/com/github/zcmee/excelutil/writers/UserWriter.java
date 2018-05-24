package com.github.zcmee.excelutil.writers;

import com.github.zcmee.excelutil.api.ExcelWriterTemplate;
import com.github.zcmee.excelutil.dtoes.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class UserWriter extends ExcelWriterTemplate<User> {
    public UserWriter(List<User> elementsToSaveExcelFile) {
        super(elementsToSaveExcelFile);
    }

    @Override
    protected void generateExcelFileFromList(List<User> elements, Integer rowStart, Sheet sheet) {
        for(User user : elements) {
            HSSFRow row = (HSSFRow) sheet.createRow(rowStart++);
            row.createCell(0).setCellValue(user.getFirstName());
            row.createCell(1).setCellValue(user.getLastName());
            row.createCell(2).setCellValue(user.getAge());
        }
    }
}
