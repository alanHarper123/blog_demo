package com.qiujintao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiujintao.mapper.ParagraphMapper;
import com.qiujintao.model.ParagraphExample;
import com.qiujintao.model.ParagraphWithBLOBs;

@Service
public class ParagraphService {
	@Autowired
	private ParagraphMapper paragraphMapper;
	public List<ParagraphWithBLOBs> getParagraphsByArticleId(int id){
		ParagraphExample paragraphExample = new ParagraphExample();
		paragraphExample.createCriteria().andArticle_idEqualTo(id);
		return paragraphMapper.selectByExampleWithBLOBs(paragraphExample);
	}
}
