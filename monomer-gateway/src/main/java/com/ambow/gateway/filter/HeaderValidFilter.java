package com.ambow.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import cn.hutool.core.collection.CollectionUtil;
import reactor.core.publisher.Mono;

/**
 * @Author Aaron
   *    验证请求头中是否包含指定参数
 * deviceId:设备ID,WEB取可以唯一识别当前设备的号
 * deviceType:设备类型，WEB, IOS, ANDROID, IPAD
 * clientVersion:客户端版本
 */
@Component
public class HeaderValidFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
    	
    	// 顺序在token认证之前
        return -100;
    }

    /**
             * 请求头必须携带参数，便于服务器后期处理
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpHeaders headers = serverHttpRequest.getHeaders();
        List<String> deviceIds = headers.get("deviceId");
        List<String> deviceTypes = headers.get("deviceType");
        List<String> clientVersions = headers.get("clientVersion");
        if (CollectionUtil.isNotEmpty(deviceIds) && CollectionUtil.isNotEmpty(deviceTypes) && CollectionUtil.isNotEmpty(clientVersions)) {
        	
        	return chain.filter(exchange);
        }
        
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
        return response.setComplete();
    }
}