package com.alex.numbercar.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceString {

    private String oldChar ;
    private String newChar;

    public String replaceChar(String old) {
       /* Pattern pattern = Pattern.compile("[АВЕ]");
        Matcher matcher = pattern.matcher(old);*/
       /* if (old.length() > 0) {
            //newChar = old.replace("А", "D");
            if (old.equals("А"|) {
                newChar = old.replace("А", "D");
            }
        }*/
        char[] string = old.toCharArray();
        if (string[0] == 1040) {
            string[0] = 'R';
        }
        if (string[4] == 1042) {
            string[4] = 'D';
        }
        newChar = String.valueOf(string);

       //repl();

        return newChar;
    }

    private void repl() {

    }
}

