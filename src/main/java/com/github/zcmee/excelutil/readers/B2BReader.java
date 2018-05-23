package com.github.zcmee.excelutil.readers;

import com.github.zcmee.excelutil.api.ExcelReaderTemplate;
import com.github.zcmee.excelutil.dtoes.B2B;
import com.github.zcmee.excelutil.utils.CellOperations;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class B2BReader extends ExcelReaderTemplate<B2B> {

    public B2BReader(Sheet sheet) {
        super(sheet);
    }

    @Override
    protected List<B2B> getXlsToJavaTransformator() {
        List<B2B> agreements = new ArrayList<>(sheet.getPhysicalNumberOfRows());

        for(int i = 1;  i < sheet.getPhysicalNumberOfRows(); ++i){
            Row row = sheet.getRow(i);
            Integer id = CellOperations.getInstance().getValueFromCellAsInteger(row, 0);
            String name = CellOperations.getInstance().getValueFromCell(row, 1);
            String address = CellOperations.getInstance().getValueFromCell(row, 2);
            Date dateSigningContract = CellOperations.getInstance().getValueFromCellAsDate(row, 3, "dd-MM-yyyy");

            B2B b2b = new B2B(id, name, address, dateSigningContract);
            agreements.add(b2b);
        }

        return agreements;
    }
}