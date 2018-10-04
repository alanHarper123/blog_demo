package com.qiujintao.controller;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiujintao.mapper.CommentMapper;
import com.qiujintao.model.Article;
import com.qiujintao.model.Comment;
import com.qiujintao.model.User;
import com.qiujintao.service.ArticleService;
import com.qiujintao.service.CommentService;
import com.qiujintao.service.MyUserDetails;
import com.qiujintao.util.File_utils;

@RestController
public class PostController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;
	@PostMapping("/new-post/api/article/save")
	public String postArticle(@Valid @RequestBody Article article) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			
			try {
				MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
				System.out.println(article);
				article.setUser_id(myUserDetails.getUser().getId());
				if(article.getId() == null) {
					articleService.saveArticle(article);
				}else {
					articleService.updateArticle(article);
				}
				return "Post Successfully";
			} catch (Exception e) {
				e.printStackTrace();
				return "error: unable to save article ";
			}
		}else {
			return "please login first";
		}
	}
	@PostMapping(value="/api/comments_001",produces = "application/json")
	public String getComments_001() {
		try {
			return File_utils.readFile("F:\\codeJava\\practice\\git\\blog_demo\\src\\main\\resources\\static\\data\\comments.json", StandardCharsets.UTF_8);
		} catch (IOException e) {
			return e.toString();
		}
	}
	@PostMapping(value="/api/comments",produces = "application/json",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getComments(@RequestBody Map<String, Integer> payload) {
		if(payload.get("article_id") == null) {
			return null;
		}else {
			List<Comment> result1 = commentService.selectDepthOneCommentsByArticleID(payload.get("article_id"));
			return result1;
			
		}
	}
	@PostMapping(value="/api/updates",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUpdates(@RequestBody Map<String, Object> payload) {
		System.out.println(payload);
		try {
			return File_utils.readFile("F:\\codeJava\\practice\\git\\blog_demo\\src\\main\\resources\\static\\data\\updates.json", StandardCharsets.UTF_8);
		} catch (IOException e) {
			return e.toString();
		}
	}
	@PostMapping(value="/api/deleteUpate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deleteUpdate(@RequestBody Map<String, Long> payload) {
		String result = "deleted update no."+payload.get("update_id");
		System.out.println("deleted update no."+payload.get("update_id"));
		return "{\"result\": \""+result+"\"}";
	}
	@GetMapping(value="/api/article/delete", params="id")
	public String deleteArticle(@RequestParam int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			try {
				MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
				Article article = articleService.findArticleById(id);
				if (myUserDetails.getUser().getId() != article.getUser_id()) {
					return "you can not delete other user's posts";
				}else {
					return "post have been deleted";
				}
			} catch (Exception e) {
				return "error: "+e.getMessage();
			}
		}else {
			return "please login first";
		}
		
		
	}

}
