package com.anisjamadar26.mytictactoe.domain;

/**
 * This class used for making string from numbers and checking string is empty or not
 */
public class StringUtility {

    public static String stringFromNumbers(int... numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number: numbers) {
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
