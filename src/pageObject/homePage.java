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
public class homePage {
	WebDriverWait wait;
	boolean expectedResult, actualResult;
	ChromeDriver driver;
	
    ///////Dropdown Container Terms
    @FindBy(xpath = "//div[@data-toggle='collapse' and @aria-controls='tch-footer__navbar-3']")
    WebElement dropdown_terms;
    
    
    ///////Dropdown Container Terms
    @FindBy(xpath = "//a[@href='/terms' and text()='Quy chế website']")
    WebElement terms;
    
    
    ///////Dropdown Container Terms
    @FindBy(xpath = "//a[@href='/policy' and text()='Bảo mật thông tin']")
    WebElement policy;
    
    
    ///////Link GOV
    @FindBy(xpath = "//a[@href=\"http://online.gov.vn/Home/WebDetails/48042\"]")
    WebElement GOV;
    
    
    public homePage(ChromeDriver driver, WebDriverWait wait) {
		   this.driver = driver;
		   this.wait = wait;
		   PageFactory.initElements(driver, this);
	 }
    
    public void goToTermsSite() {
    	
    	dropdown_terms.click();
    	terms.click();
    }
    
    public void checkCurrentSiteTerms(String previousSite) {
    	String currentSite = driver.getCurrentUrl();
    	expectedResult = true;
    	if(currentSite.equals("https://thecoffeehouse.com/terms") && !currentSite.equals(previousSite)) {
    		actualResult = true;
    	}
    	else {
    		actualResult = false;
    	}
    	Assert.assertEquals(actualResult, expectedResult);
    }
    
    
    public void goToPolicySite() {
    	dropdown_terms.click();
    	policy.click();
    }
    
    public void checkCurrentSitePolicy(String previousSite) {
    	String currentSite = driver.getCurrentUrl();
    	expectedResult = true;
    	if(currentSite.equals("https://thecoffeehouse.com/policy") && !currentSite.equals(previousSite)) {
    		actualResult = true;
    	}
    	else {
    		actualResult = false;
    	}
    	Assert.assertEquals(actualResult, expectedResult);
    }
    
    
    public void goToGOVSite() {
    	GOV.click();
    }
    
    public void checkCurrentSiteGOV(String previousSite) {
    	String currentSite = driver.getCurrentUrl();
    	expectedResult = true;
    	if(currentSite.equals("http://online.gov.vn/Home/WebDetails/48042?AspxAutoDetectCookieSupport=1") && !currentSite.equals(previousSite)) {
    		actualResult = true;
    	}
    	else {
    		actualResult = false;
    	}
    	Assert.assertEquals(actualResult, expectedResult);
    }
}