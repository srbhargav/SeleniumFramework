package com.herouapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {
	@Test(priority = 1, groups = { "negativetest", "smoketest" })

	public void invalidUsernameTestCase() {

		System.out.println("Starting the Invalid Username Test Case");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep
		sleep(2000);

		// maximize the browser window
		driver.manage().window().maximize();

		// Open Test Page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is Opened");

		sleep(2000);
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith1");

		sleep(2000);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		sleep(1000);
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		String expectedUrl = "http://the-internet.herokuapp.com/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "actual url is not matching with expected url");

		sleep(4000);
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		String expectedMessage = "Your username is invalid!";
		String actualMessage = successMessage.getText();
		// Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
		// the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);

		// close browser
		driver.quit();

	}

	
	
	@Test(priority = 2, enabled = true, groups = { "negativetest"})
	public void invalidPasswordTestCase() {

		System.out.println("Starting the Invalid Password Test Case");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep
		sleep(2000);

		// maximize the browser window
		driver.manage().window().maximize();

		// Open Test Page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is Opened");

		sleep(2000);
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		sleep(2000);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!!");

		sleep(1000);
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		String expectedUrl = "http://the-internet.herokuapp.com/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "actual url is not matching with expected url");

		sleep(4000);
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		String expectedMessage = "Your password is invalid!";
		String actualMessage = successMessage.getText();
		// Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
		// the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);

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
