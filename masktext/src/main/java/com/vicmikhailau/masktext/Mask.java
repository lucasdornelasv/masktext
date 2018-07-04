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
    Mask(String fmtString, List<IMaskCharacter> mMask) {
        this.mRawMaskString = fmtString;
        this.mMask = mMask;
        setupPrepolutate();
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
    private void setupPrepolutate() {
        mPrepopulateCharacter = new ArrayList<>();
        for (IMaskCharacter maskCharacter : mMask) {
            if (maskCharacter.isPrepopulate()) {
                mPrepopulateCharacter.add(maskCharacter);
            }
        }
    }
    //endregion

    //endregion
}
