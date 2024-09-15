package com.aws.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.aws.entity.Img;
import com.aws.repository.ImgRepository;

import software.amazon.awssdk.services.s3.S3Client;

@Service
public class ImgService {

	@Autowired
	private ImgRepository imgRepository;

	@Autowired
	private AmazonS3 amazonS3;
	

	@Value("${aws.s3.bucketName}")
	private String bucketname;

	public String uploadimage(MultipartFile file, Img  img) throws IOException  {
		
		Img im= new Img();
		

		File fileobj = convertMultiPartFileToFile(file);
		String imageName =  file.getOriginalFilename();
		PutObjectResult putObject = amazonS3.putObject(new com.amazonaws.services.s3.model.PutObjectRequest(bucketname, imageName, fileobj));
		
//		                    URL url = amazonS3.getUrl(bucketname, imageName);
		
		String url = amazonS3.getUrl(bucketname, imageName).toString();
		System.out.println("=============================");
		System.out.println(imageName);
		System.out.println(url);
		System.out.println("============================");
		im.setImgname(imageName);
		im.setImgurl(url);
		
		imgRepository.save(img);
		
		
		
		fileobj.delete();
		
		return "fileuploaded"+imageName;

	
	}
	
	
	
	

private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
    // Create a temporary file with the same name as the original file
    Path tempFile = Files.createTempFile(file.getOriginalFilename(), ".tmp");

    // Copy the MultipartFile's content to the temporary file
    try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
    }

    // Return the temporary file as a File object
    return tempFile.toFile();
    
}


	
//	private File convertMultiPartFileToFile(MultipartFile file) {
//
//		File convertedFile = new File(file.getOriginalFilename());
//
//		try (FileOutputStream fStream new FileOutputStream(convertedFile)) { fos.write(file.getBytes());
//
//		} catch (IOException e) {
//
//	
//
//		}
//
//		return convertedFile;
//
//		}
	
}
