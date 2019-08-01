package com.ambow.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 废弃，使用该方式无法灵活添加路由白名单，像在临时上线一个白名单链接时就无法动态添加
 * @author Aaron
 * @Date   2019年5月24日 下午1:54:47
 * @Desc
 */
@Deprecated
@Configuration
public class RouterConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "route")
    public RouterConfig createParserConfig() {
        
        return new RouterConfig();
    }
    
    private List<String> writeList;//该列表中的不需要验证，直接转发请求

    public List<String> getWriteList() {
        return writeList;
    }

    public void setWriteList(List<String> writeList) {
        this.writeList = writeList;
    }
}