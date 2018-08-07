package com.luucasdornelas.masktext;

public interface IMaskFormatter {

    IMask getMask();

    IFormattedText formatText(CharSequence value);

    IFormattedText formatText(String value);

    String formatString(CharSequence value);

    String formatString(String value);

    String unmaskedString(CharSequence value);

    String unmaskedString(String value);

}
