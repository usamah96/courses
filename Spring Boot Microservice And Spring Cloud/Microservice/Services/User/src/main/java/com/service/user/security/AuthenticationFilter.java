package com.service.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.user.entities.LoginEntity;
import com.service.user.entities.UserEntity;
import com.service.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Environment environment;
    private UserService userService;

    public AuthenticationFilter(Environment environment, UserService userService, AuthenticationManager authenticationManager){
        this.environment = environment;
        this.userService = userService;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res){
        try {
            LoginEntity loginEntity = new ObjectMapper().readValue(req.getInputStream(), LoginEntity.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginEntity.getEmail(), loginEntity.getPassword())
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth){
        String userEmail = ((User) auth.getPrincipal()).getUsername();
        UserEntity userEntity = userService.findUserByEmail(userEmail);

        String token = Jwts.builder().setSubject(userEntity.getId().toString())
                       .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiry"))))
                       .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                       .compact();
        res.addHeader("token", token);
    }
}
