package com.automate.regression.UI.domain;

/**
 * Domain object for TestScripts/.xlsx
 * 
 * @author Shikha A
 *
 */
public class DataManager {
	private String objectName;
	private Action action;
	private String xpath;
	private String value;
	private String result;
	private String sheetName;
	private boolean expectToPass;
	private String id;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public boolean isExpectToPass() {
		return expectToPass;
	}

	public void setExpectToPass(String expectToPass) {
		if ("Y".equalsIgnoreCase(expectToPass)) {
			this.expectToPass = true;
		} else {
			this.expectToPass = false;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
