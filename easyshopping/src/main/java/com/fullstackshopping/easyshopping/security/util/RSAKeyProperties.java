package com.fullstackshopping.easyshopping.security.util;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * Component responsible for managing private and public keys for JWT.
 * Used by WebSecurityConfig to configure JWT encoder and decoder.
 */
@Component
public class RSAKeyProperties {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAKeyProperties() {
        initializeKeys();
    }

    private void initializeKeys() {
        try {
            KeyPair pair = KeyGenerator.generateRsaKey();
            this.publicKey = (RSAPublicKey) pair.getPublic();
            this.privateKey = (RSAPrivateKey) pair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            // throw exception
            throw new RuntimeException("Failed to generate RSA key pair.", e);
        }
    }

    public RSAPublicKey getPublicKey() {
        return this.publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
