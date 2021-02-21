package com.alkaid.minio.controller;

import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RequestMapping("/minio")
@RestController
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @GetMapping(value="/createBucket/{bucket}")
    public List<String> createBucket(@PathVariable(value = "bucket") String bucket) throws Exception {

        boolean isExist = minioClient.bucketExists(bucket);
        if(isExist) {
            log.info("Bucket {} already exists.", bucket);
        } else {
            minioClient.makeBucket(bucket);
        }
        return null;
    }

    @GetMapping(value="/putFile/{bucket}/{file}")
    public String putFile(@PathVariable(value = "bucket") String bucket, @PathVariable(value = "file") String file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        file = "G:\\free jok\\make music\\偶得\\selection\\a_best\\20120703-MIC00016-0005a.WAV";
        minioClient.putObject(bucket, file.substring(file.lastIndexOf("\\")+1), file);
        return null;
    }

    @GetMapping(value="/getFile/{bucket}/{file}")
    @ResponseBody
    public InputStream getFile(HttpServletResponse response, @PathVariable(value = "bucket") String bucket, @PathVariable(value = "file") String file) {
        InputStream is = null;
        try {
            is = minioClient.getObject(bucket, file);
        } catch(MinioException | NoSuchAlgorithmException | IOException | XmlPullParserException | InvalidKeyException e) {
            log.error("error: {}", e.getMessage());
        }
        return is;
    }

    @GetMapping(value="/download/{bucket}/{file}")
    @ResponseBody
    public void download(HttpServletResponse response, @PathVariable(value = "bucket") String bucket, @PathVariable(value = "file") String file) {
        File path = null;
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file, "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(minioClient.getObject(bucket, file));
            int i = bis.read(buff, 0, 1024);
            while (i != -1) {
                os.write(buff, 0, i);
                os.flush();
                i = bis.read(buff, 0, 1024);
            }
        } catch (FileNotFoundException e1) {
            //e1.getMessage()+"系统找不到指定的文件";
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }


}
