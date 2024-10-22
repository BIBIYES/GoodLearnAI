package xyz.bibiyes.goodlearnai.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtils {

    /**
     * Author: Chen Qinfeng
     * Date: 2024-10-17
     */
    public static void sendAuthCodeEmail(String email, String authCode) throws EmailSendException {
        try {
            SimpleEmail mail = new SimpleEmail();
            mail.setHostName("smtp.qq.com");
            mail.setAuthentication("3416939515@qq.com", "dexcjsejmuppcjjj");
            mail.setFrom("3416939515@qq.com", "hzx");
            mail.setSSLOnConnect(false);
            mail.addTo(email);
            mail.setSubject("验证码");
            mail.setMsg("尊敬的用户:你好!\n 登录验证码为:" + authCode + "\n" + "(有效期为一分钟)");
            mail.send();
        } catch (EmailException e) {
            throw new EmailSendException("发送邮件失败", e);
        }
    }

    public static class EmailSendException extends Exception {
        public EmailSendException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}