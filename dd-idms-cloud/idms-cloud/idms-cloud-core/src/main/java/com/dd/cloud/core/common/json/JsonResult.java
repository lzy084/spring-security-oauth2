package com.dd.cloud.core.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Slf4j
public class JsonResult implements Serializable {
    private String code;
    private String message;
    private boolean success;
    private Object data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public JsonResult() {
        this.success = true;
        this.code="0000";
        this.message="success";
    }

    public static JsonResult create() {
        return new JsonResult();
    }

    public JsonResult code(String code) {
        this.code = code;
        return this;
    }

    public JsonResult message(String message) {
        this.message = message;
        return this;
    }

    public JsonResult success(boolean success) {
        this.success = success;
        return this;
    }

    public JsonResult data(Object data) {
        this.data = data;
        return this;
    }
    public JsonResult data(Object data, JsonInclude.Include include) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(include);
        try {
            this.data= mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            this.data=null;
            e.printStackTrace();
        }
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
