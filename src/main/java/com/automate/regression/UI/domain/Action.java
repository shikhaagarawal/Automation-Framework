package com.automate.regression.UI.domain;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automate.regression.UI.utility.CommonUtility;
import com.automate.regression.UI.utility.RegressionConstants;

/**
 * Enum for all actions which can be performed from TestScripts/.xlsx
 * 
 * @author Shikha A
 *
 */
public enum Action {

	CalendarDate {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			WebElement ele;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				ele = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				ele = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			if (null != ele) {

				Calendar c = new GregorianCalendar();
				if (null != value && value.length() > 0) {
					c.add(Calendar.DATE, Integer.parseInt(value));
				}
				Date d = c.getTime();

				DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
				ele.sendKeys(value + "-" + date.format(d));
				return true;
			} else {
				return false;
			}
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},

	Click {

		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				driver.findElement(By.id(path)).click();
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				driver.findElement(By.xpath(path)).click();
			} else {
				return false;
			}
			return true;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	CloseBrowser {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			driver.manage().deleteAllCookies();
			driver.quit();
			return true;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	DropDown {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			WebElement dropDownEle;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				dropDownEle = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				dropDownEle = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			dropDownEle.click();
			Select selectOption = new Select(dropDownEle);
			selectOption.selectByVisibleText(value);
			dropDownEle.click();
			// selectOption.selectByValue(value); - select by value
			// selectOption.selectByIndex(0); - select by index
			return true;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	/*
	 * FetchDropDownValues { public boolean performAction(WebDriver driver,
	 * String path,String value) { return false; }
	 * 
	 * 
	 * public List<WebElement> fetchAllValues(WebDriver driver, String xpath,
	 * String value) { WebElement dropDownEle =
	 * driver.findElement(By.xpath(xpath)); Select selectOption = new
	 * Select(dropDownEle); return selectOption.getOptions(); }
	 * 
	 * 
	 * public void setParameters(BrowserUtility browserUtility, CommonUtility
	 * commonUtility, String executableName, String browserName) { } },
	 */
	/*
	 * Login { private BrowserUtility browserUtility;
	 * 
	 * private String executableName;
	 * 
	 * private String browserName;
	 * 
	 * public boolean performAction(WebDriver driver, String path, String value,
	 * String objectType) {
	 * 
	 * try { if ("Chrome".equalsIgnoreCase(browserName)) {
	 * browserUtility.openChrome(value);
	 * 
	 * } else if ("IE".equalsIgnoreCase(browserName)) {
	 * browserUtility.openInternetExplorer(value); } try { String exeName =
	 * (getClass().getClassLoader().getResource(RegressionConstants.
	 * LOGIN_EXE_PATH + executableName + ".exe")).getFile();
	 * Runtime.getRuntime().exec(exeName); } catch (IOException e) {
	 * e.printStackTrace(); return false; }
	 * 
	 * } catch (InterruptedException e) { e.printStackTrace(); return false; }
	 * return true; }
	 * 
	 * public void setParameters(CommonUtility commonUtility, String
	 * browserName) { this.browserUtility = browserUtility; this.executableName
	 * = executableName; this.browserName = browserName; } },
	 */
	Logout {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			return false;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},

	MatchDisplay {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			WebElement ele;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				ele = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				ele = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			String eleText = ele.getText();
			if (null != ele && null != eleText && null != value) {
				value = value.replace("\n", "").replace("\r", "");
				eleText = eleText.replace("\n", "").replace("\r", "");
				if (eleText.equals(value)) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	MatchDynamicDisplay {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			WebElement ele;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				ele = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				ele = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			if (null != ele && null != ele.getText()) {
				if (ele.getText().indexOf(value) >= 0) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	Screenshot {
		private CommonUtility utility;

		private String browserName;

		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				if (null != utility) {
					FileUtils.copyFile(screenshot, new File(utility.getScreenshotsfolder(browserName) + value + ".png"));
				}

			} catch (IOException | HeadlessException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
			this.utility = commonUtility;
			this.browserName = browserName;
		}
	},
	ScrollUp {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			try {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-250)", "");
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	ScrollDown {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			try {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,250)", "");
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	SendKey {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			// add wait
			WebElement ele;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				ele = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				ele = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			if (null != ele) {
				ele.sendKeys(value);
				return true;
			} else {
				return false;
			}
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	SendAccessibilityKey {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			if (null != value) {
				value = value.toUpperCase();
			} else {
				return false;
			}
			WebElement ele;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				ele = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				ele = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			if (null != ele) {

				switch (value) {
				case "TAB":
					ele.sendKeys(Keys.TAB);
					return true;
				case "ENTER":
					ele.sendKeys(Keys.ENTER);
					return true;
				}
			}
			return false;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	UniqueSendKey {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			WebElement ele;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				ele = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				ele = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			if (null != ele) {
				DateFormat date = new SimpleDateFormat("yyyyMMdd-HHmmss");
				Date d = new Date();
				ele.sendKeys(value + "-" + date.format(d));
				return true;
			} else {
				return false;
			}
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	ValidateTitle {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			if (null != driver.getTitle() && driver.getTitle().equals(value)) {
				return true;
			} else {
				return false;
			}
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	ValidateTooltipText {
		// TODO this functionality is not working for garaph, xpath for graph is
		// not found
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			WebElement tooltip;
			if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
				tooltip = driver.findElement(By.id(path));
			} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
				tooltip = driver.findElement(By.xpath(path));
			} else {
				return false;
			}
			Actions builder = new Actions(driver);
			builder.moveToElement(tooltip).perform();
			// Extract text from tooltip
			String tooltip_msg = tooltip.getText();
			if (null != tooltip_msg && tooltip_msg.equals(value)) {
				return true;
			}
			return false;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	Wait {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {

			try {
				if (null == value || "".equals(value)) {
					value = "0";
				}
				Thread.sleep(new Long(value.substring(0, value.indexOf("."))));
			} catch (NumberFormatException | InterruptedException e) {
				e.printStackTrace();
				return false;
			}

			return true;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	WaitUntil {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			try {
				if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_ID)) {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));
				} else if (objectType.equalsIgnoreCase(RegressionConstants.OBJECT_ID_XPATH)) {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
				} else {
					return false;
				}
			} catch (TimeoutException e) {
				return false;
			}
			return true;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	},
	noMatchingAction {
		public boolean performAction(WebDriver driver, String path, String value, String objectType) {
			System.out.println("No matching action found");
			return false;
		}

		public void setParameters(CommonUtility commonUtility, String browserName) {
		}
	};

	public abstract boolean performAction(WebDriver driver, String path, String value, String objectType);

	public abstract void setParameters(CommonUtility commonUtility, String browserName);
}
