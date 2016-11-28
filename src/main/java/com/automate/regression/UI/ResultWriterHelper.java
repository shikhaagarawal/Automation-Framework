package com.automate.regression.UI;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.automate.regression.UI.domain.RunManager;
import com.automate.regression.UI.utility.CommonUtility;
import com.automate.regression.UI.utility.RegressionConstants;

/**
 * 
 * @author Shikha A
 *
 */

@Component
public class ResultWriterHelper {

	private XSSFWorkbook workbook;
	private int rowCount = 0;

	public synchronized boolean createResultFile(String browserName) {
		rowCount = 0;
		if (null == workbook) {
			workbook = new XSSFWorkbook();
		}
		if (null != workbook) {
			XSSFSheet sheet = workbook.createSheet(browserName + "_" + RegressionConstants.OUTPUT_FILE_SHEET_NAME);
			Row row = sheet.createRow(rowCount++);
			Cell cell = row.createCell(0);
			createCell(cell, IndexedColors.YELLOW, true, "Test Set#");
			cell = row.createCell(1);
			createCell(cell, IndexedColors.YELLOW, true, "Test Case Name");
			cell = row.createCell(2);
			createCell(cell, IndexedColors.YELLOW, true, "Sheet Name");
			cell = row.createCell(3);
			createCell(cell, IndexedColors.YELLOW, true, "Executed");
			cell = row.createCell(4);
			createCell(cell, IndexedColors.YELLOW, true, "Result");
			cell = row.createCell(5);
			createCell(cell, IndexedColors.YELLOW, true, "Remarks(<TestCaseName-SheetName-ObjectName-Result>)");
			cell = row.createCell(6);
			createCell(cell, IndexedColors.YELLOW, true, "Detailed Failure reason(if any)");
			return true;
		}
		return false;
	}

	public void createMetaData(RunManager data, String sheetName, String browserName) {
		XSSFSheet sheet = workbook.getSheet(browserName + "_" + RegressionConstants.OUTPUT_FILE_SHEET_NAME);
		if (null != sheet) {
			Row row = sheet.createRow(rowCount++);
			Cell cell = row.createCell(0);
			createCell(cell, null, false, data.getTestSetNumber());
			cell = row.createCell(1);
			createCell(cell, null, false, data.getName());
			cell = row.createCell(2);
			createCell(cell, null, false, sheetName);
			cell = row.createCell(3);
			if (data.isExecute()) {
				if (RegressionConstants.CHROME_BROWSER_NAME.equalsIgnoreCase(browserName)) {
					createCell(cell, null, false, data.isChromeBrowser() ? "YES" : "NO");
				} else if (RegressionConstants.IE_BROWSER_NAME.equalsIgnoreCase(browserName)) {
					createCell(cell, null, false, data.isIeBrowser() ? "YES" : "NO");
				} else {
					createCell(cell, null, false, "Browser detail not found");
				}
			} else {
				createCell(cell, null, false, "NO");
			}
			cell = row.createCell(4);
			if (RegressionConstants.FAIL_STATUS.equalsIgnoreCase(data.getTestResult())) {
				createCell(cell, IndexedColors.RED, true, data.getTestResult());
			} else {
				createCell(cell, null, false, data.getTestResult());
			}
			cell = row.createCell(5);
			createCell(cell, null, false, data.getRemarks());
			cell = row.createCell(6);
			createCell(cell, null, false, data.getFailureReason());
		}
	}

	public void writeIntoFile(CommonUtility commonUtility, String browserName) {
		try (FileOutputStream outputStream = new FileOutputStream(commonUtility.getOutputFolder() + browserName + "_" + RegressionConstants.OUTPUT_FILE_NAME)) {
			workbook.setActiveSheet(0);
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeResultCount(int passCount, int failCount, int notExecutedCount, String elapsedTime,String browserName) {
		XSSFSheet sheet1 = workbook.createSheet("Status");
		workbook.setSheetOrder("Status", 0);

		Row row = sheet1.createRow(0);
		Cell cell = row.createCell(0);
		createCell(cell, IndexedColors.YELLOW, true, "Test Result");
		cell = row.createCell(1);
		createCell(cell, IndexedColors.YELLOW, true, "Count");
		row = sheet1.createRow(1);
		cell = row.createCell(0);
		createCell(cell, null, false, RegressionConstants.PASS_STATUS);
		cell = row.createCell(1);
		createCell(cell, null, false, String.valueOf(passCount));
		row = sheet1.createRow(2);
		cell = row.createCell(0);
		createCell(cell, null, false, RegressionConstants.FAIL_STATUS);
		cell = row.createCell(1);
		createCell(cell, null, false, String.valueOf(failCount));
		row = sheet1.createRow(3);
		cell = row.createCell(0);
		createCell(cell, null, false, RegressionConstants.NOT_EXECUTED);
		cell = row.createCell(1);
		createCell(cell, null, false, String.valueOf(notExecutedCount));
		row = sheet1.createRow(4);
		cell = row.createCell(0);
		createCell(cell, null, false, RegressionConstants.TOTAL);
		cell = row.createCell(1);
		int totalTestCases = passCount + failCount + notExecutedCount;
		createCell(cell, null, false, String.valueOf(totalTestCases));
		Double passedTestCases = (double) passCount / (passCount + failCount);
		row = sheet1.createRow(5);
		cell = row.createCell(0);
		createCell(cell, null, false, RegressionConstants.PASSED_PER);
		cell = row.createCell(1);
		if ((passCount + failCount) == passCount) {
			createCell(cell, null, false, "100%");
		} else {
			String percentage = String.valueOf(passedTestCases);
			if (percentage.substring(percentage.indexOf('.')).length() > 3) {
				createCell(cell, null, false, percentage.substring(0, percentage.indexOf('.') + 3) + " %");
			} else {
				createCell(cell, null, false, percentage + " %");
			}
		}
		row = sheet1.createRow(7);
		cell = row.createCell(0);
		createCell(cell, IndexedColors.YELLOW, true, browserName);
		cell = row.createCell(1);
		createCell(cell, IndexedColors.YELLOW, true, elapsedTime);
	}

	private void createCell(Cell c, IndexedColors color, boolean setColor, String cellValue) {
		XSSFCellStyle style = workbook.createCellStyle();
		if (setColor) {
			style.setFillForegroundColor(color.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		c.setCellStyle(style);
		c.setCellValue(cellValue);
	}
}
