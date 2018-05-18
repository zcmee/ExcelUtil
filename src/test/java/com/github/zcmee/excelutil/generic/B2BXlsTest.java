package com.github.zcmee.excelutil.generic;

import com.github.zcmee.excelutil.dtoes.B2B;
import com.github.zcmee.excelutil.headers.B2BHeaders;
import com.github.zcmee.excelutil.readers.B2BReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class B2BXlsTest {

    @Test
    public void correctB2BWithHeadersTest() throws IOException, InvalidFormatException {
        File file = getFileFromResource("testfiles/b2b.xlsx");
        Sheet sheet = getSheet(file, 0);
        B2BReader reader = new B2BReader(sheet);
        reader.setHeaderToValidation(new B2BHeaders());
        List<B2B> agreements =  reader.generateComplaint();

        assertEquals(3, agreements.size());
    }

    @Test
    public void correctB2BWithoutHeadersTest() throws IOException, InvalidFormatException {
        File file = getFileFromResource("testfiles/b2b.xlsx");
        Sheet sheet = getSheet(file, 0);
        B2BReader reader = new B2BReader(sheet);
        List<B2B> agreements =  reader.generateComplaint();

        assertEquals(3, agreements.size());
    }

    private File getFileFromResource(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

    private Sheet getSheet(File file, int sheetNumber) throws IOException, InvalidFormatException {
        try(Workbook workbook = (Workbook) WorkbookFactory.create(file)) {
            return workbook.getSheetAt(sheetNumber);
        }
    }

}
