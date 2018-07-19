package com.vicmikhailau.masktext.maskcharacters;


import com.vicmikhailau.masktext.IMaskCharacter;

/**
 * Created by lucas on 02/04/2018.
 */

public abstract class AbstractMaskCharacter implements IMaskCharacter {
    //region FIELDS
    private final Character representation;
    //endregion

    //region CONSTRUCTORS
    public AbstractMaskCharacter(Character representation) {
        this.representation = representation;
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public char getRepresentation() {
        return representation;
    }

    @Override
    public char processCharacter(char ch) {
        return ch;
    }

    @Override
    public boolean isPrepopulate() {
        return false;
    }
    //endregion

    //endregion
}
