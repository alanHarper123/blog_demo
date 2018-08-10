package com.qiujintao.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qiujintao.model.Article;

@RestController
public class PostController {
	@PostMapping("/new-post/api/article/save")
	public String postArticle(@Valid @RequestBody Article article) {
		System.out.println(article);
		return "Post Successfully";
	}

}
