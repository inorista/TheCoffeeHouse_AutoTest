package main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.loginPage;
import pageObject.newsPage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Scanner;

import javax.lang.model.element.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Listeners(ListenerTest.class)

public class findNews {
	//////CONFIG
	public String expectedTitle, actualTitle;
	public String url = "https://thecoffeehouse.com/";
	public ChromeOptions options;
	public ChromeDriver driver;
	public WebDriverWait wait;
	public Scanner input = new Scanner(System.in);
	public boolean expectedResult, actualResult;
	
	
	///////////DATA
	
    public String news;
    
    
    /////PAGE OBJECT
	newsPage objNewsPage;
	
	public void setUpData() throws IOException {
		File file = new File("..\\\\TheCoffeHouse\\\\dataTest\\\\NewsData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		news = sheet.getRow(1).getCell(0).getStringCellValue();
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
	public void pickNews_step() {
		objNewsPage = new newsPage(driver, wait);
		objNewsPage.goToNews();
		objNewsPage.pickCategoryNews();
		objNewsPage.pickNews(news);
	}
	
	@Test(priority = 1)
	public void checkPickedNew() {
		objNewsPage.checkNew(news);
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

}
