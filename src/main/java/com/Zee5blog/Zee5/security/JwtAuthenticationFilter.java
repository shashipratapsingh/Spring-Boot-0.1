package com.Zee5blog.Zee5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestToken=request.getHeader("Authorization");

        System.out.println("requestToken");

        String username=null;
        String token=null;

        if (request!=null && requestToken.startsWith("Bearer"))
        {
            token=requestToken.substring(7);
            username=this.jwtTokenHelper.getUsernameFromToken(token);

        }else
        {
            System.out.println("jwt token does not begin with bearer");
        }
    }
}
