package me.dragon.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.UUID;

/**
 * Created by dragon on 4/18/2017.
 */
public class SaveInstagramPicByURL {
    public static OSSUploadUtil ossUploadUtil = new OSSUploadUtil();
    /**
     * 根据网络地址保存图片
     */
    public static String saveToFile(String destUrl) {
        String picPath = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080));
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection(proxy);
            httpUrl.setRequestProperty("http.proxyHost", "127.0.0.1");
            httpUrl.setRequestProperty("http.proxyPort", "1080");
            httpUrl.setConnectTimeout(60000);
            httpUrl.setReadTimeout(60000);
            httpUrl.connect();
            String fileName = getUUIDFileName() + ".jpg";
            InputStream ips = ossUploadUtil.compress(httpUrl.getInputStream());
            picPath = ossUploadUtil.uploadFile(ips, fileName, "instagram");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpUrl.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return picPath;
    }

    // UUID命名
    public static String getUUIDFileName() {
        return UUID.randomUUID().toString();
    }
}
