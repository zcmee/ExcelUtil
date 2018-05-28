package com.github.zcmee.excelutil.readers;

import com.github.zcmee.excelutil.api.ExcelReaderTemplate;
import com.github.zcmee.excelutil.dtoes.User;
import com.github.zcmee.excelutil.utils.CellOperations;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserReader extends ExcelReaderTemplate<User> {

    public UserReader(Sheet sheet) {
        super(sheet);
    }

    @Override
    protected List<User> getXlsToJavaTransformator() {
        List<User> users = new ArrayList<>(sheet.getPhysicalNumberOfRows());
        CellOperations cellOperations = CellOperations.getInstance();

        for(Row row : sheet){
            String firstName = cellOperations.getValueFromCell(row, 6);
            String lastName = cellOperations.getValueFromCell(row, 7);
            Integer age = cellOperations.getValueFromCellAsInteger(row, 8);
            Date date = cellOperations.getValueFromCellAsDate(row, 9, "yyyy-MM-dd");
            User user = new User(firstName, lastName, age, date);
            users.add(user);
        }

        return users;
    }

}
