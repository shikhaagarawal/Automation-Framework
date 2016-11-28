package com.automate.Rules.domain;

import java.util.List;

/**
 * 
 * @author Shikha A
 *
 */
public class Asset {
	
	private String assetId;
	private String name;
	private ApplicationEntryMode applicationEntryMode;
	private LifeCycleStatus lifeCycleStatus;
	private String applicationExitReason;
	private List<DataQualityErrors> dataQualityErrors;

	public ApplicationEntryMode getApplicationEntryMode() {
		return applicationEntryMode;
	}

	public void setApplicationEntryMode(ApplicationEntryMode applicationEntryMode) {
		this.applicationEntryMode = applicationEntryMode;
	}

	public List<DataQualityErrors> getDataQualityErrors() {
		return dataQualityErrors;
	}

	public void setDataQualityErrors(List<DataQualityErrors> dataQualityErrors) {
		this.dataQualityErrors = dataQualityErrors;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ApplicationEntryMode getApplicationEntryModes() {
		return applicationEntryMode;
	}

	public void setApplicationEntryModes(ApplicationEntryMode applicationEntryModes) {
		this.applicationEntryMode = applicationEntryModes;
	}

	public LifeCycleStatus getLifeCycleStatus() {
		return lifeCycleStatus;
	}

	public void setLifeCycleStatus(LifeCycleStatus lifeCycleStatus) {
		this.lifeCycleStatus = lifeCycleStatus;
	}

	public String getApplicationExitReason() {
		return applicationExitReason;
	}

	public void setApplicationExitReason(String applicationExitReason) {
		this.applicationExitReason = applicationExitReason;
	}
}
