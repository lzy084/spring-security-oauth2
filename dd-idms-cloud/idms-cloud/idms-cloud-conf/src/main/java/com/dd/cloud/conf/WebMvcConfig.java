package com.dd.cloud.conf;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        MappingJackson2HttpMessageConverter gsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> gsonMediaTypes = new ArrayList<>();
        gsonMediaTypes.add(MediaType.TEXT_PLAIN);
        gsonHttpMessageConverter.setSupportedMediaTypes(gsonMediaTypes);
        HttpMessageConverter<?> converter = gsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }
}
