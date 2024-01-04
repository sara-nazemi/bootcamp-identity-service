package com.example.bootcampisservice.security;

import com.example.bootcampisservice.sampleResponse.IdentityResponse;
import com.example.bootcampisservice.util.ResourceBundleUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Autowired
    private ResourceBundleUtil resourceBundleUtil;

    @Serial
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        IdentityResponse<Object> unauthorized = IdentityResponse.builder()
                .date(new Date())
                .hasError(true)
                .message(resourceBundleUtil.getMessage("bank.UNAUTHORIZED", request.getHeader("lang")))
                .code("UNAUTHORIZED")
                .build();

        String json = new ObjectMapper().writeValueAsString(unauthorized);

        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}