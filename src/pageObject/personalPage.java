package pageObject;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
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
public class personalPage {
	Scanner input = new Scanner(System.in);
	WebDriverWait wait;
	boolean expectedResult, actualResult;
	ChromeDriver driver;
	
    ///////Avatar user
    @FindBy(xpath = "//a[@class='img-custom header-auth mr-2 mr-sm-2 d-flex align-items-lg-start']")
    WebElement userAvatar;
    
    
    ///////Go to User infomation
    @FindBy(xpath = "//a[@href='/user-info/accountUser']")
    WebElement userInfo;
    
    
    //////Title User Infomation site
    @FindBy(xpath = "//h1[@class='row user-information' and text()='Thông tin tài khoản']")
    WebElement title_userinfo;
    
    
    ///////Go to User infomation
    @FindBy(xpath = "//a[@href='/user-info/userAddress']")
    WebElement userAddress;
    
    
    //////Title User Infomation site
    @FindBy(xpath = "//h1[@class=' user-address-title' and text()='Sổ địa chỉ']")
    WebElement title_useraddress;
    
    
    //////Go to Order history
    @FindBy(xpath = "//span[@class='text-authen' and text()='Lịch sử mua hàng']")
    WebElement orderHistory;
    
    
    //////Title Order history
    @FindBy(xpath = "//span[@class='user-info-text']")
    WebElement title_orderhistory;
  
    ///////Avatar login
    @FindBy(xpath = "//div[@class='header-right d-flex align-items-center ml-auto']//a[@class='img-custom header-auth mr-2 mr-sm-2 d-flex align-items-lg-start']")
    WebElement avatar_login;
    
    
    ///////Login avatar button
    @FindBy(xpath = "//div[@class='header__option--authen']")
    WebElement login_ava_btn;
    
    
    ///////Phone input
    @FindBy(className = "phone-input")
    WebElement phone_input;
    
    ///////Login button
    @FindBy(xpath = "//button[@class='login-btn d-lg-inline-block btn btn--orange align-items-center btn--radius-100']")
    WebElement login_btn;

    ///////OTP
    @FindBy(xpath = "//*[@id=\"modalLogin\"]/div[2]/div[4]/div/div[1]/input")
    WebElement otp_input;

    
    ///////Logout buttons
    @FindBy(xpath = "//span[@class='text-authen' and text()='Thoát']")
    WebElement logout_btn;
    
    
    public personalPage(ChromeDriver driver, WebDriverWait wait) {
 	   this.driver = driver;
 	   this.wait = wait;
 	   PageFactory.initElements(driver, this);
    }
    
    public void clickLogin() {
		avatar_login.click();
	}
   
   
   /////////////////
   public void inputPhonenumber(String phone) {
		if(login_ava_btn.isDisplayed()) {
			login_ava_btn.findElement(By.xpath("//li[@data-target='#modalLogin']")).click();
			if(driver.findElement(By.xpath("//div[@role='dialog' and @id='modalLogin' and @tabindex='-1']")).isDisplayed()) {
				phone_input.sendKeys(phone);
				login_btn.click();
			}
		}
	}
   
   
   /////////////////
   public void inputOTP_code() {
		System.out.print("Nhập mã OTP: ");
		String otp = input.next();
		otp_input.sendKeys(otp);
		login_btn.click();
	}
   
   
   /////////Move to user info
   public void movetoUserInfo() {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img_user']")));
	   userAvatar.click();
	   userInfo.click();
   }
   
   ///////Check User info site
   public void checkUserInfo_site() {
	   expectedResult = true;
	   if(title_userinfo.isDisplayed()) {
		   actualResult = true;
		   Assert.assertEquals(actualResult, expectedResult);
	   }
   }
   
   
   /////////Move to user address
   public void movetoUserAddress() {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img_user']")));
	   userAvatar.click();
	   userAddress.click();
   }
   
   ///////Check User address site
   public void checkUserAddress_site() {
	   expectedResult = true;
	   if(title_useraddress.isDisplayed()) {
		   actualResult = true;
		   Assert.assertEquals(actualResult, expectedResult);
	   }
   }
   
   /////////Move to order history
   public void movetoOrderHistory() {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img_user']")));
	   userAvatar.click();
	   orderHistory.click();
   }
   
   ///////Check order history site
   public void checkOrderHistory_site() {
	   expectedResult = true;
	   if(title_orderhistory.isDisplayed()) {
		   actualResult = true;
		   Assert.assertEquals(actualResult, expectedResult);
	   }
   }
   
   ///////Logout button
   public void clickLogout() {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img_user']")));
	   userAvatar.click();
	   logout_btn.click();
   }
   
   /////Check Logged Out?
   public void checkLoggedOut() {
	   expectedResult = true;
	   actualResult = avatar_login.isDisplayed();
	   Assert.assertEquals(actualResult, expectedResult);
   }
   
   
  
}
