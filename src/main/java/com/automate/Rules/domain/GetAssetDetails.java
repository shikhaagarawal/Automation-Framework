package com.automate.Rules.domain;

/**
 * 
 * @author Shikha A
 *
 */
public class GetAssetDetails {
	private Asset[] asset;
	private String message;

	public Asset[] getAsset() {
		return asset;
	}

	public void setAsset(Asset[] asset) {
		this.asset = asset;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
