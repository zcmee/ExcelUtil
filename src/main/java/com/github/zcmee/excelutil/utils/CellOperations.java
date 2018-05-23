package com.github.zcmee.excelutil.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

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
        if(value.isEmpty()) return null;
        return Integer.parseInt(value);
    }

    public Date getValueFromCellAsDate(Row row, int column, String dateFormat) {
        DateTimeFormatter defaultDateFormat = DateTimeFormat.forPattern(dateFormat);
        Cell cell = row.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        return getDateByCellType(cell, defaultDateFormat);
    }

    private Date getDateByCellType(Cell cell, DateTimeFormatter formatter){

        switch(cell.getCellTypeEnum()) {
            case NUMERIC:
                return cell.getDateCellValue();
            case STRING:
                String value = cell.getStringCellValue();
                return formatter.parseDateTime(value).toDate();
            default:
                return null;
        }
    }

}
