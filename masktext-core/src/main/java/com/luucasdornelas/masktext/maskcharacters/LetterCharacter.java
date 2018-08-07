package com.luucasdornelas.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class LetterCharacter extends AbstractMaskCharacter {

    //region CONSTRUCTORS
    public LetterCharacter(Character representation) {
        super(representation);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return Character.isLetter(ch);
    }
    //endregion

    //endregion
}
