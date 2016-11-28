package com.automate.Rules.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.automate.Rules.common.ApplicationConfiguration;
import com.automate.Rules.common.ApplicationConstants;
import com.automate.Rules.common.DataSetup;
import com.automate.Rules.domain.Asset;
/**
 * 
 * @author Shikha A
 *
 */
@Configuration
public class CreateAsset implements AssetCRUDInterface {
	
	@Autowired
	ApplicationConfiguration config;

	final static Logger logger = Logger.getLogger(CreateAsset.class);

	private List<Integer> assetIdsList = new LinkedList<Integer>();

	public List<Integer> getAssetIdsList() {
		return assetIdsList;
	}

	public void setAssetIdsList(List<Integer> assetIdsList) {
		this.assetIdsList = assetIdsList;
	}

	public Asset performCRUD(String fileName, DataSetup setup) {

		String url = config.getProperty(ApplicationConstants.WS_ENDPOINT_CREATEASSET);
		File file = new File(fileName);
		if (file.exists()) {
			try {
				Scanner scanner = new Scanner(file);
				Scanner s = scanner.useDelimiter("\\A");
				while (s.hasNextLine()) {
					// Insert data into db
					Asset asset = setup.callRestAPI(url, true, s.nextLine());
					if (null != asset) {
						assetIdsList.add(Integer.parseInt(asset.getAssetId()));
						logger.info(asset.getAssetId() + " : created");
					}
				}
				s.close();
				scanner.close();
				// this.assetIdsList = assetIds;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
