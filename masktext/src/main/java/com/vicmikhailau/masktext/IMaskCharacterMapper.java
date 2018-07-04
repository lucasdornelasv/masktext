package com.vicmikhailau.masktext;

/**
 * Created by lucas on 02/04/2018.
 */

public interface IMaskCharacterMapper {

    IMaskCharacter map(char ch);

    char map(IMaskCharacter maskCharacter);

}
