package com.vicmikhailau.masktext;

public class MaskEvent {
    //region FIELDS
    private final Character character;
    private final int index;
    private final IMaskCharacter before;
    private final IMaskCharacter actual;
    private final IMaskCharacter next;
    //endregion

    //region CONSTRUCTORS
    public MaskEvent(Character character, int index, IMaskCharacter before,
                     IMaskCharacter actual, IMaskCharacter next) {
        this.character = character;
        this.index = index;
        this.before = before;
        this.actual = actual;
        this.next = next;
    }
    //endregion

    //region METHODS

    //region GETTER AND SETTER METHODS
    public Character getCharacter() {
        return character;
    }

    public int getIndex() {
        return index;
    }

    public IMaskCharacter getActual() {
        return actual;
    }

    public IMaskCharacter getBefore() {
        return before;
    }

    public IMaskCharacter getNext() {
        return next;
    }
    //endregion

    //region PUBLIC METHODS
    public int getBeforeIndex() {
        return index - 1;
    }

    public int getNextIndex() {
        return index + 1;
    }

    public boolean hasBefore() {
        return before != null;
    }

    public boolean hasNext() {
        return next != null;
    }
    //endregion

    //endregion
}
