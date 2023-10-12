package com.fullstackshopping.easyshopping.util;

import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {

    /**
     * Generates an RSA key pair.
     *
     * @return A KeyPair containing the public and private keys.
     * @throws NoSuchAlgorithmException      if the RSA algorithm is not available.
     * @throws InvalidParameterException     if the key size is invalid.
     */
    public static KeyPair generateRsaKey() throws InvalidParameterException, NoSuchAlgorithmException {
        KeyPair keyPair;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
}
