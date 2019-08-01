package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto;

public class NotificationAction {

	private String action;
	private String title;

	public NotificationAction() {
	}

	public NotificationAction(String action, String title) {
		this.action = action;
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
