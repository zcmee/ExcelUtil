package com.github.zcmee.excelutil.utils;

import java.io.File;

public class FileOperations {
    private static final FileOperations instance = new FileOperations();

    public static FileOperations getInstance() { return instance; }

    private FileOperations() {

    }

    public File getFileFromResource(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }
}
