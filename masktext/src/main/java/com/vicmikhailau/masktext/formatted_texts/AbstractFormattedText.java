package com.vicmikhailau.masktext.formatted_texts;

import android.support.annotation.NonNull;

import com.vicmikhailau.masktext.IFormattedText;
import com.vicmikhailau.masktext.IMaskCharacter;
import com.vicmikhailau.masktext.Mask;

/**
 * Created by lucas on 02/04/2018.
 */

public abstract class AbstractFormattedText implements IFormattedText {
    //region FIELDS
    private final Mask mMask;
    private final String mRawString;
    private String mFormattedString;
    private String mUnmaskedString;
    //endregion

    //region CONSTRUCTORS
    public AbstractFormattedText(Mask mask, String rawString) {
        mMask = mask;
        mRawString = rawString;
    }
    //endregion

    //region METHODS

    //region ABSTRACT METHODS
    protected abstract String formatString(String str);

    protected abstract String buildUnMaskedString(String str);
    //endregion

    //region OVERRIDE METHODS
    @Override
    public Mask getMask() {
        return mMask;
    }

    @Override
    public String getRawString() {
        return mRawString;
    }

    @Override
    public String getUnMaskedString() {
        if (mUnmaskedString == null) mUnmaskedString = buildUnMaskedString(getRawString());
        return mUnmaskedString;
    }

    @Override
    public boolean isValid() {
        String str = getUnMaskedString();
        int strIndex = 0;
        int maskCharIndex = 0;
        char stringCharacter;

        IMaskCharacter maskCharacter;
        while (strIndex < str.length() && maskCharIndex < getMask().size()) {
            maskCharacter = getMask().get(maskCharIndex);

            stringCharacter = str.charAt(strIndex);

            if (maskCharacter.isValidCharacter(stringCharacter)) {
                strIndex++;
            } else if (!maskCharacter.isPrepopulate()) {
                return false;
            }
            maskCharIndex++;
        }
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        if (mFormattedString == null) mFormattedString = formatString(getRawString());
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
