package com.houduan.controller;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileConrtroller {


    private String PictureUploadPath = "springboot/houtai/src/main/resources/picture/";
    private String VideoUploadPath = "springboot/houtai/src/main/resources/video/";

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam String filetype) {
        if (file.isEmpty()) {
            return "请选择上传文件";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        File newfile;
        if (filetype.equals("picture")) {
            newfile = new File(PictureUploadPath + format);
        } else {
            newfile = new File(VideoUploadPath + format);
        }
        if (!newfile.isDirectory()) {
            newfile.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        try {
            String path = newfile.getAbsolutePath() + File.separator + fileUUID;
            File newFile = new File(path);
            file.transferTo(newFile);
            System.out.println(1);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }
    @GetMapping("/download")
    public void download(@RequestParam String path, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(path);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        String[]uuidarray=path.split("\\\\");
        String[]name=uuidarray[uuidarray.length-1].split(".");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name[0], "UTF-8"));
        response.setContentType("application/octet-stream");
        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

}