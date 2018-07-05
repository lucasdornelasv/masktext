package com.vicmikhailau.masktext.formatted_texts;

import com.vicmikhailau.masktext.IMaskCharacter;
import com.vicmikhailau.masktext.Mask;

/**
 * Created by lucas on 02/04/2018.
 */

public class DefaultFormattedText extends AbstractFormattedText {
    //region CONSTRUCTORS
    public DefaultFormattedText(Mask mask, String rawString) {
        super(mask, rawString);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    protected String formatString(String str) {
        StringBuilder builder = new StringBuilder();

        int strIndex = 0;
        int maskCharIndex = 0;
        char stringCharacter;

        IMaskCharacter maskCharacter;
        while (strIndex < str.length() && maskCharIndex < getMask().size()) {
            maskCharacter = getMask().get(maskCharIndex);
            stringCharacter = str.charAt(strIndex);

            if (maskCharacter.isValidCharacter(stringCharacter)) {
                builder.append(maskCharacter.processCharacter(stringCharacter));
                strIndex++;
                maskCharIndex++;
            } else if (maskCharacter.isPrepopulate()) {
                builder.append(maskCharacter.processCharacter(stringCharacter));
                maskCharIndex++;
            } else {
                strIndex++;
            }
        }

        return builder.toString();
    }

    @Override
    protected String buildUnMaskedString(String str) {
        StringBuilder builder = new StringBuilder();
        int inputLen = Math.min(getMask().size(), str.length());
        char ch;
        for (int i = 0; i < inputLen; i++) {
            ch = str.charAt(i);
            if (!getMask().isValidPrepopulateCharacter(ch, i))
                builder.append(ch);
        }
        return builder.toString();
    }
    //endregion

    //endregion
}
