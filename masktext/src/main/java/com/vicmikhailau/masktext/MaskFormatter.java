package com.vicmikhailau.masktext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lucas on 02/04/2018.
 */

public class MaskFormatter extends AbstractMaskFormatter implements IMaskFormatter {
    //region FIELDS
    private final Mask mask;
    //endregion

    //region CONSTRUCTORS
    private MaskFormatter(Builder builder) {
        this.mask = builder.buildMask();
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public Mask getMask() {
        return mask;
    }
    //endregion

    //endregion

    //region CLASSES
    public static class Builder extends AbstractMaskFormatter.Builder<Builder, MaskFormatter> {
        //region FIELDS
        private final Object mask;
        //endregion

        //region CONSTRUCTORS
        public Builder(String mask) {
            this.mask = mask;
        }

        public Builder(IMaskCharacter... characters) {
            this(Arrays.asList(characters));
        }

        public Builder(List<IMaskCharacter> characters) {
            mask = characters;
        }
        //endregion

        //region METHODS

        //region OVERRIDE METHODS
        @Override
        public MaskFormatter build() {
            return new MaskFormatter(this);
        }
        //endregion

        //region PACKAGE METHODS
        Mask buildMask() {
            Mask aux = null;
            if (mask instanceof String) {
                aux = toMask((String) mask);
            } else if (mask instanceof List) {
                aux = toMask((List<IMaskCharacter>) mask);
            }
            return aux;
        }
        //endregion

        //endregion
    }
    //endregion
}

