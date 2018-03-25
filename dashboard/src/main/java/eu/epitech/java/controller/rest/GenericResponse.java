package eu.epitech.java.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class GenericResponse {
    public GenericResponse() {

    }

    private static String fatalError = "{'status': 500, 'error': 'fatal', path: null}";

    private static class ErrorPLY {
        public Long timestamp;
        public Integer status;
        public String path;
        public String message;
    }

    public static ErrorPLY buildErrorPLY(Integer _status, String _message) {
        ErrorPLY ret = new ErrorPLY();
        ret.timestamp = new Date().getTime();
        ret.status = _status;
        ret.message = _message;
        return ret;
    }

    private static class SuccessPLY {
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

    public static String error(HttpServletResponse resp, ErrorPLY error, final String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            error.path = path;
            String ret = mapper.writeValueAsString(error);
            try {
                resp.sendError(error.status, error.message);
            } catch (IOException ex) {
                System.out.println("HTTP error");
            }
            return ret;
        } catch (JsonProcessingException ex) {
            resp.setStatus(503);
            System.out.println("JACKSON: " + ex.getMessage());
            return fatalError;
        }
    }

    public static String success(HttpServletResponse resp, SuccessPLY success, final String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            resp.setContentType("application/json;charset=utf-8");
            success.path = path;
            String ret = mapper.writeValueAsString(success);
            return ret;
        } catch (JsonProcessingException ex) {
            resp.setStatus(503);
            System.out.println("JACKSON: " + ex.getMessage());
            return fatalError;
        }
    }
}
