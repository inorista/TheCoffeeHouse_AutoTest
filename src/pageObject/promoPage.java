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
public class promoPage {
    WebDriverWait wait;
 	boolean expectedResult, actualResult;
 	ChromeDriver driver;
 	public String description_promo;
 	
    ///////Promo Form
    @FindBy(xpath = "//a[@class='nav-link p-0' and text()='Khuyến mãi']")
    WebElement openPromoForm;
    
    ///////Promos Container
    @FindBy(className = "tch-modal-sale-body")
    By promosContainer;
    
    ///////List Promos
    @FindBy(className = "tch-sale-card mb-2")
    List<WebElement> listPromos;
    
    
    
    ///////Promo Title
    @FindBy(id = "detail-sale-modal-title")
    By promoTitle;
    
    
    
    ///////Promo Added Title
    @FindBy(className = "tch-detail-sale-box-title mb-1")
    WebElement promoAddedTitle;
   
    
    
    public promoPage(ChromeDriver driver, WebDriverWait wait) {
 	   this.driver = driver;
 	   this.wait = wait;
 	   PageFactory.initElements(driver, this);
    }
    
    public void openPromoForm() {
    	openPromoForm.click();
    }
    
    public void choosePromo(String promo) {
    	String temp_description = new String (promo);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tch-modal-sale-body")));
    	for(int i = 0; i<listPromos.size(); i++) {
    		description_promo = listPromos.get(i).findElement(By.className("text-description mb-0")).getText();
    		if(description_promo.equals(temp_description)) {
    			listPromos.get(i).findElement(By.className("use-now")).click();
    		}
    	}
    }
    
    
    public void checkPromoAdded(String promo) {
    	String temp_description = new String (promo);
    	expectedResult = true;
    	openPromoForm.click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("detail-sale-modal-title")));
    	if(promoAddedTitle.getText().equals(temp_description)) {
    		actualResult = true;
    		Assert.assertEquals(actualResult, expectedResult);
    	}
    	else {
    		actualResult = false;
    		Assert.assertEquals(actualResult, expectedResult);	
    	}    	
    }
}
