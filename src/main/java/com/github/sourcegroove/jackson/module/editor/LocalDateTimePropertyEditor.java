package com.github.sourcegroove.jackson.module.editor;

import com.github.sourcegroove.jackson.module.DateRepresentation;
import com.github.sourcegroove.jackson.module.DateRepresentationType;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

public class LocalDateTimePropertyEditor extends PropertyEditorSupport {
    private DateRepresentationType type;

    public LocalDateTimePropertyEditor() {
        this.type = DateRepresentationType.ISO;
    }

    public LocalDateTimePropertyEditor(DateRepresentationType type) {
        this.type = type;
    }

    @Override
    public void setAsText(String text) {
        if (StringUtils.isBlank(text)) {
            this.setValue(null);
        } else {
            this.setValue(new DateRepresentation(type).of(text).toLocalDateTime());
        }
    }

    @Override
    public String getAsText() {
        LocalDateTime value = (LocalDateTime) this.getValue();
        return value != null ? new DateRepresentation(type).of(value).toString() : "";
    }

}
