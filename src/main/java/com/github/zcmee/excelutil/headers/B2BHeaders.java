package com.github.zcmee.excelutil.headers;

import com.github.zcmee.excelutil.api.HeaderToValidationStrategy;

public class B2BHeaders implements HeaderToValidationStrategy {

    @Override
    public String[] getArrayHeadersToValidation() {

        return new String[] {
            "idx",
            "imie",
            "lokalizacja",
            "datapodpisaniakontraktu"
        };

    }

}
