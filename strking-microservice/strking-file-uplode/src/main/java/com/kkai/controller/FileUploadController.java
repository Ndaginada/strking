package com.kkai.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 上传文件控制器。
 */
@RestController
public class FileUploadController {



    /**
     * 上传文件。
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        String savePath = "strking-file-uplode/src/main/resources/static/img/";
        String targetSavePath = "strking-file-uplode/target/classes/static/img/";
        String fileName = "headIcon_"+ file.getOriginalFilename();
        File fileToSave = new File(savePath + fileName);
        FileCopyUtils.copy(bytes, fileToSave);
        //保存到target一份  使其可以访问
        File fileToSaveTarget = new File(targetSavePath + fileName);
        FileCopyUtils.copy(bytes, fileToSaveTarget);
        return fileToSave.getAbsolutePath();
    }

    @RequestMapping(value = "/article_upload", method = RequestMethod.POST)
    @ResponseBody
    public String article_upload(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        String savePath = "strking-file-uplode/src/main/resources/static/img/";
        String targetSavePath = "strking-file-uplode/target/classes/static/img/";
        String fileName = "articleTitleImg_"+ file.getOriginalFilename();
        File fileToSave = new File(savePath + fileName);
        FileCopyUtils.copy(bytes, fileToSave);
        //保存到target一份  使其可以访问
        File fileToSaveTarget = new File(targetSavePath + fileName);
        FileCopyUtils.copy(bytes, fileToSaveTarget);
        return fileToSave.getAbsolutePath();
    }
}