package com.vicmikhailau.masktext;

/**
 * Created by lucas on 02/04/2018.
 */

public interface IMaskCharacter {

    char getRepresentation();

    boolean isValidCharacter(char ch);

    char processCharacter(char ch);

    boolean isPrepopulate();

}
