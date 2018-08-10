package com.qiujintao.model;

import java.util.regex.Pattern;

import javax.validation.constraints.Size;

public class Paragraph {
	static private Pattern printableCharacterPattern = Pattern.compile("\\P{Cc}");
	private int id;
	@Size(max = 65000)
	private String text;
	@Size(max=16777215)
	private byte[] image;
	@Size(max=255)
	private String imageCaption;
	public Paragraph() {}
	public Paragraph(String text) {
		this.text = text;
	}
	public Paragraph(String image, String imageCaption) {
		this.image = image.getBytes();
		this.imageCaption = imageCaption;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		if(text!=null) {
			throw new IllegalStateException("image and text cannot be populated at the same time in a Paragraph object!");
		}
		this.image = image;
	}
	public String getImageCaption() {
		return imageCaption;
	}
	public void setImageCaption(String imageCaption) {
		this.imageCaption = imageCaption;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if(text==null) {
			throw new IllegalStateException("text cannot be populated to null in a Paragraph object!");
		}
		if(!printableCharacterPattern.matcher(text).matches()) {
			throw new IllegalStateException("text cannot be non printable in a Paragraph object!");
		}
		
		this.text = text;
	}
	
	@Override
	public String toString() {
		if(image == null) {
			return "p: "+text;
		}else {
			return "img: "+new String(image)+", imageCaption: "+(imageCaption==null?"not caption":imageCaption);
		}
		
	}
	
}
