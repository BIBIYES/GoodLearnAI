package xyz.bibiyes.goodlearnai.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.bibiyes.goodlearnai.utils.Result;

public interface IFileService {
    Result uploadAvatar(MultipartFile photo);
}
