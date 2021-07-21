package com.mugoto.todoapp.shared.auth.properties;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
public class AuthProperties {
    private KeyPair keyPair;
    private int tokenValidityInHours;

    public AuthProperties(){
        this.keyPair = createNewKeyPair();
        this.tokenValidityInHours = 2;
    }

    public KeyPair createNewKeyPair() {
        return Keys.keyPairFor(SignatureAlgorithm.RS256);
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public long getTokenValidityInMs() {
        return (long) tokenValidityInHours * 60 * 60 * 1000;
    }
}
