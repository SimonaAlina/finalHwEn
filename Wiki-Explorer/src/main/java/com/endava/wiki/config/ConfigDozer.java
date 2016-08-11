package com.endava.wiki.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sroboiu on 11-Aug-16.
 */
@Configuration
public class ConfigDozer {

    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }
}
