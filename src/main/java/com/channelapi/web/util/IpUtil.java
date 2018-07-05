package com.channelapi.web.util;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huwei on 2018/7/5.
 */
public class IpUtil {

    //ipv4检查
    public static boolean isIPv4(String str) {
        if (str == null || !Pattern.matches(
                "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$",
                str)) {
            return false;
        }
        return true;
    }

    //ipv6检查
    public static boolean isIPv6(String str) {
        return isIPV6Std(str) || isIPV6Compress(str);
    }

    //ipv6十六进制表示检查
    public static boolean isIPV6Std(String str) {
        if (str == null || !Pattern.matches("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$", str)) {
            return false;
        }
        return true;
    }

    //ipv6压缩法表示检查
    public static boolean isIPV6Compress(String str) {
        if (str == null || !Pattern.matches(
                "^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$",
                str)) {
            return false;
        }
        return true;
    }

    //ip有效性检查
    public static boolean isValidIp(String str) {
        return isIPv4(str) || isIPv6(str);
    }


    public static String getRequestIp(HttpServletRequest request) {
        String ipAddress = null;
        if (request != null) {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress != null && ipAddress.length() > 15) {
                String[] aStr = ipAddress.split(",");
                ipAddress = aStr[0];
            }
            if (!isValidIp(ipAddress)) {
                ipAddress = request.getHeader("X-Real-IP");
            }
            if (!isValidIp(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (!isValidIp(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (!isValidIp(ipAddress)) {
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
            }
            if (!isValidIp(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
        }
        return ipAddress;
    }

}
