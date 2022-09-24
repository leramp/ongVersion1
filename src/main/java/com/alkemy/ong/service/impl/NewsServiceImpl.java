package com.alkemy.ong.service.impl;

import com.alkemy.ong.domain.entity.NewsEntity;
import com.alkemy.ong.domain.mapper.NewsMapper;
import com.alkemy.ong.domain.request.NewsRequest;
import com.alkemy.ong.domain.response.NewsResponse;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl implements NewsService {

    private NewsMapper newsMapper;
    private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsMapper newsMapper, NewsRepository newsRepository) {
        this.newsMapper = newsMapper;
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsResponse save(NewsRequest newsRequest) {
        NewsEntity entity = new NewsEntity();
        entity.setName(newsRequest.getName());
        entity.setContent(newsRequest.getContent());
        entity.setImageUrl(newsRequest.getImageUrl());
        entity = this.newsRepository.save(entity);
        NewsResponse response = newsMapper.entityToResponse(entity);
        return response;
    }

}