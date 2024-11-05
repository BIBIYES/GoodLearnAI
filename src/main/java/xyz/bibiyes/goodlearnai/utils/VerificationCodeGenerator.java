package xyz.bibiyes.goodlearnai.utils;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class VerificationCodeGenerator {
    /**
     * Author: Chen Qinfeng
     * Date: 2024-10-17
     */
    public static String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(10000);  // 生成 0 到 9999 之间的随机数
        return String.format("%04d", code);  // 将其格式化为4位数字，保证有4位
    }

    public static boolean verifyVerificationCode(String generatedCode, String inputCode) {
        return generatedCode.equals(inputCode);
    }
}
