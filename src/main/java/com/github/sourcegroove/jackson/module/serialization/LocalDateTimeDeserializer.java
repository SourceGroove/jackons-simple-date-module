package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.sourcegroove.jackson.module.DateBuilder;
import com.github.sourcegroove.jackson.module.DateRepresentationType;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private DateRepresentationType type;
    public LocalDateTimeDeserializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        return parser.hasToken(JsonToken.VALUE_STRING) ? new DateBuilder(this.type)
                .from(parser.getValueAsString()).toLocalDateTime() : null;
    }
}
