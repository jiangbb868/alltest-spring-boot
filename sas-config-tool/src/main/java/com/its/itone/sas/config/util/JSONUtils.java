package com.its.itone.sas.config.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    //
    public static <T> String jsonToJSONStr(T json) {
        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    //
    public static <T> T jsonStrToJSON(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        try {
            object = mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
    //
    public static <T> T jsonStrToJSON(String json, TypeReference<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        try {
            object = mapper.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}