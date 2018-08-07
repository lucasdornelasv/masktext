package com.luucasdornelas.masktext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lucas on 02/04/2018.
 */

public class MaskFormatter extends AbstractMaskFormatter implements IMaskFormatter {
    //region FIELDS
    private final IMask mask;
    //endregion

    //region CONSTRUCTORS
    private MaskFormatter(Builder builder) {
        this.mask = builder.buildMask();
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public IMask getMask() {
        return mask;
    }
    //endregion

    //endregion

    //region CLASSES
    public static class Builder extends AbstractMaskFormatter.Builder<Builder, MaskFormatter> {
        //region FIELDS
        private final String mask;
        //endregion

        //region CONSTRUCTORS
        public Builder(String mask) {
            this.mask = mask;
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
        IMask buildMask() {
            return toMask(mask);
        }
        //endregion

        //endregion
    }
    //endregion
}

