package com.vicmikhailau.masktext;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;

public class MaskedWatcher implements TextWatcher {
    //region FIELDS
    private final WeakReference<IMaskFormatter> mMaskFormatter;
    private final WeakReference<EditText> mEditText;
    private String oldFormattedValue = "";
    private int oldCursorPosition;
    //endregion

    //region CONSTRUCTORS
    public MaskedWatcher(IMaskFormatter maskedFormatter, EditText editText) {
        mMaskFormatter = new WeakReference<>(maskedFormatter);
        mEditText = new WeakReference<>(editText);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public void afterTextChanged(Editable s) {
        if (s == null)
            return;

        String value = s.toString();

        if (value.length() > oldFormattedValue.length()
                && mMaskFormatter.get().getMask().size() < value.length()) {
            value = oldFormattedValue;
        }

        IFormattedText formattedText = mMaskFormatter.get().formatText(value);

        setFormattedText(s, formattedText);
        oldFormattedValue = formattedText.toString();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        this.oldCursorPosition = mEditText.get().getSelectionStart();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.oldCursorPosition = mEditText.get().getSelectionStart();
    }
    //endregion

    //region PRIVATE METHODS
    private void setFormattedText(Editable editable, IFormattedText formattedText) {
        EditText editText = mEditText.get();
        if (editText == null) {
            return;
        }

        editText.removeTextChangedListener(this);
        editable.clear();
        if (formattedText.length() > 0) editable.append(formattedText);

        /*int deltaLength = formattedText.length() - oldFormattedValue.length();
        int newCursorPosition = oldCursorPosition;

        if (deltaLength > 0) {
            //newCursorPosition += deltaLength;
        } else if (deltaLength < 0) {
            //newCursorPosition -= 1;
        } else {
            IMask mask = mMaskFormatter.get().getMask();
            newCursorPosition = Math.max(1, Math.min(newCursorPosition, mask.size()));
            //if (mask.get(newCursorPosition - 1).isPrepopulate())
                //newCursorPosition -= 1;
        }
        newCursorPosition = Math.max(0, Math.min(newCursorPosition, formattedText.length()));
        editText.setSelection(newCursorPosition);*/
        editText.addTextChangedListener(this);
    }
    //endregion

    //endregion
}
