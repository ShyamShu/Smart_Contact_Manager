package com.SCM.Smart_Contact_Manager.services;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class imageService {
    
    
    @Autowired
    private Cloudinary cloudinary ;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String upload(MultipartFile contactImage , String FileName)
    {
        try {
            
            Map<?, ?> uploadResult = cloudinary.uploader().upload(contactImage.getBytes(), ObjectUtils.asMap(
                "public_id", FileName
            ));
             
            // Retrieve and return the public URL
            String imageUrl = uploadResult.get("url").toString();
            logger.info("Uploaded image URL: {}", imageUrl);
            return imageUrl;
        }
         catch (IOException e) 
         {
            logger.info("Exception occur in cloud uploading = "+e.toString());
            e.printStackTrace();
            return "";
        }
        
    }

    public String getURl(String string)
    {
        return cloudinary.url().transformation(
            new Transformation<>()
            .width(500)
            .height(500)
        ).generate(string);
    }
}
