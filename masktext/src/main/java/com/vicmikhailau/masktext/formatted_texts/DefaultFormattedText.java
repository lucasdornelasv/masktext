package com.vicmikhailau.masktext.formatted_texts;

import com.vicmikhailau.masktext.IMask;
import com.vicmikhailau.masktext.IMaskCharacter;
import com.vicmikhailau.masktext.Mask;

/**
 * Created by lucas on 02/04/2018.
 */

public class DefaultFormattedText extends AbstractFormattedText {
    //region CONSTRUCTORS
    public DefaultFormattedText(IMask mask, CharSequence rawText) {
        super(mask, rawText);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    protected String formatString(CharSequence text) {
        return getMask().formatString(text);
    }

    @Override
    protected String buildUnMaskedString(CharSequence text) {
        return getMask().unmaskString(text);
    }
    //endregion

    //endregion
}
