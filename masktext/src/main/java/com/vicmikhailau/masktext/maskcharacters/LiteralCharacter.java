package com.vicmikhailau.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class LiteralCharacter extends AbstractMaskCharacter {
    //region FIELDS
    private Character character;
    //endregion

    //region CONSTRUCTORS
    public LiteralCharacter() {
        this(null);
    }

    public LiteralCharacter(Character character) {
        this.character = character;
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return character == null || character == ch;
    }

    @Override
    public char processCharacter(char ch) {
        return character != null ? character : super.processCharacter(ch);
    }

    @Override
    public boolean isPrepopulate() {
        return character != null || super.isPrepopulate();
    }
    //endregion

    //endregion
}
