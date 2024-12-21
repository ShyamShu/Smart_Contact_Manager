package com.SCM.Smart_Contact_Manager.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class appConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Cloudinary cloudinary()
    {
        logger.info(apiKey);
        logger.info(apiSecret);
        logger.info(cloudName);
        return new Cloudinary(
        
        ObjectUtils.asMap(
            "cloud_name" , cloudName,
            "api_key" , apiKey,
            "api_secret" , apiSecret

        )
        );
    }
    
}
