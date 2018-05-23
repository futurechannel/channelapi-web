package com.channelapi.web.util;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URLEncoder;

public class FileUtil {

    /**
     * 下载文件时，针对不同浏览器，进行附件名的编码
     */
    public static String encodeDownloadFileName(String filename, String agent) throws IOException {
        if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = "=?UTF-8?B?" + new BASE64Encoder().encode(filename.getBytes("utf-8")) + "?=";
            filename = filename.replaceAll("\r\n", "");
        } else {
            // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        return filename;
    }
}
