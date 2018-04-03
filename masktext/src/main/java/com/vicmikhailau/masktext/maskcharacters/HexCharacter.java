package com.vicmikhailau.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class HexCharacter extends AbstractMaskCharacter {
    //region FIELDS
    private static final String HEX_CHARS = "0123456789ABCDEF";
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLetterOrDigit(ch) && HEX_CHARS.indexOf(Character.toUpperCase(ch)) != -1;
    }

    @Override
    public char processCharacter(char ch) {
        return Character.toUpperCase(ch);
    }
    //endregion

    //endregion
}
