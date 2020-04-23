package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.sourcegroove.jackson.module.DateBuilder;
import com.github.sourcegroove.jackson.module.DateRepresentationType;

import java.io.IOException;
import java.time.OffsetDateTime;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
    private DateRepresentationType type;
    public OffsetDateTimeSerializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public void serialize(OffsetDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value != null) {
            jsonGenerator.writeObject(new DateBuilder(this.type).from(value).toString());
        }
    }
}
