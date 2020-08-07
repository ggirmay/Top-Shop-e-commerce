package com.shop.top.gateway.gateway.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;
    HashMap<String,String> map = new HashMap<>();


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
         map.entrySet();

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}