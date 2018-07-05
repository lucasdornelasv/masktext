package com.vicmikhailau.masktext;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PoliMaskFormatter extends AbstractMaskFormatter implements IMaskFormatter {
    //region FIELDS
    private List<Mask> maskList;
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
    public Mask getMask() {
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
        return getMask(maskIndex).getFormattedString(value);
    }

    public Mask getMask(int maskIndex) {
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
        private List<Object> maskList;
        //endregion

        //region CONSTRUCTORS
        public Builder(String mask) {
            addMask(mask);
        }

        public Builder(IMaskCharacter... characters) {
            addMask(characters);
        }

        public Builder(List<IMaskCharacter> characters) {
            addMask(characters);
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

        public Builder addMasks(String... masks) {
            Collections.addAll(getMaskList(), masks);
            return this;
        }

        public Builder addMask(IMaskCharacter... characters) {
            return addMask(Arrays.asList(characters));
        }

        public Builder addMask(List<IMaskCharacter> characters) {
            getMaskList().add(characters);
            return this;
        }
        //endregion

        //region PRIVATE METHODS
        List<Mask> buildMaskList() {
            List<Mask> maskList = new ArrayList<>(getMaskList().size());
            Mask mask = null;
            for (Object o : getMaskList()) {
                if (o instanceof String) {
                    mask = toMask((String) o);
                } else if (o instanceof List) {
                    mask = toMask((List<IMaskCharacter>) o);
                }
                maskList.add(mask);
            }
            return maskList;
        }

        private List<Object> getMaskList() {
            if (maskList == null) maskList = new ArrayList<>(1);
            return maskList;
        }
        //endregion

        //endregion
    }
    //endregion
}
