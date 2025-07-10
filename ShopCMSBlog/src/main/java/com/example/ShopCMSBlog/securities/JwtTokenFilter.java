package com.example.ShopCMSBlog.securities;

import com.example.ShopCMSBlog.exceptions.AppException;
import com.example.ShopCMSBlog.services.impl.UserDetailServiceImpl;
import com.example.ShopCMSBlog.utils.AuthUtils;
import com.example.ShopCMSBlog.utils.UrlUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenComponent jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (UrlUtils.PUBLIC_URLS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }


        if (!AuthUtils.hasAuthorizationBearer(request)) {
            throw new AppException("Miss authentication");
        }

        String token = AuthUtils.getTokenFromHeader(request);

        if (!jwtUtil.validateAccessToken(token)) {
            throw new AppException("Token is invalid");
        }

        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }



    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token) {
        Claims claims = jwtUtil.parseClaims(token);
        String userName = (String) claims.get(Claims.SUBJECT);
        return userDetailService.loadUserByUsername(userName);
    }
}