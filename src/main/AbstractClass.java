package main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AbstractClass {
	public ChromeDriver driver;
	public ChromeOptions options;
	public static String url = "https://thecoffeehouse.com/";
	public WebDriverWait wait;
	@BeforeTest
	public void beforeTest() {
		  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  wait = new WebDriverWait(driver,10);
		  driver.get(url);
		  driver.manage().window().maximize();
		  
	  }
	@AfterTest
	  public void afterTest() throws Exception {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	  }
}
