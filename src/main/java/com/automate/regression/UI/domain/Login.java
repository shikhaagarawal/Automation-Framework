package com.automate.regression.UI.domain;

/**
 * Domain object for RunManager.xlsx-> Login sheet
 * 
 * @author Shikha A
 *
 */
public class Login {
	private String url;
	private String userName;
	private String passowrd;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

}
