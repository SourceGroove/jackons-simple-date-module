package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.sourcegroove.jackson.module.DateRepresentation;
import com.github.sourcegroove.jackson.module.DateRepresentationType;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private DateRepresentationType type;
    public LocalDateTimeSerializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public void serialize(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value != null) {
            jsonGenerator.writeObject(new DateRepresentation(this.type).of(value).serialize());
        }
    }
}
