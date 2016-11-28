package com.automate.Rules.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.automate.Rules.common.DataCleanUp;
import com.automate.Rules.common.DataSetup;
import com.automate.Rules.domain.Asset;
/**
 * 
 * @author Shikha A
 *
 */
public class DeleteAsset implements AssetCRUDInterface {

	@Autowired
	DataCleanUp cleanUp;

	// @Override
	public Asset performCRUD(String assetDetails, DataSetup setup) {
		if(cleanUp.deleteOneRecord(assetDetails)){
			return new Asset();
		}
		return null;
	}

}
