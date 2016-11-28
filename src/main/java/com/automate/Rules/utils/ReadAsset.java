package com.automate.Rules.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.automate.Rules.common.ApplicationConfiguration;
import com.automate.Rules.common.ApplicationConstants;
import com.automate.Rules.common.DataSetup;
import com.automate.Rules.domain.Asset;

/**
 * 
 * @author Shikha A
 *
 */
public class ReadAsset implements AssetCRUDInterface {

	@Autowired
	ApplicationConfiguration config;
	
	// @Override
	public Asset performCRUD(String assetDetails, DataSetup setup) {
		String url = config.getProperty(ApplicationConstants.WS_ENDPOINT_CREATEASSET) + "/" + assetDetails;
		Asset asset = setup.callRestAPI(url, false, null);
		return asset;
	}

}
