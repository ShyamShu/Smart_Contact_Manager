package com.SCM.Smart_Contact_Manager.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.*;
import java.awt.image.BufferedImage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class fileValidator implements ConstraintValidator<ValidFile , MultipartFile> {


    private static final long MAX_FILE_SIZE = 1024*1024*5;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
       

        // checking the file is empty or not if it is empty then return false and add a message
        if(file == null || file.isEmpty())
        {
            context.disableDefaultConstraintViolation();
            logger.info("file is not uploaded and reached null" );
            context.buildConstraintViolationWithTemplate("File Cannot Be Empty").addConstraintViolation();
            return false;
        }

        if(file.getSize() > MAX_FILE_SIZE)
        {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File Size Should not exceeds size 5 MB").addConstraintViolation();
            return false;  
        }

        try{
           BufferedImage bufferedImage =  ImageIO.read(file.getInputStream());

           if(bufferedImage.getHeight() > 1080  || bufferedImage.getWidth() > 1094)
           {
            context.disableDefaultConstraintViolation();
            logger.info("file dimension is not accurate ");
            context.buildConstraintViolationWithTemplate("File Cannot Be Empty").addConstraintViolation();
            return false;      
           }
        }
        catch(Exception e)
        {
           System.out.println(e.toString());
        }

        return true;
        
    }
    
}
