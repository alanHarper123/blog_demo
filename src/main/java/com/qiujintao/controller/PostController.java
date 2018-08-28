package com.qiujintao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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


import com.qiujintao.model.Article;
import com.qiujintao.model.User;
import com.qiujintao.service.ArticleService;
import com.qiujintao.service.MyUserDetails;

@RestController
public class PostController {
	
	@Autowired
	private ArticleService articleService;
	@PostMapping("/new-post/api/article/save")
	public String postArticle(@Valid @RequestBody Article article) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			
			try {
				MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
				article.setUser_id(myUserDetails.getUser().getId());
				if(article.getId() != 0) {
					articleService.saveArticle(article);
				}else {
					articleService.updateArticle(article);
				}
				return "Post Successfully";
			} catch (Exception e) {
				return "error: unable to save article "+e.getMessage();
			}
		}else {
			return "please login first";
		}
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
