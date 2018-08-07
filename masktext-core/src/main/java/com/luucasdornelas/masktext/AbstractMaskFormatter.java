package com.luucasdornelas.masktext;


import com.luucasdornelas.masktext.formatted_texts.DefaultFormattedText;

public abstract class AbstractMaskFormatter implements IMaskFormatter {
    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public IFormattedText formatText(CharSequence value) {
        return new DefaultFormattedText(getMask(), value);
    }

    @Override
    public IFormattedText formatText(String value) {
        return formatText((CharSequence) value);
    }

    @Override
    public String formatString(CharSequence value) {
        return getMask().formatText(value).toString();
    }

    @Override
    public String formatString(String value) {
        return formatString((CharSequence) value);
    }

    @Override
    public String unmaskedString(CharSequence value) {
        return getMask().unmaskText(value).toString();
    }

    @Override
    public String unmaskedString(String value) {
        return unmaskedString((CharSequence) value);
    }
    //endregion

    //endregion

    //region CLASSES
    public static abstract class Builder<B extends Builder, M extends IMaskFormatter> {
        //region FIELDS
        private IMaskCharacterMapper maskCharacterMapper;
        private OnMaskCharacterListener maskCharacterListener;
        //endregion

        //region METHODS

        //region PUBLIC METHODS
        public B withMaskCharacterMapper(IMaskCharacterMapper maskCharacterMapper) {
            this.maskCharacterMapper = maskCharacterMapper;
            return (B) this;
        }

        public B withOnMaskCharacterListener(OnMaskCharacterListener maskCharacterListener) {
            this.maskCharacterListener = maskCharacterListener;
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

        protected IMask toMask(String formatMask) {
            final IMask mask = new Mask(formatMask, getMaskCharacterMapper());
            mask.setOnMaskCharacterListener(maskCharacterListener);
            return mask;
        }
        //endregion

        //endregion
    }
    //endregion
}
