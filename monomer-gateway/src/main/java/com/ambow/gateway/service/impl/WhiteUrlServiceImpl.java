package com.ambow.gateway.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ambow.gateway.entity.WhiteUrl;
import com.ambow.gateway.mapper.WhiteUrlMapper;
import com.ambow.gateway.service.WhiteUrlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2019-05-28
 */
@Service
@CacheConfig(cacheNames = "whiteUrls")
public class WhiteUrlServiceImpl extends ServiceImpl<WhiteUrlMapper, WhiteUrl> implements WhiteUrlService {

    @Cacheable(key = "#url")
    @Override
    public WhiteUrl getByUrl(String url) {
        
        QueryWrapper<WhiteUrl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(WhiteUrl.URL, url);
        
        return getOne(queryWrapper);
    }
    
    @Cacheable(key = "#url")
    @Override
    public WhiteUrl put(String url) {
        
        QueryWrapper<WhiteUrl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(WhiteUrl.URL, url);
        WhiteUrl whiteUrl = getOne(queryWrapper);
        if (null == whiteUrl) {
            
            whiteUrl = new WhiteUrl();
            whiteUrl.setUrl(url);
            saveOrUpdate(whiteUrl);
        }
        
        return whiteUrl;
    }

    @CacheEvict(key = "#url")
    @Override
    public void delete(String url) {
        
        QueryWrapper<WhiteUrl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(WhiteUrl.URL, url);
        remove(queryWrapper);
    }
}