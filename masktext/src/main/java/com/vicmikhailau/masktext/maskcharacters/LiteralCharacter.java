package com.vicmikhailau.masktext.maskcharacters;

/**
 * Created by lucas on 02/04/2018.
 */

public class LiteralCharacter extends AbstractMaskCharacter {
    //region FIELDS
    private final boolean isPrepopulate;
    //endregion

    //region CONSTRUCTORS
    public LiteralCharacter(Character representation, boolean isPrepopulate) {
        super(representation);
        this.isPrepopulate = isPrepopulate;
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isValidCharacter(char ch) {
        return !isPrepopulate || getRepresentation() == ch;
    }

    @Override
    public char processCharacter(char ch) {
        return isPrepopulate ? getRepresentation() : super.processCharacter(ch);
    }

    @Override
    public boolean isPrepopulate() {
        return isPrepopulate;
    }
    //endregion

    //endregion
}
