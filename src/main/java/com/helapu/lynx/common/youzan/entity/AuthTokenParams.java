package com.helapu.lynx.common.youzan.entity;

public class AuthTokenParams {
	private String client_id;
	private String client_secret;
	private String grant_type;
	private String kdt_id;
	
	
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getKdt_id() {
		return kdt_id;
	}
	public void setKdt_id(String kdt_id) {
		this.kdt_id = kdt_id;
	}

	@Override
	public String toString() {
		return "[AuthToken client_id=" + this.getClient_id() + " secret=" + this.getClient_secret() + "]";
	}
}
