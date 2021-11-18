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

import pageObject.loginPage;
import pageObject.orderPage;
import pageObject.personalPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.lang.model.element.Element;
@Listeners(main.ListenerTest.class)
public class moveToUserInfo {
	public String expectedTitle, actualTitle;
	public String url = "https://thecoffeehouse.com/";
	public ChromeOptions options;
	public ChromeDriver driver;
	public WebDriverWait wait;
	public Scanner input = new Scanner(System.in);
	public boolean expectedResult, actualResult;
	
    //////GET DATA
	public String phone;
	
	//////PAGE OJECT
	
    personalPage objPersonalPage;
	
	public void setUpData() throws IOException {
		File file = new File("..\\\\TheCoffeHouse\\\\dataTest\\\\LoginData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		phone = sheet.getRow(1).getCell(0).getStringCellValue();
	}
	@BeforeTest
	public void launchBrowser() throws IOException {
		setUpData();
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		options = new ChromeOptions();
		driver = new ChromeDriver(options.addArguments(/*"--headless",*/"--window-size=1920,1200","disable-gpu"));
		wait = new WebDriverWait(driver,10);
		driver.get(url);
	}
	
	@Test(priority = 0)
	public void loginStep() throws IOException {
		objPersonalPage = new personalPage(driver, wait);
		objPersonalPage.clickLogin();
		objPersonalPage.inputPhonenumber(phone);
		objPersonalPage.inputOTP_code();
	}
	
	@Test(priority = 1)
	public void checkMovedInfoSite() {
		objPersonalPage.movetoUserInfo();
		objPersonalPage.checkUserInfo_site();
	}
	
	
	
	@AfterTest
	public void closeBrowser() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
}