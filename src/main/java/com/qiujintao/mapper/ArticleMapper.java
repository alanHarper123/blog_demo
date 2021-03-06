package com.qiujintao.mapper;

import com.qiujintao.model.Article;
import com.qiujintao.model.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	long countByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int deleteByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int insert(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int insertSelective(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	List<Article> selectByExampleWithBLOBs(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	List<Article> selectByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	Article selectByPrimaryKey(Integer id);
	
	Article selectByPrimaryKeyWithParagraphs(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int updateByPrimaryKeySelective(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int updateByPrimaryKeyWithBLOBs(Article record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Wed Aug 22 21:17:55 CST 2018
	 */
	int updateByPrimaryKey(Article record);
}