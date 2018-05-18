package com.github.zcmee.excelutil.api;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface XlsToJavaTransformator<T>  {
    List<T> generateObjectFromXls(Sheet sheet, Integer numberRows, Integer readFromRow);
}
