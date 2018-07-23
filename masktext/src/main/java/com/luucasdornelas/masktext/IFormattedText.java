package com.luucasdornelas.masktext;

/**
 * Created by lucas on 02/04/2018.
 */

public interface IFormattedText extends CharSequence {

    IMask getMask();

    String getRawString();

    CharSequence getRawText();

    String getUnMaskedString();

    boolean isValid();

}
