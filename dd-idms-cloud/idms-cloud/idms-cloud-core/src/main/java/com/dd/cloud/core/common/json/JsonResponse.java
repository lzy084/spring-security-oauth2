package com.dd.cloud.core.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * response 请求json
 */
public class JsonResponse implements Serializable {
    private int code;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

    public JsonResponse() {
        this.success = true;
    }

    public static JsonResponse create() {
        return new JsonResponse();
    }

    public JsonResponse code(int code) {
        this.code = code;
        return this;
    }

    public JsonResponse message(String message) {
        this.message = message;
        return this;
    }

    public JsonResponse success(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
