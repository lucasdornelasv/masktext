package com.vicmikhailau.masktext;

import com.vicmikhailau.masktext.formatted_texts.DefaultFormattedText;

import java.util.ArrayList;
import java.util.List;


public class Mask {
    //region FIELDS
    private String mRawMaskString;
    private List<IMaskCharacter> mMask;
    private List<IMaskCharacter> mPrepopulateCharacter;
    //endregion

    //region CONSTRUCTORS
    Mask(String fmtString, IMaskCharacterFactory factory) {
        mRawMaskString = fmtString;
        mMask = buildMask(factory, mRawMaskString);
    }

    Mask(List<IMaskCharacter> mMask) {
        this.mMask = mMask;
    }
    //endregion

    //region METHODS

    //region PUBLIC METHODS
    public String getFormatString() {
        return mRawMaskString;
    }

    public int size() {
        return mMask.size();
    }

    public IMaskCharacter get(int index) {
        return mMask.get(index);
    }

    public boolean isValidPrepopulateCharacter(char ch, int at) {
        try {
            IMaskCharacter character = mMask.get(at);
            return character.isPrepopulate() && character.isValidCharacter(ch);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isValidPrepopulateCharacter(char ch){
        for (IMaskCharacter maskCharacter : mPrepopulateCharacter){
            if (maskCharacter.isValidCharacter(ch)) {
                return true;
            }
        }
        return false;
    }

    public IFormattedText getFormattedString(String value) {
        return new DefaultFormattedText(this, value);
    }
    //endregion

    //region PRIVATE METHODS
    private List<IMaskCharacter> buildMask(IMaskCharacterFactory factory, String fmtString) {
        List<IMaskCharacter> result = new ArrayList<>();
        mPrepopulateCharacter = new ArrayList<>();
        IMaskCharacter maskCharacter;
        for (char ch : fmtString.toCharArray()) {
            maskCharacter = factory.getMaskCharacter(ch);
            if (maskCharacter.isPrepopulate()) {
                mPrepopulateCharacter.add(maskCharacter);
            }
            result.add(maskCharacter);
        }
        return result;
    }
    //endregion

    //endregion
}
