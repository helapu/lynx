package com.helapu.lynx.common.youzan.entity;

public class AuthTokenResult {
	private String access_token;
	private String expires_in;
	private String scope;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "[AuthToken accessToken=" + this.getAccess_token() + " scope=" + this.getScope() + "]";
	}
}
