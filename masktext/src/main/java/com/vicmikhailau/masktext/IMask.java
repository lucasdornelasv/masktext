package com.vicmikhailau.masktext;

public interface IMask {

    String toString();

    boolean isValid(CharSequence text);

    String formatString(CharSequence text);

    String unmaskString(CharSequence text);

    IMaskCharacter get(int index);

    int size();

    String getFormatString();

    boolean isValidPrepopulateCharacter(char ch, int at);

    void setOnMaskCharacterListener(OnMaskCharacterListener maskCharacterListener);

}
