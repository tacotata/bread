package com.example.helloproject.utils;

import com.example.helloproject.data.entity.news.NewsType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToNewsTypeConverter implements Converter<String, NewsType> {
    @Override
    public NewsType convert(String source) {
       // try {
            return NewsType.valueOf(source.toUpperCase());
      //} catch (IllegalArgumentException e) {
            //return null;
      //}
    }
}
