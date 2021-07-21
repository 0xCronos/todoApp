package com.mugoto.todoapp.shared.auth;

import com.mugoto.todoapp.shared.auth.properties.AuthProperties;
import com.mugoto.todoapp.user.domain.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Authentication  {

    private final AuthProperties authProperties;

    @Autowired
    public Authentication(AuthProperties authProperties){
        this.authProperties = authProperties;
    }

    public Map<String, String> generateToken(User user) {
        PrivateKey privateKey = authProperties.getPrivateKey();

        long timestamp = System.currentTimeMillis();

        String token = Jwts.builder().signWith(privateKey)
                .setId(user.getId().toString())
                .setIssuer("TodoApp-api")
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + authProperties.getTokenValidityInMs()))
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        return map;
    }
}
