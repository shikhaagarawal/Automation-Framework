package com.automate.UI;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.automate.regression.UI.DataManagerHelper;
import com.automate.regression.UI.ResultWriterHelper;
import com.automate.regression.UI.RunManagerHelper;
import com.automate.regression.UI.domain.Action;
import com.automate.regression.UI.domain.DataManager;
import com.automate.regression.UI.domain.RunManager;
import com.automate.regression.UI.utility.CommonUtility;
import com.automate.regression.UI.utility.RegressionConstants;

/**
 * 
 * @author Shikha A
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class DataManagerSetupTest {

	private static Logger logger = Logger.getLogger(DataManagerSetupTest.class);

	RunManagerHelper managerHelper = new RunManagerHelper();

	DataManagerHelper dataManagerHelper = new DataManagerHelper();

	private CommonUtility commonUtility = new CommonUtility();

	private ResultWriterHelper resultWriter = new ResultWriterHelper();

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setInitialValue() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Date date = new Date();
		logger.info("Test");
		commonUtility.setDateTime(dateFormat.format(date));
	}

	@Test
	public void executeChromeTestCases() throws InterruptedException {
		Instant start = Instant.now();
		WebDriver driver = null;
		int passCount = 0;
		int failCount = 0;
		int notExecutedCount = 0;

		// Create output xls header
		resultWriter.createResultFile(RegressionConstants.CHROME_BROWSER_NAME);

		RunManager[] testCases = getAllTestCase();
		for (RunManager testCase : testCases) {
			// Execute only when isExecute is set to 'Y' in RunManager.xlsx
			if (testCase.isExecute()) {
				if (testCase.isChromeBrowser()) {
					if ("Login".equalsIgnoreCase(testCase.getName())) {
						String loginURL = testCase.getRemarks();
						driver = openChrome(loginURL);
						enterCredential(testCase.getExecutableName());
						writeTestResult(testCase, RegressionConstants.NA_STATUS, RegressionConstants.CHROME_BROWSER_NAME, RegressionConstants.PASS_STATUS);
						passCount++;
						continue;
					}

					String fileName = RegressionConstants.TESTCASE_LOCATION + testCase.getName() + RegressionConstants.TESTCASE_EXTN;
					File f = new File(fileName);
					if (!f.exists()) {
						testCase.setFailureReason("Test Case does not exist");
						writeTestResult(testCase, RegressionConstants.NA_STATUS, RegressionConstants.CHROME_BROWSER_NAME, RegressionConstants.NA_STATUS);
						continue;
					} else {
						// Run only on chrome
						if (executeRunManagerTestScript(driver, fileName, testCase, RegressionConstants.CHROME_BROWSER_NAME)) {
							passCount++;
						} else {
							failCount++;
						}
					}
				} else {
					notExecutedCount++;
					writeTestResult(testCase, RegressionConstants.NA_STATUS, RegressionConstants.CHROME_BROWSER_NAME, RegressionConstants.NA_STATUS);
				}
			} else {
				notExecutedCount++;
				writeTestResult(testCase, RegressionConstants.NA_STATUS, RegressionConstants.CHROME_BROWSER_NAME, RegressionConstants.NA_STATUS);
			}
		}
		Instant end = Instant.now();
		long s = Duration.between(start, end).getSeconds();
		String elapsedTime = String.format("%d:%02d:%02d.%03d", s / 3600, (s % 3600) / 60, (s % 60), (s % 1000));
		resultWriter.writeResultCount(passCount, failCount, notExecutedCount, elapsedTime, RegressionConstants.CHROME_BROWSER_NAME);
		resultWriter.writeIntoFile(commonUtility, RegressionConstants.CHROME_BROWSER_NAME);
	}

	//IE is out of scope
	/*
	 * @Test public void executeIETestCases() throws InterruptedException {
	 * WebDriver driver = null; int passCount = 0; int failCount = 0; int
	 * notExecutedCount = 0;
	 * 
	 * // Create output xls header
	 * resultWriter.createResultFile(RegressionConstants.IE_BROWSER_NAME);
	 * 
	 * Instant start = Instant.now(); RunManager[] testCases = getAllTestCase();
	 * for (RunManager testCase : testCases) { if (testCase.isExecute()) {
	 * 
	 * if (RegressionConstants.MASTER_LOGIN_TESTCASE.equalsIgnoreCase(testCase.
	 * getName())) { String loginURL = testCase.getRemarks(); driver =
	 * openInternetExplorer(loginURL); WebElement ele =
	 * driver.findElement(By.id("overridelink")); if(null !=
	 * driver.findElement(By.id("overridelink"))){ ele.click(); }
	 * //Thread.sleep(100); enterCredential(testCase.getExecutableName());
	 * 
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 20); Alert alert =
	 * wait.until(ExpectedConditions.alertIsPresent());
	 * System.out.println(ExpectedConditions.alertIsPresent());
	 * alert.setCredentials(new UserAndPassword("ads-sso-1\\mjones32",
	 * "P@$$W0rd")); alert.accept();
	 * 
	 * 
	 * 
	 * writeTestResult(testCase, RegressionConstants.NA_STATUS,
	 * RegressionConstants.IE_BROWSER_NAME, RegressionConstants.PASS_STATUS);
	 * passCount++; continue; }
	 * 
	 * String fileName = RegressionConstants.TESTCASE_LOCATION +
	 * testCase.getName() + RegressionConstants.TESTCASE_EXTN; File f = new
	 * File(fileName); if (!f.exists()) { testCase.setFailureReason(
	 * "Test Case does not exist"); writeTestResult(testCase,
	 * RegressionConstants.NA_STATUS, RegressionConstants.IE_BROWSER_NAME,
	 * RegressionConstants.NA_STATUS); continue; } else { if
	 * (testCase.isIeBrowser()) { // Run only on IE if
	 * (executeRunManagerTestScript(driver, fileName, testCase,
	 * RegressionConstants.IE_BROWSER_NAME)) { passCount++; } else {
	 * failCount++; } } else { notExecutedCount++; writeTestResult(testCase,
	 * RegressionConstants.NA_STATUS, RegressionConstants.IE_BROWSER_NAME,
	 * RegressionConstants.NA_STATUS); } } } else { notExecutedCount++;
	 * writeTestResult(testCase, RegressionConstants.NA_STATUS,
	 * RegressionConstants.IE_BROWSER_NAME, RegressionConstants.NA_STATUS); } }
	 * Instant end = Instant.now(); long s = Duration.between(start,
	 * end).getSeconds(); String elapsedTime =
	 * String.format("%d:%02d:%02d.%03d", s / 3600, (s % 3600) / 60, (s % 60),
	 * (s % 1000)); resultWriter.writeResultCount(passCount, failCount,
	 * notExecutedCount, elapsedTime, RegressionConstants.IE_BROWSER_NAME);
	 * resultWriter.writeIntoFile(commonUtility,
	 * RegressionConstants.IE_BROWSER_NAME); }
	 */

	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}

	}

	private void takeScreenShot(String screenshotName, WebDriver driver, String browserName) {
		Action.Screenshot.setParameters(commonUtility, browserName);
		Action.Screenshot.performAction(driver, "", screenshotName, "");
	}

	private void actionFailed(RunManager testCase, StringBuffer failedTestCaseName, String screenshotName, String failureReason) {
		failedTestCaseName.append(screenshotName + ",");
		testCase.setTestResult(RegressionConstants.FAIL_STATUS);
		testCase.setRemarks(failedTestCaseName.toString());
		testCase.setFailureReason(failureReason);
	}

	private void writeTestResult(RunManager testResult, String sheetName, String browserName, String result) {
		testResult.setTestResult(result);
		resultWriter.createMetaData(testResult, sheetName, browserName);
	}

	private boolean executeRunManagerTestScript(WebDriver driver, String fileName, RunManager testCase, String browserName) {
		StringBuffer failedTestCaseName = new StringBuffer();
		// Fetch list of all sheets for matching RunManager test
		// case name
		List<DataManager[]> allTestCases = dataManagerHelper.readAllTestCases(fileName);
		for (DataManager[] dataManager : allTestCases) {
			// execute test cases
			boolean fail = false;
			for (DataManager data : dataManager) {
				String screenshotName = "";
				try {
					if (null != data && null != data.getAction()) {
						screenshotName = "TestCase-" + testCase.getTestSetNumber() + "/" + testCase.getName() + "---" + data.getSheetName() + "---" + data.getObjectName() + "---" + RegressionConstants.FAIL_STATUS;
						if (data.getAction().equals(Action.Screenshot)) {
							data.getAction().setParameters(commonUtility, browserName);
						}
						String objectId = "", type = "";
						if (null != data.getId()) {
							objectId = data.getId();
							type = RegressionConstants.OBJECT_ID_ID;
						} else if (null != data.getXpath()) {
							objectId = data.getXpath();
							type = RegressionConstants.OBJECT_ID_XPATH;
						}
						if (!data.getAction().performAction(driver, objectId, data.getValue(), type)) {

							// Check if expected value is to pass
							if (data.isExpectToPass()) {

								// Mark test case as Fail
								fail = true;
								actionFailed(testCase, failedTestCaseName, screenshotName, "Expected output not found");
								takeScreenShot(screenshotName, driver, browserName);
							}
						}
					}
				} catch (Exception e) {
					if (data.isExpectToPass()) {
						fail = true;
						actionFailed(testCase, failedTestCaseName, screenshotName, e.getMessage());
						takeScreenShot(screenshotName, driver, browserName);
					}
				}
			}
			if (fail) {
				testCase.setRemarks(failedTestCaseName.toString());
				writeTestResult(testCase, dataManager[0].getSheetName(), browserName, RegressionConstants.FAIL_STATUS);
				return false;
			} else {
				if (dataManager.length > 0) {
					writeTestResult(testCase, dataManager[0].getSheetName(), browserName, RegressionConstants.PASS_STATUS);
				}
				return true;
			}
		}
		return false;
	}

	private WebDriver openChrome(String url) throws InterruptedException {
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-extensions");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.navigate().to(url);
		logger.info(this.getClass() + "Chrome browser is openend successfully");
		return driver;
	}

	//IE is out of scope
	/*private WebDriver openInternetExplorer(String url) throws InterruptedException {
		WebDriver driver;
		DesiredCapabilities returnCapabilities = DesiredCapabilities.internetExplorer();
		returnCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		returnCapabilities.setCapability("ignoreZoomSetting", true);
		driver = new InternetExplorerDriver(returnCapabilities);
		driver.navigate().to(url);
		logger.info(this.getClass() + "Internet Explorer browser is openend successfully");
		return driver;
	}*/

	private void enterCredential(String executableName) {
		if(null != executableName && !"".equals(executableName)){
		try {
			String exeName = (getClass().getClassLoader().getResource(RegressionConstants.LOGIN_EXE_PATH + executableName + ".exe")).getFile();
			try {
				Process p = Runtime.getRuntime().exec(exeName);
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}}else{
			System.out.println("Login executable name not defined");
		}
	}

	private RunManager[] getAllTestCase() {
		RunManager[] testCases = managerHelper.readAllTestCases(RegressionConstants.MASTER_EXCEL, RegressionConstants.MASTER_TESTCASE_SHEET);
		return testCases;
	}

}
