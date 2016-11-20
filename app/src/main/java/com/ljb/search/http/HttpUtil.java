package com.ljb.search.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：ljb
 * 时间：2016/11/19 14:01
 * 邮箱：563773219@qq.com
 * 描述：
 */
public class HttpUtil {

    private static HttpURLConnection httpURLConnection;

    public static String getData(String path) {

        try {
            URL url = new URL(path);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            if (200 == httpURLConnection.getResponseCode()) {
                InputStream is = httpURLConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                is.close();
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
