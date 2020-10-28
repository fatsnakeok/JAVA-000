package java0.nio01;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: fatsnake
 * @Description": 请求8801
 * @Date:2020/10/27 22:25
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class TestGet {
    public static void main(String[] args) {

        sendRequest("http://localhost:8801");

        // 将服务端调整sleep，测试请求是否Connection reset
//        for (int i = 0; i < 300; i++) {
//            sendRequest("http://localhost:8801");
//        }
    }

    public static void sendRequest(String url) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("获取请求体内容：" + content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                //相当于关闭浏览器
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
