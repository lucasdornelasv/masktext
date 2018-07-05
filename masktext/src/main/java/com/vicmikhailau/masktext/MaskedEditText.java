package com.vicmikhailau.masktext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MaskedEditText extends AppCompatEditText {

    // ===========================================================
    // Fields
    // ===========================================================

    private MaskFormatter mMaskedFormatter;
    private MaskedWatcher mMaskedWatcher;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MaskedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskedEditText);

        if (typedArray.hasValue(R.styleable.MaskedEditText_mask)) {
            String maskStr = typedArray.getString(R.styleable.MaskedEditText_mask);

            if (maskStr != null && !maskStr.isEmpty()) {
                setMask(maskStr);
            }
        }

        typedArray.recycle();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public String getMaskString() {
        return mMaskedFormatter.getMask().getFormatString();
    }

    public String getUnMaskedText() {
        String currentText = getText().toString();
        IFormattedText formattedString = mMaskedFormatter.formatText(currentText);
        return formattedString.getUnMaskedString();
    }

    public void setMask(String mMaskStr) {
        mMaskedFormatter = new MaskFormatter.Builder(mMaskStr).build();

        if (mMaskedWatcher != null) {
            removeTextChangedListener(mMaskedWatcher);
        }

        mMaskedWatcher = new MaskedWatcher(mMaskedFormatter, this);
        addTextChangedListener(mMaskedWatcher);
    }

}
