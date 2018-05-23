package com.github.zcmee.excelutil.readers;

import com.github.zcmee.excelutil.api.ExcelReaderTemplate;
import com.github.zcmee.excelutil.dtoes.User;
import com.github.zcmee.excelutil.utils.CellOperations;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class UserReader extends ExcelReaderTemplate<User> {

    public UserReader(Sheet sheet) {
        super(sheet);
    }

    @Override
    protected List<User> getXlsToJavaTransformator() {
        List<User> users = new ArrayList<>(sheet.getPhysicalNumberOfRows());

        for(Row row : sheet){
            String firstName = CellOperations.getInstance().getValueFromCell(row, 6);
            String lastName = CellOperations.getInstance().getValueFromCell(row, 7);
            Integer age = CellOperations.getInstance().getValueFromCellAsInteger(row, 8);

            User user = new User(firstName, lastName, age);
            users.add(user);
        }

        return users;
    }

}
