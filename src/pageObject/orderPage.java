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
public class orderPage {
	WebDriverWait wait;
	boolean expectedResult, actualResult;
	ChromeDriver driver;
	
    ///////Category Container
    @FindBy(id = "menu-from-house-content")
    WebElement categoryContainer;
    
    ///////Product Container
    @FindBy(xpath = "//div[@id='cg-coffee']//div//div")
    By productContainer;
    
    
    ///////Deliver Header
    @FindBy(xpath = "//div[@class='header-delivery header-delivery--desktop header-delivery--bg  d-lg-flex align-items-center']")
    WebElement deli_header;
    
    
    ///////Input Container
    @FindBy(xpath = "//div[@class='input-group tch-box-shadow-1 border--radius-left-4']")
    WebElement input_container;

    
    ///////Input Address
    @FindBy(xpath = "//input[@class='form-control form-control--custom border-0']")
    WebElement input_address;
  
    
    ///////List Address
    @FindBy(xpath = "//ul[@class='address-autocomplete']")
    WebElement list_address;
    
    ///////Add To Cart Button
    @FindBy(xpath = "//button[@class='btn-add-item d-none d-lg-block']")
    WebElement atc_btn;   
  

    
    ///////Cart Icon
    @FindBy(xpath = "//div[@class='icon-cart icon-cart-login-have-item d-flex align-items-center justify-content-center']")
    WebElement cart_icon;
    
    
    ///////Input Customer Name
    @FindBy(xpath = "//input[@placeholder='Tên người nhận']")
    WebElement input_name;
    
    
    ///////Input Customer Phone
    @FindBy(xpath = "//input[@placeholder='Số điện thoại']")
    WebElement input_phone;
    
    ///////Order Button
    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/main/section/div[2]/div/div/div[2]/div[2]/div[2]/button")
    WebElement order_btn;
    
    ///////Confirm Container
    @FindBy(xpath = "//div[@class='modal-content']//div[@class='description']")
    WebElement confirmContainer;
    
    ///////Confirm Button
    @FindBy(xpath = "//button[@class='button-inside']")
    WebElement confirm_btn;
    
    ///////QR Code
    @FindBy(xpath = "//div[@class='scan-qr-code']")
    WebElement qr_code;
    
    
    ////////Error Message
    @FindBy(xpath = "//div[@class='notification-title text-left']")
    WebElement error_massage;    
	public orderPage(ChromeDriver driver, WebDriverWait wait) {
		   this.driver = driver;
		   this.wait = wait;
		   PageFactory.initElements(driver, this);
	 }
	
	public void pickCategory(String cateName) {
		///////////GET CATEGORY
		
		List<WebElement> list_category = categoryContainer.findElements(By.tagName("li"));
		//////////PICK CATEGORY
		for(int i = 0; i<list_category.size();i++) {
			if(list_category.get(i).getText().equals(cateName)) {
				list_category.get(i).click();
			}
		}
	}
	
	
	public void pickProduct(String productName) {
		///////////GET PRODUCT
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cg-coffee']//div//div")));
		
		
		/////////PICK PRODUCT
		String xpath_product = "//div[@class='tch-product__content__top mb-1 mb-lg-3']//h4[text()='"+productName+"']";
		driver.findElement(By.xpath(xpath_product)).click();
	}
	
    public void inputAddress(String address) throws InterruptedException {
		
		deli_header.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input-group tch-box-shadow-1 border--radius-left-4']")));
		input_address.sendKeys(address);
		Thread.sleep(1000);
		input_address.sendKeys(" ");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='address-autocomplete']")));
		driver.findElement(By.xpath("//li[@class='address-item'][1]")).click();
	}
    
    public void addProductToCart() throws InterruptedException {
		Thread.sleep(1000);
		//////ADD PRODUCT TO CART
		atc_btn.click();
		
	}
    
    public void goToCart() throws InterruptedException {
		////////GO TO CART
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='icon-cart icon-cart-login-have-item d-flex align-items-center justify-content-center']")));
		Thread.sleep(500);
		cart_icon.click();
	}

	public void fillInfo(String name, String phone) {
		////////FILL INFO
		//FILL NAME
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Tên người nhận']")));
		input_name.sendKeys(name);
		
		//FILL PHONE NUMBER
		input_phone.sendKeys(phone);
	}
	public void clickOrder() {
		order_btn.click();
	}
	public void confirmOrder() {
		///////Confirm
		order_btn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']//div[@class='description']")));
		confirm_btn.click();
	}
	
	public void checkFinishOrder() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='scan-qr-code']")));
		boolean checkmomo = qr_code.isDisplayed();
		actualResult = checkmomo;
		expectedResult = true;
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	public void checkErrorMessageDisplayed() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='notification-title text-left']")));
		boolean em_displayed = error_massage.isDisplayed();
		actualResult = em_displayed;
		expectedResult = true;
		Assert.assertEquals(actualResult, expectedResult);
	}
}

