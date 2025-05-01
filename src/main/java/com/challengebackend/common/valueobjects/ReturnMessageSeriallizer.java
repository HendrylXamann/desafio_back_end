package com.challengebackend.common.valueobjects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ReturnMessageSeriallizer extends JsonSerializer<ReturnMessage> {

    @Override
    public void serialize(ReturnMessage returnMessage, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("status", returnMessage.getStatus());
        gen.writeStringField("code", returnMessage.getCode());
        gen.writeStringField("message", returnMessage.getMessage());
        gen.writeEndObject();
    }
}
