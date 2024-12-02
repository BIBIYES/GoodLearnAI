package xyz.bibiyes.goodlearnai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.bibiyes.goodlearnai.service.IFileService;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private IFileService ifileService;
    /**
     * 上传头像
     */
    @PostMapping("/upload-avatar")
    public Result uploadAvatar(@RequestBody MultipartFile photo) {
        return ifileService.uploadAvatar(photo);
    }
}
