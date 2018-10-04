package com.qiujintao.configuration;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ReactResourcesConfig{
	class ReactResourcesFormatter{
		
//toDo uncommented in production code
//		private JsonNode resourcemap;
//		public ReactResourcesFormatter() {
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//				resourcemap = mapper.readValue(new File("F:/codeJava/practice/git/blog_demo/src/main/resources/static/js/built/manifest.json"), JsonNode.class);
//			} catch (IOException e) {
//				resourcemap = null;
//			}
//		}
		public String format(String originalPath) {
			JsonNode resourcemap;
			ObjectMapper mapper = new ObjectMapper();
			try {
				resourcemap = mapper.readValue(new File("F:/codeJava/practice/git/blog_demo/src/main/resources/static/js/built/manifest.json"), JsonNode.class);
			} catch (IOException e) {
				resourcemap = null;
			}
			if(resourcemap!=null && resourcemap.has(originalPath)) {
				return resourcemap.get(originalPath).asText();
			}
			return originalPath;
		}
	}
	@Bean
	public ReactResourcesFormatter reactResourcesFormatter() {
		return new ReactResourcesFormatter();
	}
}
