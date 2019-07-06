package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for sending, keeping and manipulating data on a
 * certain point on the grid. VIM-world objects always
 * interact with the world through these.
 */
public class Cell {

    //TODO move to its own class
    private static Map<Character, Texture> whiteFont = new HashMap<>();
    private static Map<Character, Texture> blackFont = new HashMap<>();
    private static Map<Character, Texture> redFont = new HashMap<>();
    private static Map<Character, Texture> yellowFont = new HashMap<>();
    private static Map<Character, Texture> grayFont = new HashMap<>();

    private static Font font = new Font();

    private char cellChar = ' ';
    private Texture cellLook;
    private Vector2 cartesianPosition;
    private LetterType letterType = LetterType.EMPATHY;


    Cell( float x, float y){

        cartesianPosition = new Vector2();
        cartesianPosition.x = x;
        cartesianPosition.y = y;
    }

    public Texture getCellLook(){
        return cellLook;
    }

    public void setCellLook(char cellChar, LetterType type) {


        switch (type) {

            case WHITE:
                this.cellLook = whiteFont.get(cellChar);
                break;

            case BLACK:
                this.cellLook = blackFont.get(cellChar);
                break;

            case GRAY:
                this.cellLook = grayFont.get(cellChar);
                break;

            case YELLOW:
                this.cellLook = yellowFont.get(cellChar);
                break;

            case RED:
                this.cellLook = redFont.get(cellChar);
                break;

        }

        this.cellChar = cellChar;
        this.letterType = type;
    }


    public char getCellChar() {
        return cellChar;
    }


    public Vector2 getCartesianPosition() {
        return cartesianPosition;
    }

    public void setCartesianPosition(float x, float y) {
        this.cartesianPosition.x = x;
        this.cartesianPosition.y = y;
    }


    public void dispose(){
        cellLook.dispose();
    }

    public Enum<LetterType> getLetterType() {
        return letterType;
    }

    //TODO make enum automaticly switch font color
    public void setLetterType(char letter, LetterType letterType, boolean includeGrayFont) {

        if(this.letterType != LetterType.GRAY
        || includeGrayFont){
            this.setCellLook(letter, letterType);
        }
    }

    public void setLetterType(LetterType letterType, Texture cellLook) {
        this.letterType = letterType;
        this.cellLook = cellLook;
    }

    public static class Font{

        Font(){
            getTextures();
        }
        //TODO find a better way to add these than this
        // se if wrapper class or a other hashmap can solve this
        private static void getTextures(){
            System.out.println("Loading all fonts");

            getWhiteTextures();
            getBlackTextures();
            getRedTextures();
            getGrayTextures();
            getYellowTextures();
        }

