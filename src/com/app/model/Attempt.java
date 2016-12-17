package com.app.model;

import java.util.Calendar;

public class Attempt {
	
	private int numberOfAttempt;
	private Calendar lastAttempt;
	
	public Attempt(int numberOfAttempt, Calendar lastAttempt) {
		this.numberOfAttempt = numberOfAttempt;
		this.lastAttempt = lastAttempt;
	}
	
	public final int getNumberOfAttempt() {
		return numberOfAttempt;
	}
	public final void setNumberOfAttempt(int numberOfAttempt) {
		this.numberOfAttempt = numberOfAttempt;
	}
	public final Calendar getLastAttempt() {
		return lastAttempt;
	}
	public final void setLastAttempt(Calendar lastAttempt) {
		this.lastAttempt = lastAttempt;
	}
}
