package com.automate.Rules.common;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

/**
 * 
 * @author Shikha A
 *
 */
public class DataCleanUp {

	final static Logger logger = Logger.getLogger(DataCleanUp.class);
	MongoCollection<Document> collection;
	MongoClient mongoClient;

	private void getDBConnection() {
		mongoClient = new MongoClient();
		collection = mongoClient.getDatabase(ApplicationConstants.DB_NAME).getCollection(ApplicationConstants.DB_COLLECTION_NAME);
	}

	public boolean deleteOneRecord(String assetId) {
		if (null == collection) {
			getDBConnection();
		}
		try {
			collection.findOneAndDelete(new Document("assetId", Integer.parseInt(assetId)));
			logger.info("Asset deleted from collection, assetId: " + assetId);
			return true;
		} catch (Exception e) {
			logger.debug("Problem in deleting assetId: " + assetId);
			return false;
		}
		/*
		 * for (Document document : allREsults) { String asset = (String)
		 * document.get("assetId"); } DeleteResult result =
		 * collection.deleteOne(new Document("assetId", assetId));
		 */
	}

	public void closeResources() {
		collection = null;
		mongoClient.close();
	}
}
