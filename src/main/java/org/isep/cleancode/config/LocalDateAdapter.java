package org.isep.cleancode.config;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext ctx) {
        return new JsonPrimitive(src == null ? "" : src.toString());
    }
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx)
            throws JsonParseException {
        String s = json.getAsString();
        return (s == null || s.isEmpty()) ? null : LocalDate.parse(s);
    }
}