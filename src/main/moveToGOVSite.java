package main;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.homePage;
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
public class moveToGOVSite {
	public String expectedTitle, actualTitle;
	public String url = "https://thecoffeehouse.com/";
	public ChromeOptions options;
	public ChromeDriver driver;
	public WebDriverWait wait;
	public Scanner input = new Scanner(System.in);
	public boolean expectedResult, actualResult;
	
	
	//////PAGE OJECT
	
    homePage objHomePage;
    
    @BeforeTest
	public void launchBrowser() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		options = new ChromeOptions();
		driver = new ChromeDriver(options.addArguments(/*"--headless",*/"--window-size=1920,1200","disable-gpu"));
		wait = new WebDriverWait(driver,10);
		driver.get(url);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	@Test(priority = 0)
	public void clickGOVSite() throws IOException {
		objHomePage = new homePage(driver, wait);
		objHomePage.goToGOVSite();
	}
	
	@Test(priority = 1)
	public void checkMovedGOVSite() {
		objHomePage.checkCurrentSiteGOV(url);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
}