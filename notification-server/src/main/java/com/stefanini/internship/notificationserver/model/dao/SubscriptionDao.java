package com.stefanini.internship.notificationserver.model.dao;


import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class SubscriptionDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String auth;

	@Column(name = "the_key")
	private String key;

	@Column(unique = true)
	private String endpoint;

	public SubscriptionDao() {
	}

	public SubscriptionDao(Long id, String username, String oauth, String publicKey, String endpoint) {
		this.id = id;
		this.username = username;
		this.auth = oauth;
		this.key = publicKey;
		this.endpoint = endpoint;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
