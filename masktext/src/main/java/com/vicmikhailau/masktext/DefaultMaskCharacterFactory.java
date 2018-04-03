package com.vicmikhailau.masktext;

import com.vicmikhailau.masktext.maskcharacters.AlphaNumericCharacter;
import com.vicmikhailau.masktext.maskcharacters.DigitCharacter;
import com.vicmikhailau.masktext.maskcharacters.HexCharacter;
import com.vicmikhailau.masktext.maskcharacters.LetterCharacter;
import com.vicmikhailau.masktext.maskcharacters.LiteralCharacter;
import com.vicmikhailau.masktext.maskcharacters.LowerCaseCharacter;
import com.vicmikhailau.masktext.maskcharacters.UpperCaseCharacter;

/**
 * Created by lucas on 02/04/2018.
 */

public class DefaultMaskCharacterFactory implements IMaskCharacterFactory {
    //region FIELDS
    private static final char ANYTHING_KEY = '*';
    private static final char DIGIT_KEY = '#';
    private static final char UPPERCASE_KEY = 'U';
    private static final char LOWERCASE_KEY = 'L';
    private static final char ALPHA_NUMERIC_KEY = 'A';
    private static final char CHARACTER_KEY = '?';
    private static final char HEX_KEY = 'H';
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public IMaskCharacter getMaskCharacter(char ch) {
        IMaskCharacter maskCharacter;
        switch (ch) {
            case ANYTHING_KEY:
                maskCharacter = new LiteralCharacter();
                break;
            case DIGIT_KEY:
                maskCharacter = new DigitCharacter();
                break;
            case UPPERCASE_KEY:
                maskCharacter = new UpperCaseCharacter();
                break;
            case LOWERCASE_KEY:
                maskCharacter = new LowerCaseCharacter();
                break;
            case ALPHA_NUMERIC_KEY:
                maskCharacter = new AlphaNumericCharacter();
                break;
            case CHARACTER_KEY:
                maskCharacter = new LetterCharacter();
                break;
            case HEX_KEY:
                maskCharacter = new HexCharacter();
                break;
            default: {
                maskCharacter = new LiteralCharacter(ch);
            }

        }
        return maskCharacter;
    }
    //endregion

    //endregion
}
