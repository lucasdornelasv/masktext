package com.luucasdornelas.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class UpperCaseCharacter extends AbstractMaskCharacter {
    //region CONSTRUCTORS
    public UpperCaseCharacter(Character representation) {
        super(representation);
    }
    //endregion

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
