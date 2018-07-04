package com.vicmikhailau.masktext;

import android.widget.EditText;
import android.widget.TextView;

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
    private EditText editText;
    private MaskedWatcher maskedWatcher;
    private int currentMaskIndex;
    //endregion

    //region CONSTRUCTORS
    private MaskFormatter(Builder builder) {
        this.maskList = builder.buildMaskList();
        this.editText = builder.getEditText();

        init();
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

    public String getUnMaskedEditText() {
        if (editText == null || editText.getText() == null) return "";
        return formatText(editText.getText().toString()).getUnMaskedString();
    }

    public String getUnMaskedEditText(int maskIndex) {
        if (editText == null || editText.getText() == null) return "";
        return formatText(editText.getText().toString(), maskIndex).getUnMaskedString();
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

    public void destroy() {
        destroyInternal();
    }
    //endregion

    //region PRIVATE METHODS
    private void init() {
        if (editText != null) {
            editText.addTextChangedListener(maskedWatcher = new MaskedWatcher(this, editText));
        }
    }

    private void destroyInternal() {
        if (editText != null && maskedWatcher != null) {
            editText.removeTextChangedListener(maskedWatcher);
            maskedWatcher = null;
        }
    }
    //endregion

    //endregion

    //region CLASSES
    public static class Builder {
        //region FIELDS
        private List<Object> maskList;
        private IMaskCharacterMapper maskCharacterMapper;
        private EditText editText;
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

        public Builder withMaskCharacterMapper(IMaskCharacterMapper maskCharacterMapper) {
            this.maskCharacterMapper = maskCharacterMapper;
            return this;
        }

        public <T extends EditText> Builder withEditText(T editText) {
            this.editText = editText;
            return this;
        }

        public MaskFormatter build() {
            return new MaskFormatter(this);
        }
        //endregion

        //region PRIVATE METHODS
        EditText getEditText() {
            return editText;
        }

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

        private Mask toMask(String formatMask) {
            List<IMaskCharacter> maskCharacters = new ArrayList<>(formatMask.length());
            for (char ch : formatMask.toCharArray()) {
                maskCharacters.add(getMaskCharacterMapper().map(ch));
            }
            return new Mask(formatMask, maskCharacters);
        }

        private Mask toMask(List<IMaskCharacter> maskCharacters) {
            StringBuilder formatMask = new StringBuilder();
            for (IMaskCharacter maskCharacter : maskCharacters) {
                formatMask.append(getMaskCharacterMapper().map(maskCharacter));
            }
            return new Mask(formatMask.toString(), maskCharacters);
        }

        private List<Object> getMaskList() {
            if (maskList == null) maskList = new ArrayList<>(1);
            return maskList;
        }

        private IMaskCharacterMapper getMaskCharacterMapper() {
            if (maskCharacterMapper == null) maskCharacterMapper = new DefaultMaskCharacterMapper();
            return maskCharacterMapper;
        }
        //endregion

        //endregion
    }
    //endregion
}

