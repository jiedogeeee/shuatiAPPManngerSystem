package com.doge.entity.query;



/**
 * @Description:查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class ExamQuestionItemQuery extends BaseQuery{
	/**
	 * 
	 */
	private Integer itemId;

	/**
	 * 问题ID
	 */
	private Integer questionId;

	/**
	 * 标题
	 */
	private String title;

	private String titleFuzzy;

	/**
	 * 排序
	 */
	private Integer sort;

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setTitleFuzzy(String titleFuzzy) {
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy() {
		return this.titleFuzzy;
	}

}