package com.vicmikhailau.masktext.formatted_texts;

import android.support.annotation.NonNull;

import com.vicmikhailau.masktext.IFormattedText;
import com.vicmikhailau.masktext.IMask;
import com.vicmikhailau.masktext.IMaskCharacter;
import com.vicmikhailau.masktext.Mask;

/**
 * Created by lucas on 02/04/2018.
 */

public abstract class AbstractFormattedText implements IFormattedText {
    //region FIELDS
    private final IMask mMask;
    private final CharSequence rawText;
    private String mFormattedString;
    private String mUnmaskedString;
    //endregion

    //region CONSTRUCTORS
    public AbstractFormattedText(IMask mask, CharSequence rawText) {
        mMask = mask;
        this.rawText = rawText;
    }
    //endregion

    //region METHODS

    //region ABSTRACT METHODS
    protected abstract CharSequence formatText(CharSequence str);

    protected abstract CharSequence buildUnMaskedText(CharSequence str);
    //endregion

    //region OVERRIDE METHODS
    @Override
    public IMask getMask() {
        return mMask;
    }

    @Override
    public String getRawString() {
        return rawText.toString();
    }

    @Override
    public CharSequence getRawText() {
        return rawText;
    }

    @Override
    public String getUnMaskedString() {
        if (mUnmaskedString == null) mUnmaskedString = buildUnMaskedText(rawText).toString();
        return mUnmaskedString;
    }

    @Override
    public boolean isValid() {
        return getMask().isValid(rawText);
    }

    @NonNull
    @Override
    public String toString() {
        if (mFormattedString == null) mFormattedString = formatText(getRawText()).toString();
        return mFormattedString;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }
    //endregion

    //endregion
}
