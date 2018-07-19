package com.vicmikhailau.mask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.vicmikhailau.masktext.IMaskFormatter;
import com.vicmikhailau.masktext.MaskEvent;
import com.vicmikhailau.masktext.MaskFormatter;
import com.vicmikhailau.masktext.MaskedEditText;
import com.vicmikhailau.masktext.MaskedWatcher;
import com.vicmikhailau.masktext.OnMaskCharacterListener;
import com.vicmikhailau.masktext.PoliMaskFormatter;
import com.vicmikhailau.masktext.maskcharacters.DigitCharacter;

public class MainActivity extends AppCompatActivity {

    /**
     * Use specific values for create your own mask (see example below or in xml):
     * ANYTHING_KEY = '*'
     * DIGIT_KEY = '#'
     * UPPERCASE_KEY = 'U';
     * LOWERCASE_KEY = 'L';
     * ALPHA_NUMERIC_KEY = 'A';
     * LITERAL_KEY = '\'';
     * CHARACTER_KEY = '?';
     * HEX_KEY = 'H';
     */

    /**
     * For getting unmasked text for MaskedEditText mEdtMaskedCustom just use mEdtMaskedCustom.getUnMaskedString().
     * For getting unmasked text for default EditText just use formatter.formatString(text).getUnMaskedString().
     */

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private MaskedEditText mEdtMaskedCustom;
    private EditText mEdtMasked;
    private IMaskFormatter formatter;
    private MaskedWatcher maskedWatcher;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setMask("##/##/####", "UUU-####");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    private void findViews() {
        mEdtMaskedCustom = (MaskedEditText) findViewById(R.id.edt_masked_custom);
        mEdtMasked = (EditText) findViewById(R.id.edt_masked);
    }

    /**
     * You cas use MaskedEditText declared in xml with attribute named mask
     * or
     * set mask in code for default EdiText
     *
     * @param mask your mask
     */
    private void setMask(String mask, String... masks) {
        formatter = new PoliMaskFormatter.Builder(mask)
                .addMask(masks)
                .withOnMaskCharacterListener(new OnMaskCharacterListener() {
                    boolean last;

                    @Override
                    public void onMaskCharacter(MaskEvent maskEvent) {
                        boolean isNumber = maskEvent.getNext() instanceof DigitCharacter;
                        if (isNumber != last) {
                            last = isNumber;
                            if (isNumber) {
                                mEdtMasked.setInputType(InputType.TYPE_CLASS_NUMBER);
                            } else {
                                mEdtMasked.setInputType(InputType.TYPE_CLASS_TEXT);
                            }
                        }
                    }
                })
                .build();

        mEdtMasked.addTextChangedListener(maskedWatcher = new MaskedWatcher(formatter, mEdtMasked));
    }

    private void getUnMaskedTextForEdtCustom() {
        mEdtMaskedCustom.getUnMaskedText();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
