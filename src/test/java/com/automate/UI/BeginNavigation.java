package com.automate.UI;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Shikha A
 * 
 *         Proof of Concept
 * 
 */

public class BeginNavigation {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		baseUrl = "https://central-e1.app.aexp.com";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		/*
		 * DesiredCapabilities capability = DesiredCapabilities.chrome();
		 * DesiredCapabilities.firefox();
		 * DesiredCapabilities.internetExplorer(); DesiredCapabilities.safari();
		 * driver = new RemoteWebDriver(new URL("URL"),capability)
		 */;
	}

	@Test
	public void navigateApplication() throws Exception {
		driver.get(baseUrl + "/centralWeb/app");
		driver.findElement(By.cssSelector("a.divLink")).click();
		sleep();
		driver.findElement(By.xpath("//div[@id='divCard2']/div[3]/a")).click();
		sleep();
		driver.findElement(By.xpath("//div[@id='divCard3']/div[3]/a")).click();
		sleep();
		/*
		 * driver.findElement(By.xpath("//div[@id='divCard4']/div[3]/a")).click(
		 * ); sleep();
		 */
		driver.findElement(By.cssSelector("a.dropdown-toggle")).click();
		sleep();
		// app basic info
		driver.findElement(By.partialLinkText("Add Application")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Central Asset Registry");
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("Application Inventory Management is restructuring to Central Asset Repository");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("CAR");
		sleep();
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.partialLinkText("Next"));
		actions.moveToElement(element).click().perform();
		// sleep();
		/*
		 * driver.findElement(By.id("appdirector")).clear();
		 * driver.findElement(By.id("appdirector")).sendKeys("Matthew");
		 * sleep(); element = driver.findElement(By.partialLinkText("Next"));
		 * actions.moveToElement(element).click().perform(); sleep(); element =
		 * driver.findElement(By.partialLinkText("Next"));
		 * actions.moveToElement(element).click().perform(); sleep(); element =
		 * driver.findElement(By.partialLinkText("Next"));
		 * actions.moveToElement(element).click().perform(); sleep(); element =
		 * driver.findElement(By.partialLinkText("Next"));
		 * actions.moveToElement(element).click().perform(); sleep(); element =
		 * driver.findElement(By.partialLinkText("Next"));
		 * actions.moveToElement(element).click().perform(); sleep(); element =
		 * driver.findElement(By.partialLinkText("Next"));
		 * actions.moveToElement(element).click().perform();
		 */
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
