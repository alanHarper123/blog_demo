package com.qiujintao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qiujintao.model.Article;
import com.qiujintao.model.ParagraphWithBLOBs;
import com.qiujintao.model.User;
import com.qiujintao.service.ArticleService;
import com.qiujintao.service.MyUserDetails;
import com.qiujintao.service.ParagraphService;


@Controller
public class HomeController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ParagraphService paragraphService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@GetMapping("/")
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView();
	
		List<Article> articles = articleService.findAllArticles();
		for(Article article : articles) {
			LOGGER.info(article.toString());
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
	@GetMapping("/react-index")
	public ModelAndView react_index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("react-index");
		return modelAndView;
	}

	@GetMapping(value="/view-post",params="id")
	public ModelAndView view_post(@RequestParam int id) {

		ModelAndView modelAndView = new ModelAndView();
		Article article = articleService.findArticleDetailsById(id);
		if (article==null) {
			modelAndView.setViewName("index");
			return modelAndView;
		}
		modelAndView.setViewName("new-post");
		modelAndView.addObject("article", article);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			if(((MyUserDetails)authentication.getPrincipal()).getUser().getId()==article.getUser_id()) {
				modelAndView.addObject("isAuthor", true);
			}
		}
		return modelAndView;
	}

	@GetMapping("/new-post")
	public ModelAndView new_post(HttpServletRequest request, ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(authentication instanceof AnonymousAuthenticationToken) {
////			request.getSession().setAttribute("url_prior_login", makeUrl(request));
////			ra.addFlashAttribute("url_prior_login", makeUrl(request));
//			request.getSession().setAttribute("url_prior_login", makeUrl(request));
//			return new ModelAndView("redirect:/login", model);
//		}else {
//			modelAndView.setViewName("new-post");
//			modelAndView.addObject("new_post", true);
//		}
		modelAndView.setViewName("new-post");
		modelAndView.addObject("new_post", true);
		return modelAndView;
	}
	
	public static String makeUrl(HttpServletRequest request)
	{
		
	    return request.getRequestURL().toString() + (request.getQueryString()!=null?("?" + request.getQueryString()):"");
	}
}
