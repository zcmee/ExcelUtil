package com.github.zcmee.excelutil.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class CellOperations {
    public static CellOperations getInstance() {
        return instance;
    }

    private static CellOperations instance = new CellOperations();
    private static final DataFormatter formatter = new DataFormatter();
    private CellOperations() {

    }

    public String getValueFromCell(Row row, int column) {
        Cell cell = row.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        return formatter.formatCellValue(cell).trim();
    }

    public Integer getValueFromCellAsInteger(Row row, int column) {
        String value = getValueFromCell(row, column);
        return Integer.parseInt(value);
    }

}
