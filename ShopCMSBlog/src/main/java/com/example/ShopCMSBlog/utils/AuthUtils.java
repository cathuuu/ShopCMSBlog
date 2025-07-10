package com.example.ShopCMSBlog.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;


public class AuthUtils {

    private AuthUtils() {
    }

    public static String getTokenFromHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if (!hasAuthorizationBearer(request)) {
            throw new SecurityException("Missing or invalid Authorization header");
        }
        return authorizationHeader.substring(7);
    }

    public static boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }

        return true;
    }

    public static Map<String, String> getHeaderDefaultWithToken(String token){
        Map<String, String> headers =  new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        return headers;
    }


}