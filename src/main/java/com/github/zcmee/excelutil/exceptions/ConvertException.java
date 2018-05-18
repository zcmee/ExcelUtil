package com.github.zcmee.excelutil.exceptions;

public class ConvertException extends RuntimeException {

    public ConvertException() {
        super("Konwersja pliku się nie powiodła");
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(Exception ex) {
        super(ex);
    }


}
