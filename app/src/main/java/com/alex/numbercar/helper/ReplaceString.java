package com.alex.numbercar.helper;

public class ReplaceString {

    private String newChar;

    public String replaceChar(String old) {
        char[] string = old.toCharArray();

        for (int i = 0; i < 6; i++) {
            if (string[i] == 'А') {
                string[i] = 'A';
            }
            if (string[i] == 'В') {
                string[i] = 'B';
            }
            if (string[i] == 'Е') {
                string[i] = 'E';
            }
            if (string[i] == 'К') {
                string[i] = 'K';
            }
            if (string[i] == 'М') {
                string[i] = 'M';
            }
            if (string[i] == 'Н') {
                string[i] = 'H';
            }
            if (string[i] == 'О') {
                string[i] = 'O';
            }
            if (string[i] == 'Р') {
                string[i] = 'P';
            }
            if (string[i] == 'С') {
                string[i] = 'C';
            }
            if (string[i] == 'Т') {
                string[i] = 'T';
            }
            if (string[i] == 'У') {
                string[i] = 'Y';
            }
            if (string[i] == 'Х') {
                string[i] = 'K';
            }
        }

        newChar = String.valueOf(string);
        return newChar;
    }

}

