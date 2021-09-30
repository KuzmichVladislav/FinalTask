package com.company.gum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes = new HashMap<>();
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes = new HashMap<>();
    private boolean invalidateSession;

    public SessionRequestContent(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        for (String s : Collections.list(attributeNames)) {
            requestAttributes.put(s, request.getAttribute(s));
        }
        requestParameters = new HashMap<>(request.getParameterMap());
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            Enumeration<String> sessionAttributesNames = session.getAttributeNames();
            Collections.list(sessionAttributesNames).forEach(attr -> {
                sessionAttributes.put(attr, session.getAttribute(attr));
            });
        }
    }

    public HttpServletRequest showRequest(HttpServletRequest request) {
        return request;
    }

    public void insertAttributes(HttpServletRequest request) {
        requestAttributes.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            request.setAttribute(key, value);
        });
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            sessionAttributes.entrySet().forEach(entry -> {
                String key = entry.getKey();
                Object value = entry.getValue();
                session.setAttribute(key, value);
            });
        }
        if (invalidateSession) {
            request.getSession().invalidate();
        }
    }

    public Object getAttributeByName(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public Object putAttribute(String attributeName, Object object) {
        return requestAttributes.put(attributeName, object);
    }

    public String[] getRequestParametersByName(String parameterName) {
        return requestParameters.get(parameterName);
    }

    public String getParameterByName(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        return parameters.length != 0 ? parameters[0] : null;
    }

    public Object getSessionAttributeByName(String sessionAttributeName) {
        return sessionAttributes.get(sessionAttributeName);
    }

    public Object putSessionAttribute(String sessionAttributeName, Object object) {
        return sessionAttributes.put(sessionAttributeName, object);
    }

    public void invalidateSession() {
        invalidateSession = true;
    }
}
