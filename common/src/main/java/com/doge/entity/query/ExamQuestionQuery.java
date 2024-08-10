package com.doge.entity.query;

import java.util.Date;


/**
 * @Description:考试题目查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class ExamQuestionQuery extends BaseQuery{
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
	 * 分类ID
	 */
	private Integer categoryId;

	/**
	 * 分类名称
	 */
	private String categoryName;

	private String categoryNameFuzzy;

	/**
	 * 难度
	 */
	private Integer difficultyLevel;

	/**
	 * 问题类型 0:判断 1:单选题 2:多选
	 */
	private Integer questionType;

	/**
	 * 问题描述
	 */
	private String question;

	private String questionFuzzy;

	/**
	 * 答案
	 */
	private String questionAnswer;

	private String questionAnswerFuzzy;

	/**
	 * 回答解释
	 */
	private String answerAnalysis;

	private String answerAnalysisFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 0:未发布 1:已发布
	 */
	private Integer status;

	/**
	 * 用户ID
	 */
	private String createUserId;

	private String createUserIdFuzzy;

	/**
	 * 姓名
	 */
	private String createUserName;

	private String createUserNameFuzzy;

	/**
	 * 0:内部 1:外部投稿
	 */
	private Integer postUserType;

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

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Integer getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionType() {
		return this.questionType;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getQuestionAnswer() {
		return this.questionAnswer;
	}

	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}

	public String getAnswerAnalysis() {
		return this.answerAnalysis;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setPostUserType(Integer postUserType) {
		this.postUserType = postUserType;
	}

	public Integer getPostUserType() {
		return this.postUserType;
	}

	public void setTitleFuzzy(String titleFuzzy) {
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy() {
		return this.titleFuzzy;
	}

	public void setCategoryNameFuzzy(String categoryNameFuzzy) {
		this.categoryNameFuzzy = categoryNameFuzzy;
	}

	public String getCategoryNameFuzzy() {
		return this.categoryNameFuzzy;
	}

	public void setQuestionFuzzy(String questionFuzzy) {
		this.questionFuzzy = questionFuzzy;
	}

	public String getQuestionFuzzy() {
		return this.questionFuzzy;
	}

	public void setQuestionAnswerFuzzy(String questionAnswerFuzzy) {
		this.questionAnswerFuzzy = questionAnswerFuzzy;
	}

	public String getQuestionAnswerFuzzy() {
		return this.questionAnswerFuzzy;
	}

	public void setAnswerAnalysisFuzzy(String answerAnalysisFuzzy) {
		this.answerAnalysisFuzzy = answerAnalysisFuzzy;
	}

	public String getAnswerAnalysisFuzzy() {
		return this.answerAnalysisFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	public void setCreateUserIdFuzzy(String createUserIdFuzzy) {
		this.createUserIdFuzzy = createUserIdFuzzy;
	}

	public String getCreateUserIdFuzzy() {
		return this.createUserIdFuzzy;
	}

	public void setCreateUserNameFuzzy(String createUserNameFuzzy) {
		this.createUserNameFuzzy = createUserNameFuzzy;
	}

	public String getCreateUserNameFuzzy() {
		return this.createUserNameFuzzy;
	}

}