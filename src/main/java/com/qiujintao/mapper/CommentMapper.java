package com.qiujintao.mapper;

import com.qiujintao.model.Comment;
import com.qiujintao.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	long countByExample(CommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int deleteByExample(CommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int insert(Comment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int insertSelective(Comment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	List<Comment> selectByExample(CommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	Comment selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int updateByPrimaryKeySelective(Comment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table comment
	 * @mbg.generated  Tue Oct 02 10:35:44 CST 2018
	 */
	int updateByPrimaryKey(Comment record);
}