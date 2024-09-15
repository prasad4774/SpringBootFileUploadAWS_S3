package com.aws.confi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ImgConfig {

	@Value("${cloud.aws.credentials.accessKey}")
	private String accesskey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String accesssecret;

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonS3 genarates2client()
	{
		
		AWSCredentials awsCredentials= new BasicAWSCredentials(accesskey, accesssecret);
		 return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region).build();
		
	}
}
