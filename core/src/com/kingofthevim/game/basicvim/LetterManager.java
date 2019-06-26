//Stefan Ekblom
//anno 2019-06-22
//github RawNietzsche

package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;

import java.util.*;


//TODO LATER use properties to store the file paths.
// https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
public class LetterManager extends VimWorldMatrix{

    // We only need one of these
    private static Map<Character, Texture> whiteFont;

    // For larger movements and other things
    private static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};



    public LetterManager(){
        super(rowTotal, colunmTotal);

        whiteFont = new HashMap<>();

        getTextures();
    }

    //private void setWordProperties(int row, int rowCell, boolean isBad, boolean isGood)
    //public void setPropertiesAllChars(char[] propertiesChar, PropObject prop)

    /**
     * Writes a string at the given location.
     * @param string The string to be written
     * @param row At what row the string should be written
     * @param startCell At what cell in the row the string should start from
     * @param overwriteExisting If existing chars should be jumped or written over
     */
    public void setString(String string, int row, int startCell, boolean overwriteExisting){

        if(string.length() + startCell > cellMatrix[row].length){
            throw new IndexOutOfBoundsException("word will be outside cell matrix");
        }

        int iterations = string.length()-1;
        int charNum = 0;


        for(int i = 0; i <= iterations; i++) {
            char charKey = string.charAt(charNum);

            System.out.println("char: " + charKey +
                    " in cellMatrix[" + row + "][" + (startCell + i) + "]");

            //overwrite existing cell

            if(cellMatrix[row][startCell + i].getCellLook() != null
            && ! overwriteExisting){
                System.out.println("There is already a char there!");
                iterations++;
                continue;
            }

            cellMatrix[row][startCell + i].setCellLook(whiteFont.get(charKey), charKey);

            charNum++;
        }
    }

    public void setStringArray(String[] stringArray, int startRow, boolean overwriteExisting){

        for (int i = 0; i < stringArray.length; i++) {
            if(stringArray[i] != null)
                setString(stringArray[i], startRow + i, 0, overwriteExisting);
        }
    }

    /*TODO Level maker
     make methods
        - addBackgroundText()

        - textLevelMaker(String string, boolean fatWordsLevel, boolean )
                string - everything not covered bellow gets grayed
                fatWordsLevel - the level words: chars gets nomralized
                starsBad - letters between stars gets marked as bad (*word*)
                starsGood - letters between two ~ gets marked as good (~~wo~~rd)
        - setPoints(LetterType type, int points)
        - setPoints(LetterType type, int row, int points)
        - setPoints(LetterType type, String chars, int points)
        - setPoints(LetterType type, String chars, int row, int points)
        EVERY EVENT has an analogous plethora of signatures as in setPoints above.
     */

    /**
     * Makes a string array that fits the current
     * cellmatrix
     * @param string string to turn to an array
     * @return a fitted string array
     */
    public String[] makeStringArray(String string){

        String[] returnArray = new String[rowTotal];

        int startIndex = 0;
        int endIndex = cellMatrix[0].length-1;

        for (int i = 0; i < cellMatrix.length; i++) {


            System.out.println("startIndex: " + startIndex);
            System.out.println("endIndex: " + endIndex);

            if(startIndex + endIndex > string.length()){
                returnArray[i] = string.substring(startIndex);
                break;
            }

            returnArray[i] = string.substring(startIndex, startIndex + endIndex);

            startIndex = startIndex + endIndex;
            endIndex = cellMatrix[i].length-1;
        }

        return returnArray;
    }

    //TODO make public when made ready
    private String[] makeStringArray(String string, int stopAtColumn){

        return null;
    }
    private String[] makeStringArray(String string, int stopAtColumn, int startAtColumn){//startAt puts in spaces

        return null;
    }

    public void setGoodLetters(String string){

        for (int i = 0; i < cellMatrix.length ; i++) {

            for (int j = 0; j < cellMatrix[i].length ; j++) {

                for (int k = 0; k < string.length() ; k++) {

                    if(cellMatrix[i][j].getCellChar() == string.charAt(k) ){

                        cellMatrix[i][j].setGood(true);
                    }
                }
            }
        }
    }
    public void setBadLetters(String string){

        for (int i = 0; i < cellMatrix.length ; i++) {

            for (int j = 0; j < cellMatrix[i].length ; j++) {

                for (int k = 0; k < string.length() ; k++) {

                    if(cellMatrix[i][j].getCellChar() == string.charAt(k) ){

                        cellMatrix[i][j].setBad(true);
                    }
                }
            }
        }
}



    //TODO find a better way to add these than this
    //TODO skapa script som fixar de relativa sökvägarna.
    private static void getWhiteTextures(){

        whiteFont.put('?', new Texture("fonts/size_H44_W22/questionmark_44white.png"));
        whiteFont.put(':', new Texture("fonts/size_H44_W22/colon_44white.png"));
        whiteFont.put('.', new Texture("fonts/size_H44_W22/dot_44white.png"));
        whiteFont.put('/', new Texture("fonts/size_H44_W22/forwardslash_44white.png"));
        whiteFont.put('*', new Texture("fonts/size_H44_W22/star_44white.png"));
        whiteFont.put('@', new Texture("fonts/size_H44_W22/atsign_44white.png"));
        whiteFont.put(',', new Texture("fonts/size_H44_W22/comma_44white.png"));
        whiteFont.put('!', new Texture("fonts/size_H44_W22/exclamation_44white.png"));
        whiteFont.put(';', new Texture("fonts/size_H44_W22/;_44white.png"));
        whiteFont.put('|', new Texture("fonts/size_H44_W22/|_44white.png"));
        whiteFont.put('>', new Texture("fonts/size_H44_W22/>_44white.png"));
        whiteFont.put('<', new Texture("fonts/size_H44_W22/<_44white.png"));
        whiteFont.put('}', new Texture("fonts/size_H44_W22/}_44white.png"));
        whiteFont.put('{', new Texture("fonts/size_H44_W22/{_44white.png"));
        whiteFont.put(']', new Texture("fonts/size_H44_W22/]_44white.png"));
        whiteFont.put('[', new Texture("fonts/size_H44_W22/[_44white.png"));
        whiteFont.put(')', new Texture("fonts/size_H44_W22/)_44white.png"));
        whiteFont.put('(', new Texture("fonts/size_H44_W22/(_44white.png"));
        whiteFont.put('^', new Texture("fonts/size_H44_W22/^_44white.png"));
        whiteFont.put('~', new Texture("fonts/size_H44_W22/~_44white.png"));
        whiteFont.put('=', new Texture("fonts/size_H44_W22/=_44white.png"));
        whiteFont.put('-', new Texture("fonts/size_H44_W22/-_44white.png"));
        whiteFont.put('+', new Texture("fonts/size_H44_W22/+_44white.png"));
        whiteFont.put('&', new Texture("fonts/size_H44_W22/&_44white.png"));
        whiteFont.put('%', new Texture("fonts/size_H44_W22/%_44white.png"));
        whiteFont.put('$', new Texture("fonts/size_H44_W22/$_44white.png"));
        whiteFont.put('#', new Texture("fonts/size_H44_W22/#_44white.png"));
        whiteFont.put('z', new Texture("fonts/size_H44_W22/z_44white.png"));
        whiteFont.put('y', new Texture("fonts/size_H44_W22/y_44white.png"));
        whiteFont.put('x', new Texture("fonts/size_H44_W22/x_44white.png"));
        whiteFont.put('w', new Texture("fonts/size_H44_W22/w_44white.png"));
        whiteFont.put('v', new Texture("fonts/size_H44_W22/v_44white.png"));
        whiteFont.put('u', new Texture("fonts/size_H44_W22/u_44white.png"));
        whiteFont.put('t', new Texture("fonts/size_H44_W22/t_44white.png"));
        whiteFont.put('s', new Texture("fonts/size_H44_W22/s_44white.png"));
        whiteFont.put('r', new Texture("fonts/size_H44_W22/r_44white.png"));
        whiteFont.put('q', new Texture("fonts/size_H44_W22/q_44white.png"));
        whiteFont.put('p', new Texture("fonts/size_H44_W22/p_44white.png"));
        whiteFont.put('o', new Texture("fonts/size_H44_W22/o_44white.png"));
        whiteFont.put('n', new Texture("fonts/size_H44_W22/n_44white.png"));
        whiteFont.put('m', new Texture("fonts/size_H44_W22/m_44white.png"));
        whiteFont.put('l', new Texture("fonts/size_H44_W22/l_44white.png"));
        whiteFont.put('k', new Texture("fonts/size_H44_W22/k_44white.png"));
        whiteFont.put('j', new Texture("fonts/size_H44_W22/j_44white.png"));
        whiteFont.put('i', new Texture("fonts/size_H44_W22/i_44white.png"));
        whiteFont.put('h', new Texture("fonts/size_H44_W22/h_44white.png"));
        whiteFont.put('g', new Texture("fonts/size_H44_W22/g_44white.png"));
        whiteFont.put('f', new Texture("fonts/size_H44_W22/f_44white.png"));
        whiteFont.put('e', new Texture("fonts/size_H44_W22/e_44white.png"));
        whiteFont.put('d', new Texture("fonts/size_H44_W22/d_44white.png"));
        whiteFont.put('c', new Texture("fonts/size_H44_W22/c_44white.png"));
        whiteFont.put('b', new Texture("fonts/size_H44_W22/b_44white.png"));
        whiteFont.put('a', new Texture("fonts/size_H44_W22/a_44white.png"));
        whiteFont.put('Z', new Texture("fonts/size_H44_W22/Z_44white.png"));
        whiteFont.put('Y', new Texture("fonts/size_H44_W22/Y_44white.png"));
        whiteFont.put('X', new Texture("fonts/size_H44_W22/X_44white.png"));
        whiteFont.put('W', new Texture("fonts/size_H44_W22/W_44white.png"));
        whiteFont.put('V', new Texture("fonts/size_H44_W22/V_44white.png"));
        whiteFont.put('U', new Texture("fonts/size_H44_W22/U_44white.png"));
        whiteFont.put('T', new Texture("fonts/size_H44_W22/T_44white.png"));
        whiteFont.put('S', new Texture("fonts/size_H44_W22/S_44white.png"));
        whiteFont.put('R', new Texture("fonts/size_H44_W22/R_44white.png"));
        whiteFont.put('Q', new Texture("fonts/size_H44_W22/Q_44white.png"));
        whiteFont.put('P', new Texture("fonts/size_H44_W22/P_44white.png"));
        whiteFont.put('O', new Texture("fonts/size_H44_W22/O_44white.png"));
        whiteFont.put('N', new Texture("fonts/size_H44_W22/N_44white.png"));
        whiteFont.put('M', new Texture("fonts/size_H44_W22/M_44white.png"));
        whiteFont.put('L', new Texture("fonts/size_H44_W22/L_44white.png"));
        whiteFont.put('K', new Texture("fonts/size_H44_W22/K_44white.png"));
        whiteFont.put('J', new Texture("fonts/size_H44_W22/J_44white.png"));
        whiteFont.put('I', new Texture("fonts/size_H44_W22/I_44white.png"));
        whiteFont.put('H', new Texture("fonts/size_H44_W22/H_44white.png"));
        whiteFont.put('G', new Texture("fonts/size_H44_W22/G_44white.png"));
        whiteFont.put('F', new Texture("fonts/size_H44_W22/F_44white.png"));
        whiteFont.put('E', new Texture("fonts/size_H44_W22/E_44white.png"));
        whiteFont.put('D', new Texture("fonts/size_H44_W22/D_44white.png"));
        whiteFont.put('C', new Texture("fonts/size_H44_W22/C_44white.png"));
        whiteFont.put('B', new Texture("fonts/size_H44_W22/B_44white.png"));
        whiteFont.put('A', new Texture("fonts/size_H44_W22/A_44white.png"));
        whiteFont.put('0', new Texture("fonts/size_H44_W22/0_44white.png"));
        whiteFont.put('9', new Texture("fonts/size_H44_W22/9_44white.png"));
        whiteFont.put('8', new Texture("fonts/size_H44_W22/8_44white.png"));
        whiteFont.put('7', new Texture("fonts/size_H44_W22/7_44white.png"));
        whiteFont.put('6', new Texture("fonts/size_H44_W22/6_44white.png"));
        whiteFont.put('5', new Texture("fonts/size_H44_W22/5_44white.png"));
        whiteFont.put('4', new Texture("fonts/size_H44_W22/4_44white.png"));
        whiteFont.put('3', new Texture("fonts/size_H44_W22/3_44white.png"));
        whiteFont.put('2', new Texture("fonts/size_H44_W22/2_44white.png"));
        whiteFont.put('1', new Texture("fonts/size_H44_W22/1_44white.png"));
    }

    private static void getTextures(){
        getWhiteTextures();
    }
}
