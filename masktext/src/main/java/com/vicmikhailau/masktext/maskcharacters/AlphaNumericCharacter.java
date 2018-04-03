package com.vicmikhailau.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class AlphaNumericCharacter extends AbstractMaskCharacter {
    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLetterOrDigit(ch);
    }
    //endregion

    //endregion
}
