package com.github.zcmee.excelutil.generic.writers;

import com.github.zcmee.excelutil.dtoes.User;
import com.github.zcmee.excelutil.headers.UserHeaders;
import com.github.zcmee.excelutil.utils.DateOperations;
import com.github.zcmee.excelutil.utils.ExcelOperations;
import com.github.zcmee.excelutil.writers.UserWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WriterUserXlsTest {

    @Test
    public void writerUserWithoutHeaders() throws IOException, InvalidFormatException {
        UserWriter writer = new UserWriter(getFilledList());
        File file = writer.generateExcelFile();
        saveOnDisc("/home/Adam/Desktop/withoutheaders.xlsx", file);

        assertTrue(true);
    }

    @Test
    public void writerUserWithHeaders() throws IOException, InvalidFormatException {
        UserWriter writer = new UserWriter(getFilledList());
        writer.setHeaders(new UserHeaders());
        File file = writer.generateExcelFile();
        saveOnDisc("/home/Adam/Desktop/withheaders.xls", file);

        assertTrue(true);
    }

    @Test
    public void writerUserExceptedValues() throws IOException {
        UserWriter writer = new UserWriter(getFilledList());
        writer.setHeaders(new UserHeaders());
        File file = writer.generateExcelFile();

        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);
        Row row = sheet.getRow(2);
        String firstName = row.getCell(0).getStringCellValue();
        String lastName = row.getCell(1).getStringCellValue();
        Integer age = (int) row.getCell(2).getNumericCellValue();
        String formattedDate = row.getCell(3).getStringCellValue();
        Date dateOfSignation = DateOperations.getInstance().getDateFromString("yyyy-MM-dd", formattedDate);

        User userTest = new User(firstName, lastName, age,dateOfSignation);
        User exceptedUser = getFilledList().get(1);

        Assert.assertEquals(exceptedUser, userTest);
    }


    private void saveOnDisc(String path, File file) {
        try(OutputStream out = new FileOutputStream(new File(path))){
            out.write(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Nie udało się zapisać danych na dysku");
        }
    }

    private List<User> getFilledList() {
        User user1 = new User("Adam", "Bravo", 12, DateOperations.getInstance().getDateFromString("yyyy-MM-dd", "2019-09-10"));
        User user2 = new User("Piotr", "Jajeczniak", 25, DateOperations.getInstance().getDateFromString("yyyy-MM-dd", "2019-09-11"));

        List<User> users =  new ArrayList<>(Arrays.asList(user1, user2));
        return users;
    }

}
