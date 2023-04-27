package com.lrc.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.lrc.common.Result;
import com.lrc.entity.NxSystemFileInfo;
import com.lrc.exception.CustomException;
import com.lrc.service.NxSystemFileInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/files")
public class NxSystemFileInfoController {

    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/static/files";
    @Resource
    private NxSystemFileInfoService nxSystemFileInfoService;


    @PostMapping("/upload")
    public Result<NxSystemFileInfo> upload(MultipartFile file) throws IOException {
        String originalName = file.getOriginalFilename();
        if (originalName == null){
            return Result.error("1001","文件名不能为空");
        }
        if (!originalName.contains("png") && !originalName.contains("jpg")&& !originalName.contains("jpeg") && !originalName.contains("gif")){
            return Result.error("1002","只能上传图片");
        }
        String fileName = FileUtil.mainName(originalName)+System.currentTimeMillis()+"."+FileUtil.extName(originalName);
        FileUtil.writeBytes(file.getBytes(),BASE_PATH+fileName);

        NxSystemFileInfo info = new NxSystemFileInfo();
        info.setOriginname(originalName);
        info.setFilename(fileName);
        NxSystemFileInfo addInfo = nxSystemFileInfoService.addNxSystemFile(info);
        if (addInfo != null){
            return Result.success(addInfo);
        }
        return Result.error("1003","上传失败");
    }

    @PutMapping
    public Result update(@RequestBody NxSystemFileInfo nxSystemFileInfo){
        nxSystemFileInfoService.updateNxSystemFile(nxSystemFileInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        nxSystemFileInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<NxSystemFileInfo> findNxSystemFileById(@PathVariable Integer id){
        NxSystemFileInfo nxSystemFileById = nxSystemFileInfoService.findNxSystemFileById(id);
        return Result.success(nxSystemFileById);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {
        if (StrUtil.isBlank(id) || "null".equals(id)) {
            throw new CustomException("1001", "您未上传文件");
        }
        NxSystemFileInfo nxSystemFileById = nxSystemFileInfoService.findNxSystemFileById(Integer.valueOf(id));
        if (nxSystemFileById == null){
            throw new CustomException("1001","没有找到该文件");
        }
        byte[] bytes = FileUtil.readBytes(BASE_PATH+nxSystemFileById.getFilename());
        response.reset();
        response.addHeader("Content-Disposition","attachment;finame="+ URLEncoder.encode(nxSystemFileById.getOriginname(),"UTF-8"));
        response.addHeader("Content-Length",""+bytes.length);
        BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(bytes);
        toClient.flush();
        toClient.close();
    }
}
