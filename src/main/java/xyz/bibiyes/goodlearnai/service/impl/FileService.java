package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.bibiyes.goodlearnai.service.IFileService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.upload.url-prefix}")
    private String urlPrefix;

    @Override
    public Result uploadAvatar(MultipartFile photo) {
        // 检查文件是否为空
        if (photo.isEmpty()) {
            return Result.error("上传失败，请选择文件");
        }

        // 获取原始文件名
        String originalFilename = photo.getOriginalFilename();
        // 获取文件后缀
        String suffix = null;
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        // 生成新的文件名
        String fileName = UUID.randomUUID().toString() + suffix;

        // 创建目标文件对象
        File dest = new File(uploadPath + fileName);
        
        // 确保目标目录存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            // 保存文件
            photo.transferTo(dest);
            // 返回文件访问URL路径
            String fileUrl = urlPrefix + fileName;
            return Result.success("上传成功", fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }
}
