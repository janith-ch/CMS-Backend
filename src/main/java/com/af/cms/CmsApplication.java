package com.af.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.af.cms.util.FileStorageProperties;
import com.af.cms.util.FileStoragePropertiesResearch;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class,
	FileStoragePropertiesResearch.class
})

public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}
