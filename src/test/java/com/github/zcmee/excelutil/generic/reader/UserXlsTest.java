package com.github.zcmee.excelutil.generic.reader;

import com.github.zcmee.excelutil.dtoes.User;
import com.github.zcmee.excelutil.exceptions.ConvertException;
import com.github.zcmee.excelutil.readers.UserReader;
import com.github.zcmee.excelutil.utils.FileOperations;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserXlsTest {

    @Test
    public void testUserFile() throws IOException, InvalidFormatException {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/users.xls");
        Sheet sheet = getSheet(file, 0);
        UserReader reader = new UserReader(sheet);
        List<User> users =  reader.generateComplaint();
        assertEquals(2, users.size());
    }

    private Sheet getSheet(File file, int sheetNumber) throws IOException, InvalidFormatException {
        try(Workbook workbook = (Workbook) WorkbookFactory.create(file)) {
             return workbook.getSheetAt(sheetNumber);
        } catch(Exception ex) {
            throw new ConvertException(ex);
        }
    }
}
