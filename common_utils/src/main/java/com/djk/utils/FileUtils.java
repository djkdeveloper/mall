package com.djk.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * Created by dujinkai on 2018/7/28.
 * 文件工具类
 */
public class FileUtils {
    private static FileUtils ourInstance = new FileUtils();

    public static FileUtils getInstance() {
        return ourInstance;
    }

    private FileUtils() {
    }

    /**
     * 复制文件
     *
     * @param datas 图片数据
     * @param path  图片路径
     */
    public void copyFile(byte[] datas, String path) {
        try {
            FileCopyUtils.copy(datas, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
