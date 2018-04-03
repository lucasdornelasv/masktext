package com.vicmikhailau.masktext;

/**
 * Created by lucas on 02/04/2018.
 */

public interface IFormattedText extends CharSequence {

    Mask getMask();

    String getRawString();

    String getUnMaskedString();

    boolean isValid();

}
