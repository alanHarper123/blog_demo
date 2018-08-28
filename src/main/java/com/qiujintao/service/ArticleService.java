package com.qiujintao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiujintao.mapper.ArticleMapper;
import com.qiujintao.mapper.ParagraphMapper;
import com.qiujintao.model.Article;
import com.qiujintao.model.ArticleExample;
import com.qiujintao.model.ParagraphWithBLOBs;

@Service
public class ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ParagraphMapper paragraphMapper;
	public List<Article> findAllArticles(){
		ArticleExample articleExample = new ArticleExample();
		articleExample.setOrderByClause("id desc");
		return articleMapper.selectByExampleWithBLOBs(articleExample);
	}
	public Article findArticleDetailsById(int id){
		return articleMapper.selectByPrimaryKeyWithParagraphs(id);
	}
	public Article findArticleById(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}
	@Transactional
	public void saveArticle(Article article) {
		articleMapper.insert(article);
		for(ParagraphWithBLOBs paragraphWithBLOBs:article.getParagraphs()) {
			paragraphWithBLOBs.setArticle_id(article.getId());
			paragraphMapper.insert(paragraphWithBLOBs);
		}
	}
	@Transactional
	public void updateArticle(Article article) {
		articleMapper.updateByPrimaryKeyWithBLOBs(article);
		for(ParagraphWithBLOBs paragraphWithBLOBs:article.getParagraphs()) {
			paragraphWithBLOBs.setArticle_id(article.getId());
			paragraphMapper.updateByPrimaryKeyWithBLOBs(paragraphWithBLOBs);
		}
	}
}
