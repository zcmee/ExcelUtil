package com.github.zcmee.excelutil.utils;

public class TextOperations {
    private static TextOperations instance = new TextOperations();

    public static TextOperations getInstance() {
        return instance;
    }

    private TextOperations() {
    }

    public String prepareToComparison(String text) {
        String preparedText = text.replaceAll("[\n\t ]","").toLowerCase();
        return replacePolishCharacters(preparedText);
    }

    public String replacePolishCharacters(String text) {
        String replacedText = text;
        replacedText = replacedText.replace("Ż", "Z");
        replacedText = replacedText.replace("Ć", "C");
        replacedText = replacedText.replace("Ź", "Z");
        replacedText = replacedText.replace("Ó", "O");
        replacedText = replacedText.replace("Ł", "L");
        replacedText = replacedText.replace("Ń", "N");
        replacedText = replacedText.replace("Ą", "A");
        replacedText = replacedText.replace("Ę", "E");
        replacedText = replacedText.replace("Ś", "S");
        replacedText = replacedText.replace("ż", "z");
        replacedText = replacedText.replace("ć", "c");
        replacedText = replacedText.replace("ź", "z");
        replacedText = replacedText.replace("ó", "o");
        replacedText = replacedText.replace("ł", "l");
        replacedText = replacedText.replace("ń", "n");
        replacedText = replacedText.replace("ą", "a");
        replacedText = replacedText.replace("ę", "e");
        replacedText = replacedText.replace("ś", "s");

        return replacedText;
    }

}
