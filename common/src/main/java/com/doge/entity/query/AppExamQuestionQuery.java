package com.doge.entity.query;



/**
 * @Description:考试问题查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class AppExamQuestionQuery extends BaseQuery{
	/**
	 * 自增ID
	 */
	private Integer id;

	/**
	 * 考试ID
	 */
	private Integer examId;

	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 问题ID
	 */
	private Integer questionId;

	/**
	 * 用户答案
	 */
	private String userAnswer;

	private String userAnswerFuzzy;

	/**
	 * 0:未作答 1:正确  2:错误
	 */
	private Integer answerResult;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getExamId() {
		return this.examId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getUserAnswer() {
		return this.userAnswer;
	}

	public void setAnswerResult(Integer answerResult) {
		this.answerResult = answerResult;
	}

	public Integer getAnswerResult() {
		return this.answerResult;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	public void setUserAnswerFuzzy(String userAnswerFuzzy) {
		this.userAnswerFuzzy = userAnswerFuzzy;
	}

	public String getUserAnswerFuzzy() {
		return this.userAnswerFuzzy;
	}

}