package com.github.sourcegroove.jackson.module.serialization;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Base64;

public class ByteArrayDeserializer extends JsonDeserializer<byte[]> {
    @Override
    public byte[] deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        byte[] bytes = null;
        if (parser.hasToken(JsonToken.VALUE_STRING)) {
            bytes = deserialize(parser.getValueAsString());
        }
        return bytes;
    }

    public static byte[] deserialize(String value) {
        return Base64.getDecoder().decode(value);
    }

}
