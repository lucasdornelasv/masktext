package com.vicmikhailau.masktext.maskcharacters;


import com.vicmikhailau.masktext.IMaskCharacter;

/**
 * Created by lucas on 02/04/2018.
 */

public abstract class AbstractMaskCharacter implements IMaskCharacter {
    //region METHODS

    //region OVERRIDE METHODS
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
