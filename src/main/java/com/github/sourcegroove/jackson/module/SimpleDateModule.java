package com.github.sourcegroove.jackson.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.sourcegroove.jackson.module.serialization.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class SimpleDateModule extends SimpleModule {

    public SimpleDateModule() {
        addSerializers(DateRepresentationType.ISO);
    }

    public SimpleDateModule(DateRepresentationType type) {
        addSerializers(type);
    }
    
    private void addSerializers(DateRepresentationType type) {
        this.addSerializer(Date.class, new DateSerializer(type));
        this.addSerializer(LocalDate.class, new LocalDateSerializer(type));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(type));
        this.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer(type));
        this.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer(type));

        this.addDeserializer(Date.class, new DateDeserializer(type));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(type));
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(type));
        this.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer(type));
        this.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer(type));
    }


}
