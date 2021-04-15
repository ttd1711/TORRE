/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.dz.apigw.properties.AppProperties;
import com.dz.apigw.utils.IConfig;
import com.dz.apigw.utils.ILogger;
/**
 *
 * @author Administrator
 */
@Component
public class AuthorizationIPAddressFilter extends OncePerRequestFilter implements ILogger, IConfig{
    @Autowired
    private AppProperties appProperties;
    
    private final JwtTokenUtil jwtTokenUtil;
    
    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
        MediaType.valueOf("text/*"),
        MediaType.APPLICATION_FORM_URLENCODED,
        MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.valueOf("application/*+json"),
        MediaType.valueOf("application/*+xml"),
        MediaType.MULTIPART_FORM_DATA
    );    
    public AuthorizationIPAddressFilter(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    private String getIpAddress(HttpServletRequest request) {
        String remoteAdr = "";
        if (request != null) {
            remoteAdr = request.getHeader("X-FORWADED-FOR");
            if (remoteAdr == null || "".equals(remoteAdr)) {
                remoteAdr = request.getRemoteAddr();
            }
        }
        return remoteAdr;
    }    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        doFilterWrapped(wrapRequest(request), wrapResponse(response), chain);
    }


    protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            beforeRequest(request, response);

            iLogger.trace("Checking Remote Ip Adress '{}' for the request", request.getRemoteAddr());
            if(this.jwtTokenUtil.ipFilter(appProperties.getUser().getIpaddress(), request.getRemoteAddr())){ // ip is valid        
                iLogger.trace("Ip Adress '{}' is valid", request.getRemoteAddr());
            }
            else{
                iLogger.error("Ip Adress '{}' is not allowed", request.getRemoteAddr());
            }

            filterChain.doFilter(request, response);
        }
        finally {
            afterRequest(request, response);
            response.copyBodyToResponse();
        }
    }

    protected void beforeRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        logRequestHeader(request, request.getRemoteAddr() + "|>");
    }

    protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        if (iLogger.isInfoEnabled()) {
            logRequestBody(request, request.getRemoteAddr() + "|>");
            logResponse(response, request.getRemoteAddr() + "|<");
        }
    }

    private static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            iLogger.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
        } else {
            iLogger.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
        }
        Collections.list(request.getHeaderNames()).forEach(headerName ->
            Collections.list(request.getHeaders(headerName)).forEach(headerValue ->
                iLogger.info("{} {}: {}", prefix, headerName, headerValue)));
        iLogger.info("{}", prefix);
    }

    private static void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
        }
    }

    private static void logResponse(ContentCachingResponseWrapper response, String prefix) {
        int status = response.getStatus();
        iLogger.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
        response.getHeaderNames().forEach(headerName ->
            response.getHeaders(headerName).forEach(headerValue ->
                iLogger.info("{} {}: {}", prefix, headerName, headerValue)));
        iLogger.info("{}", prefix);
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0 && content.length <= 5000) {
            logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
        }
        else if (content.length > 2000) {
            
        }
    }

    private static void logContent(byte[] content, String contentType, String contentEncoding, String prefix) {
        MediaType mediaType = MediaType.valueOf(contentType);
        boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                String contentString = new String(content, contentEncoding);
                Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> iLogger.info("{} {}", prefix, line));
            } catch (UnsupportedEncodingException e) {
                iLogger.info("{} [{} bytes content]", prefix, content.length);
            }
        } else {
            iLogger.info("{} [{} bytes content]", prefix, content.length);
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }    
}
