package com.ambow.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                                                                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        
        return ipAddress;
    }

    public static void main(String[] args) {
        try {
            //用 getLocalHost() 方法创建的InetAddress的对象
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostName());//主机名
            System.out.println(address.getCanonicalHostName());//主机别名
            System.out.println(address.getHostAddress());//获取IP地址
            System.out.println("===============");

            //用域名创建 InetAddress对象
            InetAddress address1 = InetAddress.getByName("www.wodexiangce.cn");
            //获取的是该网站的ip地址，如果我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地址
            System.out.println(address1.getHostName());//www.wodexiangce.cn
            System.out.println(address1.getCanonicalHostName());//124.237.121.122
            System.out.println(address1.getHostAddress());//124.237.121.122
            System.out.println("===============");

            //用IP地址创建InetAddress对象
            InetAddress address2 = InetAddress.getByName("220.181.111.188");
            System.out.println(address2.getHostName());//220.181.111.188
            System.out.println(address2.getCanonicalHostName());//220.181.111.188
            System.out.println(address2.getHostAddress());//220.181.111.188
            System.out.println("===============");

            //根据主机名返回其可能的所有InetAddress对象
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress addr : addresses) {
                System.out.println(addr);
                //www.baidu.com/220.181.111.188
                //www.baidu.com/220.181.112.244
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}