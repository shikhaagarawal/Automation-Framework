package com.automate.regression.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.automate.regression.UI.domain.Action;
import com.automate.regression.UI.domain.DataManager;
import com.automate.regression.UI.utility.RegressionConstants;

/**
 * Read all xlsx and sheets in TestScripts folder
 * 
 * @author Shikha A
 *
 */
@Component
public class DataManagerHelper {

	private static Logger logger = Logger.getLogger(DataManagerHelper.class);

	// Return list of testScripts xlsx, with all sheets as list array
	public List<DataManager[]> readAllTestCases(String fileName) {
		List<DataManager[]> allSheetsData = new ArrayList<DataManager[]>();
		XSSFWorkbook runManagerExcel = null;
		try {
			runManagerExcel = new XSSFWorkbook(new FileInputStream(new File(fileName)));
			int sheetCount = runManagerExcel.getNumberOfSheets();
			for (int i = 0; i < sheetCount; i++) {
				XSSFSheet testCaseScenario = runManagerExcel.getSheetAt(i);
				Iterator<Row> rowIterator = testCaseScenario.iterator();
				if (testCaseScenario.getPhysicalNumberOfRows() > 0) {
					DataManager[] testCases = new DataManager[testCaseScenario.getPhysicalNumberOfRows() - 1];
					String testSheetName = runManagerExcel.getSheetName(i);
					if (rowIterator.hasNext()) {
						// Skip header
						rowIterator.next();
					}

					int count = 0;
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						if (null != row.getCell(1) && "" != row.getCell(1).toString()) {
							DataManager test = new DataManager();
							test.setObjectName(null != row.getCell(0) ? row.getCell(0).toString().trim() : "");
							try {
								test.setAction(null != row.getCell(1) ? Action.valueOf(row.getCell(1).toString().trim()) : Action.noMatchingAction);
							} catch (IllegalArgumentException ie) {
								test.setAction(Action.noMatchingAction);
							}
							test.setExpectToPass(null != row.getCell(2) ? row.getCell(2).toString().trim() : "");
							String columnValue = null != row.getCell(3) ? row.getCell(3).toString().trim() : "";
							if (RegressionConstants.OBJECT_ID_ID.equalsIgnoreCase(columnValue)) {
								test.setId(null != row.getCell(4) ? row.getCell(4).toString().trim() : "");
							} else if (RegressionConstants.OBJECT_ID_XPATH.equalsIgnoreCase(columnValue)) {
								test.setXpath(null != row.getCell(4) ? row.getCell(4).toString().trim() : "");
							}
							test.setValue(null != row.getCell(5) ? row.getCell(5).toString().trim() : "");
							test.setSheetName(testSheetName);
							testCases[count] = test;
							count++;
						}
					}
					allSheetsData.add(testCases);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(this.getClass() + "Error in reading RunManager-Select Test Case excel");
		} finally {
			if (null != runManagerExcel) {
				try {
					runManagerExcel.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(this.getClass() + "Error in reading RunManager-Select Test Case excel");
				}
			}
		}
		return allSheetsData;
	}

}
