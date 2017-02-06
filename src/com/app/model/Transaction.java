package com.app.model;

public class Transaction {
	
	private long userId;
	private String subjectId;
	private String status;
	
	public Transaction(){
		super();
	}
	
	public Transaction(long userId, String subjectId, String status) {
		super();
		this.userId = userId;
		this.subjectId = subjectId;
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [userId=" + userId + ", subjectId=" + subjectId + ", status=" + status + "]";
	}
}