        private static void getBlackTextures(){

            blackFont.put('?', new Texture("fonts/size_H44_W22/questionmark_44black.png"));
            blackFont.put(':', new Texture("fonts/size_H44_W22/colon_44black.png"));
            blackFont.put('.', new Texture("fonts/size_H44_W22/dot_44black.png"));
            blackFont.put('/', new Texture("fonts/size_H44_W22/forwardslash_44black.png"));
            blackFont.put('*', new Texture("fonts/size_H44_W22/star_44black.png"));
            blackFont.put('@', new Texture("fonts/size_H44_W22/atsign_44black.png"));
            blackFont.put(',', new Texture("fonts/size_H44_W22/comma_44black.png"));
            blackFont.put('!', new Texture("fonts/size_H44_W22/exclamation_44black.png"));
            blackFont.put(';', new Texture("fonts/size_H44_W22/;_44black.png"));
            blackFont.put('|', new Texture("fonts/size_H44_W22/|_44black.png"));
            blackFont.put('>', new Texture("fonts/size_H44_W22/>_44black.png"));
            blackFont.put('<', new Texture("fonts/size_H44_W22/<_44black.png"));
            blackFont.put('}', new Texture("fonts/size_H44_W22/}_44black.png"));
            blackFont.put('{', new Texture("fonts/size_H44_W22/{_44black.png"));
            blackFont.put(']', new Texture("fonts/size_H44_W22/]_44black.png"));
            blackFont.put('[', new Texture("fonts/size_H44_W22/[_44black.png"));
            blackFont.put(')', new Texture("fonts/size_H44_W22/)_44black.png"));
            blackFont.put('(', new Texture("fonts/size_H44_W22/(_44black.png"));
            blackFont.put('^', new Texture("fonts/size_H44_W22/^_44black.png"));
            blackFont.put('~', new Texture("fonts/size_H44_W22/~_44black.png"));
            blackFont.put('=', new Texture("fonts/size_H44_W22/=_44black.png"));
            blackFont.put('-', new Texture("fonts/size_H44_W22/-_44black.png"));
            blackFont.put('+', new Texture("fonts/size_H44_W22/+_44black.png"));
            blackFont.put('&', new Texture("fonts/size_H44_W22/&_44black.png"));
            blackFont.put('%', new Texture("fonts/size_H44_W22/%_44black.png"));
            blackFont.put('$', new Texture("fonts/size_H44_W22/$_44black.png"));
            blackFont.put('#', new Texture("fonts/size_H44_W22/#_44black.png"));
            blackFont.put('z', new Texture("fonts/size_H44_W22/z_44black.png"));
            blackFont.put('y', new Texture("fonts/size_H44_W22/y_44black.png"));
            blackFont.put('x', new Texture("fonts/size_H44_W22/x_44black.png"));
            blackFont.put('w', new Texture("fonts/size_H44_W22/w_44black.png"));
            blackFont.put('v', new Texture("fonts/size_H44_W22/v_44black.png"));
            blackFont.put('u', new Texture("fonts/size_H44_W22/u_44black.png"));
            blackFont.put('t', new Texture("fonts/size_H44_W22/t_44black.png"));
            blackFont.put('s', new Texture("fonts/size_H44_W22/s_44black.png"));
            blackFont.put('r', new Texture("fonts/size_H44_W22/r_44black.png"));
            blackFont.put('q', new Texture("fonts/size_H44_W22/q_44black.png"));
            blackFont.put('p', new Texture("fonts/size_H44_W22/p_44black.png"));
            blackFont.put('o', new Texture("fonts/size_H44_W22/o_44black.png"));
            blackFont.put('n', new Texture("fonts/size_H44_W22/n_44black.png"));
            blackFont.put('m', new Texture("fonts/size_H44_W22/m_44black.png"));
            blackFont.put('l', new Texture("fonts/size_H44_W22/l_44black.png"));
            blackFont.put('k', new Texture("fonts/size_H44_W22/k_44black.png"));
            blackFont.put('j', new Texture("fonts/size_H44_W22/j_44black.png"));
            blackFont.put('i', new Texture("fonts/size_H44_W22/i_44black.png"));
            blackFont.put('h', new Texture("fonts/size_H44_W22/h_44black.png"));
            blackFont.put('g', new Texture("fonts/size_H44_W22/g_44black.png"));
            blackFont.put('f', new Texture("fonts/size_H44_W22/f_44black.png"));
            blackFont.put('e', new Texture("fonts/size_H44_W22/e_44black.png"));
            blackFont.put('d', new Texture("fonts/size_H44_W22/d_44black.png"));
            blackFont.put('c', new Texture("fonts/size_H44_W22/c_44black.png"));
            blackFont.put('b', new Texture("fonts/size_H44_W22/b_44black.png"));
            blackFont.put('a', new Texture("fonts/size_H44_W22/a_44black.png"));
            blackFont.put('Z', new Texture("fonts/size_H44_W22/Z_44black.png"));
            blackFont.put('Y', new Texture("fonts/size_H44_W22/Y_44black.png"));
            blackFont.put('X', new Texture("fonts/size_H44_W22/X_44black.png"));
            blackFont.put('W', new Texture("fonts/size_H44_W22/W_44black.png"));
            blackFont.put('V', new Texture("fonts/size_H44_W22/V_44black.png"));
            blackFont.put('U', new Texture("fonts/size_H44_W22/U_44black.png"));
            blackFont.put('T', new Texture("fonts/size_H44_W22/T_44black.png"));
            blackFont.put('S', new Texture("fonts/size_H44_W22/S_44black.png"));
            blackFont.put('R', new Texture("fonts/size_H44_W22/R_44black.png"));
            blackFont.put('Q', new Texture("fonts/size_H44_W22/Q_44black.png"));
            blackFont.put('P', new Texture("fonts/size_H44_W22/P_44black.png"));
            blackFont.put('O', new Texture("fonts/size_H44_W22/O_44black.png"));
            blackFont.put('N', new Texture("fonts/size_H44_W22/N_44black.png"));
            blackFont.put('M', new Texture("fonts/size_H44_W22/M_44black.png"));
            blackFont.put('L', new Texture("fonts/size_H44_W22/L_44black.png"));
            blackFont.put('K', new Texture("fonts/size_H44_W22/K_44black.png"));
            blackFont.put('J', new Texture("fonts/size_H44_W22/J_44black.png"));
            blackFont.put('I', new Texture("fonts/size_H44_W22/I_44black.png"));
            blackFont.put('H', new Texture("fonts/size_H44_W22/H_44black.png"));
            blackFont.put('G', new Texture("fonts/size_H44_W22/G_44black.png"));
            blackFont.put('F', new Texture("fonts/size_H44_W22/F_44black.png"));
            blackFont.put('E', new Texture("fonts/size_H44_W22/E_44black.png"));
            blackFont.put('D', new Texture("fonts/size_H44_W22/D_44black.png"));
            blackFont.put('C', new Texture("fonts/size_H44_W22/C_44black.png"));
            blackFont.put('B', new Texture("fonts/size_H44_W22/B_44black.png"));
            blackFont.put('A', new Texture("fonts/size_H44_W22/A_44black.png"));
            blackFont.put('0', new Texture("fonts/size_H44_W22/0_44black.png"));
            blackFont.put('9', new Texture("fonts/size_H44_W22/9_44black.png"));
            blackFont.put('8', new Texture("fonts/size_H44_W22/8_44black.png"));
            blackFont.put('7', new Texture("fonts/size_H44_W22/7_44black.png"));
            blackFont.put('6', new Texture("fonts/size_H44_W22/6_44black.png"));
            blackFont.put('5', new Texture("fonts/size_H44_W22/5_44black.png"));
            blackFont.put('4', new Texture("fonts/size_H44_W22/4_44black.png"));
            blackFont.put('3', new Texture("fonts/size_H44_W22/3_44black.png"));
            blackFont.put('2', new Texture("fonts/size_H44_W22/2_44black.png"));
            blackFont.put('1', new Texture("fonts/size_H44_W22/1_44black.png"));
            blackFont.put('\'', new Texture("fonts/size_H44_W22/singleQuote_44black.png"));
            blackFont.put('`', new Texture("fonts/size_H44_W22/graveAccent_44black.png"));
            blackFont.put('\"', new Texture("fonts/size_H44_W22/doubleQuote_44black.png"));

        }
        private static void getRedTextures(){
            redFont.put('?', new Texture("fonts/size_H44_W22/questionmark_44red.png"));
            redFont.put(':', new Texture("fonts/size_H44_W22/colon_44red.png"));
            redFont.put('.', new Texture("fonts/size_H44_W22/dot_44red.png"));
            redFont.put('/', new Texture("fonts/size_H44_W22/forwardslash_44red.png"));
            redFont.put('*', new Texture("fonts/size_H44_W22/star_44red.png"));
            redFont.put('@', new Texture("fonts/size_H44_W22/atsign_44red.png"));
            redFont.put(',', new Texture("fonts/size_H44_W22/comma_44red.png"));
            redFont.put('!', new Texture("fonts/size_H44_W22/exclamation_44red.png"));
            redFont.put(';', new Texture("fonts/size_H44_W22/;_44red.png"));
            redFont.put('|', new Texture("fonts/size_H44_W22/|_44red.png"));
            redFont.put('>', new Texture("fonts/size_H44_W22/>_44red.png"));
            redFont.put('<', new Texture("fonts/size_H44_W22/<_44red.png"));
            redFont.put('}', new Texture("fonts/size_H44_W22/}_44red.png"));
            redFont.put('{', new Texture("fonts/size_H44_W22/{_44red.png"));
            redFont.put(']', new Texture("fonts/size_H44_W22/]_44red.png"));
            redFont.put('[', new Texture("fonts/size_H44_W22/[_44red.png"));
            redFont.put(')', new Texture("fonts/size_H44_W22/)_44red.png"));
            redFont.put('(', new Texture("fonts/size_H44_W22/(_44red.png"));
            redFont.put('^', new Texture("fonts/size_H44_W22/^_44red.png"));
            redFont.put('~', new Texture("fonts/size_H44_W22/~_44red.png"));
            redFont.put('=', new Texture("fonts/size_H44_W22/=_44red.png"));
            redFont.put('-', new Texture("fonts/size_H44_W22/-_44red.png"));
            redFont.put('+', new Texture("fonts/size_H44_W22/+_44red.png"));
            redFont.put('&', new Texture("fonts/size_H44_W22/&_44red.png"));
            redFont.put('%', new Texture("fonts/size_H44_W22/%_44red.png"));
            redFont.put('$', new Texture("fonts/size_H44_W22/$_44red.png"));
            redFont.put('#', new Texture("fonts/size_H44_W22/#_44red.png"));
            redFont.put('z', new Texture("fonts/size_H44_W22/z_44red.png"));
            redFont.put('y', new Texture("fonts/size_H44_W22/y_44red.png"));
            redFont.put('x', new Texture("fonts/size_H44_W22/x_44red.png"));
            redFont.put('w', new Texture("fonts/size_H44_W22/w_44red.png"));
            redFont.put('v', new Texture("fonts/size_H44_W22/v_44red.png"));
            redFont.put('u', new Texture("fonts/size_H44_W22/u_44red.png"));
            redFont.put('t', new Texture("fonts/size_H44_W22/t_44red.png"));
            redFont.put('s', new Texture("fonts/size_H44_W22/s_44red.png"));
            redFont.put('r', new Texture("fonts/size_H44_W22/r_44red.png"));
            redFont.put('q', new Texture("fonts/size_H44_W22/q_44red.png"));
            redFont.put('p', new Texture("fonts/size_H44_W22/p_44red.png"));
            redFont.put('o', new Texture("fonts/size_H44_W22/o_44red.png"));
            redFont.put('n', new Texture("fonts/size_H44_W22/n_44red.png"));
            redFont.put('m', new Texture("fonts/size_H44_W22/m_44red.png"));
            redFont.put('l', new Texture("fonts/size_H44_W22/l_44red.png"));
            redFont.put('k', new Texture("fonts/size_H44_W22/k_44red.png"));
            redFont.put('j', new Texture("fonts/size_H44_W22/j_44red.png"));
            redFont.put('i', new Texture("fonts/size_H44_W22/i_44red.png"));
            redFont.put('h', new Texture("fonts/size_H44_W22/h_44red.png"));
            redFont.put('g', new Texture("fonts/size_H44_W22/g_44red.png"));
            redFont.put('f', new Texture("fonts/size_H44_W22/f_44red.png"));
            redFont.put('e', new Texture("fonts/size_H44_W22/e_44red.png"));
            redFont.put('d', new Texture("fonts/size_H44_W22/d_44red.png"));
            redFont.put('c', new Texture("fonts/size_H44_W22/c_44red.png"));
            redFont.put('b', new Texture("fonts/size_H44_W22/b_44red.png"));
            redFont.put('a', new Texture("fonts/size_H44_W22/a_44red.png"));
            redFont.put('Z', new Texture("fonts/size_H44_W22/Z_44red.png"));
            redFont.put('Y', new Texture("fonts/size_H44_W22/Y_44red.png"));
            redFont.put('X', new Texture("fonts/size_H44_W22/X_44red.png"));
            redFont.put('W', new Texture("fonts/size_H44_W22/W_44red.png"));
            redFont.put('V', new Texture("fonts/size_H44_W22/V_44red.png"));
            redFont.put('U', new Texture("fonts/size_H44_W22/U_44red.png"));
            redFont.put('T', new Texture("fonts/size_H44_W22/T_44red.png"));
            redFont.put('S', new Texture("fonts/size_H44_W22/S_44red.png"));
            redFont.put('R', new Texture("fonts/size_H44_W22/R_44red.png"));
            redFont.put('Q', new Texture("fonts/size_H44_W22/Q_44red.png"));
            redFont.put('P', new Texture("fonts/size_H44_W22/P_44red.png"));
            redFont.put('O', new Texture("fonts/size_H44_W22/O_44red.png"));
            redFont.put('N', new Texture("fonts/size_H44_W22/N_44red.png"));
            redFont.put('M', new Texture("fonts/size_H44_W22/M_44red.png"));
            redFont.put('L', new Texture("fonts/size_H44_W22/L_44red.png"));
            redFont.put('K', new Texture("fonts/size_H44_W22/K_44red.png"));
            redFont.put('J', new Texture("fonts/size_H44_W22/J_44red.png"));
            redFont.put('I', new Texture("fonts/size_H44_W22/I_44red.png"));
            redFont.put('H', new Texture("fonts/size_H44_W22/H_44red.png"));
            redFont.put('G', new Texture("fonts/size_H44_W22/G_44red.png"));
            redFont.put('F', new Texture("fonts/size_H44_W22/F_44red.png"));
            redFont.put('E', new Texture("fonts/size_H44_W22/E_44red.png"));
            redFont.put('D', new Texture("fonts/size_H44_W22/D_44red.png"));
            redFont.put('C', new Texture("fonts/size_H44_W22/C_44red.png"));
            redFont.put('B', new Texture("fonts/size_H44_W22/B_44red.png"));
            redFont.put('A', new Texture("fonts/size_H44_W22/A_44red.png"));
            redFont.put('0', new Texture("fonts/size_H44_W22/0_44red.png"));
            redFont.put('9', new Texture("fonts/size_H44_W22/9_44red.png"));
            redFont.put('8', new Texture("fonts/size_H44_W22/8_44red.png"));
            redFont.put('7', new Texture("fonts/size_H44_W22/7_44red.png"));
            redFont.put('6', new Texture("fonts/size_H44_W22/6_44red.png"));
            redFont.put('5', new Texture("fonts/size_H44_W22/5_44red.png"));
            redFont.put('4', new Texture("fonts/size_H44_W22/4_44red.png"));
            redFont.put('3', new Texture("fonts/size_H44_W22/3_44red.png"));
            redFont.put('2', new Texture("fonts/size_H44_W22/2_44red.png"));
            redFont.put('1', new Texture("fonts/size_H44_W22/1_44red.png"));
            redFont.put('\'', new Texture("fonts/size_H44_W22/singleQuote_44red.png"));
            redFont.put('`', new Texture("fonts/size_H44_W22/graveAccent_44red.png"));
            redFont.put('\"', new Texture("fonts/size_H44_W22/doubleQuote_44red.png"));
        }

