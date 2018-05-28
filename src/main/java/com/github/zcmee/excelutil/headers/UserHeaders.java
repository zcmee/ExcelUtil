package com.github.zcmee.excelutil.headers;

import com.github.zcmee.excelutil.api.ExcelHeadersStrategy;

public class UserHeaders implements ExcelHeadersStrategy {

    @Override
    public String[] getArrayHeaders() {
        return new String[] {
                "Imię",
                "Naziwsko",
                "Wiek",
                "Data podpisania kontraktu"
        };
    }
}
