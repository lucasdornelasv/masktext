package com.vicmikhailau.masktext;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaskFormatter implements IMaskFormatter {
    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public IFormattedText formatText(CharSequence value) {
        return formatText(value.toString());
    }

    @Override
    public IFormattedText formatText(String value) {
        return getMask().getFormattedString(value);
    }

    @Override
    public String unmaskedString(CharSequence value) {
        return unmaskedString(value.toString());
    }

    @Override
    public String unmaskedString(String value) {
        return formatText(value).getUnMaskedString();
    }
    //endregion

    //endregion

    //region CLASSES
    public static abstract class Builder<B extends Builder, M extends IMaskFormatter> {
        //region FIELDS
        private IMaskCharacterMapper maskCharacterMapper;
        //endregion

        //region METHODS

        //region PUBLIC METHODS
        public B withMaskCharacterMapper(IMaskCharacterMapper maskCharacterMapper) {
            this.maskCharacterMapper = maskCharacterMapper;
            return (B) this;
        }

        //region ABSTRACT PUBLIC METHODS
        abstract M build();
        //endregion

        //endregion

        //region PROTECTED METHODS
        protected IMaskCharacterMapper getMaskCharacterMapper() {
            if (maskCharacterMapper == null) maskCharacterMapper = new DefaultMaskCharacterMapper();
            return maskCharacterMapper;
        }

        protected Mask toMask(String formatMask) {
            List<IMaskCharacter> maskCharacters = new ArrayList<>(formatMask.length());
            for (char ch : formatMask.toCharArray()) {
                maskCharacters.add(getMaskCharacterMapper().map(ch));
            }
            return new Mask(formatMask, maskCharacters);
        }

        protected Mask toMask(List<IMaskCharacter> maskCharacters) {
            StringBuilder formatMask = new StringBuilder();
            for (IMaskCharacter maskCharacter : maskCharacters) {
                formatMask.append(getMaskCharacterMapper().map(maskCharacter));
            }
            return new Mask(formatMask.toString(), maskCharacters);
        }
        //endregion

        //endregion
    }
    //endregion
}
