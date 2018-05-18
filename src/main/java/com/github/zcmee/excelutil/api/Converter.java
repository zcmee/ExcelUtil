package com.github.zcmee.excelutil.api;

public interface Converter<T,R> {
    R convert(T element);
}
