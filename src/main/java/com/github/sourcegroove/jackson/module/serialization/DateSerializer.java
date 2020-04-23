package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.sourcegroove.jackson.module.DateBuilder;
import com.github.sourcegroove.jackson.module.DateRepresentationType;

import java.io.IOException;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {
    private DateRepresentationType type;
    public DateSerializer(DateRepresentationType type){
        this.type = type;
    }
    @Override
    public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value != null) {
            jsonGenerator.writeObject(new DateBuilder(this.type).from(value).toString());
        }
    }
}
