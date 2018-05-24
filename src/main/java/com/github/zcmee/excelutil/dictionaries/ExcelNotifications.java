package com.github.zcmee.excelutil.dictionaries;

public class ExcelNotifications {

    private static ExcelNotifications instance = new ExcelNotifications();

    public static ExcelNotifications getInstance() {
        return instance;
    }

    private ExcelNotifications() {

    }

    public static final String FILE_NOT_FOUND = "Podany plik excel nie istnieję";
    public static final String EMPTY_FILE = "Przesłany plik excel jest pusty";
    public static final String INVALID_FILE = "Przesłany plik jest nie poprawny";
    public static final String INVALID_VALIDATION_HEADERS = "Niepoprawna walidacja nagłówków w przesłanym pliku excel";

    public static final String INVALID_CREATE_EXCEL_FILE = "Nieudało się utworzyć pliku Excel";
}
