package com.example.pastebinproject.controller.controllerUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtils {
    public static void logStart(HttpServletRequest request) {
        System.out.println();
        System.out.println(request.getRequestURI());
        System.out.println(DevelopmentServices.consoleMessage() + "invoked EndPoint={" + request.getRequestURL() + "}");
    }

    public static void logEnd() {
        System.out.println();
    }
}
