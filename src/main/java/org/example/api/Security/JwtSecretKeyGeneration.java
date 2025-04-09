package org.example.api.Security;


import java.util.Base64;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class JwtSecretKeyGeneration {

    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
        String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Key generada:" + encodeKey);
    }

}