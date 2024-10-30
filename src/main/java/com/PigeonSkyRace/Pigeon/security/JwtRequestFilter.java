package com.PigeonSkyRace.Pigeon.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader  = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid Authorization header.");
            return;
        }

        String token = authorizationHeader.substring(7);

        try {
            Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
            JWT.require(algorithm).build().verify(token);

            String breederId = JWT.decode(token).getClaim("breederId").asString();
            request.setAttribute("breederId", breederId);

        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Token verification failed.");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
