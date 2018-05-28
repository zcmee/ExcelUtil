package com.github.zcmee.excelutil.generic.reader;

import com.github.zcmee.excelutil.dtoes.User;
import com.github.zcmee.excelutil.readers.UserReader;
import com.github.zcmee.excelutil.utils.DateOperations;
import com.github.zcmee.excelutil.utils.ExcelOperations;
import com.github.zcmee.excelutil.utils.FileOperations;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserXlsTest {
    private final static DateOperations dateOperations = DateOperations.getInstance();

    @Test
    public void testUserFileWithCorrectData() {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/users.xls");

        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);
        UserReader reader = new UserReader(sheet);
        List<User> users =  reader.generateComplaint();
        List<User> exceptedUsers = getExceptedListUserForExcelFile();

        User tmpUser = new User("Piotr", "Kowalski", 33, dateOperations.getDateFromString("yyyy-MM-dd", "2018-09-10"));

        assertThat(users, hasItems(tmpUser));
        assertEquals(users.size(), exceptedUsers.size());
        assertThat(users, is(exceptedUsers));
    }

    @Test
    public void testUserFileWithWrongData() {
        File file = FileOperations.getInstance().getFileFromResource("testfiles/users.xls");

        Sheet sheet = ExcelOperations.getInstance().getSheet(file, 0);
        UserReader reader = new UserReader(sheet);
        List<User> users =  reader.generateComplaint();
        List<User> exceptedUsers = getWrongListUserForExcelFile();

        assertThat(users, not(equalTo(exceptedUsers)));
    }

    private List<User> getExceptedListUserForExcelFile() {
        User user1 = new User("Piotr", "Kowalski", 33, dateOperations.getDateFromString("yyyy-MM-dd", "2018-09-10"));
        User user2 = new User("Tomasz", "Terka", 29, dateOperations.getDateFromString("yyyy-MM-dd", "2018-09-11"));

        List<User> exceptedUsers = new ArrayList<>(Arrays.asList(user1, user2));
        return exceptedUsers;
    }

    private List<User> getWrongListUserForExcelFile() {
        User user1 = new User("Piotr", "Kowalski", 33, dateOperations.getDateFromString("yyyy-MM-dd", "2018-09-10"));
        User user2 = new User("Tomasz", "Terka", 45, dateOperations.getDateFromString("yyyy-MM-dd", "2018-09-11"));

        List<User> exceptedUsers = new ArrayList<>(Arrays.asList(user1, user2));
        return exceptedUsers;
    }

}
