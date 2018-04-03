package com.vicmikhailau.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class UpperCaseCharacter extends AbstractMaskCharacter {
    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isUpperCase(ch);
    }

    @Override
    public char processCharacter(char ch) {
        return Character.toUpperCase(ch);
    }
    //endregion

    //endregion
}
