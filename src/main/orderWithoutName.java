package main;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import pageObject.orderPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.lang.model.element.Element;
@Listeners(main.ListenerTest.class)
public class orderWithoutName {
	public String expectedTitle, actualTitle;
	public String url = "https://thecoffeehouse.com/";
	public ChromeOptions options;
	public ChromeDriver driver;
	public WebDriverWait wait;
	public Scanner input = new Scanner(System.in);
	public boolean expectedResult, actualResult;
	
	///////////GET DATA
	
    public String phone,cateName, productName, address, name;
	
    //////////PAGE OBJECTT
    
    orderPage objOrderPage;
	
    public void setUpData() throws IOException {
		File file = new File("..\\\\TheCoffeHouse\\\\dataTest\\\\OrderData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		///////DATA FROM EXCEL
		cateName = sheet.getRow(2).getCell(0).getStringCellValue();
		productName = sheet.getRow(2).getCell(1).getStringCellValue();
		address = sheet.getRow(2).getCell(2).getStringCellValue();
		name = sheet.getRow(2).getCell(3).getStringCellValue();
		phone = sheet.getRow(2).getCell(4).getStringCellValue();
	}
    
    @BeforeTest
	public void launchBrowser() throws IOException {
		setUpData();
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		options = new ChromeOptions();
		driver = new ChromeDriver(options.addArguments(/*"--headless",*/"disable-gpu"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,10);
		driver.get(url);
	}  
	
	@Test(priority = 0)
	public void orderProduct_step() throws InterruptedException {
		objOrderPage = new orderPage(driver, wait);
		objOrderPage.pickCategory(cateName);
		objOrderPage.pickProduct(productName);
		objOrderPage.inputAddress(address);
		objOrderPage.addProductToCart();
		objOrderPage.goToCart();
		objOrderPage.fillInfo(name, phone);
		objOrderPage.clickOrder();
	}
	@Test(priority = 1)
	public void checkFinished() {
		objOrderPage.checkErrorMessageDisplayed();
	}
	
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
}