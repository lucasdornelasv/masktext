package com.luucasdornelas.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class LowerCaseCharacter extends AbstractMaskCharacter {
    //region CONSTRUCTORS
    public LowerCaseCharacter(Character representation) {
        super(representation);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLowerCase(ch);
    }

    @Override
    public char processCharacter(char ch) {
        return Character.toLowerCase(ch);
    }
    //endregion

    //endregion
}
