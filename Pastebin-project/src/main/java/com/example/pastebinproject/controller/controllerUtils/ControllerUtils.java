package com.example.pastebinproject.controller.controllerUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class ControllerUtils {
    private static Map<String, String> parameters;

    public static void logStart(HttpServletRequest request) {
        System.out.println();
        System.out.println(request.getMethod() + " " + request.getRequestURI() + " "
                + "Params=" + getRequestParameters(request));
        System.out.println(DevelopmentServices.consoleMessage() + "invoked EndPoint={" + request.getRequestURL() + "}");
    }

    public static void logEnd() {
        System.out.println();
    }


    public static Map<String, String> getRequestParameters(HttpServletRequest request) {
        String all_parameters = request.getQueryString();
        parameters = new HashMap<>();

        if (all_parameters != null) {
            String[] params = all_parameters.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                try {
                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
                    String value = URLDecoder.decode(keyValue[1], "UTF-8");
                    parameters.put(key, value);
                } catch (UnsupportedEncodingException e) {
                    // Обработка исключения
                    e.printStackTrace();
                }
            }
        }

        return parameters;
    }
}
