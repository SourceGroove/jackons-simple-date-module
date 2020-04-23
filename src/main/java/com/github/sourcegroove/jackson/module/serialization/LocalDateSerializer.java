package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.sourcegroove.jackson.module.DateBuilder;
import com.github.sourcegroove.jackson.module.DateRepresentationType;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    private DateRepresentationType type;
    public LocalDateSerializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value != null) {
            jsonGenerator.writeObject(new DateBuilder(this.type).from(value).toString());
        }
    }
}
