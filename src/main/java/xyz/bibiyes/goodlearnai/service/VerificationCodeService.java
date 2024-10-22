package xyz.bibiyes.goodlearnai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.exception.CustomException;
import xyz.bibiyes.goodlearnai.utils.EmailUtils;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.utils.VerificationCodeGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    /**
     * Author: Chen Qinfeng
     * Date: 2024-10-17
     */
    private static final Logger logger = LoggerFactory.getLogger(VerificationCodeService.class);
    public static ConcurrentHashMap<String, String> verificationCodeCache = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void sendVerificationCode(String email) {
        String verificationCode = VerificationCodeGenerator.generateVerificationCode();
        CompletableFuture.runAsync(() -> {
            try {
                if(verificationCodeCache.get(email) != null){
                    throw new CustomException("请勿重复发送验证码");
                }
                EmailUtils.sendAuthCodeEmail(email, verificationCode);
                verificationCodeCache.put(email, verificationCode);
                logger.info("验证码已生成并存储到缓存中，邮箱：{}，验证码：{}", email, verificationCode);

                // 设置定时器删除验证码
                scheduler.schedule(() -> {
                    verificationCodeCache.remove(email);
                    logger.info("验证码已从缓存中移除，邮箱：{}", email);
                }, 1, TimeUnit.MINUTES);

                logger.info("验证码已成功发送到邮箱：{}", email);
            } catch (Exception e) {
                logger.error("发送验证码邮件失败，邮箱：{}", email, e);
            }
        });
    }

    public static boolean verifyVerificationCode(String email, String inputCode) {
        logger.info("正在验证邮箱：{}，输入的验证码：{}", email, inputCode);
        String cachedCode = verificationCodeCache.get(email);
        logger.info("当前缓存中的验证码：{}", cachedCode);

        if (cachedCode == null) {
            logger.info("没有找到对应邮箱：{} 的缓存验证码", email);
            return false;
        }

        boolean isCodeValid = cachedCode.equals(inputCode);
        if (isCodeValid) {
            logger.info("验证码验证成功，邮箱：{}", email);
        } else {
            logger.info("验证码验证失败，邮箱：{}，输入的验证码：{}，缓存中的验证码：{}", email, inputCode, cachedCode);
        }
        return isCodeValid;
    }
}
