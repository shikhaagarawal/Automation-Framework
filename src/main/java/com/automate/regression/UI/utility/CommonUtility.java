package com.automate.regression.UI.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Shikha A
 *
 */
@Component
public class CommonUtility {

	private String dateTime;

	public boolean applicationLogin() {
		try {
			try {
				Runtime.getRuntime().exec("login.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Thread.sleep(3000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*public void wait(String idName, String pathName) {
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 30);
		if (StringUtils.isNotEmpty(idName)) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(idName)));
		} else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pathName)));
		}
	}*/

	public String getOutputFolder() {
		String folderName = RegressionConstants.OUTPUT_FOLDER + "/" + dateTime + "/";
		File f = new File(folderName);
		if (!f.exists()) {
			f.mkdirs();
		}
		// RegressionConstants.OUTPUT_FOLDER
		return folderName;
	}

	public String getScreenshotsfolder(String browserName) {
		String folderName = getOutputFolder() + RegressionConstants.Screenshot_FOLDER + "/" +browserName+"/";
		File f = new File(folderName);
		if (!f.exists()) {
			f.mkdirs();
		}
		return folderName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
