package com.github.sourcegroove.jackson.module.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Base64;

public class ByteArraySerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeObject(serialize(value));
        }
    }

    public static String serialize(byte[] value) {
        byte[] encoded = Base64.getEncoder().encode(value);
        return new String(encoded);
    }
}
