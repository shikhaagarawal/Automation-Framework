package com.automate.Rules.domain;

import java.util.Date;

/**
 * 
 * @author Shikha A
 *
 */
public class DataQualityErrors implements Cloneable {

	private DataQualityErrorType dataQualityErrorType;
	private Date reportedOn;
	private String errorCode;
	private String ruleDescription;
	private String correctiveAction;

	public DataQualityErrors(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public DataQualityErrors(String errorCode, Date reported, DataQualityErrorType dataQualityErrorType) {
		super();
		this.errorCode = errorCode;
		this.reportedOn = reported;
		this.dataQualityErrorType = dataQualityErrorType;
	}

	/**
	 * @return the dataQualityErrorType
	 */
	public DataQualityErrorType getDataQualityErrorType() {
		return dataQualityErrorType;
	}

	/**
	 * @param dataQualityErrorType
	 *            the dataQualityErrorType to set
	 */
	public void setDataQualityErrorType(DataQualityErrorType dataQualityErrorType) {
		this.dataQualityErrorType = dataQualityErrorType;
	}

	/**
	 * @return the reportedOn
	 */
	public Date getReportedOn() {
		return reportedOn;
	}

	/**
	 * @param reportedOn
	 *            the reportedOn to set
	 */
	public void setReportedOn(Date reportedOn) {
		this.reportedOn = reportedOn;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the ruleDescription
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}

	/**
	 * @param ruleDescription
	 *            the ruleDescription to set
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	/**
	 * @return the correctiveAction
	 */
	public String getCorrectiveAction() {
		return correctiveAction;
	}

	/**
	 * @param correctiveAction
	 *            the correctiveAction to set
	 */
	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	@Override
	public boolean equals(Object obj) {
		DataQualityErrors finding = (DataQualityErrors) obj;
		return this.errorCode.equals(finding.errorCode);
	}
}
