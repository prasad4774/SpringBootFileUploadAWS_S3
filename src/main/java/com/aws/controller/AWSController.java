package com.aws.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.entity.Img;
import com.aws.service.ImgService;

@RestController
public class AWSController {

	@Autowired
	private ImgService imgService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadimg(@RequestParam("file") MultipartFile file, Img img) throws IOException
	{
		
		return new ResponseEntity<>(imgService.uploadimage(file, img), HttpStatus.CREATED);
	}
}
