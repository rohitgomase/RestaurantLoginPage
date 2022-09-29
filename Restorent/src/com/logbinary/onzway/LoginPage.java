package com.logbinary.onzway;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPage {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/java software/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qaadmin.onzway.com");

	}

	@Test
	public void verifyWithValidUnamePassTest() {
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("restaurant@onzway.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("777777");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		String actresult = driver.findElement(By.xpath("//span[contains(text(), 'Dashboard')]")).getText();
		String expresult = "Dashboard";
		Assert.assertEquals(actresult, expresult);
	}

	@Test
	public void verifyWithBlankUnamePassTest() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(" ");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(" ");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		String actresult = driver
				.findElement(By.xpath("//div[contains(text(), 'This field is required.') and @id='email-error']"))
				.getText();
		String expresult = "This field is required.";
		Assert.assertEquals(actresult, expresult);
	}

	@Test
	public void verifyMsgAfterLogout() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("restaurant@onzway.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("777777");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.xpath("//img[@src=\"/admin/img/avatar5.png\"]")).click();
		driver.findElement(By.xpath("//*[@href=\"/users/logout\"]")).click();
		String actresult = driver.findElement(By.xpath("//p[@class='text-success text-center']")).getText();
		String expresult = " You have been logged out successfully";
		Assert.assertEquals(actresult, expresult);
	}

	@AfterMethod
	public void end() {
		driver.close();
	}

}
