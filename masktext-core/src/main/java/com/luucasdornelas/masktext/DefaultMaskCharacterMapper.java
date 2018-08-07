package com.luucasdornelas.masktext;

import com.luucasdornelas.masktext.maskcharacters.AlphaNumericCharacter;
import com.luucasdornelas.masktext.maskcharacters.DigitCharacter;
import com.luucasdornelas.masktext.maskcharacters.HexCharacter;
import com.luucasdornelas.masktext.maskcharacters.LetterCharacter;
import com.luucasdornelas.masktext.maskcharacters.LiteralCharacter;
import com.luucasdornelas.masktext.maskcharacters.LowerCaseCharacter;
import com.luucasdornelas.masktext.maskcharacters.UpperCaseCharacter;

/**
 * Created by lucas on 02/04/2018.
 */

public class DefaultMaskCharacterMapper implements IMaskCharacterMapper {
    //region FIELDS
    private static final Character ANYTHING_KEY = '*';
    private static final Character DIGIT_KEY = '#';
    private static final Character UPPERCASE_KEY = 'U';
    private static final Character LOWERCASE_KEY = 'L';
    private static final Character ALPHA_NUMERIC_KEY = 'A';
    private static final Character CHARACTER_KEY = '?';
    private static final Character HEX_KEY = 'H';
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public IMaskCharacter map(char ch) {
        if (ch == ANYTHING_KEY) return new LiteralCharacter(ANYTHING_KEY, false);
        if (ch == DIGIT_KEY) return new DigitCharacter(DIGIT_KEY);
        if (ch == UPPERCASE_KEY) return new UpperCaseCharacter(UPPERCASE_KEY);
        if (ch == LOWERCASE_KEY) return new LowerCaseCharacter(LOWERCASE_KEY);
        if (ch == ALPHA_NUMERIC_KEY) return new AlphaNumericCharacter(ALPHA_NUMERIC_KEY);
        if (ch == CHARACTER_KEY) return new LetterCharacter(CHARACTER_KEY);
        if (ch == HEX_KEY) return new HexCharacter(HEX_KEY);
        return new LiteralCharacter(ch, true);
    }
    //endregion

    //endregion
}
