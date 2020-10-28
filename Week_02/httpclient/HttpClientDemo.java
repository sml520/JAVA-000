package com.sxb.rocketmq.demo;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;


import java.io.IOException;

/**
 * @Author 张元亮
 * @Date 2020/10/28 21:55
 * @Version 1.0
 */
public class HttpClientDemo {

    /**
     * get请求
     *
     * @return
     */
    public static String doGet(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            System.out.println(response.getCode());
            if (response.getCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                HttpEntity entity1 = response.getEntity();
                String res = EntityUtils.toString(entity1);
                return res;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8801";
        String res = doGet(url);
        System.out.println(res);
    }
}
