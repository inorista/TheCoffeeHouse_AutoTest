package pageObject;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.lang.model.element.Element;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class newsPage {
	   Scanner input = new Scanner(System.in);
	   WebDriverWait wait;
	   boolean expectedResult, actualResult;
	   ChromeDriver driver;
	   
	   ///////Move to News page
	   @FindBy(xpath = "//li[@class='nav-item']//a[@href='/blogs']")
	   WebElement news_btn;
	   
	   ///////Pick category News
	   @FindBy(xpath = "//a[text()='Cập nhật từ Nhà']")
	   WebElement cate_news;
	   
	   ///////List News
	   @FindBy(className = "custom-item")
	   List<WebElement> list_news;
	   
	   
	   
	   
	   public newsPage(ChromeDriver driver, WebDriverWait wait) {
		   this.driver = driver;
		   this.wait = wait;
		   PageFactory.initElements(driver, this);
	   }
	   
		public void goToNews() {
	        news_btn.click();
		}
		
		public void pickCategoryNews() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Cập nhật từ Nhà']")));
			cate_news.click();
		}
		
		public void pickNews(String news) {
			String temp_new = new String (news);
			for(int i = 0; i<list_news.size(); i++) {
				String name_news = list_news.get(i).findElement(By.tagName("h5")).getText();
				if(name_news.equals(temp_new)) {
					list_news.get(i).findElement(By.tagName("button")).click();
				}
			}
		}
		
		public void checkNew(String title_new) {
			expectedResult = true;
			String xpath = "//h2[text()='"+title_new+"']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			boolean title_displayed = driver.findElement(By.xpath(xpath)).isDisplayed();
			actualResult = title_displayed;
			Assert.assertEquals(actualResult, expectedResult);
		}
}
