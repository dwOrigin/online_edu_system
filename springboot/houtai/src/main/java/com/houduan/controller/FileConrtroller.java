package com.houduan.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.houduan.common.Constants;
import com.houduan.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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

    @Value("${file-save-path}")
    private String fileSavePath;

    @GetMapping("/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam String filetype, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "请选择上传文件";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        File newfile;
        String urlpath;
        if (filetype.equals("picture")) {
            newfile = new File(fileSavePath + "picture/" + format);
            urlpath = "picture/" + format;
        } else {
            newfile = new File(fileSavePath + "video/" + format);
            urlpath = "video/" + format;
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
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + urlpath + fileUUID;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }

    @GetMapping("/download")
    public void download(@RequestParam String url, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        String[] newpath = url.split("/");
        String path = fileSavePath + newpath[3];
        for (int i = 4; i < newpath.length; i++) {
            path = path + "/" + newpath[i];
        }
        File uploadFile = new File(path);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(newpath[newpath.length - 1], "UTF-8"));
        response.setContentType("application/octet-stream");
        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @RequestMapping("/removeFile")
    public Result removeFile(String url, HttpServletRequest request) {
        String path = transfor(url);
        File file = new File(path);
        //判断此文件是否为空
        if (file != null) {
            //文件不为空，执行删除
            file.delete();
            return Result.success(Constants.CODE_200, "删除成功");
        } else {
            //为空提示错误信息
            return Result.error(Constants.CODE_500, "文件为空");
        }
    }

    public String transfor(String url) {
        String[] newpath = url.split("/");
        String path = fileSavePath + newpath[3];
        for (int i = 4; i < newpath.length; i++) {
            path = path + "/" + newpath[i];
        }
        return path;
    }
}