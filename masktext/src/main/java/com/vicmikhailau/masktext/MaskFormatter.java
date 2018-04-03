package com.vicmikhailau.masktext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucas on 02/04/2018.
 */

public class MaskFormatter {
    //region FIELDS
    private List<Mask> maskList;
    private int currentMaskIndex;
    //endregion

    //region CONSTRUCTORS
    private MaskFormatter(Builder builder) {
        this.maskList = builder.buildMaskList();
    }
    //endregion

    //region METHODS

    //region STATIC METHODS
    public static Builder builder(String mask) {
        return new Builder(mask);
    }

    public static Builder builder(IMaskCharacter... characters) {
        return new Builder(characters);
    }

    public static Builder builder(List<IMaskCharacter> characters) {
        return new Builder(characters);
    }

    public static MaskFormatter getInstance(String mask) {
        return builder(mask).build();
    }

    public static MaskFormatter getInstance(IMaskCharacter... characters) {
        return builder(characters).build();
    }

    public static MaskFormatter getInstance(List<IMaskCharacter> characters) {
        return builder(characters).build();
    }
    //endregion

    //region PUBLIC METHODS
    public Mask getCurrentMask() {
        return getMask(getCurrentMaskIndex());
    }

    public int getCurrentMaskLength() {
        return getCurrentMask().size();
    }

    public Mask getMask(int maskIndex) {
        return maskList.get(maskIndex);
    }

    public int getCurrentMaskIndex() {
        return currentMaskIndex;
    }

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

    public IFormattedText formatText(String value, int maskIndex) {
        return getMask(maskIndex).getFormattedString(value);
    }
    //endregion

    //endregion

    //region CLASSES
    public static class Builder {
        //region FIELDS
        private List<Object> maskList;
        private IMaskCharacterFactory factory;
        //endregion

        //region CONSTRUCTORS
        private Builder(String mask) {
            addMask(mask);
        }

        private Builder(IMaskCharacter... characters) {
            addMask(characters);
        }

        private Builder(List<IMaskCharacter> characters) {
            addMask(characters);
        }
        //endregion

        //region METHODS

        //region PUBLIC METHODS
        public Builder addMask(String... masks) {
            Collections.addAll(getMaskList(), masks);
            return this;
        }

        public Builder addMask(IMaskCharacter... characters) {
            return addMask(Arrays.asList(characters));
        }

        public Builder addMask(List<IMaskCharacter> characters) {
            getMaskList().add(new Mask(characters));
            return this;
        }

        private Builder withFactory(IMaskCharacterFactory factory) {
            this.factory = factory;
            return this;
        }

        public MaskFormatter build() {
            return new MaskFormatter(this);
        }
        //endregion

        //region PRIVATE METHODS
        List<Mask> buildMaskList() {
            List<Mask> maskList = new ArrayList<>(getMaskList().size());
            Mask mask = null;
            for (Object o : getMaskList()) {
                if (o instanceof String) {
                    mask = new Mask((String) o, getFactory());
                } else if (o instanceof Mask) {
                    mask = (Mask) o;
                }
                maskList.add(mask);
            }
            return maskList;
        }

        private List<Object> getMaskList() {
            if (maskList == null) maskList = new ArrayList<>(1);
            return maskList;
        }

        private IMaskCharacterFactory getFactory() {
            if (factory == null) factory = new DefaultMaskCharacterFactory();
            return factory;
        }
        //endregion

        //endregion
    }
    //endregion
}

