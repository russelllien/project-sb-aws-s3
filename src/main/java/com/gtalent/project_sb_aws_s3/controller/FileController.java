package com.gtalent.project_sb_aws_s3.controller;

import com.gtalent.project_sb_aws_s3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3")
public class FileController {

//    private FileService fileService;
//
//    public  FileController(FileService fileService){
//        this.fileService = fileService;
//    }

    @Autowired
    private FileService fileService;

    // ============水桶操作===========
    // http://localhost:80/s3//bucKet/create/{bucketName}
    @PostMapping("/bucket/create/{bucketName}")
    // http://192.168.100.1/bucket/create/tsmc-logs: bucketName == tsmc-logs
    public String createBucket(@PathVariable String bucketName){
        return fileService.createBucket(bucketName);
    }

    @GetMapping("/bucket/list")
    public List<String> getBucketList(){
        return fileService.getBucketList();
    }

    // ============== 檔案操作 ===============
    @PostMapping("file/upload/{bucketName}")
    // http://192.168.100.1/bucket/create/my-bucket-1: bucketName == my-bucket-1
    public String uploadFile(@PathVariable String bucketName, @RequestParam("file") MultipartFile file){
        return fileService.uploadFile(bucketName, file);
    }

    @DeleteMapping("/file/delete/{bucketName}/{fileName}")
    // http://192.168.100.1/file/delete/my-bucket-1/20251014213456-gtalent:
    // bucketName == my-bucket-1
    // fileName == 20251014213456-gtalent
    public String deleteFile(@PathVariable String bucketName, @PathVariable String fileName){
        return fileService.deleteFile(bucketName, fileName);
    }
}

/*
    建立 Bucket: 使用 createBucket()，加上錯誤處理與存在檢查
    列出 Bucket: 使用 listBuckets()，...
    上傳檔案:     使用  putObject()，...
    下載檔案:     使用  getObjectAsBytes()，...
    刪除檔案:     使用  deleteObject()，...





 */