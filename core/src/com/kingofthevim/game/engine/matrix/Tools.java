package com.kingofthevim.game.engine.matrix;

public class Tools {

    public static int lastSpaceLocation(String string){
        char[] charArray = string.toCharArray();

        for (int i = charArray.length - 1; i > 0; i--) {

            if(charArray[i] == ' ')
                return i;
        }

        return -1;
    }

    public static boolean isBrokenUpStringPair(String upper, String lower){
        char upperLstChar = upper.charAt(upper.length() - 1);
        char lowerFrsChar = lower.charAt(0);

        return charIsLetter(upperLstChar) && charIsLetter(lowerFrsChar);
    }


    public static boolean lastCharInStringIsLetter(String string, boolean checkCapitals){
        char strChar = string.charAt(string.length() - 1);

        if(strChar > 64 && strChar < 90){

            return true;
        }

        if(checkCapitals && strChar > 96 && strChar < 122){

            return true;
        }

        return false;
    }

    /**
     * Checks if char is a symbol and thus next to each
     * other constitutes a word. Is used together with
     * isSymbol() to separate between words and WORDS.
     * Space is not included
     * @param character char to check
     * @return true if char is a symbol
     */
    public static boolean isSymbol(char character ){
        return ((character >= '!' && character <= '/')
                || (character >= ':' && character <= '@')
                || (character >= '[' && character <= '_')
                || (character >= '{' && character <= '~'));
    }

    /**
     * Checks if char is a letter.
     * Space is not included
     * @param character char to check
     * @return true if char is a letter or number
     */
    public static boolean charIsLetter(char character){

        if(character > 64 && character < 90)
            return true;

        if(character > 96 && character < 122)
            return true;

        return false;
    }

    /**
     * Checks if char is a letter or number and thus
     * next to each other constitutes a word. Is used
     * together with isSymbol() to separate between
     * words and WORDS. Space is not included
     * @param character char to check
     * @return true if char is a letter or number
     */
    public static boolean isLetterOrNumber(char character ){
        return ((character >= '0' && character <= '9')
                || (character >= 'a' && character <= 'z')
                || (character >= 'A' && character <= 'Z'));
    }

    /**
     * Checks if char is a letter, number or symbol
     * @param character char to check
     * @return true if char is a letter, number or symbol
     */
    public static boolean isLetterNumberSymbol(char character){
        return isSymbol(character) || isLetterOrNumber(character);
    }

    /**
     * Checks if char is a operator
     * @param character char to check
     * @return true if char is a vim-operator
     */
    public static boolean isOperator(char character){
        return character == 'd';
    }

    /**
     * VIM-movement-rules based on chars
     *
     * the method aspires to contain all the word-movement
     * rules to be used in the word-movement-methods. it
     * checks between the current and privius char according
     * to rules divided by if-statements. If any rule is true,
     * it returns true.
     * @param currCellChar the char of the current cell
     * @param prevCellChar the char of the previus cell
     * @return true if a ruled if followed.
     */
    public static boolean wordMovementRules(char currCellChar, char prevCellChar){
        if(isLetterOrNumber(prevCellChar)
                && isLetterOrNumber(currCellChar)){
            return false;
        }
        if(isSymbol(prevCellChar)
                && isSymbol(currCellChar)){
            return false;
        }

        if( isLetterOrNumber(prevCellChar)
                && currCellChar == ' '){
            return true;
        }

        if( isSymbol(prevCellChar)
                && currCellChar == ' '){
            return true;
        }

        if( isSymbol(prevCellChar)
                && isLetterOrNumber(currCellChar)){
            return true;
        }

        if( isLetterOrNumber(prevCellChar)
                && isSymbol(currCellChar)){
            return true;
        }


        return false;
    }

    /**
     * tries to parse integer, if
     * fail, it returns zero
     * @param string that which is to be parsed
     * @return parsed integer or zero
     */
    public static int tryParseInt(String string){
        try{
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException e){
            System.out.println("tryParseInt failed to parse: \"" + string + "\"");
            return 0;
        }
    }

}
