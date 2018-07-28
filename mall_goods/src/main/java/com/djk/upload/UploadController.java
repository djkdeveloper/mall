package com.djk.upload;

import com.djk.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/28.
 * 上传控制器
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 文件存放路径
     */
    @Value("${upload.path}")
    private String path;

    /**
     * 访问路径
     */
    @Value("${upload.accessPath}")
    private String accessPath;

    /**
     * 上传文件
     *
     * @param request 请求实体
     * @return 返回文件地址
     */
    @PostMapping
    public String upload(MultipartHttpServletRequest request) throws Exception {
        MultipartFile multipartFile = request.getFile("file");
        if (Objects.isNull(multipartFile)) {
            return "";
        }
        // 文件名称
        String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
        FileUtils.getInstance().copyFile(multipartFile.getBytes(), path + fileName);

        return accessPath + fileName;
    }
}
