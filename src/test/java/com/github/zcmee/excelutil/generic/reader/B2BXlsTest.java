package com.github.zcmee.excelutil.generic.reader;

import com.github.zcmee.excelutil.dtoes.B2B;
import com.github.zcmee.excelutil.headers.B2BHeaders;
import com.github.zcmee.excelutil.readers.B2BReader;
import com.github.zcmee.excelutil.utils.ExcelOperations;
import com.github.zcmee.excelutil.utils.FileOperations;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class B2BXlsTest {

    @Test
    public void correctB2BWithHeadersTest() throws IOException, InvalidFormatException {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/b2b.xlsx");
        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);

        B2BReader reader = new B2BReader(sheet);
        reader.setHeaderToValidation(new B2BHeaders());
        List<B2B> agreements =  reader.generateComplaint();

        assertEquals(3, agreements.size());
    }

    @Test
    public void correctB2BWithoutHeadersTest() throws IOException, InvalidFormatException {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/b2b.xlsx");

        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);
        B2BReader reader = new B2BReader(sheet);
        List<B2B> agreements =  reader.generateComplaint();

        assertEquals(3, agreements.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void writerB2BWithWrongHeaders() throws IOException, InvalidFormatException {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/b2b.xlsx");
        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);
        B2BReader reader = new B2BReader(sheet);
        reader.setHeaderToValidation(new WrongB2BHeaders());
        List<B2B> users =  reader.generateComplaint();
        assertTrue(false);
    }

    @Test
    public void correctB2BWithoutHeadersTestWithEmptyStringFields() throws IOException, InvalidFormatException {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/b2b2.xlsx");
        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);
        B2BReader reader = new B2BReader(sheet);
        List<B2B> agreements =  reader.generateComplaint();

        assertEquals(4, agreements.size());
    }

}
