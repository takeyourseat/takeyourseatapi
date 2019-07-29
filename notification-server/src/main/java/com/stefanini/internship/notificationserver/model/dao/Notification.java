package com.stefanini.internship.notificationserver.model.dao;

import java.util.List;

public class Notification {

	private String title;
	private String image;
	private String icon;
	private String body;
	private boolean silent;
	private Object data;
	private List<Object> actions;

	public Notification() {
	}

	public Notification(String title, String image, String icon, String body, boolean silent, Object data, List<Object> actions) {
		this.title = title;
		this.image = image;
		this.icon = icon;
		this.body = body;
		this.silent = silent;
		this.data = data;
		this.actions = actions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isSilent() {
		return silent;
	}

	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Object> getActions() {
		return actions;
	}

	public void setActions(List<Object> actions) {
		this.actions = actions;
	}
}
