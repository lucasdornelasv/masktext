package com.vicmikhailau.masktext;

public interface IMaskFormatter {

    Mask getMask();

    IFormattedText formatText(CharSequence value);

    IFormattedText formatText(String value);

    String unmaskedString(CharSequence value);

    String unmaskedString(String value);

}
