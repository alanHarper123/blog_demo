package com.qiujintao.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qiujintao.model.Article;
import com.qiujintao.model.User;

@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@GetMapping("/")
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			User user1 = new User();
			user1.setId(12);
			users.add(user1);
		}
		List<Article> articles = new ArrayList<>();
		Article article = new Article("",null);
		articles.add(article);
		article.setTitle("Trump's seething Russia probe tweets may return to haunt him as evidence in an obstruction trial");
		try {
			BufferedImage bufferedImage = ImageIO.read(
					new File("C:\\Users\\alan\\Desktop\\trumps-seething-russia-probe-tweets-may-return-to-haunt-him-as-evidence-in-an-obstruction-trial.jpg"));
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", os);
			os.flush();
			article.setImage(Base64.getEncoder().encodeToString(os.toByteArray()).getBytes());
		} catch (Exception e) {
			LOGGER.error("read image fails");
		}
	
		modelAndView.addObject("articles", articles);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	@GetMapping("/single-audio")
	public ModelAndView audio() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("single-audio");
		return modelAndView;
	}
	@GetMapping("/new-post")
	public ModelAndView new_post() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("new-post");
		return modelAndView;
	}
}
