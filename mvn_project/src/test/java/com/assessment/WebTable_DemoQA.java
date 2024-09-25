package com.assessment;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebTable_DemoQA {

	 WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://demoqa.com/webtables");
	        driver.manage().window().maximize();
	    }

	    @Test(priority = 1)
	    public void editGivenName() throws InterruptedException {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebElement elee = driver.findElement(By.xpath("//h1[normalize-space()='Web Tables']"));
	        js.executeScript("arguments[0].scrollIntoView();", elee);
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//span[@id='edit-record-1']")).click();
	        Thread.sleep(2000);

	        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
	        firstNameField.clear();
	        Thread.sleep(2000);
	        firstNameField.sendKeys("Jugal");
	        Thread.sleep(2000);

	        driver.findElement(By.id("submit")).click();
	        Thread.sleep(2000);        
	    }

	    @Test(priority = 2)
	    public void deleteGivenName() throws InterruptedException {
	    	WebElement deleteButton = driver.findElement(By.xpath("//span[@id='delete-record-2']"));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
	    	deleteButton.click();
	    	Thread.sleep(2000); 


	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}

