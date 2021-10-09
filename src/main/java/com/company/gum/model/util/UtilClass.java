package com.company.gum.model.util;

import com.company.gum.controller.SessionRequestContent;

/**
 * The Class UtilClass.
 *
 * @author Vladislav Kuzmich
 */
public class UtilClass {

    /** The Constant LESS_THAN_SIGN. */
    public static final String LESS_THAN_SIGN = "<";
    
    /** The Constant GREATER_THAN_SIGN. */
    public static final String GREATER_THAN_SIGN = ">";
    
    /** The Constant REPLACEMENT. */
    public static final String REPLACEMENT = "";

    /** The instance. */
    private static UtilClass instance;

    /**
     * Instantiates a new util class.
     */
    private UtilClass() {
    }

    /**
     * Gets the single instance of UtilClass.
     *
     * @return single instance of UtilClass
     */
    public static UtilClass getInstance() {
        if (instance == null) {
            instance = new UtilClass();
        }
        return instance;
    }

    /**
     * Gets the string from description.
     *
     * @param requestContent the request content
     * @param parameterName the parameter name
     * @return the string from description
     */
    public String getStringFromDescription(SessionRequestContent requestContent, String parameterName) {
        return requestContent.getParameterByName(parameterName).isBlank()
                ? (String) requestContent.getSessionAttributeByName(parameterName)
                : requestContent.getParameterByName(parameterName).strip()
                .replace(LESS_THAN_SIGN, REPLACEMENT).replace(GREATER_THAN_SIGN, REPLACEMENT);
    }
}
