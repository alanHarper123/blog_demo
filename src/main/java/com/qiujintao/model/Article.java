package com.qiujintao.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Article {
	private int id;
	@NotEmpty
	@Size(max=255)
	private String title;
	@Size(max=16777215)
	private byte[] image;
	@Size(max=500)
	private String excerpt;
	@NotNull
	@Valid
	private List<Paragraph> paragraphs;
	public Article() {}
	public Article(String title, List<Paragraph> paragraphs) {
		this.title = title;
		this.paragraphs= paragraphs;
	}
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "[title: "+title+", paragraphs: "+paragraphs.toString()+"]";
	}
}
