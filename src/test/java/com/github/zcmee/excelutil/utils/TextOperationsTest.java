package com.github.zcmee.excelutil.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;

@RunWith(value = Parameterized.class)
public class TextOperationsTest {
    private String value;
    private String exceptedValue;

    public TextOperationsTest(String value, String exceptedValue) {
        this.value = value;
        this.exceptedValue = exceptedValue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"", ""},
            {"Polska", "polska"},
            {"Lorem ipsum dolor sit amet, consectetur adipiscing elit", "loremipsumdolorsitamet,consecteturadipiscingelit"},
            {"Integer.MAX_VALUE", "integer.max_value"},
            {"edward\nmajer czy   k", "edwardmajerczyk"},
            {"     a    b       c", "abc"}
        });
    }

    @Test
    public void prepareToComparison() throws Exception {
        TextOperations instance = TextOperations.getInstance();
        Assert.assertThat(instance.prepareToComparison(value), is(exceptedValue));
    }

}