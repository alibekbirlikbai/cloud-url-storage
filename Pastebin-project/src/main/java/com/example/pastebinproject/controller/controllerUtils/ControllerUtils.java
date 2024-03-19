package com.example.pastebinproject.controller.controllerUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ControllerUtils {
    public static void logStart(HttpServletRequest request) {
        System.out.println();
        System.out.println(request.getMethod() + " " + request.getRequestURI() + " "
                + getRequestParameters(request));
        System.out.println(DevelopmentServices.consoleMessage() + "invoked EndPoint={" + request.getRequestURL() + "}");
    }

    public static void logEnd() {
        System.out.println();
    }



    private static String getRequestParameters(HttpServletRequest request) {
        StringBuilder requestParameters_pretty = new StringBuilder();

        if (request.getQueryString() != null) {
            requestParameters_pretty.append("[");

            requestParameters_pretty.append(request.getQueryString().replaceAll("&", " & "));

            requestParameters_pretty.append("]");
        }

        return requestParameters_pretty.toString();
    }
}
