package com.assessment;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.google.common.io.Files;


public class Login_DemoQA {
	public String[][] readdata() throws InvalidFormatException, IOException {
		String data[][]=null;
		//1.to get a filepath
		String filepath="C:\\Users\\Admin\\Desktop\\jugal.xlsx";
		//2.to make file
		File file=new File(filepath);
		//3.to open a excel file
		XSSFWorkbook workbook =new XSSFWorkbook(file);
		//4.to open a sheet
		Sheet sheet=workbook.getSheet("Sheet4");
		//5.to select a row
		int n_row=sheet.getPhysicalNumberOfRows();
		System.out.println("No of rows are : "+n_row);
		data=new String[n_row][];
		for (int i = 0; i < data.length; i++) {
			Row row=sheet.getRow(i);
			
			//6.to select col
			int n_col=row.getPhysicalNumberOfCells();
			System.out.println("No of col are : "+n_col);
			data[i]=new String[n_col];
			for (int j = 0; j < data[i].length; j++) {
				
				Cell cell=row.getCell(j);
				//7.to convert all value into string 
				cell.setCellType(CellType.STRING);
				
				//8.to get value from the cell
				data[i][j]=cell.getStringCellValue();
			}
		}
		return data;
		
	}
	WebDriver driver;
	@Test
	public void test() throws InterruptedException, InvalidFormatException, IOException, HeadlessException, AWTException {
		String[][] data=readdata();
		
		for (int i = 0; i < data.length; i++) {
			
		driver=new ChromeDriver();
		driver.get("https://www.demo.guru99.com/V4/index.php");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		driver.findElement(By.name("uid")).sendKeys(data[i][0]);
		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys(data[i][1]);
		Thread.sleep(2000);
		driver.findElement(By.name("btnLogin")).click();
        Thread.sleep(5000);
        
        
        	 if (driver.getCurrentUrl().equals("https://www.demo.guru99.com/V4/manager/Managerhomepage.php")) {
     			
             	System.out.println(data[i][0]+" is Login SuccessFully ");
            	File file=((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
            	Files.copy(file,new File("C:\\Users\\ashir\\git\\repository\\Assignment_8\\Screenshots"));	
           	
     		} 
             
             else  {
            	 
            	System.out.println(data[i][0]+" is not Login SuccessFully ");
            	Alert a1=driver.switchTo().alert();
      			File file=((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
              	Files.copy(file,new File("C:\\Users\\Admin\\eclipse-workspace\\mvn_project\\Screen Shot\\file.png"));
              	
      			Thread.sleep(2000);
      			System.out.println(a1.getText());
      			Thread.sleep(2000);
      			a1.accept();
			}
         	
		}
        
		 driver.close();	
		 
	}
}