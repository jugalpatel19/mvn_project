package com.assessment;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DemoQa_Form {
	
	 private ChromeDriver driver;
	    private JavascriptExecutor js;
	    private TakesScreenshot ss;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        js = (JavascriptExecutor) driver;
	        ss = (TakesScreenshot) driver;
	    }

	    @Test
	    public void fillForm() throws InterruptedException {
	        driver.get("https://demoqa.com/automation-practice-form");

	        // Fill the form
	        driver.findElement(By.id("firstName")).sendKeys("Jugal");
	        Thread.sleep(3000);
	        
	       driver.findElement(By.id("lastName")).sendKeys("Patel");
	       Thread.sleep(3000);
	       driver.findElement(By.id("userEmail")).sendKeys("jp@gmail.com");
	       Thread.sleep(3000); 
	       driver.findElement(By.xpath("//label[normalize-space()='Male']")).click();
	        driver.findElement(By.id("userNumber")).sendKeys("9727843389");
	        Thread.sleep(3000);
	        
	        // Date of Birth
	        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("dateOfBirthInput")));
	        Thread.sleep(2000);
	        driver.findElement(By.id("dateOfBirthInput")).click();
	        
	        // Year Selection
	        new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']")))
	                .selectByVisibleText("1986");
	        Thread.sleep(3000);
	        
	        // Month Selection
	        new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']")))
	                .selectByVisibleText("August");
	        Thread.sleep(3000);
	        
	        // Date Selection
	        driver.findElement(By.cssSelector("div[aria-label='Choose Tuesday, August 19th, 1986']")).click();
	        Thread.sleep(3000);
	        
	        // Subjects and Hobbies
	        driver.findElement(By.xpath("//input[@id='subjectsInput']")).sendKeys("PHP");
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//label[normalize-space()='Sports']")).click();
	        
	        // Upload Picture
//	        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\ashir\\Downloads\\ss-2.png");
	        
	        // Address
	        driver.findElement(By.id("currentAddress")).sendKeys("Surat");
	        
	        // State and City selection
	        driver.findElement(By.xpath("//div[@id='state']//div[contains(@class,'css-tlfecz-indicatorContainer')]")).click();
	        driver.findElement(By.xpath("//div[text()='Haryana']")).click();
	        driver.findElement(By.xpath("//*[@id=\"city\"]/div/div[2]/div")).click();
	        driver.findElement(By.xpath("//div[text()='Karnal']")).click();
	        Thread.sleep(3000);
	        
	        // Take screenshot of filled form
	        takeScreenshot("Form1");
	        
	        // Submit the form
	        driver.findElement(By.xpath("//button[@id='submit']")).click();
	        
	        // Take screenshot after submission
	        takeScreenshot("Form2");
	    }

	    private void takeScreenshot(String screenshotName) {
	        File sourcefile = ss.getScreenshotAs(OutputType.FILE);
	        File targetFile = new File("C:\\Users\\Admin\\eclipse-workspace\\mvn_project\\Screen Shot\\" + screenshotName + ".png");
	        sourcefile.renameTo(targetFile);
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}


