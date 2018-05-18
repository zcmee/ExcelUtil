package com.github.zcmee.excelutil.generic;

import com.github.zcmee.excelutil.dtoes.User;
import com.github.zcmee.excelutil.exceptions.ConvertException;
import com.github.zcmee.excelutil.readers.UserReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserXlsTest {

    @Test
    public void testUserFile() throws IOException, InvalidFormatException {
        File file = getFileFromResource("testfiles/users.xls");
        Sheet sheet = getSheet(file, 0);
        UserReader reader = new UserReader(sheet);
        List<User> users =  reader.generateComplaint();
        assertEquals(2, users.size());
    }

    private File getFileFromResource(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

//    @TODO Duplicate code
    //czy zrobić statyczne

    private Sheet getSheet(File file, int sheetNumber) throws IOException, InvalidFormatException {
        try(Workbook workbook = (Workbook) WorkbookFactory.create(file)) {
             return workbook.getSheetAt(sheetNumber);
        } catch(Exception ex) {
            throw new ConvertException(ex);
        }
    }
}
