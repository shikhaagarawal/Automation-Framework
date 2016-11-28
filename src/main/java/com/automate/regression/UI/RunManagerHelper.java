package com.automate.regression.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.automate.regression.UI.domain.RunManager;

/**
 * Read RunManager.xlsx which is controls text case to execute or not
 * 
 * @author Shikha A
 *
 */
@Component
public class RunManagerHelper {

	private static Logger logger = Logger.getLogger(RunManagerHelper.class);

	// Return array of all test cases from RunManager.xlsx
	public RunManager[] readAllTestCases(String xlsName, String sheetName) {
		RunManager[] testCases = null;
		XSSFWorkbook runManagerExcel = null;
		try {
			runManagerExcel = new XSSFWorkbook(new FileInputStream(new File(xlsName)));
			XSSFSheet testCaseSheet = runManagerExcel.getSheet(sheetName);
			testCases = new RunManager[testCaseSheet.getPhysicalNumberOfRows() - 1];
			Iterator<Row> rowIterator = testCaseSheet.iterator();
			if (rowIterator.hasNext()) {
				// SKip header
				rowIterator.next();
			}
			int count = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				RunManager test = new RunManager();
				test.setTestSetNumber(null != row.getCell(0) ? row.getCell(0).toString().trim() : "");
				test.setName(null != row.getCell(1) ? row.getCell(1).toString().trim() : "");
				test.setExecute(null != row.getCell(2) ? row.getCell(2).toString().trim() : "");
				test.setChromeBrowser(null != row.getCell(3) ? row.getCell(3).toString().trim() : "");
				test.setIeBrowser(null != row.getCell(4) ? row.getCell(4).toString().trim() : "");
				test.setExecutableName(null != row.getCell(5) ? row.getCell(5).toString().trim() : "");
				test.setRemarks(null != row.getCell(6) ? row.getCell(6).toString().trim() : "");
				testCases[count] = test;
				count++;
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
		return testCases;
	}

}
