package eu.epitech.java.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class GenericResponse {
    public GenericResponse() {

    }

    private static String fatalError = "{'status': 500, 'error': 'fatal', path: null}";

    public static class ErrorPLY {
        public Long timestamp;
        public Integer status;
        public String path;
        public String error;
        public String message;
    }

    public static ErrorPLY buildErrorPLY(Integer _status, String _error, String _message) {
        ErrorPLY ret = new ErrorPLY();
        ret.timestamp = new Date().getTime();
        ret.status = _status;
        ret.error = _error;
        ret.message = _message;
        return ret;
    }

    public static class SuccessPLY {
        public Long timestamp;
        public Integer status = 200;
        public String path;
        public String error = null;
        public String message = null;
        public Object payload = null;
    }

    public static SuccessPLY buildSuccessPLY(Object payload) {
        SuccessPLY ret = new SuccessPLY();
        ret.timestamp = new Date().getTime();
        ret.payload = payload;
        return ret;
    }

    public static String error(ErrorPLY error, final String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String ret = mapper.writeValueAsString(error);
            return ret;
        } catch (JsonProcessingException ex) {
            return fatalError;
        }
    }

    public static String success(SuccessPLY success, final String path) {
        buildSuccessPLY("pbeu");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String ret = mapper.writeValueAsString(success);
            return ret;
        } catch (JsonProcessingException ex) {
            return fatalError;
        }
    }
}
