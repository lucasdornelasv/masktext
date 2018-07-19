package com.vicmikhailau.masktext;


import java.util.ArrayList;
import java.util.List;


public class Mask implements IMask {
    //region FIELDS
    private final String mRawMaskString;
    private final IMaskCharacterMapper maskCharacterMapper;
    private List<IMaskCharacter> mMaskCharacters;
    private List<IMaskCharacter> mPrepopulateCharacters;

    private OnMaskCharacterListener maskCharacterListener;
    //endregion

    //region CONSTRUCTORS
    public Mask(String fmtString, IMaskCharacterMapper maskCharacterMapper) {
        this.mRawMaskString = fmtString;
        this.maskCharacterMapper = maskCharacterMapper;

        init();
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public String toString() {
        return getFormatString();
    }

    @Override
    public boolean isValid(CharSequence text) {
        int strIndex = 0;
        int maskCharIndex = 0;
        char stringCharacter;

        IMaskCharacter maskCharacter;
        while (strIndex < text.length() && maskCharIndex < size()) {
            maskCharacter = get(maskCharIndex);
            stringCharacter = text.charAt(strIndex);

            if (maskCharacter.isValidCharacter(stringCharacter)) {
                strIndex++;
            } else if (!maskCharacter.isPrepopulate()) {
                return false;
            }
            maskCharIndex++;
        }
        return true;
    }

    @Override
    public CharSequence formatText(CharSequence text) {
        final StringBuilder builder = new StringBuilder();

        int strIndex = 0;
        int maskCharIndex = 0;
        char stringCharacter;

        if (strIndex < text.length()) {
            IMaskCharacter maskCharacterBefore;
            IMaskCharacter maskCharacterNext = null;
            IMaskCharacter maskCharacterActual = null;

            while (maskCharIndex < size()) {
                maskCharacterBefore = maskCharacterActual;
                maskCharacterActual = maskCharacterNext == null ? get(maskCharIndex) : maskCharacterNext;
                maskCharacterNext = get(maskCharIndex + 1);
                do {
                    stringCharacter = text.charAt(strIndex);

                    if (maskCharacterListener != null) {
                        maskCharacterListener.onMaskCharacter(new MaskEvent(
                                stringCharacter, maskCharIndex,
                                maskCharacterBefore, maskCharacterActual, maskCharacterNext
                        ));
                    }

                    if (maskCharacterActual.isValidCharacter(stringCharacter)) {
                        builder.append(maskCharacterActual.processCharacter(stringCharacter));
                        strIndex++;
                        maskCharIndex++;
                        break;
                    } else if (maskCharacterActual.isPrepopulate()) {
                        builder.append(maskCharacterActual.processCharacter(stringCharacter));
                        maskCharIndex++;
                        break;
                    } else {
                        strIndex++;
                    }
                } while (strIndex < text.length());
            }
        }

        return builder;
    }

    @Override
    public CharSequence unmaskText(CharSequence text) {
        final StringBuilder builder = new StringBuilder();
        int inputLen = Math.min(size(), text.length());
        char ch;
        for (int i = 0; i < inputLen; i++) {
            ch = text.charAt(i);
            if (!isValidPrepopulateCharacter(ch, i))
                builder.append(ch);
        }
        return builder;
    }

    @Override
    public int size() {
        return mMaskCharacters.size();
    }

    @Override
    public IMaskCharacter get(int index) {
        try {
            return mMaskCharacters.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public String getFormatString() {
        return mRawMaskString;
    }

    @Override
    public boolean isValidPrepopulateCharacter(char ch, int at) {
        try {
            IMaskCharacter character = mMaskCharacters.get(at);
            return character.isPrepopulate() && character.isValidCharacter(ch);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public void setOnMaskCharacterListener(OnMaskCharacterListener maskCharacterListener) {
        this.maskCharacterListener = maskCharacterListener;
    }
    //endregion

    //region PUBLIC METHODS
    public boolean isValidPrepopulateCharacter(char ch) {
        for (IMaskCharacter maskCharacter : mPrepopulateCharacters) {
            if (maskCharacter.isValidCharacter(ch)) {
                return true;
            }
        }
        return false;
    }
    //endregion

    //region PRIVATE METHODS
    private void init() {
        final int sizeMask = mRawMaskString.length();
        mMaskCharacters = new ArrayList<>(sizeMask);
        mPrepopulateCharacters = new ArrayList<>();

        char ch;
        IMaskCharacter maskCharacter;
        for (int i = 0; i < sizeMask; i++) {
            ch = mRawMaskString.charAt(i);
            maskCharacter = maskCharacterMapper.map(ch);
            if (maskCharacter.isPrepopulate()) {
                mPrepopulateCharacters.add(maskCharacter);
            }
            mMaskCharacters.add(maskCharacter);
        }
    }
    //endregion

    //endregion
}
