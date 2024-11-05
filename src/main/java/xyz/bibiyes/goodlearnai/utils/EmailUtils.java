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
            mail.setHostName("smtp.qq.com");
            mail.setAuthentication("3416939515@qq.com", "dexcjsejmuppcjjj");
            mail.setFrom("3416939515@qq.com", "hzx");
            mail.setSSLOnConnect(false);
            mail.addTo(email);
            mail.setCharset("UTF-8");
            mail.setSubject("好助学【验证码】");
            String emailContent = "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>验证码邮件</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f4f4f4;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            width: 100%;\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 20px auto;\n" +
                    "            background-color: #fff;\n" +
                    "            padding: 20px;\n" +
                    "            border-radius: 8px;\n" +
                    "            box-shadow: 0 2px 5px rgba(0,0,0,0.1);\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            color: #333;\n" +
                    "            font-size: 24px;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            color: #666;\n" +
                    "            font-size: 16px;\n" +
                    "        }\n" +
                    "        .code {\n" +
                    "            font-size: 22px;\n" +
                    "            color: #4CAF50;\n" +
                    "            font-weight: bold;\n" +
                    "            background-color: #f0f0f0;\n" +
                    "            padding: 10px;\n" +
                    "            border-radius: 5px;\n" +
                    "            text-align: center;\n" +
                    "            letter-spacing: 2px;\n" +
                    "            margin: 20px 0;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            font-size: 12px;\n" +
                    "            color: #999;\n" +
                    "            text-align: center;\n" +
                    "            margin-top: 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1>好助学验证码</h1>\n" +
                    "        <p>您好，</p>\n" +
                    "        <p>您正在进行重要操作，您的验证码为：</p>\n" +
                    "        <div class=\"code\">" + authCode + "</div>\n" +
                    "        <p>请在 5 分钟内输入验证码进行验证。若非您本人操作，请忽略此邮件。</p>\n" +
                    "        <p>感谢您的使用！</p>\n" +
                    "        <div class=\"footer\">\n" +
                    "            &copy; 2024 重庆工业职业技术学院. 保留所有权利。\n" +
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
