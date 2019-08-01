package com.ambow.gateway.filter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.ambow.constant.TokenConstant;
import com.ambow.gateway.service.WhiteUrlService;
import com.ambow.utils.JWTUtils;

import cn.hutool.core.collection.CollectionUtil;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Aaron
 * @Date 2019年5月7日 下午2:20:55
 * @Desc 前置路由器，认证相关的会直接路由到指定服务，采用以服务名小写的方式路由。
 */
@Component
public class TokenValidGlobalFilter implements GlobalFilter, Ordered {
    
    private static final Logger logger = LoggerFactory.getLogger(TokenValidGlobalFilter.class);
    
    @Autowired
    private WhiteUrlService whiteUrlService;
    
    @Override
    public int getOrder() {
        
        return -99;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String uri = serverHttpRequest.getPath().value();
        logger.info("访问链接：" + uri);
        
        //路由所有/auth/路径下的请求，不经过token验证
        if (null != whiteUrlService.getByUrl(uri)) {
            
            return chain.filter(exchange);
        }
        
        List<String> tokenHeaders = serverHttpRequest.getHeaders().get(TokenConstant.TOKEN_HEADER);
        if (CollectionUtil.isNotEmpty(tokenHeaders)) {
            
            String token = tokenHeaders.get(0);
            if (StringUtils.isNotBlank(token)) {

                Map<String, Object> map = JWTUtils.parse(token);

                //token正确且未过期,更新过期时间，当前时间延后24小时,响应头中携带新签发的token
                if (CollectionUtil.isNotEmpty(map)) {

                    token = JWTUtils.sign(map, new Date(System.currentTimeMillis() + TokenConstant.TOKEN_EXPIRE_TIME));
                    response.getHeaders().add(TokenConstant.TOKEN_HEADER, token);

                    //往下层服务中转发当前用户的userId
                    ServerHttpRequest newServerHttpRequest = serverHttpRequest.mutate().header(TokenConstant.PAYLOAD_USER_ID, String.valueOf((Long) map.get(TokenConstant.PAYLOAD_USER_ID))).build();
                    return chain.filter(exchange.mutate().request(newServerHttpRequest).response(response).build());
                }
            }
        }
        
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}