        private static void getGrayTextures(){
            grayFont.put('?', new Texture("fonts/size_H44_W22/questionmark_44gray.png"));
            grayFont.put(':', new Texture("fonts/size_H44_W22/colon_44gray.png"));
            grayFont.put('.', new Texture("fonts/size_H44_W22/dot_44gray.png"));
            grayFont.put('/', new Texture("fonts/size_H44_W22/forwardslash_44gray.png"));
            grayFont.put('*', new Texture("fonts/size_H44_W22/star_44gray.png"));
            grayFont.put('@', new Texture("fonts/size_H44_W22/atsign_44gray.png"));
            grayFont.put(',', new Texture("fonts/size_H44_W22/comma_44gray.png"));
            grayFont.put('!', new Texture("fonts/size_H44_W22/exclamation_44gray.png"));
            grayFont.put(';', new Texture("fonts/size_H44_W22/;_44gray.png"));
            grayFont.put('|', new Texture("fonts/size_H44_W22/|_44gray.png"));
            grayFont.put('>', new Texture("fonts/size_H44_W22/>_44gray.png"));
            grayFont.put('<', new Texture("fonts/size_H44_W22/<_44gray.png"));
            grayFont.put('}', new Texture("fonts/size_H44_W22/}_44gray.png"));
            grayFont.put('{', new Texture("fonts/size_H44_W22/{_44gray.png"));
            grayFont.put(']', new Texture("fonts/size_H44_W22/]_44gray.png"));
            grayFont.put('[', new Texture("fonts/size_H44_W22/[_44gray.png"));
            grayFont.put(')', new Texture("fonts/size_H44_W22/)_44gray.png"));
            grayFont.put('(', new Texture("fonts/size_H44_W22/(_44gray.png"));
            grayFont.put('^', new Texture("fonts/size_H44_W22/^_44gray.png"));
            grayFont.put('~', new Texture("fonts/size_H44_W22/~_44gray.png"));
            grayFont.put('=', new Texture("fonts/size_H44_W22/=_44gray.png"));
            grayFont.put('-', new Texture("fonts/size_H44_W22/-_44gray.png"));
            grayFont.put('+', new Texture("fonts/size_H44_W22/+_44gray.png"));
            grayFont.put('&', new Texture("fonts/size_H44_W22/&_44gray.png"));
            grayFont.put('%', new Texture("fonts/size_H44_W22/%_44gray.png"));
            grayFont.put('$', new Texture("fonts/size_H44_W22/$_44gray.png"));
            grayFont.put('#', new Texture("fonts/size_H44_W22/#_44gray.png"));
            grayFont.put('z', new Texture("fonts/size_H44_W22/z_44gray.png"));
            grayFont.put('y', new Texture("fonts/size_H44_W22/y_44gray.png"));
            grayFont.put('x', new Texture("fonts/size_H44_W22/x_44gray.png"));
            grayFont.put('w', new Texture("fonts/size_H44_W22/w_44gray.png"));
            grayFont.put('v', new Texture("fonts/size_H44_W22/v_44gray.png"));
            grayFont.put('u', new Texture("fonts/size_H44_W22/u_44gray.png"));
            grayFont.put('t', new Texture("fonts/size_H44_W22/t_44gray.png"));
            grayFont.put('s', new Texture("fonts/size_H44_W22/s_44gray.png"));
            grayFont.put('r', new Texture("fonts/size_H44_W22/r_44gray.png"));
            grayFont.put('q', new Texture("fonts/size_H44_W22/q_44gray.png"));
            grayFont.put('p', new Texture("fonts/size_H44_W22/p_44gray.png"));
            grayFont.put('o', new Texture("fonts/size_H44_W22/o_44gray.png"));
            grayFont.put('n', new Texture("fonts/size_H44_W22/n_44gray.png"));
            grayFont.put('m', new Texture("fonts/size_H44_W22/m_44gray.png"));
            grayFont.put('l', new Texture("fonts/size_H44_W22/l_44gray.png"));
            grayFont.put('k', new Texture("fonts/size_H44_W22/k_44gray.png"));
            grayFont.put('j', new Texture("fonts/size_H44_W22/j_44gray.png"));
            grayFont.put('i', new Texture("fonts/size_H44_W22/i_44gray.png"));
            grayFont.put('h', new Texture("fonts/size_H44_W22/h_44gray.png"));
            grayFont.put('g', new Texture("fonts/size_H44_W22/g_44gray.png"));
            grayFont.put('f', new Texture("fonts/size_H44_W22/f_44gray.png"));
            grayFont.put('e', new Texture("fonts/size_H44_W22/e_44gray.png"));
            grayFont.put('d', new Texture("fonts/size_H44_W22/d_44gray.png"));
            grayFont.put('c', new Texture("fonts/size_H44_W22/c_44gray.png"));
            grayFont.put('b', new Texture("fonts/size_H44_W22/b_44gray.png"));
            grayFont.put('a', new Texture("fonts/size_H44_W22/a_44gray.png"));
            grayFont.put('Z', new Texture("fonts/size_H44_W22/Z_44gray.png"));
            grayFont.put('Y', new Texture("fonts/size_H44_W22/Y_44gray.png"));
            grayFont.put('X', new Texture("fonts/size_H44_W22/X_44gray.png"));
            grayFont.put('W', new Texture("fonts/size_H44_W22/W_44gray.png"));
            grayFont.put('V', new Texture("fonts/size_H44_W22/V_44gray.png"));
            grayFont.put('U', new Texture("fonts/size_H44_W22/U_44gray.png"));
            grayFont.put('T', new Texture("fonts/size_H44_W22/T_44gray.png"));
            grayFont.put('S', new Texture("fonts/size_H44_W22/S_44gray.png"));
            grayFont.put('R', new Texture("fonts/size_H44_W22/R_44gray.png"));
            grayFont.put('Q', new Texture("fonts/size_H44_W22/Q_44gray.png"));
            grayFont.put('P', new Texture("fonts/size_H44_W22/P_44gray.png"));
            grayFont.put('O', new Texture("fonts/size_H44_W22/O_44gray.png"));
            grayFont.put('N', new Texture("fonts/size_H44_W22/N_44gray.png"));
            grayFont.put('M', new Texture("fonts/size_H44_W22/M_44gray.png"));
            grayFont.put('L', new Texture("fonts/size_H44_W22/L_44gray.png"));
            grayFont.put('K', new Texture("fonts/size_H44_W22/K_44gray.png"));
            grayFont.put('J', new Texture("fonts/size_H44_W22/J_44gray.png"));
            grayFont.put('I', new Texture("fonts/size_H44_W22/I_44gray.png"));
            grayFont.put('H', new Texture("fonts/size_H44_W22/H_44gray.png"));
            grayFont.put('G', new Texture("fonts/size_H44_W22/G_44gray.png"));
            grayFont.put('F', new Texture("fonts/size_H44_W22/F_44gray.png"));
            grayFont.put('E', new Texture("fonts/size_H44_W22/E_44gray.png"));
            grayFont.put('D', new Texture("fonts/size_H44_W22/D_44gray.png"));
            grayFont.put('C', new Texture("fonts/size_H44_W22/C_44gray.png"));
            grayFont.put('B', new Texture("fonts/size_H44_W22/B_44gray.png"));
            grayFont.put('A', new Texture("fonts/size_H44_W22/A_44gray.png"));
            grayFont.put('0', new Texture("fonts/size_H44_W22/0_44gray.png"));
            grayFont.put('9', new Texture("fonts/size_H44_W22/9_44gray.png"));
            grayFont.put('8', new Texture("fonts/size_H44_W22/8_44gray.png"));
            grayFont.put('7', new Texture("fonts/size_H44_W22/7_44gray.png"));
            grayFont.put('6', new Texture("fonts/size_H44_W22/6_44gray.png"));
            grayFont.put('5', new Texture("fonts/size_H44_W22/5_44gray.png"));
            grayFont.put('4', new Texture("fonts/size_H44_W22/4_44gray.png"));
            grayFont.put('3', new Texture("fonts/size_H44_W22/3_44gray.png"));
            grayFont.put('2', new Texture("fonts/size_H44_W22/2_44gray.png"));
            grayFont.put('1', new Texture("fonts/size_H44_W22/1_44gray.png"));
            grayFont.put('\'', new Texture("fonts/size_H44_W22/singleQuote_44gray.png"));
            grayFont.put('`', new Texture("fonts/size_H44_W22/graveAccent_44gray.png"));
            grayFont.put('\"', new Texture("fonts/size_H44_W22/doubleQuote_44gray.png"));

        }
        private static void getYellowTextures(){
            yellowFont.put('?', new Texture("fonts/size_H44_W22/questionmark_44yellow.png"));
            yellowFont.put(':', new Texture("fonts/size_H44_W22/colon_44yellow.png"));
            yellowFont.put('.', new Texture("fonts/size_H44_W22/dot_44yellow.png"));
            yellowFont.put('/', new Texture("fonts/size_H44_W22/forwardslash_44yellow.png"));
            yellowFont.put('*', new Texture("fonts/size_H44_W22/star_44yellow.png"));
            yellowFont.put('@', new Texture("fonts/size_H44_W22/atsign_44yellow.png"));
            yellowFont.put(',', new Texture("fonts/size_H44_W22/comma_44yellow.png"));
            yellowFont.put('!', new Texture("fonts/size_H44_W22/exclamation_44yellow.png"));
            yellowFont.put(';', new Texture("fonts/size_H44_W22/;_44yellow.png"));
            yellowFont.put('|', new Texture("fonts/size_H44_W22/|_44yellow.png"));
            yellowFont.put('>', new Texture("fonts/size_H44_W22/>_44yellow.png"));
            yellowFont.put('<', new Texture("fonts/size_H44_W22/<_44yellow.png"));
            yellowFont.put('}', new Texture("fonts/size_H44_W22/}_44yellow.png"));
            yellowFont.put('{', new Texture("fonts/size_H44_W22/{_44yellow.png"));
            yellowFont.put(']', new Texture("fonts/size_H44_W22/]_44yellow.png"));
            yellowFont.put('[', new Texture("fonts/size_H44_W22/[_44yellow.png"));
            yellowFont.put(')', new Texture("fonts/size_H44_W22/)_44yellow.png"));
            yellowFont.put('(', new Texture("fonts/size_H44_W22/(_44yellow.png"));
            yellowFont.put('^', new Texture("fonts/size_H44_W22/^_44yellow.png"));
            yellowFont.put('~', new Texture("fonts/size_H44_W22/~_44yellow.png"));
            yellowFont.put('=', new Texture("fonts/size_H44_W22/=_44yellow.png"));
            yellowFont.put('-', new Texture("fonts/size_H44_W22/-_44yellow.png"));
            yellowFont.put('+', new Texture("fonts/size_H44_W22/+_44yellow.png"));
            yellowFont.put('&', new Texture("fonts/size_H44_W22/&_44yellow.png"));
            yellowFont.put('%', new Texture("fonts/size_H44_W22/%_44yellow.png"));
            yellowFont.put('$', new Texture("fonts/size_H44_W22/$_44yellow.png"));
            yellowFont.put('#', new Texture("fonts/size_H44_W22/#_44yellow.png"));
            yellowFont.put('z', new Texture("fonts/size_H44_W22/z_44yellow.png"));
            yellowFont.put('y', new Texture("fonts/size_H44_W22/y_44yellow.png"));
            yellowFont.put('x', new Texture("fonts/size_H44_W22/x_44yellow.png"));
            yellowFont.put('w', new Texture("fonts/size_H44_W22/w_44yellow.png"));
            yellowFont.put('v', new Texture("fonts/size_H44_W22/v_44yellow.png"));
            yellowFont.put('u', new Texture("fonts/size_H44_W22/u_44yellow.png"));
            yellowFont.put('t', new Texture("fonts/size_H44_W22/t_44yellow.png"));
            yellowFont.put('s', new Texture("fonts/size_H44_W22/s_44yellow.png"));
            yellowFont.put('r', new Texture("fonts/size_H44_W22/r_44yellow.png"));
            yellowFont.put('q', new Texture("fonts/size_H44_W22/q_44yellow.png"));
            yellowFont.put('p', new Texture("fonts/size_H44_W22/p_44yellow.png"));
            yellowFont.put('o', new Texture("fonts/size_H44_W22/o_44yellow.png"));
            yellowFont.put('n', new Texture("fonts/size_H44_W22/n_44yellow.png"));
            yellowFont.put('m', new Texture("fonts/size_H44_W22/m_44yellow.png"));
            yellowFont.put('l', new Texture("fonts/size_H44_W22/l_44yellow.png"));
            yellowFont.put('k', new Texture("fonts/size_H44_W22/k_44yellow.png"));
            yellowFont.put('j', new Texture("fonts/size_H44_W22/j_44yellow.png"));
            yellowFont.put('i', new Texture("fonts/size_H44_W22/i_44yellow.png"));
            yellowFont.put('h', new Texture("fonts/size_H44_W22/h_44yellow.png"));
            yellowFont.put('g', new Texture("fonts/size_H44_W22/g_44yellow.png"));
            yellowFont.put('f', new Texture("fonts/size_H44_W22/f_44yellow.png"));
            yellowFont.put('e', new Texture("fonts/size_H44_W22/e_44yellow.png"));
            yellowFont.put('d', new Texture("fonts/size_H44_W22/d_44yellow.png"));
            yellowFont.put('c', new Texture("fonts/size_H44_W22/c_44yellow.png"));
            yellowFont.put('b', new Texture("fonts/size_H44_W22/b_44yellow.png"));
            yellowFont.put('a', new Texture("fonts/size_H44_W22/a_44yellow.png"));
            yellowFont.put('Z', new Texture("fonts/size_H44_W22/Z_44yellow.png"));
            yellowFont.put('Y', new Texture("fonts/size_H44_W22/Y_44yellow.png"));
            yellowFont.put('X', new Texture("fonts/size_H44_W22/X_44yellow.png"));
            yellowFont.put('W', new Texture("fonts/size_H44_W22/W_44yellow.png"));
            yellowFont.put('V', new Texture("fonts/size_H44_W22/V_44yellow.png"));
            yellowFont.put('U', new Texture("fonts/size_H44_W22/U_44yellow.png"));
            yellowFont.put('T', new Texture("fonts/size_H44_W22/T_44yellow.png"));
            yellowFont.put('S', new Texture("fonts/size_H44_W22/S_44yellow.png"));
            yellowFont.put('R', new Texture("fonts/size_H44_W22/R_44yellow.png"));
            yellowFont.put('Q', new Texture("fonts/size_H44_W22/Q_44yellow.png"));
            yellowFont.put('P', new Texture("fonts/size_H44_W22/P_44yellow.png"));
            yellowFont.put('O', new Texture("fonts/size_H44_W22/O_44yellow.png"));
            yellowFont.put('N', new Texture("fonts/size_H44_W22/N_44yellow.png"));
            yellowFont.put('M', new Texture("fonts/size_H44_W22/M_44yellow.png"));
            yellowFont.put('L', new Texture("fonts/size_H44_W22/L_44yellow.png"));
            yellowFont.put('K', new Texture("fonts/size_H44_W22/K_44yellow.png"));
            yellowFont.put('J', new Texture("fonts/size_H44_W22/J_44yellow.png"));
            yellowFont.put('I', new Texture("fonts/size_H44_W22/I_44yellow.png"));
            yellowFont.put('H', new Texture("fonts/size_H44_W22/H_44yellow.png"));
            yellowFont.put('G', new Texture("fonts/size_H44_W22/G_44yellow.png"));
            yellowFont.put('F', new Texture("fonts/size_H44_W22/F_44yellow.png"));
            yellowFont.put('E', new Texture("fonts/size_H44_W22/E_44yellow.png"));
            yellowFont.put('D', new Texture("fonts/size_H44_W22/D_44yellow.png"));
            yellowFont.put('C', new Texture("fonts/size_H44_W22/C_44yellow.png"));
            yellowFont.put('B', new Texture("fonts/size_H44_W22/B_44yellow.png"));
            yellowFont.put('A', new Texture("fonts/size_H44_W22/A_44yellow.png"));
            yellowFont.put('0', new Texture("fonts/size_H44_W22/0_44yellow.png"));
            yellowFont.put('9', new Texture("fonts/size_H44_W22/9_44yellow.png"));
            yellowFont.put('8', new Texture("fonts/size_H44_W22/8_44yellow.png"));
            yellowFont.put('7', new Texture("fonts/size_H44_W22/7_44yellow.png"));
            yellowFont.put('6', new Texture("fonts/size_H44_W22/6_44yellow.png"));
            yellowFont.put('5', new Texture("fonts/size_H44_W22/5_44yellow.png"));
            yellowFont.put('4', new Texture("fonts/size_H44_W22/4_44yellow.png"));
            yellowFont.put('3', new Texture("fonts/size_H44_W22/3_44yellow.png"));
            yellowFont.put('2', new Texture("fonts/size_H44_W22/2_44yellow.png"));
            yellowFont.put('1', new Texture("fonts/size_H44_W22/1_44yellow.png"));
            yellowFont.put('\'', new Texture("fonts/size_H44_W22/singleQuote_44yellow.png"));
            yellowFont.put('`', new Texture("fonts/size_H44_W22/graveAccent_44yellow.png"));
            yellowFont.put('\"', new Texture("fonts/size_H44_W22/doubleQuote_44yellow.png"));
        }

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
            whiteFont.put('\'', new Texture("fonts/size_H44_W22/singleQuote_44white.png"));
            whiteFont.put('`', new Texture("fonts/size_H44_W22/graveAccent_44white.png"));
            whiteFont.put('\"', new Texture("fonts/size_H44_W22/doubleQuote_44white.png"));
        }

    }
}
