package com.herouapp.theinternet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestCase {

	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp( String browser) {
//		Create driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		/*
		 * //default: System.out.println("Do not know how to start " + browser +
		 * ", starting chrome instead"); System.setProperty("webdriver.chrome.driver",
		 * "src/main/resources/chromedriver.exe"); driver = new ChromeDriver(); break;
		 */
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// maximize the browser window
		driver.manage().window().maximize();

	}

	@Test(priority = 1, groups = { "positivetest", "smoketest" })
	public void positiveLoginTestCase() {

		System.out.println("Starting the Test Case");

		// Open Test Page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is Opened");

		sleep(2000);
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		sleep(2000);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		sleep(1000);
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "actual url is not matching with expected url");

		sleep(4000);
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
		// Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
		// the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);

	}

	/*
	 * @Parameters({ "username", "password", "expectedMessage" })
	 * 
	 * @Test(priority = 2, groups = { "negativetest", "smoketest" })
	 * 
	 * public void negativeLoginTestCase(String username, String password, String
	 * expectedErrorMessage) {
	 * 
	 * System.out.println("Starting the negative Login Test Case:" + username +
	 * " and " + password);
	 * 
	 * // Open Test Page String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url); System.out.println("Page is Opened");
	 * 
	 * sleep(2000); WebElement usernameElement =
	 * driver.findElement(By.id("username")); usernameElement.sendKeys(username);
	 * 
	 * sleep(2000); WebElement passwordElement =
	 * driver.findElement(By.name("password")); passwordElement.sendKeys(password);
	 * 
	 * sleep(1000); WebElement loginButton =
	 * driver.findElement(By.tagName("button")); loginButton.click();
	 * 
	 * String expectedUrl = "http://the-internet.herokuapp.com/login"; String
	 * actualUrl = driver.getCurrentUrl(); Assert.assertEquals(actualUrl,
	 * expectedUrl, "actual url is not matching with expected url");
	 * 
	 * sleep(4000); WebElement successMessage =
	 * driver.findElement(By.cssSelector("#flash")); // String expectedMessage =
	 * "Your username is invalid!"; String actualMessage = successMessage.getText();
	 * // Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
	 * // the same as expected");
	 * Assert.assertTrue(actualMessage.contains(expectedErrorMessage),
	 * "Actual message does not contain expected message.\nActual Message: " +
	 * actualMessage + "\nExpected Message: " + expectedErrorMessage);
	 * 
	 * }
	 */

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativetest", "smokeTests" })
	public void negativeLoginTestCase(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting negativeLoginTest with " + username + " and " + password);

//		open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

//		enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);

//		enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

//		click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		sleep(3000);

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);
	}

	@AfterMethod(alwaysRun = true)
	private void teardown() {
		// close browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
