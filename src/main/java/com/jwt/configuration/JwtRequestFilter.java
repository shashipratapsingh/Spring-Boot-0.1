package com.jwt.configuration;

import com.jwt.Util.JwtUtil;
import com.jwt.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl service;


    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

            String jwtToken=null;
            String userName=null;
            final String header=request.getHeader("/Authorization");
            if(header!=null && header.startsWith("Bearer ")){
                jwtToken=header.substring(7);
                try {
                    userName =jwtUtil.getUserNameFromToken(jwtToken);

                }catch (IllegalArgumentException e){
                    System.out.println("Unable to get jwt token");
                }catch (ExpiredJwtException e)
                {
                    System.out.println("jwt expired token");
                }
        } else {
                System.out.println("jwt token don't start with bearer");
            }
            if(userName!= null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails= service.loadUserByUsername(userName);
                if(jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

            filterChain.doFilter(request,response);
    }
}
