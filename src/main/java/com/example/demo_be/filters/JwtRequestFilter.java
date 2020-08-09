package com.example.demo_be.filters;

import com.example.demo_be.models.JwtUser;
import com.example.demo_be.services.UserService;
import com.example.demo_be.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //request.getHeader("User-Agent");
        //request.getHeader("Accept-Language");

        final String authorizationHeader = request.getHeader("Authorization");


        if (authorizationHeader != null && isValidSchemaToken(authorizationHeader)) {
            String username = null;
            String jwt = null;

            jwt = authorizationHeader.substring(7);
            username = jwtUtils.getUsernameFromToken(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                JwtUser userDetails = jwtUtils.getUserDetails(jwt);
                if (jwtUtils.validateToken(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        chain.doFilter(request, response);
        this.resetAuthenticationAfterRequest();
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private boolean isValidSchemaToken(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            return false;
        }
        //verifica del passaggio di un token con lo schema corretto per filtrare chiamate errate alle API
        Pattern pattern = Pattern.compile("([A-Za-z]+) ([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*)");
        Matcher m = pattern.matcher(authorizationHeader);
        return m.matches() && m.group(1).equalsIgnoreCase("BEARER");
    }
}

