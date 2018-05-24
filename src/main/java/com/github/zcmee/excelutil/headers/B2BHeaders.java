package com.github.zcmee.excelutil.headers;

import com.github.zcmee.excelutil.api.ExcelHeadersStrategy;

public class B2BHeaders implements ExcelHeadersStrategy {

    @Override
    public String[] getArrayHeaders() {

        return new String[] {
            "idx",
            "imie",
            "lokalizacja",
            "datapodpisaniakontraktu"
        };

    }

}
