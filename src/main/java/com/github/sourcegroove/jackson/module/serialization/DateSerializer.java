package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.sourcegroove.jackson.module.DateRepresentation;
import com.github.sourcegroove.jackson.module.DateRepresentationType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;

@Slf4j
public class DateSerializer extends JsonSerializer<Date> {
    private DateRepresentationType type;
    public DateSerializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        log.trace("Serializing date " + value);
        if(value != null) {
            jsonGenerator.writeObject(new DateRepresentation(this.type).of(value).serialize());
        }
    }
}
