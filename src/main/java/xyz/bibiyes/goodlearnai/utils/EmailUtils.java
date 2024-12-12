package xyz.bibiyes.goodlearnai.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {

    /**
     * Author: Chen Qinfeng
     * Date: 2024-10-17
     */
    public static void sendAuthCodeEmail(String email, String authCode) throws EmailSendException {
        try {
            HtmlEmail mail = new HtmlEmail();
            mail.setHostName("smtp.163.com");
            mail.setAuthentication("mousehaocat@163.com", "NBiANiyppQADxYXg");
            mail.setFrom("mousehaocat@163.com", "好助学");
            mail.setSSLOnConnect(false);
            mail.addTo(email);
            mail.setCharset("UTF-8");
            mail.setSubject("好助学【验证码】");

            String emailContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>验证码邮件</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f4f4f9;\n" +
                    "            color: #333;\n" +
                    "            padding: 20px;\n" +
                    "            margin: 0;\n" +
                    "        }\n" +
                    "        .email-container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #fff;\n" +
                    "            border-radius: 10px;\n" +
                    "            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);\n" +
                    "            padding: 20px;\n" +
                    "        }\n" +
                    "        .email-header {\n" +
                    "            text-align: center;\n" +
                    "            padding-bottom: 20px;\n" +
                    "            border-bottom: 1px solid #ddd;\n" +
                    "        }\n" +
                    "        .email-header h1 {\n" +
                    "            font-size: 24px;\n" +
                    "            color: #4CAF50;\n" +
                    "        }\n" +
                    "        .email-content {\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px;\n" +
                    "        }\n" +
                    "        .verification-code {\n" +
                    "            font-size: 32px;\n" +
                    "            font-weight: bold;\n" +
                    "            color: #FF5722;\n" +
                    "            padding: 10px 20px;\n" +
                    "            background-color: #f1f1f1;\n" +
                    "            border-radius: 8px;\n" +
                    "            margin: 20px 0;\n" +
                    "        }\n" +
                    "        .email-footer {\n" +
                    "            text-align: center;\n" +
                    "            padding-top: 20px;\n" +
                    "            font-size: 12px;\n" +
                    "            color: #777;\n" +
                    "            border-top: 1px solid #ddd;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"email-container\">\n" +
                    "        <div class=\"email-header\">\n" +
                    "            <h1>好助学 验证码</h1>\n" +
                    "        </div>\n" +
                    "        <div class=\"email-content\">\n" +
                    "            <p>您好，</p>\n" +
                    "            <p>您正在进行重要操作，验证码如下：</p>\n" +
                    "            <div class=\"verification-code\">" + authCode + "</div>\n" +
                    "            <p>请在 5 分钟内输入验证码进行验证。<br>若非您本人操作，请忽略此邮件。</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"email-footer\">\n" +
                    "            © 2024 重庆工业职业技术学院. 保留所有权利。\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";
            mail.setHtmlMsg(emailContent);
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
