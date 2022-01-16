package com.sansung;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.beust.jcommander.Parameters;
import com.google.common.util.concurrent.UncheckedTimeoutException;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Baseclass {
	public static WebDriver driver;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;

	// Avenstack Extends Report
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;

	@BeforeSuite
	public void beforeSuite() {
				browserlaunch("https://www.samsung.com/in/");
		////		reporter = new ExtentHtmlReporter("./target/JavaCombine.html");
		System.out.println("My Before Suite");
		////		extent = new ExtentReports();
		////		extent.attachReporter(reporter);
	}	

	@AfterMethod
	public void afterMethod() {
		//		extent.flush();
	}

	@BeforeTest
	public static void browserlaunch(String browser) {
		//		public static void browserlaunch(@Optional("firefox")String browser)
		//		System.setProperty("webdriver.chrome.driver", "E:\\eclipse-workspace\\JavaCombine\\Driver\\chromedriver.exe");

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("No Browser Matched");
			break;
		}

	}

	public static WebElement findElementById(String id) {
		return driver.findElement(By.id(id));
	}

	public static WebElement findElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public static WebElement findElementname(By name) {
		return driver.findElement(name);
	}

	public static WebElement findElementid(By id) {
		return driver.findElement(id);
	}

	public static String sendtestData(WebElement element ,String key) throws FileNotFoundException {
		File f = new File("F:\\newprograms\\saturdayToday\\data.property");
		FileReader reader = new FileReader(f);
		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String k =  prop.getProperty(key);
		element.sendKeys(k);
		return k;
	}
	public static String SendByExcel(WebElement element,int i, int j) throws IOException {
		String value = null;
		File f = new File("F:\\newprograms\\saturdayToday\\data\\hotallog.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		Row row = sheet.getRow(i);
		Cell cell = row.getCell(j);
		value = cell.getStringCellValue();
		element.sendKeys(value);
		return value;
	}
	public static String readbysheet(int i, int j) throws IOException {
		String value = null;
		File f = new File("F:\\newprograms\\saturdayToday\\data\\hotallog.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		Row row = sheet.getRow(i);
		Cell cell = row.getCell(j);
		value = cell.getStringCellValue();
		return value;
	}
	
	public static int getrowcount() throws IOException {
		File f = new File("F:\\newprograms\\saturdayToday\\data\\hotallog.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
	 int numofrows =sheet.getPhysicalNumberOfRows();
	    return numofrows;
	}
	public static int getcellcount() throws IOException {
		File f = new File("F:\\\\newprograms\\\\saturdayToday\\\\data\\\\hotallog.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		Row row =sheet.getRow(0);
		int rows= sheet .getPhysicalNumberOfRows();
		int numofcolumns =row.getPhysicalNumberOfCells();
		return numofcolumns;
		
	}
	 public static void pageClose() {
		driver.close();
	}

	public static void screenShot() {
		try {
			TakesScreenshot tk = (TakesScreenshot) driver;
			FileUtils.copyFile(tk.getScreenshotAs(OutputType.FILE), new File("./ScreenShot/screen.jpeg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void jsGetAttribute(WebElement element, String string) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String data = (String) js.executeScript("return arguments[0].getAttribute('value')", element);
		System.out.println(data);
	}
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}


	public static void select(WebElement element,String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
     public static void captureScreen() throws IOException {
		Date d = new Date();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./ScreenShot/" + d.toString().replace(":", "_") + ".png"));
	}
	public static void selectDropDownindex(WebElement element, int value) {
		Select select = new Select(element);
		select.selectByIndex(value);
	}
	public static void selectDropDowntext(WebElement element,  String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	public static void selectDropDown(WebElement element, String string) {
		Select select = new Select(element);
		select.selectByValue(string);
	}
	public static void dataSend(WebElement element, String data) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(data);
	}

}
