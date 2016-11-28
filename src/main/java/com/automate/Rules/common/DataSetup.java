package com.automate.Rules.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.automate.Rules.domain.Asset;
import com.automate.Rules.domain.AssetDetails;
import com.automate.Rules.domain.GetAssetDetails;

/**
 * 
 * @author Shikha A
 *
 */
public class DataSetup {

	final static Logger logger = Logger.getLogger(DataSetup.class);

	public Asset callRestAPI(String apiURL, boolean isPost, String jsonObject) {
		logger.info("Attempting to connect NODE API, url: " + apiURL);

		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> entity;
		HttpMethod method = HttpMethod.GET;
		if (isPost) {
			method = HttpMethod.POST;
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			if (null != jsonObject) {
				entity = new HttpEntity<String>(jsonObject, header);
			} else {
				entity = new HttpEntity<String>(header);
			}
			ResponseEntity<AssetDetails> response = restTemplate.exchange(apiURL, method, entity, new ParameterizedTypeReference<AssetDetails>() {
			});
			if (response.getStatusCode() == HttpStatus.OK) {
				AssetDetails asset = response.getBody();
				if (StringUtils.isBlank(asset.getMessage())) {
					return asset.getAsset();
				} else {
					logger.info(asset.getMessage());
				}
			}
		} else {
			method = HttpMethod.GET;
			HttpHeaders header = new HttpHeaders();
			header.set("token", "0000");
			entity = new HttpEntity<String>(header);
			ResponseEntity<GetAssetDetails> response = restTemplate.exchange(apiURL, method, entity, new ParameterizedTypeReference<GetAssetDetails>() {
			});
			if (response.getStatusCode() == HttpStatus.OK) {
				GetAssetDetails asset = response.getBody();
				if (StringUtils.isBlank(asset.getMessage())) {
					if(null != asset && null != asset.getAsset()){
						return asset.getAsset()[0];
					}
				} else {
					logger.info(asset.getMessage());
				}
			}
		}

		// clean objects
		restTemplate = null;

		return null;

	}
}
