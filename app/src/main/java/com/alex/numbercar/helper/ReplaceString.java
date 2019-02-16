package com.alex.numbercar.helper;

public class ReplaceString {

    public String replaceChar(String old) {
        char[] string = old.toCharArray();

        for (int i = 0; i < 6; i++) {
            if (string[i] == 'А') {
                string[i] = 'а';
            }
            if (string[i] == 'В') {
                string[i] = 'в';
            }
            if (string[i] == 'Е') {
                string[i] = 'е';
            }
            if (string[i] == 'К') {
                string[i] = 'к';
            }
            if (string[i] == 'М') {
                string[i] = 'м';
            }
            if (string[i] == 'Н') {
                string[i] = 'н';
            }
            if (string[i] == 'О') {
                string[i] = 'о';
            }
            if (string[i] == 'Р') {
                string[i] = 'р';
            }
            if (string[i] == 'С') {
                string[i] = 'с';
            }
            if (string[i] == 'Т') {
                string[i] = 'т';
            }
            if (string[i] == 'У') {
                string[i] = 'у';
            }
            if (string[i] == 'Х') {
                string[i] = 'х';
            }
        }

        String newChar = String.valueOf(string);
        return newChar;
    }

}

