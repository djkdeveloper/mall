package com.djk.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 将jdk8 时间转化成json
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value == null) {
            jgen.writeNull();
        } else {
            jgen.writeString( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(value));
        }
    }
}
