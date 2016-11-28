package com.automate.Rules.utils;

import com.automate.Rules.common.DataSetup;
import com.automate.Rules.domain.Asset;

/**
 * 
 * @author Shikha A
 *
 */
public interface AssetCRUDInterface {
	public abstract Asset performCRUD(String fileName, DataSetup setup);
}
