package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.sourcegroove.jackson.module.DateRepresentation;
import com.github.sourcegroove.jackson.module.DateRepresentationType;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private DateRepresentationType type;
    public LocalDateDeserializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        return parser.hasToken(JsonToken.VALUE_STRING) || parser.hasToken(JsonToken.VALUE_NUMBER_INT) ? 
                new DateRepresentation(this.type).of(parser.getValueAsString()).toLocalDate() : 
                null;
    }

}
