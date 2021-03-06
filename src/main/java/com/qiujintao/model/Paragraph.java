package com.qiujintao.model;

import java.util.regex.Pattern;

import javax.validation.constraints.Size;

public class Paragraph {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column paragraph.article_id
	 * @mbg.generated  Tue Aug 21 20:40:11 CST 2018
	 */
	private Integer article_id;

	private Integer id;
	
	@Size(max=255)
	private String imageCaption;
	public Paragraph() {}

	public String getImageCaption() {
		return imageCaption;
	}
	public void setImageCaption(String imageCaption) {
		this.imageCaption = imageCaption;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column paragraph.article_id
	 * @return  the value of paragraph.article_id
	 * @mbg.generated  Tue Aug 21 20:40:11 CST 2018
	 */
	public Integer getArticle_id() {
		return article_id;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column paragraph.article_id
	 * @param article_id  the value for paragraph.article_id
	 * @mbg.generated  Tue Aug 21 20:40:11 CST 2018
	 */
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	
}
