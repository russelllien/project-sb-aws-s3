package com.gtalent.project_sb_aws_s3.service;

import com.gtalent.project_sb_aws_s3.file.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    // 建構式注入
//    private S3Client s3Client;
//
//    public FileServiceImpl(S3Client s3Client) {
//        this.s3Client = s3Client;
//    }
//

    // 欄位式注入
    @Autowired
    private S3Client s3Client;

    @Override
    public String createBucket(String bucketName) {
        s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        return "Bucket created: " + bucketName;

    }

    @Override
    public List<String> getBucketList() {
        return s3Client.listBuckets()
                .buckets()
                .stream()
                .map(Bucket::name)
                .toList();
        // ["my-bucket-1", "tsmc-log", "gtalent-1"]
    }

    @Override
    public String uploadFile(String bucketName, MultipartFile file){

        // UUID.randomUUID() 產生隨機字串，避免檔名重複產生檔案覆蓋
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try{
            byte[] fileBytes = file.getBytes();

            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
            // RequestBody.fromBytes(fileBytes) 把你要上傳的檔案內容包裝成AWS 可以接受的格式
            s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
            // fromByte(byte []): 傳送byte，檔案在記憶體中，適合Web 上傳
            // fromFile(Path): 傳送本地檔案，檔案在硬碟中
            // fromInputStream(): 傳送串聯資料
            // fromString(String): 傳送純文字
            return "File upload:" + fileName;
        }catch (IOException e){
            return "File upload failed" + e.getMessage();
        }
    }

    @Override
    public File downloadFile(String bucketName, String fileName){
            // getObjectAsBytes() 取得檔案內容並轉成byte
            byte[] bytes = s3Client.getObjectAsBytes(GetObjectRequest.builder()
                    .bucket(bucketName).key(fileName).build()).asByteArray();
            return new File(fileName, bytes);
    }

    @Override
    public String deleteFile(String bucketName, String fileName){
        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(fileName).build());
        return "File deleted:" + fileName;
    }


}
