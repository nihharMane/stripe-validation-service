package com.Validation.payments.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HmacSHA256Util {

    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String DEFAULT_SECRET = "my-secret-key";

    // ✅ 1. Method with only JSON (used in service)
    public static String generateHmac(String jsonInput) {
        return generateHmac(jsonInput, DEFAULT_SECRET);
    }

    // ✅ 2. Method with JSON + secret (used in tests)
    public static String generateHmac(String jsonInput, String secret) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);

            SecretKeySpec secretKey =
                    new SecretKeySpec(secret.getBytes(), HMAC_SHA256);

            mac.init(secretKey);

            byte[] rawHmac = mac.doFinal(jsonInput.getBytes());

            return Base64.getEncoder().encodeToString(rawHmac);

        } catch (Exception e) {
            throw new RuntimeException("Error while generating HMAC", e);
        }
    }
}