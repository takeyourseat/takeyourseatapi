package com.stefanini.internship.placemanagement.notifications.model.dto;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;

public class ModifiedPlace {

	private Place place;
	private PlaceRequest placeRequest;

	public ModifiedPlace() {
	}

	public ModifiedPlace(Place place, PlaceRequest placeRequest) {
		this.place = place;
		this.placeRequest = placeRequest;
	}

	public Place getPlace() {
		return place;
	}

	public ModifiedPlace setPlace(Place place) {
		this.place = place;
		return this;
	}

	public PlaceRequest getPlaceRequest() {
		return placeRequest;
	}

	public ModifiedPlace setPlaceRequest(PlaceRequest placeRequest) {
		this.placeRequest = placeRequest;
		return this;
	}
}
