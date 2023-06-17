package backend.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonConverter {

    private static final ObjectMapper ObjectMapper = new ObjectMapper();

    public static <T> String getJson(T object) throws JsonProcessingException {
        return ObjectMapper.writeValueAsString(object);
    }

    public static <T> T getObjetc(String json, Class<T> objectType) throws JsonProcessingException {
        return ObjectMapper.readValue(json, objectType);
    }

}
