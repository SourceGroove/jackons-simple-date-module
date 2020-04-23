package com.github.sourcegroove.jackson.module.editor;

import com.github.sourcegroove.jackson.module.DateRepresentation;
import com.github.sourcegroove.jackson.module.DateRepresentationType;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {
    private DateRepresentationType type;

    public DatePropertyEditor() {
        this.type = DateRepresentationType.ISO;
    }

    public DatePropertyEditor(DateRepresentationType type) {
        this.type = type;
    }

    @Override
    public void setAsText(String text) {
        if (StringUtils.isBlank(text)) {
            this.setValue(null);
        } else {
            this.setValue(new DateRepresentation(type).of(text).toDate());
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date) this.getValue();
        return value != null ? new DateRepresentation(type).of(value).toString() : "";
    }

}
