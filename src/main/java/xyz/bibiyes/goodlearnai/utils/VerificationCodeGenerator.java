package xyz.bibiyes.goodlearnai.utils;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.math.BigInteger;

@Service
public class VerificationCodeGenerator {
    /**
     * Author: Chen Qinfeng
     * Date: 2024-10-17
     */
    public static String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32).substring(0, 6);
    }
    public static boolean verifyVerificationCode(String generatedCode, String inputCode) {
        return generatedCode.equals(inputCode);
    }
}
