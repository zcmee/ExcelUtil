package com.github.zcmee.excelutil.api;

import com.github.zcmee.excelutil.utils.CellOperations;
import com.github.zcmee.excelutil.utils.TextOperations;
import com.github.zcmee.excelutil.dictionaries.ExcelNotifications;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public abstract class ExcelReaderTemplate<T> {
    protected abstract List<T> getXlsToJavaTransformator();
    protected Sheet sheet;
    private ExcelHeadersStrategy excelHeaders;

    public ExcelReaderTemplate(Sheet sheet){
        this.sheet = sheet;
    }

    public List<T> generateComplaint() {
        validXlsFile();
        return getXlsToJavaTransformator();
    }

    public int getActualNumberRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    protected void validateHeadersXls() {
        Row row = sheet.getRow(0);
        if(row == null) throw new IllegalArgumentException(ExcelNotifications.INVALID_FILE);
        for (int i = 0; i < excelHeaders.getArrayHeaders().length; ++i) {
            String cellVaule = CellOperations.getInstance().getValueFromCell(row, i).trim();
            String cellForComparison = TextOperations.getInstance().prepareToComparison(cellVaule);
            String header = excelHeaders.getArrayHeaders()[i];
            if (!header.equalsIgnoreCase(cellForComparison)) {
                throw new IllegalArgumentException(ExcelNotifications.INVALID_VALIDATION_HEADERS);
            }
        }
    }

    public ExcelHeadersStrategy getHeaderToValidation() {
        return excelHeaders;
    }

    public void setHeaderToValidation(ExcelHeadersStrategy excelHeaders) {
        this.excelHeaders = excelHeaders;
    }

    //@TODO clean code???
    private boolean isValidHeaders() {
        if(excelHeaders == null) {
            return false; }
        else if(excelHeaders.getArrayHeaders().length < 1)
            return false;

        return true;
    }

    private void validXlsFile() {
        if(sheet == null) throw new IllegalArgumentException(ExcelNotifications.FILE_NOT_FOUND);
        if(isValidHeaders()) validateHeadersXls();
        if(getActualNumberRows() < 1) throw new IllegalArgumentException(ExcelNotifications.EMPTY_FILE);
    }

}

