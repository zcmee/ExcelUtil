package com.github.zcmee.excelutil.generic.reader;

import com.github.zcmee.excelutil.api.ExcelHeadersStrategy;

public class WrongB2BHeaders implements ExcelHeadersStrategy {

    @Override
    public String[] getArrayHeaders() {
        return new String[] {
                "idx",
                "imie",
                "lokalizacj77a",
                "Datapodpisaniakontraktu"
        };
    }
}
