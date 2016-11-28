package com.automate.regression.UI.domain;

/**
 * Domain object for RunManager.xlsx-> Select Test Case sheet
 * 
 * @author Shikha A
 *
 */
public class RunManager {
	private String name;
	private boolean execute;
	private boolean chromeBrowser;
	private boolean ieBrowser;
	private String testResult;
	private String remarks;
	private String failureReason;
	private String executableName;
	private String testSetNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		if ("Y".equalsIgnoreCase(execute)) {
			this.execute = true;
		} else {
			this.execute = false;
		}
	}

	public boolean isChromeBrowser() {
		return chromeBrowser;
	}

	public void setChromeBrowser(String chromeBrowser) {
		if ("Y".equalsIgnoreCase(chromeBrowser)) {
			this.chromeBrowser = true;
		} else {
			this.chromeBrowser = false;
		}
	}

	public boolean isIeBrowser() {
		return ieBrowser;
	}

	public void setIeBrowser(String ieBrowser) {
		if ("Y".equalsIgnoreCase(ieBrowser)) {
			this.ieBrowser = true;
		} else {
			this.ieBrowser = false;
		}
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public String getExecutableName() {
		return executableName;
	}

	public void setExecutableName(String executableName) {
		this.executableName = executableName;
	}

	public String getTestSetNumber() {
		return testSetNumber;
	}

	public void setTestSetNumber(String testSetNumber) {
		this.testSetNumber = testSetNumber;
	}
}
