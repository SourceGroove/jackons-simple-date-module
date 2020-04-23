package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.sourcegroove.jackson.module.DateBuilder;
import com.github.sourcegroove.jackson.module.DateRepresentationType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private DateRepresentationType type;
    public LocalDateDeserializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        return parser.hasToken(JsonToken.VALUE_STRING) ? new DateBuilder(this.type)
                .from(parser.getValueAsString()).toLocalDate() : null;
    }

}
