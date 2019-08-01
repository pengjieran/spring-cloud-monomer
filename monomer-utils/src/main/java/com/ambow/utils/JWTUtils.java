package com.ambow.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用jwt加密有效数据，防止数据被修改
 */
public class JWTUtils {

    public static final String PUBLIC_KEY = "SmyC47re$ML%F72x6koqyUQ0XLQOZizt";

    public static final Key KEY = Keys.hmacShaKeyFor(PUBLIC_KEY.getBytes(Charset.forName("UTF-8")));

    /**
     * 加密数据，设置过期时间，过期时间非必须
     *
     * @param claims
     * @param expirationDate
     * @return
     */
    public static String sign(Map<String, Object> claims, Date expirationDate) {

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        if (null != expirationDate) {

            jwtBuilder.setExpiration(expirationDate);//过期时间
        }
        jwtBuilder.signWith(Keys.hmacShaKeyFor(PUBLIC_KEY.getBytes()));//加密秘钥，这里只使用了简单的秘钥加密，想更安全可以换为RSA非对称加密
        return jwtBuilder.compact();
    }

    /**
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    public static Map<String, Object> parse(String token) {

        Map<String, Object> map = new HashMap<>();

        try {

            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            claims.forEach((k, v) -> {
                map.put(k, v);
            });
        } catch (ExpiredJwtException e) {

            //token过期异常，这里不需要处理
            //e.printStackTrace();
        }

        return map;
    }
}
