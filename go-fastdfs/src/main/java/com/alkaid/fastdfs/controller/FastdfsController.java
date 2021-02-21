package com.alkaid.fastdfs.controller;

import cn.hutool.http.HttpUtil;
import com.alkaid.fastdfs.config.FastConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class FastdfsController {

    @Autowired
    private FastConfig config;

    @GetMapping(value="/index")
    public String index(HashMap<String, Object> map) {
        return "index";
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file) {
        String result = "";
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                    file.getOriginalFilename());

            Map<String, Object> params = new HashMap<>();
            params.put("file", isr);
            params.put("path", "86501729");
            params.put("output", "json");
            String resp = HttpUtil.post(config.getUrl(), params);
            log.info("resp: {}", resp);
            result = resp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/upload1")
    public String upload1(MultipartFile file) {
        String result = "";
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = null;
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(200000)
                    .setSocketTimeout(2000000)
                    .build();
            HttpPost httpPost = new HttpPost(config.getUrl());
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(Charset.forName("UTF-8"))
                    .addTextBody("output", "json")
                    .addBinaryBody("file", file.getInputStream(),
                            ContentType.DEFAULT_BINARY, file.getOriginalFilename());
            httpPost.setEntity(multipartEntityBuilder.build());
            httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String respStr = EntityUtils.toString(httpResponse.getEntity());
                System.out.println(respStr);
                result = respStr;
            }

            httpClient.close();
            httpResponse.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/upload2")
    public String upload2(MultipartFile file) {
        String result = "";
        try {
            OkHttpClient httpClient = new OkHttpClient();
            MultipartBody multipartBody = new MultipartBody.Builder().
                    setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getOriginalFilename(),
                            RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"),
                                    file.getBytes()))
                    .addFormDataPart("output", "json")
                    .build();

            Request request = new Request.Builder()
                    .url(config.getUrl())
                    .post(multipartBody)
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    result = body.string();
                    log.info(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
