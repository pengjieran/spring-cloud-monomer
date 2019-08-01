package com.ambow.gateway.service;

import com.ambow.gateway.entity.WhiteUrl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aaron
 * @since 2019-05-28
 */
public interface WhiteUrlService extends IService<WhiteUrl> {

    WhiteUrl getByUrl(String url);
    
    WhiteUrl put(String url);
    
    void delete(String url);
}