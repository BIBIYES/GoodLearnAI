package xyz.bibiyes.goodlearnai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtils {
    
    private static final Logger log = LoggerFactory.getLogger(EmailUtils.class);
    
    private static JavaMailSender mailSender;
    
    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        EmailUtils.mailSender = mailSender;
    }

    /**
     * Author: Chen Qinfeng
     * Date: 2024-10-17
     * Updated: 2024-12-13
     */
    public static void sendAuthCodeEmail(String to, String authCode) throws EmailSendException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom("2315124408@qq.com", "好助学");
            helper.setTo(to);
            helper.setSubject("好助学【验证码】");
            
            String emailContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>验证码邮件</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            background-color: #f9f9f9;\n" +
                    "            font-family: 'Arial', sans-serif;\n" +
                    "            color: #333333;\n" +
                    "        }\n" +
                    "        .email-wrapper {\n" +
                    "            width: 100%;\n" +
                    "            background-color: #f9f9f9;\n" +
                    "            padding: 20px 0;\n" +
                    "        }\n" +
                    "        .email-container {\n" +
                    "            width: 90%;\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #ffffff;\n" +
                    "            border-radius: 10px;\n" +
                    "            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);\n" +
                    "            overflow: hidden;\n" +
                    "        }\n" +
                    "        .email-header {\n" +
                    "            background: linear-gradient(135deg, #74ebd5, #acb6e5);\n" +
                    "            padding: 30px 20px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .email-header h1 {\n" +
                    "            margin: 0;\n" +
                    "            font-size: 26px;\n" +
                    "            color: #ffffff;\n" +
                    "            display: flex;\n" +
                    "            align-items: center;\n" +
                    "            justify-content: center;\n" +
                    "        }\n" +
                    "        .email-header h1::before {\n" +
                    "            content: '📧';\n" +
                    "            margin-right: 10px;\n" +
                    "            font-size: 24px;\n" +
                    "        }\n" +
                    "        .email-body {\n" +
                    "            padding: 30px 20px;\n" +
                    "            font-size: 16px;\n" +
                    "            line-height: 1.6;\n" +
                    "            color: #555555;\n" +
                    "        }\n" +
                    "        .email-body p {\n" +
                    "            margin: 15px 0;\n" +
                    "        }\n" +
                    "        .verification-code {\n" +
                    "            display: inline-block;\n" +
                    "            background: linear-gradient(135deg, #667eea, #764ba2);\n" +
                    "            color: #ffffff;\n" +
                    "            font-size: 28px;\n" +
                    "            font-weight: bold;\n" +
                    "            padding: 15px 25px;\n" +
                    "            border-radius: 8px;\n" +
                    "            letter-spacing: 3px;\n" +
                    "            margin: 20px auto;\n" +
                    "            text-align: center;\n" +
                    "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);\n" +
                    "            display: block;\n" +
                    "            width: fit-content;\n" +
                    "        }\n" +
                    "        .email-footer {\n" +
                    "            background-color: #f1f1f1;\n" +
                    "            padding: 20px;\n" +
                    "            text-align: center;\n" +
                    "            font-size: 14px;\n" +
                    "            color: #888888;\n" +
                    "        }\n" +
                    "        .email-footer a {\n" +
                    "            color: #667eea;\n" +
                    "            text-decoration: none;\n" +
                    "        }\n" +
                    "        @media only screen and (max-width: 600px) {\n" +
                    "            .email-header h1 {\n" +
                    "                font-size: 22px;\n" +
                    "            }\n" +
                    "            .verification-code {\n" +
                    "                font-size: 24px;\n" +
                    "                padding: 12px 20px;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"email-wrapper\">\n" +
                    "        <div class=\"email-container\">\n" +
                    "            <div class=\"email-header\">\n" +
                    "                <h1>🔒 好助学 验证码</h1>\n" +
                    "            </div>\n" +
                    "            <div class=\"email-body\">\n" +
                    "                <p>您好，</p>\n" +
                    "                <p>您正在进行重要操作，请使用以下验证码完成验证：</p>\n" +
                    "                <div class=\"verification-code\">" + authCode + "</div>\n" +
                    "                <p>验证码将在 <strong>5 分钟</strong> 内失效。</p>\n" +
                    "                <p>如果这不是您本人操作，请忽略此邮件。</p>\n" +
                    "                <p>😊 感谢使用好助学！</p>\n" +
                    "            </div>\n" +
                    "            <div class=\"email-footer\">\n" +
                    "                <p>© 2024 好助学. 保留所有权利。</p>\n" +
                    "                <p>访问我们的网站：<a href=\"https://www.goodlearnai.com\">goodlearnai.com</a></p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";

            helper.setText(emailContent, true);
            
            mailSender.send(message);
            
        } catch (Exception e) {
            log.error("发送邮件失败", e);
            throw new EmailSendException("发送邮件失败", e);
        }
    }

    public static class EmailSendException extends Exception {
        public EmailSendException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
