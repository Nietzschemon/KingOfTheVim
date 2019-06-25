//Stefan Ekblom
//anno 2019-06-22
//github RawNietzsche

package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;


//TODO LATER use properties to store the file paths.
// https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
public class LetterManager extends VimWorldMatrix{

    // We only need one of these
    private static Map<Character, Texture> fontCollection;

    // For larger movements and other things
    private static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};



    public LetterManager(){
        super(rowTotal, colunmTotal);

        fontCollection = new HashMap<>();

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

            cellMatrix[row][startCell + i].setCellLook(fontCollection.get(charKey), charKey);
            charNum++;
        }
    }

    public void setStringArray(String[] stringArray, int startRow, boolean overwriteExisting){

        for (int i = 0; i < stringArray.length; i++) {
            setString(stringArray[i], startRow + i, 0, overwriteExisting);
        }

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
    private static void getTextures(){

        fontCollection.put('?', new Texture("fontTest/questionmark.png"));
        fontCollection.put(':', new Texture("fontTest/colon.png"));
        fontCollection.put('.', new Texture("fontTest/dot.png"));
        fontCollection.put('/', new Texture("fontTest/forwardslash.png"));
        fontCollection.put('*', new Texture("fontTest/star.png"));
        fontCollection.put('@', new Texture("fontTest/atsign.png"));
        fontCollection.put(',', new Texture("fontTest/comma.png"));
        fontCollection.put('!', new Texture("fontTest/exclamation.png"));
        fontCollection.put(';', new Texture("fontTest/;.png"));
        fontCollection.put('|', new Texture("fontTest/|.png"));
        fontCollection.put('>', new Texture("fontTest/>.png"));
        fontCollection.put('<', new Texture("fontTest/<.png"));
        fontCollection.put('}', new Texture("fontTest/}.png"));
        fontCollection.put('{', new Texture("fontTest/{.png"));
        fontCollection.put(']', new Texture("fontTest/].png"));
        fontCollection.put('[', new Texture("fontTest/[.png"));
        fontCollection.put(')', new Texture("fontTest/).png"));
        fontCollection.put('(', new Texture("fontTest/(.png"));
        fontCollection.put('^', new Texture("fontTest/^.png"));
        fontCollection.put('~', new Texture("fontTest/~.png"));
        fontCollection.put('=', new Texture("fontTest/=.png"));
        fontCollection.put('-', new Texture("fontTest/-.png"));
        fontCollection.put('+', new Texture("fontTest/+.png"));
        fontCollection.put('&', new Texture("fontTest/&.png"));
        fontCollection.put('%', new Texture("fontTest/%.png"));
        fontCollection.put('$', new Texture("fontTest/$.png"));
        fontCollection.put('#', new Texture("fontTest/#.png"));
        fontCollection.put('z', new Texture("fontTest/z.png"));
        fontCollection.put('y', new Texture("fontTest/y.png"));
        fontCollection.put('x', new Texture("fontTest/x.png"));
        fontCollection.put('w', new Texture("fontTest/w.png"));
        fontCollection.put('v', new Texture("fontTest/v.png"));
        fontCollection.put('u', new Texture("fontTest/u.png"));
        fontCollection.put('t', new Texture("fontTest/t.png"));
        fontCollection.put('s', new Texture("fontTest/s.png"));
        fontCollection.put('r', new Texture("fontTest/r.png"));
        fontCollection.put('q', new Texture("fontTest/q.png"));
        fontCollection.put('p', new Texture("fontTest/p.png"));
        fontCollection.put('o', new Texture("fontTest/o.png"));
        fontCollection.put('n', new Texture("fontTest/n.png"));
        fontCollection.put('m', new Texture("fontTest/m.png"));
        fontCollection.put('l', new Texture("fontTest/l.png"));
        fontCollection.put('k', new Texture("fontTest/k.png"));
        fontCollection.put('j', new Texture("fontTest/j.png"));
        fontCollection.put('i', new Texture("fontTest/i.png"));
        fontCollection.put('h', new Texture("fontTest/h.png"));
        fontCollection.put('g', new Texture("fontTest/g.png"));
        fontCollection.put('f', new Texture("fontTest/f.png"));
        fontCollection.put('e', new Texture("fontTest/e.png"));
        fontCollection.put('d', new Texture("fontTest/d.png"));
        fontCollection.put('c', new Texture("fontTest/c.png"));
        fontCollection.put('b', new Texture("fontTest/b.png"));
        fontCollection.put('a', new Texture("fontTest/a.png"));
        fontCollection.put('Z', new Texture("fontTest/Z.png"));
        fontCollection.put('Y', new Texture("fontTest/Y.png"));
        fontCollection.put('X', new Texture("fontTest/X.png"));
        fontCollection.put('W', new Texture("fontTest/W.png"));
        fontCollection.put('V', new Texture("fontTest/V.png"));
        fontCollection.put('U', new Texture("fontTest/U.png"));
        fontCollection.put('T', new Texture("fontTest/T.png"));
        fontCollection.put('S', new Texture("fontTest/S.png"));
        fontCollection.put('R', new Texture("fontTest/R.png"));
        fontCollection.put('Q', new Texture("fontTest/Q.png"));
        fontCollection.put('P', new Texture("fontTest/P.png"));
        fontCollection.put('O', new Texture("fontTest/O.png"));
        fontCollection.put('N', new Texture("fontTest/N.png"));
        fontCollection.put('M', new Texture("fontTest/M.png"));
        fontCollection.put('L', new Texture("fontTest/L.png"));
        fontCollection.put('K', new Texture("fontTest/K.png"));
        fontCollection.put('J', new Texture("fontTest/J.png"));
        fontCollection.put('I', new Texture("fontTest/I.png"));
        fontCollection.put('H', new Texture("fontTest/H.png"));
        fontCollection.put('G', new Texture("fontTest/G.png"));
        fontCollection.put('F', new Texture("fontTest/F.png"));
        fontCollection.put('E', new Texture("fontTest/E.png"));
        fontCollection.put('D', new Texture("fontTest/D.png"));
        fontCollection.put('C', new Texture("fontTest/C.png"));
        fontCollection.put('B', new Texture("fontTest/B.png"));
        fontCollection.put('A', new Texture("fontTest/A.png"));
        fontCollection.put('0', new Texture("fontTest/0.png"));
        fontCollection.put('9', new Texture("fontTest/9.png"));
        fontCollection.put('8', new Texture("fontTest/8.png"));
        fontCollection.put('7', new Texture("fontTest/7.png"));
        fontCollection.put('6', new Texture("fontTest/6.png"));
        fontCollection.put('5', new Texture("fontTest/5.png"));
        fontCollection.put('4', new Texture("fontTest/4.png"));
        fontCollection.put('3', new Texture("fontTest/3.png"));
        fontCollection.put('2', new Texture("fontTest/2.png"));
        fontCollection.put('1', new Texture("fontTest/1.png"));
    }
}
