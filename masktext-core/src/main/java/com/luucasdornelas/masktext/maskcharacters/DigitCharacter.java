package com.luucasdornelas.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class DigitCharacter extends AbstractMaskCharacter {
    //region CONSTRUCTORS
    public DigitCharacter(Character representation) {
        super(representation);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isDigit(ch);
    }
    //endregion

    //endregion
}
