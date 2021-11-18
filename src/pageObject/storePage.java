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
public class storePage {
	WebDriverWait wait;
	boolean expectedResult, actualResult;
	ChromeDriver driver;
	
    ///////Go To Store Page
    @FindBy(xpath = "//li[@class='nav-item']//a[@href='/store']")
    WebElement store_btn;
    
    ///////Get list store
    @FindBy(className = "tch-store-item")
    WebElement list_province;
    
    ///////Input address
    @FindBy(xpath = "//input[@type='text' and @placeholder='Nhập tên đường, quận huyện, tỉnh thành']")
    WebElement input_storeAddress;
    
    ///////Input address
    @FindBy(className = "store-info")
    WebElement storeInfo;
	
  
	public storePage(ChromeDriver driver, WebDriverWait wait) {
		   this.driver = driver;
		   this.wait = wait;
		   PageFactory.initElements(driver, this);
	 }
	
	public void goToStore() {
        store_btn.click();
	}
	
	public void getProvince(String province) throws InterruptedException {
		List<WebElement> items = list_province.findElements(By.className("tch-store-name"));
		for (int i = 0; i<items.size(); i++) {
			String title = items.get(i).getText();
			if(title.indexOf(province) != -1) {
				items.get(i).click();
			}
		}
	}
	
	public void inputAddress(String storeAddress) {
		input_storeAddress.sendKeys(storeAddress);
	}
	
	public void checkShowAddress() throws InterruptedException {
		expectedResult = true;
		boolean checkShowInfo = storeInfo.isDisplayed();
		actualResult = checkShowInfo;
		Assert.assertEquals(actualResult, expectedResult);
	}

}
