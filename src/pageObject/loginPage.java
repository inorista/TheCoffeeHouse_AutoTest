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
import java.util.Scanner;

import javax.lang.model.element.Element;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class loginPage {
   Scanner input = new Scanner(System.in);
   WebDriverWait wait;
   boolean expectedResult, actualResult;
   ChromeDriver driver;
   
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

   
   ///////Avatar icon
   @FindBy(xpath = "//img[@class='img_user']")
   WebElement avatar_icon;
   
   ///////Error message
   @FindBy(xpath = "//div[@class='error-message']")
   WebElement error_mess;
   
   
   public loginPage(ChromeDriver driver, WebDriverWait wait) {
	   this.driver = driver;
	   this.wait = wait;
	   PageFactory.initElements(driver, this);
   }
   /////////////////
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
   
   //////////////// 
   public void checkLoggedin() {
		expectedResult = true;
		try {
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img_user']")));
		    actualResult = avatar_icon.isDisplayed();
		    Assert.assertEquals(actualResult, expectedResult);
		}
		catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='error-message']")));
			actualResult = error_mess.isDisplayed();
			Assert.assertEquals(actualResult, expectedResult);
		}
	}
}
