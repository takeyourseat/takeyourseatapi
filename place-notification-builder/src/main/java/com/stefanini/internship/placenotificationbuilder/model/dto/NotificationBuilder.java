package com.stefanini.internship.placenotificationbuilder.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationBuilder {

	private String title;
	private String image;
	private String icon;
	private String body;
	private boolean silent;
	private Map<String, Object> data = new HashMap<>();
	private List<NotificationAction> actions = new ArrayList<>();

	public NotificationBuilder() {
	}

	public NotificationBuilder(String title, String image, String icon, String body, boolean silent, Map<String, Object> data, List<NotificationAction> actions) {
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

	public NotificationBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getImage() {
		return image;
	}

	public NotificationBuilder setImage(String image) {
		this.image = image;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public NotificationBuilder setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getBody() {
		return body;
	}

	public NotificationBuilder setBody(String body) {
		this.body = body;
		return this;
	}

	public boolean isSilent() {
		return silent;
	}

	public NotificationBuilder setSilent(boolean silent) {
		this.silent = silent;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public NotificationBuilder setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}

	public List<NotificationAction> getActions() {
		return actions;
	}

	public NotificationBuilder setActions(List<NotificationAction> actions) {
		this.actions = actions;
		return this;
	}
}
