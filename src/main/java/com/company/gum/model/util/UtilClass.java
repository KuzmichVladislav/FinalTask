package com.company.gum.model.util;

import com.company.gum.controller.SessionRequestContent;

public class UtilClass {

    public static final String LESS_THAN_SIGN = "<";
    public static final String GREATER_THAN_SIGN = ">";
    public static final String REPLACEMENT = "";

    private static UtilClass instance;

    private UtilClass() {
    }

    public static UtilClass getInstance() {
        if (instance == null) {
            instance = new UtilClass();
        }
        return instance;
    }

    public String getStringFromDescription(SessionRequestContent requestContent, String parameterName) {
        return requestContent.getParameterByName(parameterName).isBlank()
                ? (String) requestContent.getSessionAttributeByName(parameterName)
                : requestContent.getParameterByName(parameterName).strip()
                .replace(LESS_THAN_SIGN, REPLACEMENT).replace(GREATER_THAN_SIGN, REPLACEMENT);
    }
}
