package com.kingofthevim.game.basicvim;

public class Tools {

    static int lastSpaceLocation(String string){
        char[] charArray = string.toCharArray();

        for (int i = charArray.length - 1; i > 0; i--) {

            if(charArray[i] == ' ')
                return i;
        }

        return -1;
    }

    static boolean isBrokenUpStringPair(String upper, String lower){
        char upperLstChar = upper.charAt(upper.length() - 1);
        char lowerFrsChar = lower.charAt(0);

        return charIsLetter(upperLstChar) && charIsLetter(lowerFrsChar);
    }

    static boolean charIsLetter(char charToCheck){

        if(charToCheck > 64 && charToCheck < 90)
            return true;

        if(charToCheck > 96 && charToCheck < 122)
            return true;

        return false;
    }

    static boolean lastCharInStringIsLetter(String string, boolean checkCapitals){
        char strChar = string.charAt(string.length() - 1);

        if(strChar > 64 && strChar < 90){

            return true;
        }

        if(checkCapitals && strChar > 96 && strChar < 122){

            return true;
        }

        return false;
    }
}
