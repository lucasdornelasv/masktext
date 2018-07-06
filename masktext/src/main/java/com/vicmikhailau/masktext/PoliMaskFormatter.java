package com.vicmikhailau.masktext;


import com.vicmikhailau.masktext.formatted_texts.DefaultFormattedText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PoliMaskFormatter extends AbstractMaskFormatter implements IMaskFormatter {
    //region FIELDS
    private List<IMask> maskList;
    private int currentMaskIndex;
    //endregion

    //region CONSTRUCTORS
    private PoliMaskFormatter(Builder builder) {
        this.maskList = builder.buildMaskList();
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public IMask getMask() {
        return getMask(getCurrentMaskIndex());
    }

    @Override
    public IFormattedText formatText(String value) {
        IFormattedText formattedText = formatText(value, getCurrentMaskIndex());
        if (maskList.size() > 1 && !formattedText.isValid()) {
            int skipIndex = getCurrentMaskIndex();
            int i = skipIndex > 0 ? 0 : 1;
            IFormattedText formattedTextBase = formattedText;
            boolean isValid = false;
            while (i < maskList.size()) {
                if (skipIndex != i && (isValid = (formattedText = formatText(value, i)).isValid())) {
                    break;
                }
                i++;
            }
            if (!isValid) {
                formattedText = formattedTextBase;
                currentMaskIndex = skipIndex;
            } else currentMaskIndex = i;
        }

        return formattedText;
    }
    //endregion

    //region PUBLIC METHODS
    public IFormattedText formatText(String value, int maskIndex) {
        return new DefaultFormattedText(getMask(maskIndex), value);
    }

    public IMask getMask(int maskIndex) {
        return maskList.get(maskIndex);
    }

    public int getCurrentMaskIndex() {
        return currentMaskIndex;
    }
    //endregion

    //endregion

    //region CLASSES
    public static class Builder extends AbstractMaskFormatter.Builder<Builder, PoliMaskFormatter> {
        //region FIELDS
        private List<String> maskList;
        //endregion

        //region CONSTRUCTORS
        public Builder(String mask) {
            addMask(mask);
        }

        public Builder(String... mask) {
            addMask(mask);
        }

        public Builder(List<String> mask) {
            addMask(mask);
        }
        //endregion

        //region METHODS

        //region OVERRIDE METHODS
        @Override
        public PoliMaskFormatter build() {
            return new PoliMaskFormatter(this);
        }
        //endregion

        //region PUBLIC METHODS
        public Builder addMask(String mask) {
            getMaskList().add(mask);
            return this;
        }

        public Builder addMask(String... masks) {
            Collections.addAll(getMaskList(), masks);
            return this;
        }

        public Builder addMask(List<String> masks) {
            getMaskList().addAll(masks);
            return this;
        }
        //endregion

        //region PRIVATE METHODS
        List<IMask> buildMaskList() {
            final List<IMask> maskList = new ArrayList<>(getMaskList().size());
            for (String mask : getMaskList()) {
                maskList.add(toMask(mask));
            }
            return maskList;
        }

        private List<String> getMaskList() {
            if (maskList == null) maskList = new ArrayList<>();
            return maskList;
        }
        //endregion

        //endregion
    }
    //endregion
}
