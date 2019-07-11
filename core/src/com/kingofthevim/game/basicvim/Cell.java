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

    private static Map<Character, Texture> whiteFont = new HashMap<>();
    private static Map<Character, Texture> whiteGreenFont = new HashMap<>();
    private static Map<Character, Texture> whiteRedFont = new HashMap<>();
    private static Map<Character, Texture> whitePurpleFont = new HashMap<>();
    private static Map<Character, Texture> blackYellowFont = new HashMap<>();
    private static Map<Character, Texture> blackFont = new HashMap<>();
    private static Map<Character, Texture> redFont = new HashMap<>();
    private static Map<Character, Texture> yellowFont = new HashMap<>();
    private static Map<Character, Texture> grayFont = new HashMap<>();

    private static Font font = new Font();

    private char cellChar = ' ';
    private Texture cellLook;
    private Texture cellLookDefault;
    private Vector2 cartesianPosition;
    private LetterType letterType = LetterType.EMPATHY;


    Cell( float x, float y){

        cartesianPosition = new Vector2();
        cartesianPosition.x = x;
        cartesianPosition.y = y;
    }
    Cell(Vector2 position){

        cartesianPosition = position;
    }
    Cell(Texture look, Vector2 position){

        cellLook = look;
        cartesianPosition = position;
    }
    public Texture getCellLook(){
        return cellLook;
    }

    public void setCellLook(char cellChar, LetterType type, boolean isDefault) {


        switch (type) {

            case BLACK:
                this.cellLook = blackFont.get(cellChar);
                if(isDefault)this.cellLookDefault = blackFont.get(cellChar);
                break;

            case BLACK_YELLOW:
                this.cellLook = blackYellowFont.get(cellChar);
                if(isDefault)this.cellLookDefault = blackYellowFont.get(cellChar);
                break;

            case GRAY:
                this.cellLook = grayFont.get(cellChar);
                if(isDefault)this.cellLookDefault = grayFont.get(cellChar);
                break;


            case RED:
                this.cellLook = redFont.get(cellChar);
                if(isDefault)this.cellLookDefault = redFont.get(cellChar);
                break;

            case YELLOW:
                this.cellLook = yellowFont.get(cellChar);
                if(isDefault)this.cellLookDefault = yellowFont.get(cellChar);
                break;

            case WHITE:
                this.cellLook = whiteFont.get(cellChar);
                if(isDefault)this.cellLookDefault = whiteFont.get(cellChar);
                break;

            case WHITE_GREEN:
                this.cellLook = whiteGreenFont.get(cellChar);
                if(isDefault)this.cellLookDefault = whiteGreenFont.get(cellChar);
                break;

            case WHITE_RED:
                this.cellLook = whiteRedFont.get(cellChar);
                if(isDefault)this.cellLookDefault = whiteRedFont.get(cellChar);
                break;

            case WHITE_PURPLE:
                this.cellLook = whitePurpleFont.get(cellChar);
                if(isDefault)this.cellLookDefault = whitePurpleFont.get(cellChar);
                break;

        }

        this.cellChar = cellChar;
        if(isDefault) this.letterType = type;
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

    public void setCellLookTemp(LetterType letterType){

        setCellLook(cellChar, letterType, false);

    }

    public void setCellLookToDefault(){
        this.cellLook = this.cellLookDefault;
    }


    public void dispose(){
        cellLook.dispose();
        cellLookDefault.dispose();
    }

    public Enum<LetterType> getLetterType() {
        return letterType;
    }

    public void setLetterType(LetterType letterType) {

        this.setCellLook(cellChar, letterType, true);
    }

    public void setLetterType(char letter, LetterType letterType, boolean includeGrayFont) {

        if(this.letterType != LetterType.GRAY
        || includeGrayFont){
            this.setCellLook(letter, letterType, true);
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
            getWhiteGreenTextures();
            getWhiteRedTextures();
            getWhitePurpleTextures();
            getBlackYellowTextures();
        }

        private static void getBlackTextures(){

            blackFont.put('?', new Texture("fonts/size_42_f_black_bg_none/questionmark_42black_bg_none.png"));
            blackFont.put(':', new Texture("fonts/size_42_f_black_bg_none/colon_42black_bg_none.png"));
            blackFont.put('.', new Texture("fonts/size_42_f_black_bg_none/dot_42black_bg_none.png"));
            blackFont.put('/', new Texture("fonts/size_42_f_black_bg_none/forwardslash_42black_bg_none.png"));
            blackFont.put('*', new Texture("fonts/size_42_f_black_bg_none/star_42black_bg_none.png"));
            blackFont.put('@', new Texture("fonts/size_42_f_black_bg_none/atsign_42black_bg_none.png"));
            blackFont.put(',', new Texture("fonts/size_42_f_black_bg_none/comma_42black_bg_none.png"));
            blackFont.put('!', new Texture("fonts/size_42_f_black_bg_none/exclamation_42black_bg_none.png"));
            blackFont.put(';', new Texture("fonts/size_42_f_black_bg_none/;_42black_bg_none.png"));
            blackFont.put('|', new Texture("fonts/size_42_f_black_bg_none/|_42black_bg_none.png"));
            blackFont.put('>', new Texture("fonts/size_42_f_black_bg_none/>_42black_bg_none.png"));
            blackFont.put('<', new Texture("fonts/size_42_f_black_bg_none/<_42black_bg_none.png"));
            blackFont.put('}', new Texture("fonts/size_42_f_black_bg_none/}_42black_bg_none.png"));
            blackFont.put('{', new Texture("fonts/size_42_f_black_bg_none/{_42black_bg_none.png"));
            blackFont.put(']', new Texture("fonts/size_42_f_black_bg_none/]_42black_bg_none.png"));
            blackFont.put('[', new Texture("fonts/size_42_f_black_bg_none/[_42black_bg_none.png"));
            blackFont.put(')', new Texture("fonts/size_42_f_black_bg_none/)_42black_bg_none.png"));
            blackFont.put('(', new Texture("fonts/size_42_f_black_bg_none/(_42black_bg_none.png"));
            blackFont.put('^', new Texture("fonts/size_42_f_black_bg_none/^_42black_bg_none.png"));
            blackFont.put('~', new Texture("fonts/size_42_f_black_bg_none/~_42black_bg_none.png"));
            blackFont.put('=', new Texture("fonts/size_42_f_black_bg_none/=_42black_bg_none.png"));
            blackFont.put('-', new Texture("fonts/size_42_f_black_bg_none/-_42black_bg_none.png"));
            blackFont.put('+', new Texture("fonts/size_42_f_black_bg_none/+_42black_bg_none.png"));
            blackFont.put('&', new Texture("fonts/size_42_f_black_bg_none/&_42black_bg_none.png"));
            blackFont.put('%', new Texture("fonts/size_42_f_black_bg_none/%_42black_bg_none.png"));
            blackFont.put('$', new Texture("fonts/size_42_f_black_bg_none/$_42black_bg_none.png"));
            blackFont.put('#', new Texture("fonts/size_42_f_black_bg_none/#_42black_bg_none.png"));
            blackFont.put('z', new Texture("fonts/size_42_f_black_bg_none/z_42black_bg_none.png"));
            blackFont.put('y', new Texture("fonts/size_42_f_black_bg_none/y_42black_bg_none.png"));
            blackFont.put('x', new Texture("fonts/size_42_f_black_bg_none/x_42black_bg_none.png"));
            blackFont.put('w', new Texture("fonts/size_42_f_black_bg_none/w_42black_bg_none.png"));
            blackFont.put('v', new Texture("fonts/size_42_f_black_bg_none/v_42black_bg_none.png"));
            blackFont.put('u', new Texture("fonts/size_42_f_black_bg_none/u_42black_bg_none.png"));
            blackFont.put('t', new Texture("fonts/size_42_f_black_bg_none/t_42black_bg_none.png"));
            blackFont.put('s', new Texture("fonts/size_42_f_black_bg_none/s_42black_bg_none.png"));
            blackFont.put('r', new Texture("fonts/size_42_f_black_bg_none/r_42black_bg_none.png"));
            blackFont.put('q', new Texture("fonts/size_42_f_black_bg_none/q_42black_bg_none.png"));
            blackFont.put('p', new Texture("fonts/size_42_f_black_bg_none/p_42black_bg_none.png"));
            blackFont.put('o', new Texture("fonts/size_42_f_black_bg_none/o_42black_bg_none.png"));
            blackFont.put('n', new Texture("fonts/size_42_f_black_bg_none/n_42black_bg_none.png"));
            blackFont.put('m', new Texture("fonts/size_42_f_black_bg_none/m_42black_bg_none.png"));
            blackFont.put('l', new Texture("fonts/size_42_f_black_bg_none/l_42black_bg_none.png"));
            blackFont.put('k', new Texture("fonts/size_42_f_black_bg_none/k_42black_bg_none.png"));
            blackFont.put('j', new Texture("fonts/size_42_f_black_bg_none/j_42black_bg_none.png"));
            blackFont.put('i', new Texture("fonts/size_42_f_black_bg_none/i_42black_bg_none.png"));
            blackFont.put('h', new Texture("fonts/size_42_f_black_bg_none/h_42black_bg_none.png"));
            blackFont.put('g', new Texture("fonts/size_42_f_black_bg_none/g_42black_bg_none.png"));
            blackFont.put('f', new Texture("fonts/size_42_f_black_bg_none/f_42black_bg_none.png"));
            blackFont.put('e', new Texture("fonts/size_42_f_black_bg_none/e_42black_bg_none.png"));
            blackFont.put('d', new Texture("fonts/size_42_f_black_bg_none/d_42black_bg_none.png"));
            blackFont.put('c', new Texture("fonts/size_42_f_black_bg_none/c_42black_bg_none.png"));
            blackFont.put('b', new Texture("fonts/size_42_f_black_bg_none/b_42black_bg_none.png"));
            blackFont.put('a', new Texture("fonts/size_42_f_black_bg_none/a_42black_bg_none.png"));
            blackFont.put('Z', new Texture("fonts/size_42_f_black_bg_none/Z_42black_bg_none.png"));
            blackFont.put('Y', new Texture("fonts/size_42_f_black_bg_none/Y_42black_bg_none.png"));
            blackFont.put('X', new Texture("fonts/size_42_f_black_bg_none/X_42black_bg_none.png"));
            blackFont.put('W', new Texture("fonts/size_42_f_black_bg_none/W_42black_bg_none.png"));
            blackFont.put('V', new Texture("fonts/size_42_f_black_bg_none/V_42black_bg_none.png"));
            blackFont.put('U', new Texture("fonts/size_42_f_black_bg_none/U_42black_bg_none.png"));
            blackFont.put('T', new Texture("fonts/size_42_f_black_bg_none/T_42black_bg_none.png"));
            blackFont.put('S', new Texture("fonts/size_42_f_black_bg_none/S_42black_bg_none.png"));
            blackFont.put('R', new Texture("fonts/size_42_f_black_bg_none/R_42black_bg_none.png"));
            blackFont.put('Q', new Texture("fonts/size_42_f_black_bg_none/Q_42black_bg_none.png"));
            blackFont.put('P', new Texture("fonts/size_42_f_black_bg_none/P_42black_bg_none.png"));
            blackFont.put('O', new Texture("fonts/size_42_f_black_bg_none/O_42black_bg_none.png"));
            blackFont.put('N', new Texture("fonts/size_42_f_black_bg_none/N_42black_bg_none.png"));
            blackFont.put('M', new Texture("fonts/size_42_f_black_bg_none/M_42black_bg_none.png"));
            blackFont.put('L', new Texture("fonts/size_42_f_black_bg_none/L_42black_bg_none.png"));
            blackFont.put('K', new Texture("fonts/size_42_f_black_bg_none/K_42black_bg_none.png"));
            blackFont.put('J', new Texture("fonts/size_42_f_black_bg_none/J_42black_bg_none.png"));
            blackFont.put('I', new Texture("fonts/size_42_f_black_bg_none/I_42black_bg_none.png"));
            blackFont.put('H', new Texture("fonts/size_42_f_black_bg_none/H_42black_bg_none.png"));
            blackFont.put('G', new Texture("fonts/size_42_f_black_bg_none/G_42black_bg_none.png"));
            blackFont.put('F', new Texture("fonts/size_42_f_black_bg_none/F_42black_bg_none.png"));
            blackFont.put('E', new Texture("fonts/size_42_f_black_bg_none/E_42black_bg_none.png"));
            blackFont.put('D', new Texture("fonts/size_42_f_black_bg_none/D_42black_bg_none.png"));
            blackFont.put('C', new Texture("fonts/size_42_f_black_bg_none/C_42black_bg_none.png"));
            blackFont.put('B', new Texture("fonts/size_42_f_black_bg_none/B_42black_bg_none.png"));
            blackFont.put('A', new Texture("fonts/size_42_f_black_bg_none/A_42black_bg_none.png"));
            blackFont.put('0', new Texture("fonts/size_42_f_black_bg_none/0_42black_bg_none.png"));
            blackFont.put('9', new Texture("fonts/size_42_f_black_bg_none/9_42black_bg_none.png"));
            blackFont.put('8', new Texture("fonts/size_42_f_black_bg_none/8_42black_bg_none.png"));
            blackFont.put('7', new Texture("fonts/size_42_f_black_bg_none/7_42black_bg_none.png"));
            blackFont.put('6', new Texture("fonts/size_42_f_black_bg_none/6_42black_bg_none.png"));
            blackFont.put('5', new Texture("fonts/size_42_f_black_bg_none/5_42black_bg_none.png"));
            blackFont.put('4', new Texture("fonts/size_42_f_black_bg_none/4_42black_bg_none.png"));
            blackFont.put('3', new Texture("fonts/size_42_f_black_bg_none/3_42black_bg_none.png"));
            blackFont.put('2', new Texture("fonts/size_42_f_black_bg_none/2_42black_bg_none.png"));
            blackFont.put('1', new Texture("fonts/size_42_f_black_bg_none/1_42black_bg_none.png"));
            blackFont.put('\'', new Texture("fonts/size_42_f_black_bg_none/singleQuote_42black_bg_none.png"));
            blackFont.put('`', new Texture("fonts/size_42_f_black_bg_none/graveAccent_42black_bg_none.png"));
            blackFont.put('\"', new Texture("fonts/size_42_f_black_bg_none/doubleQuote_42black_bg_none.png"));

        }
        private static void getRedTextures(){
            redFont.put('?', new Texture("fonts/size_42_f_red_bg_none/questionmark_42red_bg_none.png"));
            redFont.put(':', new Texture("fonts/size_42_f_red_bg_none/colon_42red_bg_none.png"));
            redFont.put('.', new Texture("fonts/size_42_f_red_bg_none/dot_42red_bg_none.png"));
            redFont.put('/', new Texture("fonts/size_42_f_red_bg_none/forwardslash_42red_bg_none.png"));
            redFont.put('*', new Texture("fonts/size_42_f_red_bg_none/star_42red_bg_none.png"));
            redFont.put('@', new Texture("fonts/size_42_f_red_bg_none/atsign_42red_bg_none.png"));
            redFont.put(',', new Texture("fonts/size_42_f_red_bg_none/comma_42red_bg_none.png"));
            redFont.put('!', new Texture("fonts/size_42_f_red_bg_none/exclamation_42red_bg_none.png"));
            redFont.put(';', new Texture("fonts/size_42_f_red_bg_none/;_42red_bg_none.png"));
            redFont.put('|', new Texture("fonts/size_42_f_red_bg_none/|_42red_bg_none.png"));
            redFont.put('>', new Texture("fonts/size_42_f_red_bg_none/>_42red_bg_none.png"));
            redFont.put('<', new Texture("fonts/size_42_f_red_bg_none/<_42red_bg_none.png"));
            redFont.put('}', new Texture("fonts/size_42_f_red_bg_none/}_42red_bg_none.png"));
            redFont.put('{', new Texture("fonts/size_42_f_red_bg_none/{_42red_bg_none.png"));
            redFont.put(']', new Texture("fonts/size_42_f_red_bg_none/]_42red_bg_none.png"));
            redFont.put('[', new Texture("fonts/size_42_f_red_bg_none/[_42red_bg_none.png"));
            redFont.put(')', new Texture("fonts/size_42_f_red_bg_none/)_42red_bg_none.png"));
            redFont.put('(', new Texture("fonts/size_42_f_red_bg_none/(_42red_bg_none.png"));
            redFont.put('^', new Texture("fonts/size_42_f_red_bg_none/^_42red_bg_none.png"));
            redFont.put('~', new Texture("fonts/size_42_f_red_bg_none/~_42red_bg_none.png"));
            redFont.put('=', new Texture("fonts/size_42_f_red_bg_none/=_42red_bg_none.png"));
            redFont.put('-', new Texture("fonts/size_42_f_red_bg_none/-_42red_bg_none.png"));
            redFont.put('+', new Texture("fonts/size_42_f_red_bg_none/+_42red_bg_none.png"));
            redFont.put('&', new Texture("fonts/size_42_f_red_bg_none/&_42red_bg_none.png"));
            redFont.put('%', new Texture("fonts/size_42_f_red_bg_none/%_42red_bg_none.png"));
            redFont.put('$', new Texture("fonts/size_42_f_red_bg_none/$_42red_bg_none.png"));
            redFont.put('#', new Texture("fonts/size_42_f_red_bg_none/#_42red_bg_none.png"));
            redFont.put('z', new Texture("fonts/size_42_f_red_bg_none/z_42red_bg_none.png"));
            redFont.put('y', new Texture("fonts/size_42_f_red_bg_none/y_42red_bg_none.png"));
            redFont.put('x', new Texture("fonts/size_42_f_red_bg_none/x_42red_bg_none.png"));
            redFont.put('w', new Texture("fonts/size_42_f_red_bg_none/w_42red_bg_none.png"));
            redFont.put('v', new Texture("fonts/size_42_f_red_bg_none/v_42red_bg_none.png"));
            redFont.put('u', new Texture("fonts/size_42_f_red_bg_none/u_42red_bg_none.png"));
            redFont.put('t', new Texture("fonts/size_42_f_red_bg_none/t_42red_bg_none.png"));
            redFont.put('s', new Texture("fonts/size_42_f_red_bg_none/s_42red_bg_none.png"));
            redFont.put('r', new Texture("fonts/size_42_f_red_bg_none/r_42red_bg_none.png"));
            redFont.put('q', new Texture("fonts/size_42_f_red_bg_none/q_42red_bg_none.png"));
            redFont.put('p', new Texture("fonts/size_42_f_red_bg_none/p_42red_bg_none.png"));
            redFont.put('o', new Texture("fonts/size_42_f_red_bg_none/o_42red_bg_none.png"));
            redFont.put('n', new Texture("fonts/size_42_f_red_bg_none/n_42red_bg_none.png"));
            redFont.put('m', new Texture("fonts/size_42_f_red_bg_none/m_42red_bg_none.png"));
            redFont.put('l', new Texture("fonts/size_42_f_red_bg_none/l_42red_bg_none.png"));
            redFont.put('k', new Texture("fonts/size_42_f_red_bg_none/k_42red_bg_none.png"));
            redFont.put('j', new Texture("fonts/size_42_f_red_bg_none/j_42red_bg_none.png"));
            redFont.put('i', new Texture("fonts/size_42_f_red_bg_none/i_42red_bg_none.png"));
            redFont.put('h', new Texture("fonts/size_42_f_red_bg_none/h_42red_bg_none.png"));
            redFont.put('g', new Texture("fonts/size_42_f_red_bg_none/g_42red_bg_none.png"));
            redFont.put('f', new Texture("fonts/size_42_f_red_bg_none/f_42red_bg_none.png"));
            redFont.put('e', new Texture("fonts/size_42_f_red_bg_none/e_42red_bg_none.png"));
            redFont.put('d', new Texture("fonts/size_42_f_red_bg_none/d_42red_bg_none.png"));
            redFont.put('c', new Texture("fonts/size_42_f_red_bg_none/c_42red_bg_none.png"));
            redFont.put('b', new Texture("fonts/size_42_f_red_bg_none/b_42red_bg_none.png"));
            redFont.put('a', new Texture("fonts/size_42_f_red_bg_none/a_42red_bg_none.png"));
            redFont.put('Z', new Texture("fonts/size_42_f_red_bg_none/Z_42red_bg_none.png"));
            redFont.put('Y', new Texture("fonts/size_42_f_red_bg_none/Y_42red_bg_none.png"));
            redFont.put('X', new Texture("fonts/size_42_f_red_bg_none/X_42red_bg_none.png"));
            redFont.put('W', new Texture("fonts/size_42_f_red_bg_none/W_42red_bg_none.png"));
            redFont.put('V', new Texture("fonts/size_42_f_red_bg_none/V_42red_bg_none.png"));
            redFont.put('U', new Texture("fonts/size_42_f_red_bg_none/U_42red_bg_none.png"));
            redFont.put('T', new Texture("fonts/size_42_f_red_bg_none/T_42red_bg_none.png"));
            redFont.put('S', new Texture("fonts/size_42_f_red_bg_none/S_42red_bg_none.png"));
            redFont.put('R', new Texture("fonts/size_42_f_red_bg_none/R_42red_bg_none.png"));
            redFont.put('Q', new Texture("fonts/size_42_f_red_bg_none/Q_42red_bg_none.png"));
            redFont.put('P', new Texture("fonts/size_42_f_red_bg_none/P_42red_bg_none.png"));
            redFont.put('O', new Texture("fonts/size_42_f_red_bg_none/O_42red_bg_none.png"));
            redFont.put('N', new Texture("fonts/size_42_f_red_bg_none/N_42red_bg_none.png"));
            redFont.put('M', new Texture("fonts/size_42_f_red_bg_none/M_42red_bg_none.png"));
            redFont.put('L', new Texture("fonts/size_42_f_red_bg_none/L_42red_bg_none.png"));
            redFont.put('K', new Texture("fonts/size_42_f_red_bg_none/K_42red_bg_none.png"));
            redFont.put('J', new Texture("fonts/size_42_f_red_bg_none/J_42red_bg_none.png"));
            redFont.put('I', new Texture("fonts/size_42_f_red_bg_none/I_42red_bg_none.png"));
            redFont.put('H', new Texture("fonts/size_42_f_red_bg_none/H_42red_bg_none.png"));
            redFont.put('G', new Texture("fonts/size_42_f_red_bg_none/G_42red_bg_none.png"));
            redFont.put('F', new Texture("fonts/size_42_f_red_bg_none/F_42red_bg_none.png"));
            redFont.put('E', new Texture("fonts/size_42_f_red_bg_none/E_42red_bg_none.png"));
            redFont.put('D', new Texture("fonts/size_42_f_red_bg_none/D_42red_bg_none.png"));
            redFont.put('C', new Texture("fonts/size_42_f_red_bg_none/C_42red_bg_none.png"));
            redFont.put('B', new Texture("fonts/size_42_f_red_bg_none/B_42red_bg_none.png"));
            redFont.put('A', new Texture("fonts/size_42_f_red_bg_none/A_42red_bg_none.png"));
            redFont.put('0', new Texture("fonts/size_42_f_red_bg_none/0_42red_bg_none.png"));
            redFont.put('9', new Texture("fonts/size_42_f_red_bg_none/9_42red_bg_none.png"));
            redFont.put('8', new Texture("fonts/size_42_f_red_bg_none/8_42red_bg_none.png"));
            redFont.put('7', new Texture("fonts/size_42_f_red_bg_none/7_42red_bg_none.png"));
            redFont.put('6', new Texture("fonts/size_42_f_red_bg_none/6_42red_bg_none.png"));
            redFont.put('5', new Texture("fonts/size_42_f_red_bg_none/5_42red_bg_none.png"));
            redFont.put('4', new Texture("fonts/size_42_f_red_bg_none/4_42red_bg_none.png"));
            redFont.put('3', new Texture("fonts/size_42_f_red_bg_none/3_42red_bg_none.png"));
            redFont.put('2', new Texture("fonts/size_42_f_red_bg_none/2_42red_bg_none.png"));
            redFont.put('1', new Texture("fonts/size_42_f_red_bg_none/1_42red_bg_none.png"));
            redFont.put('\'', new Texture("fonts/size_42_f_red_bg_none/singleQuote_42red_bg_none.png"));
            redFont.put('`', new Texture("fonts/size_42_f_red_bg_none/graveAccent_42red_bg_none.png"));
            redFont.put('\"', new Texture("fonts/size_42_f_red_bg_none/doubleQuote_42red_bg_none.png"));
        }
        private static void getGrayTextures(){
            grayFont.put('?', new Texture("fonts/size_42_f_gray_bg_none/questionmark_42gray_bg_none.png"));
            grayFont.put(':', new Texture("fonts/size_42_f_gray_bg_none/colon_42gray_bg_none.png"));
            grayFont.put('.', new Texture("fonts/size_42_f_gray_bg_none/dot_42gray_bg_none.png"));
            grayFont.put('/', new Texture("fonts/size_42_f_gray_bg_none/forwardslash_42gray_bg_none.png"));
            grayFont.put('*', new Texture("fonts/size_42_f_gray_bg_none/star_42gray_bg_none.png"));
            grayFont.put('@', new Texture("fonts/size_42_f_gray_bg_none/atsign_42gray_bg_none.png"));
            grayFont.put(',', new Texture("fonts/size_42_f_gray_bg_none/comma_42gray_bg_none.png"));
            grayFont.put('!', new Texture("fonts/size_42_f_gray_bg_none/exclamation_42gray_bg_none.png"));
            grayFont.put(';', new Texture("fonts/size_42_f_gray_bg_none/;_42gray_bg_none.png"));
            grayFont.put('|', new Texture("fonts/size_42_f_gray_bg_none/|_42gray_bg_none.png"));
            grayFont.put('>', new Texture("fonts/size_42_f_gray_bg_none/>_42gray_bg_none.png"));
            grayFont.put('<', new Texture("fonts/size_42_f_gray_bg_none/<_42gray_bg_none.png"));
            grayFont.put('}', new Texture("fonts/size_42_f_gray_bg_none/}_42gray_bg_none.png"));
            grayFont.put('{', new Texture("fonts/size_42_f_gray_bg_none/{_42gray_bg_none.png"));
            grayFont.put(']', new Texture("fonts/size_42_f_gray_bg_none/]_42gray_bg_none.png"));
            grayFont.put('[', new Texture("fonts/size_42_f_gray_bg_none/[_42gray_bg_none.png"));
            grayFont.put(')', new Texture("fonts/size_42_f_gray_bg_none/)_42gray_bg_none.png"));
            grayFont.put('(', new Texture("fonts/size_42_f_gray_bg_none/(_42gray_bg_none.png"));
            grayFont.put('^', new Texture("fonts/size_42_f_gray_bg_none/^_42gray_bg_none.png"));
            grayFont.put('~', new Texture("fonts/size_42_f_gray_bg_none/~_42gray_bg_none.png"));
            grayFont.put('=', new Texture("fonts/size_42_f_gray_bg_none/=_42gray_bg_none.png"));
            grayFont.put('-', new Texture("fonts/size_42_f_gray_bg_none/-_42gray_bg_none.png"));
            grayFont.put('+', new Texture("fonts/size_42_f_gray_bg_none/+_42gray_bg_none.png"));
            grayFont.put('&', new Texture("fonts/size_42_f_gray_bg_none/&_42gray_bg_none.png"));
            grayFont.put('%', new Texture("fonts/size_42_f_gray_bg_none/%_42gray_bg_none.png"));
            grayFont.put('$', new Texture("fonts/size_42_f_gray_bg_none/$_42gray_bg_none.png"));
            grayFont.put('#', new Texture("fonts/size_42_f_gray_bg_none/#_42gray_bg_none.png"));
            grayFont.put('z', new Texture("fonts/size_42_f_gray_bg_none/z_42gray_bg_none.png"));
            grayFont.put('y', new Texture("fonts/size_42_f_gray_bg_none/y_42gray_bg_none.png"));
            grayFont.put('x', new Texture("fonts/size_42_f_gray_bg_none/x_42gray_bg_none.png"));
            grayFont.put('w', new Texture("fonts/size_42_f_gray_bg_none/w_42gray_bg_none.png"));
            grayFont.put('v', new Texture("fonts/size_42_f_gray_bg_none/v_42gray_bg_none.png"));
            grayFont.put('u', new Texture("fonts/size_42_f_gray_bg_none/u_42gray_bg_none.png"));
            grayFont.put('t', new Texture("fonts/size_42_f_gray_bg_none/t_42gray_bg_none.png"));
            grayFont.put('s', new Texture("fonts/size_42_f_gray_bg_none/s_42gray_bg_none.png"));
            grayFont.put('r', new Texture("fonts/size_42_f_gray_bg_none/r_42gray_bg_none.png"));
            grayFont.put('q', new Texture("fonts/size_42_f_gray_bg_none/q_42gray_bg_none.png"));
            grayFont.put('p', new Texture("fonts/size_42_f_gray_bg_none/p_42gray_bg_none.png"));
            grayFont.put('o', new Texture("fonts/size_42_f_gray_bg_none/o_42gray_bg_none.png"));
            grayFont.put('n', new Texture("fonts/size_42_f_gray_bg_none/n_42gray_bg_none.png"));
            grayFont.put('m', new Texture("fonts/size_42_f_gray_bg_none/m_42gray_bg_none.png"));
            grayFont.put('l', new Texture("fonts/size_42_f_gray_bg_none/l_42gray_bg_none.png"));
            grayFont.put('k', new Texture("fonts/size_42_f_gray_bg_none/k_42gray_bg_none.png"));
            grayFont.put('j', new Texture("fonts/size_42_f_gray_bg_none/j_42gray_bg_none.png"));
            grayFont.put('i', new Texture("fonts/size_42_f_gray_bg_none/i_42gray_bg_none.png"));
            grayFont.put('h', new Texture("fonts/size_42_f_gray_bg_none/h_42gray_bg_none.png"));
            grayFont.put('g', new Texture("fonts/size_42_f_gray_bg_none/g_42gray_bg_none.png"));
            grayFont.put('f', new Texture("fonts/size_42_f_gray_bg_none/f_42gray_bg_none.png"));
            grayFont.put('e', new Texture("fonts/size_42_f_gray_bg_none/e_42gray_bg_none.png"));
            grayFont.put('d', new Texture("fonts/size_42_f_gray_bg_none/d_42gray_bg_none.png"));
            grayFont.put('c', new Texture("fonts/size_42_f_gray_bg_none/c_42gray_bg_none.png"));
            grayFont.put('b', new Texture("fonts/size_42_f_gray_bg_none/b_42gray_bg_none.png"));
            grayFont.put('a', new Texture("fonts/size_42_f_gray_bg_none/a_42gray_bg_none.png"));
            grayFont.put('Z', new Texture("fonts/size_42_f_gray_bg_none/Z_42gray_bg_none.png"));
            grayFont.put('Y', new Texture("fonts/size_42_f_gray_bg_none/Y_42gray_bg_none.png"));
            grayFont.put('X', new Texture("fonts/size_42_f_gray_bg_none/X_42gray_bg_none.png"));
            grayFont.put('W', new Texture("fonts/size_42_f_gray_bg_none/W_42gray_bg_none.png"));
            grayFont.put('V', new Texture("fonts/size_42_f_gray_bg_none/V_42gray_bg_none.png"));
            grayFont.put('U', new Texture("fonts/size_42_f_gray_bg_none/U_42gray_bg_none.png"));
            grayFont.put('T', new Texture("fonts/size_42_f_gray_bg_none/T_42gray_bg_none.png"));
            grayFont.put('S', new Texture("fonts/size_42_f_gray_bg_none/S_42gray_bg_none.png"));
            grayFont.put('R', new Texture("fonts/size_42_f_gray_bg_none/R_42gray_bg_none.png"));
            grayFont.put('Q', new Texture("fonts/size_42_f_gray_bg_none/Q_42gray_bg_none.png"));
            grayFont.put('P', new Texture("fonts/size_42_f_gray_bg_none/P_42gray_bg_none.png"));
            grayFont.put('O', new Texture("fonts/size_42_f_gray_bg_none/O_42gray_bg_none.png"));
            grayFont.put('N', new Texture("fonts/size_42_f_gray_bg_none/N_42gray_bg_none.png"));
            grayFont.put('M', new Texture("fonts/size_42_f_gray_bg_none/M_42gray_bg_none.png"));
            grayFont.put('L', new Texture("fonts/size_42_f_gray_bg_none/L_42gray_bg_none.png"));
            grayFont.put('K', new Texture("fonts/size_42_f_gray_bg_none/K_42gray_bg_none.png"));
            grayFont.put('J', new Texture("fonts/size_42_f_gray_bg_none/J_42gray_bg_none.png"));
            grayFont.put('I', new Texture("fonts/size_42_f_gray_bg_none/I_42gray_bg_none.png"));
            grayFont.put('H', new Texture("fonts/size_42_f_gray_bg_none/H_42gray_bg_none.png"));
            grayFont.put('G', new Texture("fonts/size_42_f_gray_bg_none/G_42gray_bg_none.png"));
            grayFont.put('F', new Texture("fonts/size_42_f_gray_bg_none/F_42gray_bg_none.png"));
            grayFont.put('E', new Texture("fonts/size_42_f_gray_bg_none/E_42gray_bg_none.png"));
            grayFont.put('D', new Texture("fonts/size_42_f_gray_bg_none/D_42gray_bg_none.png"));
            grayFont.put('C', new Texture("fonts/size_42_f_gray_bg_none/C_42gray_bg_none.png"));
            grayFont.put('B', new Texture("fonts/size_42_f_gray_bg_none/B_42gray_bg_none.png"));
            grayFont.put('A', new Texture("fonts/size_42_f_gray_bg_none/A_42gray_bg_none.png"));
            grayFont.put('0', new Texture("fonts/size_42_f_gray_bg_none/0_42gray_bg_none.png"));
            grayFont.put('9', new Texture("fonts/size_42_f_gray_bg_none/9_42gray_bg_none.png"));
            grayFont.put('8', new Texture("fonts/size_42_f_gray_bg_none/8_42gray_bg_none.png"));
            grayFont.put('7', new Texture("fonts/size_42_f_gray_bg_none/7_42gray_bg_none.png"));
            grayFont.put('6', new Texture("fonts/size_42_f_gray_bg_none/6_42gray_bg_none.png"));
            grayFont.put('5', new Texture("fonts/size_42_f_gray_bg_none/5_42gray_bg_none.png"));
            grayFont.put('4', new Texture("fonts/size_42_f_gray_bg_none/4_42gray_bg_none.png"));
            grayFont.put('3', new Texture("fonts/size_42_f_gray_bg_none/3_42gray_bg_none.png"));
            grayFont.put('2', new Texture("fonts/size_42_f_gray_bg_none/2_42gray_bg_none.png"));
            grayFont.put('1', new Texture("fonts/size_42_f_gray_bg_none/1_42gray_bg_none.png"));
            grayFont.put('\'', new Texture("fonts/size_42_f_gray_bg_none/singleQuote_42gray_bg_none.png"));
            grayFont.put('`', new Texture("fonts/size_42_f_gray_bg_none/graveAccent_42gray_bg_none.png"));
            grayFont.put('\"', new Texture("fonts/size_42_f_gray_bg_none/doubleQuote_42gray_bg_none.png"));

        }
        private static void getYellowTextures(){
            yellowFont.put('?', new Texture("fonts/size_42_f_yellow_bg_none/questionmark_42yellow_bg_none.png"));
            yellowFont.put(':', new Texture("fonts/size_42_f_yellow_bg_none/colon_42yellow_bg_none.png"));
            yellowFont.put('.', new Texture("fonts/size_42_f_yellow_bg_none/dot_42yellow_bg_none.png"));
            yellowFont.put('/', new Texture("fonts/size_42_f_yellow_bg_none/forwardslash_42yellow_bg_none.png"));
            yellowFont.put('*', new Texture("fonts/size_42_f_yellow_bg_none/star_42yellow_bg_none.png"));
            yellowFont.put('@', new Texture("fonts/size_42_f_yellow_bg_none/atsign_42yellow_bg_none.png"));
            yellowFont.put(',', new Texture("fonts/size_42_f_yellow_bg_none/comma_42yellow_bg_none.png"));
            yellowFont.put('!', new Texture("fonts/size_42_f_yellow_bg_none/exclamation_42yellow_bg_none.png"));
            yellowFont.put(';', new Texture("fonts/size_42_f_yellow_bg_none/;_42yellow_bg_none.png"));
            yellowFont.put('|', new Texture("fonts/size_42_f_yellow_bg_none/|_42yellow_bg_none.png"));
            yellowFont.put('>', new Texture("fonts/size_42_f_yellow_bg_none/>_42yellow_bg_none.png"));
            yellowFont.put('<', new Texture("fonts/size_42_f_yellow_bg_none/<_42yellow_bg_none.png"));
            yellowFont.put('}', new Texture("fonts/size_42_f_yellow_bg_none/}_42yellow_bg_none.png"));
            yellowFont.put('{', new Texture("fonts/size_42_f_yellow_bg_none/{_42yellow_bg_none.png"));
            yellowFont.put(']', new Texture("fonts/size_42_f_yellow_bg_none/]_42yellow_bg_none.png"));
            yellowFont.put('[', new Texture("fonts/size_42_f_yellow_bg_none/[_42yellow_bg_none.png"));
            yellowFont.put(')', new Texture("fonts/size_42_f_yellow_bg_none/)_42yellow_bg_none.png"));
            yellowFont.put('(', new Texture("fonts/size_42_f_yellow_bg_none/(_42yellow_bg_none.png"));
            yellowFont.put('^', new Texture("fonts/size_42_f_yellow_bg_none/^_42yellow_bg_none.png"));
            yellowFont.put('~', new Texture("fonts/size_42_f_yellow_bg_none/~_42yellow_bg_none.png"));
            yellowFont.put('=', new Texture("fonts/size_42_f_yellow_bg_none/=_42yellow_bg_none.png"));
            yellowFont.put('-', new Texture("fonts/size_42_f_yellow_bg_none/-_42yellow_bg_none.png"));
            yellowFont.put('+', new Texture("fonts/size_42_f_yellow_bg_none/+_42yellow_bg_none.png"));
            yellowFont.put('&', new Texture("fonts/size_42_f_yellow_bg_none/&_42yellow_bg_none.png"));
            yellowFont.put('%', new Texture("fonts/size_42_f_yellow_bg_none/%_42yellow_bg_none.png"));
            yellowFont.put('$', new Texture("fonts/size_42_f_yellow_bg_none/$_42yellow_bg_none.png"));
            yellowFont.put('#', new Texture("fonts/size_42_f_yellow_bg_none/#_42yellow_bg_none.png"));
            yellowFont.put('z', new Texture("fonts/size_42_f_yellow_bg_none/z_42yellow_bg_none.png"));
            yellowFont.put('y', new Texture("fonts/size_42_f_yellow_bg_none/y_42yellow_bg_none.png"));
            yellowFont.put('x', new Texture("fonts/size_42_f_yellow_bg_none/x_42yellow_bg_none.png"));
            yellowFont.put('w', new Texture("fonts/size_42_f_yellow_bg_none/w_42yellow_bg_none.png"));
            yellowFont.put('v', new Texture("fonts/size_42_f_yellow_bg_none/v_42yellow_bg_none.png"));
            yellowFont.put('u', new Texture("fonts/size_42_f_yellow_bg_none/u_42yellow_bg_none.png"));
            yellowFont.put('t', new Texture("fonts/size_42_f_yellow_bg_none/t_42yellow_bg_none.png"));
            yellowFont.put('s', new Texture("fonts/size_42_f_yellow_bg_none/s_42yellow_bg_none.png"));
            yellowFont.put('r', new Texture("fonts/size_42_f_yellow_bg_none/r_42yellow_bg_none.png"));
            yellowFont.put('q', new Texture("fonts/size_42_f_yellow_bg_none/q_42yellow_bg_none.png"));
            yellowFont.put('p', new Texture("fonts/size_42_f_yellow_bg_none/p_42yellow_bg_none.png"));
            yellowFont.put('o', new Texture("fonts/size_42_f_yellow_bg_none/o_42yellow_bg_none.png"));
            yellowFont.put('n', new Texture("fonts/size_42_f_yellow_bg_none/n_42yellow_bg_none.png"));
            yellowFont.put('m', new Texture("fonts/size_42_f_yellow_bg_none/m_42yellow_bg_none.png"));
            yellowFont.put('l', new Texture("fonts/size_42_f_yellow_bg_none/l_42yellow_bg_none.png"));
            yellowFont.put('k', new Texture("fonts/size_42_f_yellow_bg_none/k_42yellow_bg_none.png"));
            yellowFont.put('j', new Texture("fonts/size_42_f_yellow_bg_none/j_42yellow_bg_none.png"));
            yellowFont.put('i', new Texture("fonts/size_42_f_yellow_bg_none/i_42yellow_bg_none.png"));
            yellowFont.put('h', new Texture("fonts/size_42_f_yellow_bg_none/h_42yellow_bg_none.png"));
            yellowFont.put('g', new Texture("fonts/size_42_f_yellow_bg_none/g_42yellow_bg_none.png"));
            yellowFont.put('f', new Texture("fonts/size_42_f_yellow_bg_none/f_42yellow_bg_none.png"));
            yellowFont.put('e', new Texture("fonts/size_42_f_yellow_bg_none/e_42yellow_bg_none.png"));
            yellowFont.put('d', new Texture("fonts/size_42_f_yellow_bg_none/d_42yellow_bg_none.png"));
            yellowFont.put('c', new Texture("fonts/size_42_f_yellow_bg_none/c_42yellow_bg_none.png"));
            yellowFont.put('b', new Texture("fonts/size_42_f_yellow_bg_none/b_42yellow_bg_none.png"));
            yellowFont.put('a', new Texture("fonts/size_42_f_yellow_bg_none/a_42yellow_bg_none.png"));
            yellowFont.put('Z', new Texture("fonts/size_42_f_yellow_bg_none/Z_42yellow_bg_none.png"));
            yellowFont.put('Y', new Texture("fonts/size_42_f_yellow_bg_none/Y_42yellow_bg_none.png"));
            yellowFont.put('X', new Texture("fonts/size_42_f_yellow_bg_none/X_42yellow_bg_none.png"));
            yellowFont.put('W', new Texture("fonts/size_42_f_yellow_bg_none/W_42yellow_bg_none.png"));
            yellowFont.put('V', new Texture("fonts/size_42_f_yellow_bg_none/V_42yellow_bg_none.png"));
            yellowFont.put('U', new Texture("fonts/size_42_f_yellow_bg_none/U_42yellow_bg_none.png"));
            yellowFont.put('T', new Texture("fonts/size_42_f_yellow_bg_none/T_42yellow_bg_none.png"));
            yellowFont.put('S', new Texture("fonts/size_42_f_yellow_bg_none/S_42yellow_bg_none.png"));
            yellowFont.put('R', new Texture("fonts/size_42_f_yellow_bg_none/R_42yellow_bg_none.png"));
            yellowFont.put('Q', new Texture("fonts/size_42_f_yellow_bg_none/Q_42yellow_bg_none.png"));
            yellowFont.put('P', new Texture("fonts/size_42_f_yellow_bg_none/P_42yellow_bg_none.png"));
            yellowFont.put('O', new Texture("fonts/size_42_f_yellow_bg_none/O_42yellow_bg_none.png"));
            yellowFont.put('N', new Texture("fonts/size_42_f_yellow_bg_none/N_42yellow_bg_none.png"));
            yellowFont.put('M', new Texture("fonts/size_42_f_yellow_bg_none/M_42yellow_bg_none.png"));
            yellowFont.put('L', new Texture("fonts/size_42_f_yellow_bg_none/L_42yellow_bg_none.png"));
            yellowFont.put('K', new Texture("fonts/size_42_f_yellow_bg_none/K_42yellow_bg_none.png"));
            yellowFont.put('J', new Texture("fonts/size_42_f_yellow_bg_none/J_42yellow_bg_none.png"));
            yellowFont.put('I', new Texture("fonts/size_42_f_yellow_bg_none/I_42yellow_bg_none.png"));
            yellowFont.put('H', new Texture("fonts/size_42_f_yellow_bg_none/H_42yellow_bg_none.png"));
            yellowFont.put('G', new Texture("fonts/size_42_f_yellow_bg_none/G_42yellow_bg_none.png"));
            yellowFont.put('F', new Texture("fonts/size_42_f_yellow_bg_none/F_42yellow_bg_none.png"));
            yellowFont.put('E', new Texture("fonts/size_42_f_yellow_bg_none/E_42yellow_bg_none.png"));
            yellowFont.put('D', new Texture("fonts/size_42_f_yellow_bg_none/D_42yellow_bg_none.png"));
            yellowFont.put('C', new Texture("fonts/size_42_f_yellow_bg_none/C_42yellow_bg_none.png"));
            yellowFont.put('B', new Texture("fonts/size_42_f_yellow_bg_none/B_42yellow_bg_none.png"));
            yellowFont.put('A', new Texture("fonts/size_42_f_yellow_bg_none/A_42yellow_bg_none.png"));
            yellowFont.put('0', new Texture("fonts/size_42_f_yellow_bg_none/0_42yellow_bg_none.png"));
            yellowFont.put('9', new Texture("fonts/size_42_f_yellow_bg_none/9_42yellow_bg_none.png"));
            yellowFont.put('8', new Texture("fonts/size_42_f_yellow_bg_none/8_42yellow_bg_none.png"));
            yellowFont.put('7', new Texture("fonts/size_42_f_yellow_bg_none/7_42yellow_bg_none.png"));
            yellowFont.put('6', new Texture("fonts/size_42_f_yellow_bg_none/6_42yellow_bg_none.png"));
            yellowFont.put('5', new Texture("fonts/size_42_f_yellow_bg_none/5_42yellow_bg_none.png"));
            yellowFont.put('4', new Texture("fonts/size_42_f_yellow_bg_none/4_42yellow_bg_none.png"));
            yellowFont.put('3', new Texture("fonts/size_42_f_yellow_bg_none/3_42yellow_bg_none.png"));
            yellowFont.put('2', new Texture("fonts/size_42_f_yellow_bg_none/2_42yellow_bg_none.png"));
            yellowFont.put('1', new Texture("fonts/size_42_f_yellow_bg_none/1_42yellow_bg_none.png"));
            yellowFont.put('\'', new Texture("fonts/size_42_f_yellow_bg_none/singleQuote_42yellow_bg_none.png"));
            yellowFont.put('`', new Texture("fonts/size_42_f_yellow_bg_none/graveAccent_42yellow_bg_none.png"));
            yellowFont.put('\"', new Texture("fonts/size_42_f_yellow_bg_none/doubleQuote_42yellow_bg_none.png"));
        }
        private static void getWhiteTextures(){

            whiteFont.put('?', new Texture("fonts/size_42_f_white_bg_none/questionmark_42white_bg_none.png"));
            whiteFont.put(':', new Texture("fonts/size_42_f_white_bg_none/colon_42white_bg_none.png"));
            whiteFont.put('.', new Texture("fonts/size_42_f_white_bg_none/dot_42white_bg_none.png"));
            whiteFont.put('/', new Texture("fonts/size_42_f_white_bg_none/forwardslash_42white_bg_none.png"));
            whiteFont.put('*', new Texture("fonts/size_42_f_white_bg_none/star_42white_bg_none.png"));
            whiteFont.put('@', new Texture("fonts/size_42_f_white_bg_none/atsign_42white_bg_none.png"));
            whiteFont.put(',', new Texture("fonts/size_42_f_white_bg_none/comma_42white_bg_none.png"));
            whiteFont.put('!', new Texture("fonts/size_42_f_white_bg_none/exclamation_42white_bg_none.png"));
            whiteFont.put(';', new Texture("fonts/size_42_f_white_bg_none/;_42white_bg_none.png"));
            whiteFont.put('|', new Texture("fonts/size_42_f_white_bg_none/|_42white_bg_none.png"));
            whiteFont.put('>', new Texture("fonts/size_42_f_white_bg_none/>_42white_bg_none.png"));
            whiteFont.put('<', new Texture("fonts/size_42_f_white_bg_none/<_42white_bg_none.png"));
            whiteFont.put('}', new Texture("fonts/size_42_f_white_bg_none/}_42white_bg_none.png"));
            whiteFont.put('{', new Texture("fonts/size_42_f_white_bg_none/{_42white_bg_none.png"));
            whiteFont.put(']', new Texture("fonts/size_42_f_white_bg_none/]_42white_bg_none.png"));
            whiteFont.put('[', new Texture("fonts/size_42_f_white_bg_none/[_42white_bg_none.png"));
            whiteFont.put(')', new Texture("fonts/size_42_f_white_bg_none/)_42white_bg_none.png"));
            whiteFont.put('(', new Texture("fonts/size_42_f_white_bg_none/(_42white_bg_none.png"));
            whiteFont.put('^', new Texture("fonts/size_42_f_white_bg_none/^_42white_bg_none.png"));
            whiteFont.put('~', new Texture("fonts/size_42_f_white_bg_none/~_42white_bg_none.png"));
            whiteFont.put('=', new Texture("fonts/size_42_f_white_bg_none/=_42white_bg_none.png"));
            whiteFont.put('-', new Texture("fonts/size_42_f_white_bg_none/-_42white_bg_none.png"));
            whiteFont.put('+', new Texture("fonts/size_42_f_white_bg_none/+_42white_bg_none.png"));
            whiteFont.put('&', new Texture("fonts/size_42_f_white_bg_none/&_42white_bg_none.png"));
            whiteFont.put('%', new Texture("fonts/size_42_f_white_bg_none/%_42white_bg_none.png"));
            whiteFont.put('$', new Texture("fonts/size_42_f_white_bg_none/$_42white_bg_none.png"));
            whiteFont.put('#', new Texture("fonts/size_42_f_white_bg_none/#_42white_bg_none.png"));
            whiteFont.put('z', new Texture("fonts/size_42_f_white_bg_none/z_42white_bg_none.png"));
            whiteFont.put('y', new Texture("fonts/size_42_f_white_bg_none/y_42white_bg_none.png"));
            whiteFont.put('x', new Texture("fonts/size_42_f_white_bg_none/x_42white_bg_none.png"));
            whiteFont.put('w', new Texture("fonts/size_42_f_white_bg_none/w_42white_bg_none.png"));
            whiteFont.put('v', new Texture("fonts/size_42_f_white_bg_none/v_42white_bg_none.png"));
            whiteFont.put('u', new Texture("fonts/size_42_f_white_bg_none/u_42white_bg_none.png"));
            whiteFont.put('t', new Texture("fonts/size_42_f_white_bg_none/t_42white_bg_none.png"));
            whiteFont.put('s', new Texture("fonts/size_42_f_white_bg_none/s_42white_bg_none.png"));
            whiteFont.put('r', new Texture("fonts/size_42_f_white_bg_none/r_42white_bg_none.png"));
            whiteFont.put('q', new Texture("fonts/size_42_f_white_bg_none/q_42white_bg_none.png"));
            whiteFont.put('p', new Texture("fonts/size_42_f_white_bg_none/p_42white_bg_none.png"));
            whiteFont.put('o', new Texture("fonts/size_42_f_white_bg_none/o_42white_bg_none.png"));
            whiteFont.put('n', new Texture("fonts/size_42_f_white_bg_none/n_42white_bg_none.png"));
            whiteFont.put('m', new Texture("fonts/size_42_f_white_bg_none/m_42white_bg_none.png"));
            whiteFont.put('l', new Texture("fonts/size_42_f_white_bg_none/l_42white_bg_none.png"));
            whiteFont.put('k', new Texture("fonts/size_42_f_white_bg_none/k_42white_bg_none.png"));
            whiteFont.put('j', new Texture("fonts/size_42_f_white_bg_none/j_42white_bg_none.png"));
            whiteFont.put('i', new Texture("fonts/size_42_f_white_bg_none/i_42white_bg_none.png"));
            whiteFont.put('h', new Texture("fonts/size_42_f_white_bg_none/h_42white_bg_none.png"));
            whiteFont.put('g', new Texture("fonts/size_42_f_white_bg_none/g_42white_bg_none.png"));
            whiteFont.put('f', new Texture("fonts/size_42_f_white_bg_none/f_42white_bg_none.png"));
            whiteFont.put('e', new Texture("fonts/size_42_f_white_bg_none/e_42white_bg_none.png"));
            whiteFont.put('d', new Texture("fonts/size_42_f_white_bg_none/d_42white_bg_none.png"));
            whiteFont.put('c', new Texture("fonts/size_42_f_white_bg_none/c_42white_bg_none.png"));
            whiteFont.put('b', new Texture("fonts/size_42_f_white_bg_none/b_42white_bg_none.png"));
            whiteFont.put('a', new Texture("fonts/size_42_f_white_bg_none/a_42white_bg_none.png"));
            whiteFont.put('Z', new Texture("fonts/size_42_f_white_bg_none/Z_42white_bg_none.png"));
            whiteFont.put('Y', new Texture("fonts/size_42_f_white_bg_none/Y_42white_bg_none.png"));
            whiteFont.put('X', new Texture("fonts/size_42_f_white_bg_none/X_42white_bg_none.png"));
            whiteFont.put('W', new Texture("fonts/size_42_f_white_bg_none/W_42white_bg_none.png"));
            whiteFont.put('V', new Texture("fonts/size_42_f_white_bg_none/V_42white_bg_none.png"));
            whiteFont.put('U', new Texture("fonts/size_42_f_white_bg_none/U_42white_bg_none.png"));
            whiteFont.put('T', new Texture("fonts/size_42_f_white_bg_none/T_42white_bg_none.png"));
            whiteFont.put('S', new Texture("fonts/size_42_f_white_bg_none/S_42white_bg_none.png"));
            whiteFont.put('R', new Texture("fonts/size_42_f_white_bg_none/R_42white_bg_none.png"));
            whiteFont.put('Q', new Texture("fonts/size_42_f_white_bg_none/Q_42white_bg_none.png"));
            whiteFont.put('P', new Texture("fonts/size_42_f_white_bg_none/P_42white_bg_none.png"));
            whiteFont.put('O', new Texture("fonts/size_42_f_white_bg_none/O_42white_bg_none.png"));
            whiteFont.put('N', new Texture("fonts/size_42_f_white_bg_none/N_42white_bg_none.png"));
            whiteFont.put('M', new Texture("fonts/size_42_f_white_bg_none/M_42white_bg_none.png"));
            whiteFont.put('L', new Texture("fonts/size_42_f_white_bg_none/L_42white_bg_none.png"));
            whiteFont.put('K', new Texture("fonts/size_42_f_white_bg_none/K_42white_bg_none.png"));
            whiteFont.put('J', new Texture("fonts/size_42_f_white_bg_none/J_42white_bg_none.png"));
            whiteFont.put('I', new Texture("fonts/size_42_f_white_bg_none/I_42white_bg_none.png"));
            whiteFont.put('H', new Texture("fonts/size_42_f_white_bg_none/H_42white_bg_none.png"));
            whiteFont.put('G', new Texture("fonts/size_42_f_white_bg_none/G_42white_bg_none.png"));
            whiteFont.put('F', new Texture("fonts/size_42_f_white_bg_none/F_42white_bg_none.png"));
            whiteFont.put('E', new Texture("fonts/size_42_f_white_bg_none/E_42white_bg_none.png"));
            whiteFont.put('D', new Texture("fonts/size_42_f_white_bg_none/D_42white_bg_none.png"));
            whiteFont.put('C', new Texture("fonts/size_42_f_white_bg_none/C_42white_bg_none.png"));
            whiteFont.put('B', new Texture("fonts/size_42_f_white_bg_none/B_42white_bg_none.png"));
            whiteFont.put('A', new Texture("fonts/size_42_f_white_bg_none/A_42white_bg_none.png"));
            whiteFont.put('0', new Texture("fonts/size_42_f_white_bg_none/0_42white_bg_none.png"));
            whiteFont.put('9', new Texture("fonts/size_42_f_white_bg_none/9_42white_bg_none.png"));
            whiteFont.put('8', new Texture("fonts/size_42_f_white_bg_none/8_42white_bg_none.png"));
            whiteFont.put('7', new Texture("fonts/size_42_f_white_bg_none/7_42white_bg_none.png"));
            whiteFont.put('6', new Texture("fonts/size_42_f_white_bg_none/6_42white_bg_none.png"));
            whiteFont.put('5', new Texture("fonts/size_42_f_white_bg_none/5_42white_bg_none.png"));
            whiteFont.put('4', new Texture("fonts/size_42_f_white_bg_none/4_42white_bg_none.png"));
            whiteFont.put('3', new Texture("fonts/size_42_f_white_bg_none/3_42white_bg_none.png"));
            whiteFont.put('2', new Texture("fonts/size_42_f_white_bg_none/2_42white_bg_none.png"));
            whiteFont.put('1', new Texture("fonts/size_42_f_white_bg_none/1_42white_bg_none.png"));
            whiteFont.put('\'', new Texture("fonts/size_42_f_white_bg_none/singleQuote_42white_bg_none.png"));
            whiteFont.put('`', new Texture("fonts/size_42_f_white_bg_none/graveAccent_42white_bg_none.png"));
            whiteFont.put('\"', new Texture("fonts/size_42_f_white_bg_none/doubleQuote_42white_bg_none.png"));
        }
        private static void getWhiteGreenTextures(){

            whiteGreenFont.put('?', new Texture("fonts/size_42_f_white_bg_green/questionmark_42white_bg_green.png"));
            whiteGreenFont.put(':', new Texture("fonts/size_42_f_white_bg_green/colon_42white_bg_green.png"));
            whiteGreenFont.put('.', new Texture("fonts/size_42_f_white_bg_green/dot_42white_bg_green.png"));
            whiteGreenFont.put('/', new Texture("fonts/size_42_f_white_bg_green/forwardslash_42white_bg_green.png"));
            whiteGreenFont.put('*', new Texture("fonts/size_42_f_white_bg_green/star_42white_bg_green.png"));
            whiteGreenFont.put('@', new Texture("fonts/size_42_f_white_bg_green/atsign_42white_bg_green.png"));
            whiteGreenFont.put(',', new Texture("fonts/size_42_f_white_bg_green/comma_42white_bg_green.png"));
            whiteGreenFont.put('!', new Texture("fonts/size_42_f_white_bg_green/exclamation_42white_bg_green.png"));
            whiteGreenFont.put(';', new Texture("fonts/size_42_f_white_bg_green/;_42white_bg_green.png"));
            whiteGreenFont.put('|', new Texture("fonts/size_42_f_white_bg_green/|_42white_bg_green.png"));
            whiteGreenFont.put('>', new Texture("fonts/size_42_f_white_bg_green/>_42white_bg_green.png"));
            whiteGreenFont.put('<', new Texture("fonts/size_42_f_white_bg_green/<_42white_bg_green.png"));
            whiteGreenFont.put('}', new Texture("fonts/size_42_f_white_bg_green/}_42white_bg_green.png"));
            whiteGreenFont.put('{', new Texture("fonts/size_42_f_white_bg_green/{_42white_bg_green.png"));
            whiteGreenFont.put(']', new Texture("fonts/size_42_f_white_bg_green/]_42white_bg_green.png"));
            whiteGreenFont.put('[', new Texture("fonts/size_42_f_white_bg_green/[_42white_bg_green.png"));
            whiteGreenFont.put(')', new Texture("fonts/size_42_f_white_bg_green/)_42white_bg_green.png"));
            whiteGreenFont.put('(', new Texture("fonts/size_42_f_white_bg_green/(_42white_bg_green.png"));
            whiteGreenFont.put('^', new Texture("fonts/size_42_f_white_bg_green/^_42white_bg_green.png"));
            whiteGreenFont.put('~', new Texture("fonts/size_42_f_white_bg_green/~_42white_bg_green.png"));
            whiteGreenFont.put('=', new Texture("fonts/size_42_f_white_bg_green/=_42white_bg_green.png"));
            whiteGreenFont.put('-', new Texture("fonts/size_42_f_white_bg_green/-_42white_bg_green.png"));
            whiteGreenFont.put('+', new Texture("fonts/size_42_f_white_bg_green/+_42white_bg_green.png"));
            whiteGreenFont.put('&', new Texture("fonts/size_42_f_white_bg_green/&_42white_bg_green.png"));
            whiteGreenFont.put('%', new Texture("fonts/size_42_f_white_bg_green/%_42white_bg_green.png"));
            whiteGreenFont.put('$', new Texture("fonts/size_42_f_white_bg_green/$_42white_bg_green.png"));
            whiteGreenFont.put('#', new Texture("fonts/size_42_f_white_bg_green/#_42white_bg_green.png"));
            whiteGreenFont.put('z', new Texture("fonts/size_42_f_white_bg_green/z_42white_bg_green.png"));
            whiteGreenFont.put('y', new Texture("fonts/size_42_f_white_bg_green/y_42white_bg_green.png"));
            whiteGreenFont.put('x', new Texture("fonts/size_42_f_white_bg_green/x_42white_bg_green.png"));
            whiteGreenFont.put('w', new Texture("fonts/size_42_f_white_bg_green/w_42white_bg_green.png"));
            whiteGreenFont.put('v', new Texture("fonts/size_42_f_white_bg_green/v_42white_bg_green.png"));
            whiteGreenFont.put('u', new Texture("fonts/size_42_f_white_bg_green/u_42white_bg_green.png"));
            whiteGreenFont.put('t', new Texture("fonts/size_42_f_white_bg_green/t_42white_bg_green.png"));
            whiteGreenFont.put('s', new Texture("fonts/size_42_f_white_bg_green/s_42white_bg_green.png"));
            whiteGreenFont.put('r', new Texture("fonts/size_42_f_white_bg_green/r_42white_bg_green.png"));
            whiteGreenFont.put('q', new Texture("fonts/size_42_f_white_bg_green/q_42white_bg_green.png"));
            whiteGreenFont.put('p', new Texture("fonts/size_42_f_white_bg_green/p_42white_bg_green.png"));
            whiteGreenFont.put('o', new Texture("fonts/size_42_f_white_bg_green/o_42white_bg_green.png"));
            whiteGreenFont.put('n', new Texture("fonts/size_42_f_white_bg_green/n_42white_bg_green.png"));
            whiteGreenFont.put('m', new Texture("fonts/size_42_f_white_bg_green/m_42white_bg_green.png"));
            whiteGreenFont.put('l', new Texture("fonts/size_42_f_white_bg_green/l_42white_bg_green.png"));
            whiteGreenFont.put('k', new Texture("fonts/size_42_f_white_bg_green/k_42white_bg_green.png"));
            whiteGreenFont.put('j', new Texture("fonts/size_42_f_white_bg_green/j_42white_bg_green.png"));
            whiteGreenFont.put('i', new Texture("fonts/size_42_f_white_bg_green/i_42white_bg_green.png"));
            whiteGreenFont.put('h', new Texture("fonts/size_42_f_white_bg_green/h_42white_bg_green.png"));
            whiteGreenFont.put('g', new Texture("fonts/size_42_f_white_bg_green/g_42white_bg_green.png"));
            whiteGreenFont.put('f', new Texture("fonts/size_42_f_white_bg_green/f_42white_bg_green.png"));
            whiteGreenFont.put('e', new Texture("fonts/size_42_f_white_bg_green/e_42white_bg_green.png"));
            whiteGreenFont.put('d', new Texture("fonts/size_42_f_white_bg_green/d_42white_bg_green.png"));
            whiteGreenFont.put('c', new Texture("fonts/size_42_f_white_bg_green/c_42white_bg_green.png"));
            whiteGreenFont.put('b', new Texture("fonts/size_42_f_white_bg_green/b_42white_bg_green.png"));
            whiteGreenFont.put('a', new Texture("fonts/size_42_f_white_bg_green/a_42white_bg_green.png"));
            whiteGreenFont.put('Z', new Texture("fonts/size_42_f_white_bg_green/Z_42white_bg_green.png"));
            whiteGreenFont.put('Y', new Texture("fonts/size_42_f_white_bg_green/Y_42white_bg_green.png"));
            whiteGreenFont.put('X', new Texture("fonts/size_42_f_white_bg_green/X_42white_bg_green.png"));
            whiteGreenFont.put('W', new Texture("fonts/size_42_f_white_bg_green/W_42white_bg_green.png"));
            whiteGreenFont.put('V', new Texture("fonts/size_42_f_white_bg_green/V_42white_bg_green.png"));
            whiteGreenFont.put('U', new Texture("fonts/size_42_f_white_bg_green/U_42white_bg_green.png"));
            whiteGreenFont.put('T', new Texture("fonts/size_42_f_white_bg_green/T_42white_bg_green.png"));
            whiteGreenFont.put('S', new Texture("fonts/size_42_f_white_bg_green/S_42white_bg_green.png"));
            whiteGreenFont.put('R', new Texture("fonts/size_42_f_white_bg_green/R_42white_bg_green.png"));
            whiteGreenFont.put('Q', new Texture("fonts/size_42_f_white_bg_green/Q_42white_bg_green.png"));
            whiteGreenFont.put('P', new Texture("fonts/size_42_f_white_bg_green/P_42white_bg_green.png"));
            whiteGreenFont.put('O', new Texture("fonts/size_42_f_white_bg_green/O_42white_bg_green.png"));
            whiteGreenFont.put('N', new Texture("fonts/size_42_f_white_bg_green/N_42white_bg_green.png"));
            whiteGreenFont.put('M', new Texture("fonts/size_42_f_white_bg_green/M_42white_bg_green.png"));
            whiteGreenFont.put('L', new Texture("fonts/size_42_f_white_bg_green/L_42white_bg_green.png"));
            whiteGreenFont.put('K', new Texture("fonts/size_42_f_white_bg_green/K_42white_bg_green.png"));
            whiteGreenFont.put('J', new Texture("fonts/size_42_f_white_bg_green/J_42white_bg_green.png"));
            whiteGreenFont.put('I', new Texture("fonts/size_42_f_white_bg_green/I_42white_bg_green.png"));
            whiteGreenFont.put('H', new Texture("fonts/size_42_f_white_bg_green/H_42white_bg_green.png"));
            whiteGreenFont.put('G', new Texture("fonts/size_42_f_white_bg_green/G_42white_bg_green.png"));
            whiteGreenFont.put('F', new Texture("fonts/size_42_f_white_bg_green/F_42white_bg_green.png"));
            whiteGreenFont.put('E', new Texture("fonts/size_42_f_white_bg_green/E_42white_bg_green.png"));
            whiteGreenFont.put('D', new Texture("fonts/size_42_f_white_bg_green/D_42white_bg_green.png"));
            whiteGreenFont.put('C', new Texture("fonts/size_42_f_white_bg_green/C_42white_bg_green.png"));
            whiteGreenFont.put('B', new Texture("fonts/size_42_f_white_bg_green/B_42white_bg_green.png"));
            whiteGreenFont.put('A', new Texture("fonts/size_42_f_white_bg_green/A_42white_bg_green.png"));
            whiteGreenFont.put('0', new Texture("fonts/size_42_f_white_bg_green/0_42white_bg_green.png"));
            whiteGreenFont.put('9', new Texture("fonts/size_42_f_white_bg_green/9_42white_bg_green.png"));
            whiteGreenFont.put('8', new Texture("fonts/size_42_f_white_bg_green/8_42white_bg_green.png"));
            whiteGreenFont.put('7', new Texture("fonts/size_42_f_white_bg_green/7_42white_bg_green.png"));
            whiteGreenFont.put('6', new Texture("fonts/size_42_f_white_bg_green/6_42white_bg_green.png"));
            whiteGreenFont.put('5', new Texture("fonts/size_42_f_white_bg_green/5_42white_bg_green.png"));
            whiteGreenFont.put('4', new Texture("fonts/size_42_f_white_bg_green/4_42white_bg_green.png"));
            whiteGreenFont.put('3', new Texture("fonts/size_42_f_white_bg_green/3_42white_bg_green.png"));
            whiteGreenFont.put('2', new Texture("fonts/size_42_f_white_bg_green/2_42white_bg_green.png"));
            whiteGreenFont.put('1', new Texture("fonts/size_42_f_white_bg_green/1_42white_bg_green.png"));
            whiteGreenFont.put('\'', new Texture("fonts/size_42_f_white_bg_green/singleQuote_42white_bg_green.png"));
            whiteGreenFont.put('`', new Texture("fonts/size_42_f_white_bg_green/graveAccent_42white_bg_green.png"));
            whiteGreenFont.put('\"', new Texture("fonts/size_42_f_white_bg_green/doubleQuote_42white_bg_green.png"));
        }
        private static void getWhitePurpleTextures(){

            whitePurpleFont.put('?', new Texture("fonts/size_42_f_white_bg_purple/questionmark_42white_bg_purple.png"));
            whitePurpleFont.put(':', new Texture("fonts/size_42_f_white_bg_purple/colon_42white_bg_purple.png"));
            whitePurpleFont.put('.', new Texture("fonts/size_42_f_white_bg_purple/dot_42white_bg_purple.png"));
            whitePurpleFont.put('/', new Texture("fonts/size_42_f_white_bg_purple/forwardslash_42white_bg_purple.png"));
            whitePurpleFont.put('*', new Texture("fonts/size_42_f_white_bg_purple/star_42white_bg_purple.png"));
            whitePurpleFont.put('@', new Texture("fonts/size_42_f_white_bg_purple/atsign_42white_bg_purple.png"));
            whitePurpleFont.put(',', new Texture("fonts/size_42_f_white_bg_purple/comma_42white_bg_purple.png"));
            whitePurpleFont.put('!', new Texture("fonts/size_42_f_white_bg_purple/exclamation_42white_bg_purple.png"));
            whitePurpleFont.put(';', new Texture("fonts/size_42_f_white_bg_purple/;_42white_bg_purple.png"));
            whitePurpleFont.put('|', new Texture("fonts/size_42_f_white_bg_purple/|_42white_bg_purple.png"));
            whitePurpleFont.put('>', new Texture("fonts/size_42_f_white_bg_purple/>_42white_bg_purple.png"));
            whitePurpleFont.put('<', new Texture("fonts/size_42_f_white_bg_purple/<_42white_bg_purple.png"));
            whitePurpleFont.put('}', new Texture("fonts/size_42_f_white_bg_purple/}_42white_bg_purple.png"));
            whitePurpleFont.put('{', new Texture("fonts/size_42_f_white_bg_purple/{_42white_bg_purple.png"));
            whitePurpleFont.put(']', new Texture("fonts/size_42_f_white_bg_purple/]_42white_bg_purple.png"));
            whitePurpleFont.put('[', new Texture("fonts/size_42_f_white_bg_purple/[_42white_bg_purple.png"));
            whitePurpleFont.put(')', new Texture("fonts/size_42_f_white_bg_purple/)_42white_bg_purple.png"));
            whitePurpleFont.put('(', new Texture("fonts/size_42_f_white_bg_purple/(_42white_bg_purple.png"));
            whitePurpleFont.put('^', new Texture("fonts/size_42_f_white_bg_purple/^_42white_bg_purple.png"));
            whitePurpleFont.put('~', new Texture("fonts/size_42_f_white_bg_purple/~_42white_bg_purple.png"));
            whitePurpleFont.put('=', new Texture("fonts/size_42_f_white_bg_purple/=_42white_bg_purple.png"));
            whitePurpleFont.put('-', new Texture("fonts/size_42_f_white_bg_purple/-_42white_bg_purple.png"));
            whitePurpleFont.put('+', new Texture("fonts/size_42_f_white_bg_purple/+_42white_bg_purple.png"));
            whitePurpleFont.put('&', new Texture("fonts/size_42_f_white_bg_purple/&_42white_bg_purple.png"));
            whitePurpleFont.put('%', new Texture("fonts/size_42_f_white_bg_purple/%_42white_bg_purple.png"));
            whitePurpleFont.put('$', new Texture("fonts/size_42_f_white_bg_purple/$_42white_bg_purple.png"));
            whitePurpleFont.put('#', new Texture("fonts/size_42_f_white_bg_purple/#_42white_bg_purple.png"));
            whitePurpleFont.put('z', new Texture("fonts/size_42_f_white_bg_purple/z_42white_bg_purple.png"));
            whitePurpleFont.put('y', new Texture("fonts/size_42_f_white_bg_purple/y_42white_bg_purple.png"));
            whitePurpleFont.put('x', new Texture("fonts/size_42_f_white_bg_purple/x_42white_bg_purple.png"));
            whitePurpleFont.put('w', new Texture("fonts/size_42_f_white_bg_purple/w_42white_bg_purple.png"));
            whitePurpleFont.put('v', new Texture("fonts/size_42_f_white_bg_purple/v_42white_bg_purple.png"));
            whitePurpleFont.put('u', new Texture("fonts/size_42_f_white_bg_purple/u_42white_bg_purple.png"));
            whitePurpleFont.put('t', new Texture("fonts/size_42_f_white_bg_purple/t_42white_bg_purple.png"));
            whitePurpleFont.put('s', new Texture("fonts/size_42_f_white_bg_purple/s_42white_bg_purple.png"));
            whitePurpleFont.put('r', new Texture("fonts/size_42_f_white_bg_purple/r_42white_bg_purple.png"));
            whitePurpleFont.put('q', new Texture("fonts/size_42_f_white_bg_purple/q_42white_bg_purple.png"));
            whitePurpleFont.put('p', new Texture("fonts/size_42_f_white_bg_purple/p_42white_bg_purple.png"));
            whitePurpleFont.put('o', new Texture("fonts/size_42_f_white_bg_purple/o_42white_bg_purple.png"));
            whitePurpleFont.put('n', new Texture("fonts/size_42_f_white_bg_purple/n_42white_bg_purple.png"));
            whitePurpleFont.put('m', new Texture("fonts/size_42_f_white_bg_purple/m_42white_bg_purple.png"));
            whitePurpleFont.put('l', new Texture("fonts/size_42_f_white_bg_purple/l_42white_bg_purple.png"));
            whitePurpleFont.put('k', new Texture("fonts/size_42_f_white_bg_purple/k_42white_bg_purple.png"));
            whitePurpleFont.put('j', new Texture("fonts/size_42_f_white_bg_purple/j_42white_bg_purple.png"));
            whitePurpleFont.put('i', new Texture("fonts/size_42_f_white_bg_purple/i_42white_bg_purple.png"));
            whitePurpleFont.put('h', new Texture("fonts/size_42_f_white_bg_purple/h_42white_bg_purple.png"));
            whitePurpleFont.put('g', new Texture("fonts/size_42_f_white_bg_purple/g_42white_bg_purple.png"));
            whitePurpleFont.put('f', new Texture("fonts/size_42_f_white_bg_purple/f_42white_bg_purple.png"));
            whitePurpleFont.put('e', new Texture("fonts/size_42_f_white_bg_purple/e_42white_bg_purple.png"));
            whitePurpleFont.put('d', new Texture("fonts/size_42_f_white_bg_purple/d_42white_bg_purple.png"));
            whitePurpleFont.put('c', new Texture("fonts/size_42_f_white_bg_purple/c_42white_bg_purple.png"));
            whitePurpleFont.put('b', new Texture("fonts/size_42_f_white_bg_purple/b_42white_bg_purple.png"));
            whitePurpleFont.put('a', new Texture("fonts/size_42_f_white_bg_purple/a_42white_bg_purple.png"));
            whitePurpleFont.put('Z', new Texture("fonts/size_42_f_white_bg_purple/Z_42white_bg_purple.png"));
            whitePurpleFont.put('Y', new Texture("fonts/size_42_f_white_bg_purple/Y_42white_bg_purple.png"));
            whitePurpleFont.put('X', new Texture("fonts/size_42_f_white_bg_purple/X_42white_bg_purple.png"));
            whitePurpleFont.put('W', new Texture("fonts/size_42_f_white_bg_purple/W_42white_bg_purple.png"));
            whitePurpleFont.put('V', new Texture("fonts/size_42_f_white_bg_purple/V_42white_bg_purple.png"));
            whitePurpleFont.put('U', new Texture("fonts/size_42_f_white_bg_purple/U_42white_bg_purple.png"));
            whitePurpleFont.put('T', new Texture("fonts/size_42_f_white_bg_purple/T_42white_bg_purple.png"));
            whitePurpleFont.put('S', new Texture("fonts/size_42_f_white_bg_purple/S_42white_bg_purple.png"));
            whitePurpleFont.put('R', new Texture("fonts/size_42_f_white_bg_purple/R_42white_bg_purple.png"));
            whitePurpleFont.put('Q', new Texture("fonts/size_42_f_white_bg_purple/Q_42white_bg_purple.png"));
            whitePurpleFont.put('P', new Texture("fonts/size_42_f_white_bg_purple/P_42white_bg_purple.png"));
            whitePurpleFont.put('O', new Texture("fonts/size_42_f_white_bg_purple/O_42white_bg_purple.png"));
            whitePurpleFont.put('N', new Texture("fonts/size_42_f_white_bg_purple/N_42white_bg_purple.png"));
            whitePurpleFont.put('M', new Texture("fonts/size_42_f_white_bg_purple/M_42white_bg_purple.png"));
            whitePurpleFont.put('L', new Texture("fonts/size_42_f_white_bg_purple/L_42white_bg_purple.png"));
            whitePurpleFont.put('K', new Texture("fonts/size_42_f_white_bg_purple/K_42white_bg_purple.png"));
            whitePurpleFont.put('J', new Texture("fonts/size_42_f_white_bg_purple/J_42white_bg_purple.png"));
            whitePurpleFont.put('I', new Texture("fonts/size_42_f_white_bg_purple/I_42white_bg_purple.png"));
            whitePurpleFont.put('H', new Texture("fonts/size_42_f_white_bg_purple/H_42white_bg_purple.png"));
            whitePurpleFont.put('G', new Texture("fonts/size_42_f_white_bg_purple/G_42white_bg_purple.png"));
            whitePurpleFont.put('F', new Texture("fonts/size_42_f_white_bg_purple/F_42white_bg_purple.png"));
            whitePurpleFont.put('E', new Texture("fonts/size_42_f_white_bg_purple/E_42white_bg_purple.png"));
            whitePurpleFont.put('D', new Texture("fonts/size_42_f_white_bg_purple/D_42white_bg_purple.png"));
            whitePurpleFont.put('C', new Texture("fonts/size_42_f_white_bg_purple/C_42white_bg_purple.png"));
            whitePurpleFont.put('B', new Texture("fonts/size_42_f_white_bg_purple/B_42white_bg_purple.png"));
            whitePurpleFont.put('A', new Texture("fonts/size_42_f_white_bg_purple/A_42white_bg_purple.png"));
            whitePurpleFont.put('0', new Texture("fonts/size_42_f_white_bg_purple/0_42white_bg_purple.png"));
            whitePurpleFont.put('9', new Texture("fonts/size_42_f_white_bg_purple/9_42white_bg_purple.png"));
            whitePurpleFont.put('8', new Texture("fonts/size_42_f_white_bg_purple/8_42white_bg_purple.png"));
            whitePurpleFont.put('7', new Texture("fonts/size_42_f_white_bg_purple/7_42white_bg_purple.png"));
            whitePurpleFont.put('6', new Texture("fonts/size_42_f_white_bg_purple/6_42white_bg_purple.png"));
            whitePurpleFont.put('5', new Texture("fonts/size_42_f_white_bg_purple/5_42white_bg_purple.png"));
            whitePurpleFont.put('4', new Texture("fonts/size_42_f_white_bg_purple/4_42white_bg_purple.png"));
            whitePurpleFont.put('3', new Texture("fonts/size_42_f_white_bg_purple/3_42white_bg_purple.png"));
            whitePurpleFont.put('2', new Texture("fonts/size_42_f_white_bg_purple/2_42white_bg_purple.png"));
            whitePurpleFont.put('1', new Texture("fonts/size_42_f_white_bg_purple/1_42white_bg_purple.png"));
            whitePurpleFont.put('\'', new Texture("fonts/size_42_f_white_bg_purple/singleQuote_42white_bg_purple.png"));
            whitePurpleFont.put('`', new Texture("fonts/size_42_f_white_bg_purple/graveAccent_42white_bg_purple.png"));
            whitePurpleFont.put('\"', new Texture("fonts/size_42_f_white_bg_purple/doubleQuote_42white_bg_purple.png"));
        }

        private static void getWhiteRedTextures(){

            whiteRedFont.put('?', new Texture("fonts/size_42_f_white_bg_red/questionmark_42white_bg_red.png"));
            whiteRedFont.put(':', new Texture("fonts/size_42_f_white_bg_red/colon_42white_bg_red.png"));
            whiteRedFont.put('.', new Texture("fonts/size_42_f_white_bg_red/dot_42white_bg_red.png"));
            whiteRedFont.put('/', new Texture("fonts/size_42_f_white_bg_red/forwardslash_42white_bg_red.png"));
            whiteRedFont.put('*', new Texture("fonts/size_42_f_white_bg_red/star_42white_bg_red.png"));
            whiteRedFont.put('@', new Texture("fonts/size_42_f_white_bg_red/atsign_42white_bg_red.png"));
            whiteRedFont.put(',', new Texture("fonts/size_42_f_white_bg_red/comma_42white_bg_red.png"));
            whiteRedFont.put('!', new Texture("fonts/size_42_f_white_bg_red/exclamation_42white_bg_red.png"));
            whiteRedFont.put(';', new Texture("fonts/size_42_f_white_bg_red/;_42white_bg_red.png"));
            whiteRedFont.put('|', new Texture("fonts/size_42_f_white_bg_red/|_42white_bg_red.png"));
            whiteRedFont.put('>', new Texture("fonts/size_42_f_white_bg_red/>_42white_bg_red.png"));
            whiteRedFont.put('<', new Texture("fonts/size_42_f_white_bg_red/<_42white_bg_red.png"));
            whiteRedFont.put('}', new Texture("fonts/size_42_f_white_bg_red/}_42white_bg_red.png"));
            whiteRedFont.put('{', new Texture("fonts/size_42_f_white_bg_red/{_42white_bg_red.png"));
            whiteRedFont.put(']', new Texture("fonts/size_42_f_white_bg_red/]_42white_bg_red.png"));
            whiteRedFont.put('[', new Texture("fonts/size_42_f_white_bg_red/[_42white_bg_red.png"));
            whiteRedFont.put(')', new Texture("fonts/size_42_f_white_bg_red/)_42white_bg_red.png"));
            whiteRedFont.put('(', new Texture("fonts/size_42_f_white_bg_red/(_42white_bg_red.png"));
            whiteRedFont.put('^', new Texture("fonts/size_42_f_white_bg_red/^_42white_bg_red.png"));
            whiteRedFont.put('~', new Texture("fonts/size_42_f_white_bg_red/~_42white_bg_red.png"));
            whiteRedFont.put('=', new Texture("fonts/size_42_f_white_bg_red/=_42white_bg_red.png"));
            whiteRedFont.put('-', new Texture("fonts/size_42_f_white_bg_red/-_42white_bg_red.png"));
            whiteRedFont.put('+', new Texture("fonts/size_42_f_white_bg_red/+_42white_bg_red.png"));
            whiteRedFont.put('&', new Texture("fonts/size_42_f_white_bg_red/&_42white_bg_red.png"));
            whiteRedFont.put('%', new Texture("fonts/size_42_f_white_bg_red/%_42white_bg_red.png"));
            whiteRedFont.put('$', new Texture("fonts/size_42_f_white_bg_red/$_42white_bg_red.png"));
            whiteRedFont.put('#', new Texture("fonts/size_42_f_white_bg_red/#_42white_bg_red.png"));
            whiteRedFont.put('z', new Texture("fonts/size_42_f_white_bg_red/z_42white_bg_red.png"));
            whiteRedFont.put('y', new Texture("fonts/size_42_f_white_bg_red/y_42white_bg_red.png"));
            whiteRedFont.put('x', new Texture("fonts/size_42_f_white_bg_red/x_42white_bg_red.png"));
            whiteRedFont.put('w', new Texture("fonts/size_42_f_white_bg_red/w_42white_bg_red.png"));
            whiteRedFont.put('v', new Texture("fonts/size_42_f_white_bg_red/v_42white_bg_red.png"));
            whiteRedFont.put('u', new Texture("fonts/size_42_f_white_bg_red/u_42white_bg_red.png"));
            whiteRedFont.put('t', new Texture("fonts/size_42_f_white_bg_red/t_42white_bg_red.png"));
            whiteRedFont.put('s', new Texture("fonts/size_42_f_white_bg_red/s_42white_bg_red.png"));
            whiteRedFont.put('r', new Texture("fonts/size_42_f_white_bg_red/r_42white_bg_red.png"));
            whiteRedFont.put('q', new Texture("fonts/size_42_f_white_bg_red/q_42white_bg_red.png"));
            whiteRedFont.put('p', new Texture("fonts/size_42_f_white_bg_red/p_42white_bg_red.png"));
            whiteRedFont.put('o', new Texture("fonts/size_42_f_white_bg_red/o_42white_bg_red.png"));
            whiteRedFont.put('n', new Texture("fonts/size_42_f_white_bg_red/n_42white_bg_red.png"));
            whiteRedFont.put('m', new Texture("fonts/size_42_f_white_bg_red/m_42white_bg_red.png"));
            whiteRedFont.put('l', new Texture("fonts/size_42_f_white_bg_red/l_42white_bg_red.png"));
            whiteRedFont.put('k', new Texture("fonts/size_42_f_white_bg_red/k_42white_bg_red.png"));
            whiteRedFont.put('j', new Texture("fonts/size_42_f_white_bg_red/j_42white_bg_red.png"));
            whiteRedFont.put('i', new Texture("fonts/size_42_f_white_bg_red/i_42white_bg_red.png"));
            whiteRedFont.put('h', new Texture("fonts/size_42_f_white_bg_red/h_42white_bg_red.png"));
            whiteRedFont.put('g', new Texture("fonts/size_42_f_white_bg_red/g_42white_bg_red.png"));
            whiteRedFont.put('f', new Texture("fonts/size_42_f_white_bg_red/f_42white_bg_red.png"));
            whiteRedFont.put('e', new Texture("fonts/size_42_f_white_bg_red/e_42white_bg_red.png"));
            whiteRedFont.put('d', new Texture("fonts/size_42_f_white_bg_red/d_42white_bg_red.png"));
            whiteRedFont.put('c', new Texture("fonts/size_42_f_white_bg_red/c_42white_bg_red.png"));
            whiteRedFont.put('b', new Texture("fonts/size_42_f_white_bg_red/b_42white_bg_red.png"));
            whiteRedFont.put('a', new Texture("fonts/size_42_f_white_bg_red/a_42white_bg_red.png"));
            whiteRedFont.put('Z', new Texture("fonts/size_42_f_white_bg_red/Z_42white_bg_red.png"));
            whiteRedFont.put('Y', new Texture("fonts/size_42_f_white_bg_red/Y_42white_bg_red.png"));
            whiteRedFont.put('X', new Texture("fonts/size_42_f_white_bg_red/X_42white_bg_red.png"));
            whiteRedFont.put('W', new Texture("fonts/size_42_f_white_bg_red/W_42white_bg_red.png"));
            whiteRedFont.put('V', new Texture("fonts/size_42_f_white_bg_red/V_42white_bg_red.png"));
            whiteRedFont.put('U', new Texture("fonts/size_42_f_white_bg_red/U_42white_bg_red.png"));
            whiteRedFont.put('T', new Texture("fonts/size_42_f_white_bg_red/T_42white_bg_red.png"));
            whiteRedFont.put('S', new Texture("fonts/size_42_f_white_bg_red/S_42white_bg_red.png"));
            whiteRedFont.put('R', new Texture("fonts/size_42_f_white_bg_red/R_42white_bg_red.png"));
            whiteRedFont.put('Q', new Texture("fonts/size_42_f_white_bg_red/Q_42white_bg_red.png"));
            whiteRedFont.put('P', new Texture("fonts/size_42_f_white_bg_red/P_42white_bg_red.png"));
            whiteRedFont.put('O', new Texture("fonts/size_42_f_white_bg_red/O_42white_bg_red.png"));
            whiteRedFont.put('N', new Texture("fonts/size_42_f_white_bg_red/N_42white_bg_red.png"));
            whiteRedFont.put('M', new Texture("fonts/size_42_f_white_bg_red/M_42white_bg_red.png"));
            whiteRedFont.put('L', new Texture("fonts/size_42_f_white_bg_red/L_42white_bg_red.png"));
            whiteRedFont.put('K', new Texture("fonts/size_42_f_white_bg_red/K_42white_bg_red.png"));
            whiteRedFont.put('J', new Texture("fonts/size_42_f_white_bg_red/J_42white_bg_red.png"));
            whiteRedFont.put('I', new Texture("fonts/size_42_f_white_bg_red/I_42white_bg_red.png"));
            whiteRedFont.put('H', new Texture("fonts/size_42_f_white_bg_red/H_42white_bg_red.png"));
            whiteRedFont.put('G', new Texture("fonts/size_42_f_white_bg_red/G_42white_bg_red.png"));
            whiteRedFont.put('F', new Texture("fonts/size_42_f_white_bg_red/F_42white_bg_red.png"));
            whiteRedFont.put('E', new Texture("fonts/size_42_f_white_bg_red/E_42white_bg_red.png"));
            whiteRedFont.put('D', new Texture("fonts/size_42_f_white_bg_red/D_42white_bg_red.png"));
            whiteRedFont.put('C', new Texture("fonts/size_42_f_white_bg_red/C_42white_bg_red.png"));
            whiteRedFont.put('B', new Texture("fonts/size_42_f_white_bg_red/B_42white_bg_red.png"));
            whiteRedFont.put('A', new Texture("fonts/size_42_f_white_bg_red/A_42white_bg_red.png"));
            whiteRedFont.put('0', new Texture("fonts/size_42_f_white_bg_red/0_42white_bg_red.png"));
            whiteRedFont.put('9', new Texture("fonts/size_42_f_white_bg_red/9_42white_bg_red.png"));
            whiteRedFont.put('8', new Texture("fonts/size_42_f_white_bg_red/8_42white_bg_red.png"));
            whiteRedFont.put('7', new Texture("fonts/size_42_f_white_bg_red/7_42white_bg_red.png"));
            whiteRedFont.put('6', new Texture("fonts/size_42_f_white_bg_red/6_42white_bg_red.png"));
            whiteRedFont.put('5', new Texture("fonts/size_42_f_white_bg_red/5_42white_bg_red.png"));
            whiteRedFont.put('4', new Texture("fonts/size_42_f_white_bg_red/4_42white_bg_red.png"));
            whiteRedFont.put('3', new Texture("fonts/size_42_f_white_bg_red/3_42white_bg_red.png"));
            whiteRedFont.put('2', new Texture("fonts/size_42_f_white_bg_red/2_42white_bg_red.png"));
            whiteRedFont.put('1', new Texture("fonts/size_42_f_white_bg_red/1_42white_bg_red.png"));
            whiteRedFont.put('\'', new Texture("fonts/size_42_f_white_bg_red/singleQuote_42white_bg_red.png"));
            whiteRedFont.put('`', new Texture("fonts/size_42_f_white_bg_red/graveAccent_42white_bg_red.png"));
            whiteRedFont.put('\"', new Texture("fonts/size_42_f_white_bg_red/doubleQuote_42white_bg_red.png"));
        }

        private static void getBlackYellowTextures(){

            blackYellowFont.put('?', new Texture("fonts/size_42_f_black_bg_yellow/questionmark_42black_bg_yellow.png"));
            blackYellowFont.put(':', new Texture("fonts/size_42_f_black_bg_yellow/colon_42black_bg_yellow.png"));
            blackYellowFont.put('.', new Texture("fonts/size_42_f_black_bg_yellow/dot_42black_bg_yellow.png"));
            blackYellowFont.put('/', new Texture("fonts/size_42_f_black_bg_yellow/forwardslash_42black_bg_yellow.png"));
            blackYellowFont.put('*', new Texture("fonts/size_42_f_black_bg_yellow/star_42black_bg_yellow.png"));
            blackYellowFont.put('@', new Texture("fonts/size_42_f_black_bg_yellow/atsign_42black_bg_yellow.png"));
            blackYellowFont.put(',', new Texture("fonts/size_42_f_black_bg_yellow/comma_42black_bg_yellow.png"));
            blackYellowFont.put('!', new Texture("fonts/size_42_f_black_bg_yellow/exclamation_42black_bg_yellow.png"));
            blackYellowFont.put(';', new Texture("fonts/size_42_f_black_bg_yellow/;_42black_bg_yellow.png"));
            blackYellowFont.put('|', new Texture("fonts/size_42_f_black_bg_yellow/|_42black_bg_yellow.png"));
            blackYellowFont.put('>', new Texture("fonts/size_42_f_black_bg_yellow/>_42black_bg_yellow.png"));
            blackYellowFont.put('<', new Texture("fonts/size_42_f_black_bg_yellow/<_42black_bg_yellow.png"));
            blackYellowFont.put('}', new Texture("fonts/size_42_f_black_bg_yellow/}_42black_bg_yellow.png"));
            blackYellowFont.put('{', new Texture("fonts/size_42_f_black_bg_yellow/{_42black_bg_yellow.png"));
            blackYellowFont.put(']', new Texture("fonts/size_42_f_black_bg_yellow/]_42black_bg_yellow.png"));
            blackYellowFont.put('[', new Texture("fonts/size_42_f_black_bg_yellow/[_42black_bg_yellow.png"));
            blackYellowFont.put(')', new Texture("fonts/size_42_f_black_bg_yellow/)_42black_bg_yellow.png"));
            blackYellowFont.put('(', new Texture("fonts/size_42_f_black_bg_yellow/(_42black_bg_yellow.png"));
            blackYellowFont.put('^', new Texture("fonts/size_42_f_black_bg_yellow/^_42black_bg_yellow.png"));
            blackYellowFont.put('~', new Texture("fonts/size_42_f_black_bg_yellow/~_42black_bg_yellow.png"));
            blackYellowFont.put('=', new Texture("fonts/size_42_f_black_bg_yellow/=_42black_bg_yellow.png"));
            blackYellowFont.put('-', new Texture("fonts/size_42_f_black_bg_yellow/-_42black_bg_yellow.png"));
            blackYellowFont.put('+', new Texture("fonts/size_42_f_black_bg_yellow/+_42black_bg_yellow.png"));
            blackYellowFont.put('&', new Texture("fonts/size_42_f_black_bg_yellow/&_42black_bg_yellow.png"));
            blackYellowFont.put('%', new Texture("fonts/size_42_f_black_bg_yellow/%_42black_bg_yellow.png"));
            blackYellowFont.put('$', new Texture("fonts/size_42_f_black_bg_yellow/$_42black_bg_yellow.png"));
            blackYellowFont.put('#', new Texture("fonts/size_42_f_black_bg_yellow/#_42black_bg_yellow.png"));
            blackYellowFont.put('z', new Texture("fonts/size_42_f_black_bg_yellow/z_42black_bg_yellow.png"));
            blackYellowFont.put('y', new Texture("fonts/size_42_f_black_bg_yellow/y_42black_bg_yellow.png"));
            blackYellowFont.put('x', new Texture("fonts/size_42_f_black_bg_yellow/x_42black_bg_yellow.png"));
            blackYellowFont.put('w', new Texture("fonts/size_42_f_black_bg_yellow/w_42black_bg_yellow.png"));
            blackYellowFont.put('v', new Texture("fonts/size_42_f_black_bg_yellow/v_42black_bg_yellow.png"));
            blackYellowFont.put('u', new Texture("fonts/size_42_f_black_bg_yellow/u_42black_bg_yellow.png"));
            blackYellowFont.put('t', new Texture("fonts/size_42_f_black_bg_yellow/t_42black_bg_yellow.png"));
            blackYellowFont.put('s', new Texture("fonts/size_42_f_black_bg_yellow/s_42black_bg_yellow.png"));
            blackYellowFont.put('r', new Texture("fonts/size_42_f_black_bg_yellow/r_42black_bg_yellow.png"));
            blackYellowFont.put('q', new Texture("fonts/size_42_f_black_bg_yellow/q_42black_bg_yellow.png"));
            blackYellowFont.put('p', new Texture("fonts/size_42_f_black_bg_yellow/p_42black_bg_yellow.png"));
            blackYellowFont.put('o', new Texture("fonts/size_42_f_black_bg_yellow/o_42black_bg_yellow.png"));
            blackYellowFont.put('n', new Texture("fonts/size_42_f_black_bg_yellow/n_42black_bg_yellow.png"));
            blackYellowFont.put('m', new Texture("fonts/size_42_f_black_bg_yellow/m_42black_bg_yellow.png"));
            blackYellowFont.put('l', new Texture("fonts/size_42_f_black_bg_yellow/l_42black_bg_yellow.png"));
            blackYellowFont.put('k', new Texture("fonts/size_42_f_black_bg_yellow/k_42black_bg_yellow.png"));
            blackYellowFont.put('j', new Texture("fonts/size_42_f_black_bg_yellow/j_42black_bg_yellow.png"));
            blackYellowFont.put('i', new Texture("fonts/size_42_f_black_bg_yellow/i_42black_bg_yellow.png"));
            blackYellowFont.put('h', new Texture("fonts/size_42_f_black_bg_yellow/h_42black_bg_yellow.png"));
            blackYellowFont.put('g', new Texture("fonts/size_42_f_black_bg_yellow/g_42black_bg_yellow.png"));
            blackYellowFont.put('f', new Texture("fonts/size_42_f_black_bg_yellow/f_42black_bg_yellow.png"));
            blackYellowFont.put('e', new Texture("fonts/size_42_f_black_bg_yellow/e_42black_bg_yellow.png"));
            blackYellowFont.put('d', new Texture("fonts/size_42_f_black_bg_yellow/d_42black_bg_yellow.png"));
            blackYellowFont.put('c', new Texture("fonts/size_42_f_black_bg_yellow/c_42black_bg_yellow.png"));
            blackYellowFont.put('b', new Texture("fonts/size_42_f_black_bg_yellow/b_42black_bg_yellow.png"));
            blackYellowFont.put('a', new Texture("fonts/size_42_f_black_bg_yellow/a_42black_bg_yellow.png"));
            blackYellowFont.put('Z', new Texture("fonts/size_42_f_black_bg_yellow/Z_42black_bg_yellow.png"));
            blackYellowFont.put('Y', new Texture("fonts/size_42_f_black_bg_yellow/Y_42black_bg_yellow.png"));
            blackYellowFont.put('X', new Texture("fonts/size_42_f_black_bg_yellow/X_42black_bg_yellow.png"));
            blackYellowFont.put('W', new Texture("fonts/size_42_f_black_bg_yellow/W_42black_bg_yellow.png"));
            blackYellowFont.put('V', new Texture("fonts/size_42_f_black_bg_yellow/V_42black_bg_yellow.png"));
            blackYellowFont.put('U', new Texture("fonts/size_42_f_black_bg_yellow/U_42black_bg_yellow.png"));
            blackYellowFont.put('T', new Texture("fonts/size_42_f_black_bg_yellow/T_42black_bg_yellow.png"));
            blackYellowFont.put('S', new Texture("fonts/size_42_f_black_bg_yellow/S_42black_bg_yellow.png"));
            blackYellowFont.put('R', new Texture("fonts/size_42_f_black_bg_yellow/R_42black_bg_yellow.png"));
            blackYellowFont.put('Q', new Texture("fonts/size_42_f_black_bg_yellow/Q_42black_bg_yellow.png"));
            blackYellowFont.put('P', new Texture("fonts/size_42_f_black_bg_yellow/P_42black_bg_yellow.png"));
            blackYellowFont.put('O', new Texture("fonts/size_42_f_black_bg_yellow/O_42black_bg_yellow.png"));
            blackYellowFont.put('N', new Texture("fonts/size_42_f_black_bg_yellow/N_42black_bg_yellow.png"));
            blackYellowFont.put('M', new Texture("fonts/size_42_f_black_bg_yellow/M_42black_bg_yellow.png"));
            blackYellowFont.put('L', new Texture("fonts/size_42_f_black_bg_yellow/L_42black_bg_yellow.png"));
            blackYellowFont.put('K', new Texture("fonts/size_42_f_black_bg_yellow/K_42black_bg_yellow.png"));
            blackYellowFont.put('J', new Texture("fonts/size_42_f_black_bg_yellow/J_42black_bg_yellow.png"));
            blackYellowFont.put('I', new Texture("fonts/size_42_f_black_bg_yellow/I_42black_bg_yellow.png"));
            blackYellowFont.put('H', new Texture("fonts/size_42_f_black_bg_yellow/H_42black_bg_yellow.png"));
            blackYellowFont.put('G', new Texture("fonts/size_42_f_black_bg_yellow/G_42black_bg_yellow.png"));
            blackYellowFont.put('F', new Texture("fonts/size_42_f_black_bg_yellow/F_42black_bg_yellow.png"));
            blackYellowFont.put('E', new Texture("fonts/size_42_f_black_bg_yellow/E_42black_bg_yellow.png"));
            blackYellowFont.put('D', new Texture("fonts/size_42_f_black_bg_yellow/D_42black_bg_yellow.png"));
            blackYellowFont.put('C', new Texture("fonts/size_42_f_black_bg_yellow/C_42black_bg_yellow.png"));
            blackYellowFont.put('B', new Texture("fonts/size_42_f_black_bg_yellow/B_42black_bg_yellow.png"));
            blackYellowFont.put('A', new Texture("fonts/size_42_f_black_bg_yellow/A_42black_bg_yellow.png"));
            blackYellowFont.put('0', new Texture("fonts/size_42_f_black_bg_yellow/0_42black_bg_yellow.png"));
            blackYellowFont.put('9', new Texture("fonts/size_42_f_black_bg_yellow/9_42black_bg_yellow.png"));
            blackYellowFont.put('8', new Texture("fonts/size_42_f_black_bg_yellow/8_42black_bg_yellow.png"));
            blackYellowFont.put('7', new Texture("fonts/size_42_f_black_bg_yellow/7_42black_bg_yellow.png"));
            blackYellowFont.put('6', new Texture("fonts/size_42_f_black_bg_yellow/6_42black_bg_yellow.png"));
            blackYellowFont.put('5', new Texture("fonts/size_42_f_black_bg_yellow/5_42black_bg_yellow.png"));
            blackYellowFont.put('4', new Texture("fonts/size_42_f_black_bg_yellow/4_42black_bg_yellow.png"));
            blackYellowFont.put('3', new Texture("fonts/size_42_f_black_bg_yellow/3_42black_bg_yellow.png"));
            blackYellowFont.put('2', new Texture("fonts/size_42_f_black_bg_yellow/2_42black_bg_yellow.png"));
            blackYellowFont.put('1', new Texture("fonts/size_42_f_black_bg_yellow/1_42black_bg_yellow.png"));
            blackYellowFont.put('\'', new Texture("fonts/size_42_f_black_bg_yellow/singleQuote_42black_bg_yellow.png"));
            blackYellowFont.put('`', new Texture("fonts/size_42_f_black_bg_yellow/graveAccent_42black_bg_yellow.png"));
            blackYellowFont.put('\"', new Texture("fonts/size_42_f_black_bg_yellow/doubleQuote_42black_bg_yellow.png"));
        }
    }
}
