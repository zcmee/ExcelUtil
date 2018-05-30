package com.github.zcmee.excelutil.api;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;

import static com.github.zcmee.excelutil.dictionaries.ExcelNotifications.INVALID_CREATE_EXCEL_FILE;

public abstract class ExcelWriterTemplate<T> {
    protected abstract void generateExcelFileFromList(List<T> elements, Integer rowStart, Sheet sheet);
    private ExcelHeadersStrategy headers;
    protected Workbook workbook; // Handles both XSSF and HSSF automatically
    private Sheet sheet;
    private List<T> elementsToSaveExcelFile;

    public ExcelWriterTemplate(List<T> elementsToSaveExcelFile) {
        this.elementsToSaveExcelFile = elementsToSaveExcelFile;
    }

    private void createExcelFileIfNeeded() throws IOException {
        try (HSSFWorkbook wb = new HSSFWorkbook()) { this.workbook = wb; }
    }

    public File generateExcelFile() throws IOException {
        createExcelFileIfNeeded();
        sheet = workbook.createSheet();
        if(headers != null) saveHeadersToWorkbook();
        Integer rowStartNumber = getStartFromRow();
        generateExcelFileFromList(elementsToSaveExcelFile, rowStartNumber, sheet);
        return geExcelFile();
    }

    public List<T> getElementsToSaveExcelFile() {
        return elementsToSaveExcelFile;
    }

    public void setElementsToSaveExcelFile(List<T> elementsToSaveExcelFile) {
        this.elementsToSaveExcelFile = elementsToSaveExcelFile;
    }

    public void setHeaders(ExcelHeadersStrategy headers) {
        this.headers = headers;
    }

    private Integer getStartFromRow() {
        if(headers == null) { return 0; }
        return 1;
    }

    private void saveHeadersToWorkbook() {
        sheet = workbook.getSheetAt(0);
        HSSFRow row = (HSSFRow) sheet.createRow(0);
        for(int i = 0; i < headers.getArrayHeaders().length; ++i) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(getCellType());
            cell.setCellValue(headers.getArrayHeaders()[i]);
        }
    }

    private CellStyle getCellType() {
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        return style;
    }

    private File geExcelFile() throws IOException {
        File file = File.createTempFile("workbook", "xlsx");
        try(FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
            workbook.close();
            return file;
        } catch (IOException ex) {
            workbook.close();
            throw new IllegalArgumentException(INVALID_CREATE_EXCEL_FILE);
        }
    }

}