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
public class test {
	public String expectedTitle, actualTitle;
	public static String url = "https://thecoffeehouse.com/";
	public static ChromeOptions options = new ChromeOptions();;

	public Scanner input = new Scanner(System.in);
	public static String name_news;
	public static void main(String[] args) throws IOException {
		File file = new File("..\\\\TheCoffeHouse\\\\dataTest\\\\NewsData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		String news = new String (sheet.getRow(1).getCell(0).getStringCellValue());
		String news2 = new String ("CHAI FRESH LUÔN BÊN BẠN TRONG MỌI KHOẢNH KHẮC");
		System.out.println(news);
		System.out.println(news2);
		System.out.println(news.equals(news2));
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options.addArguments(/*"--headless",*/"--window-size=1920,1200","disable-gpu"));
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.get(url);
		driver.findElement(By.xpath("//li[@class='nav-item']//a[@href='/blogs']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Cập nhật từ Nhà']")));
		driver.findElement(By.xpath("//a[text()='Cập nhật từ Nhà']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-home']")));
		List<WebElement> list_news = driver.findElements(By.className("custom-item"));
		
		System.out.print(list_news);
		for(int i = 0; i<list_news.size(); i++) {
			name_news = list_news.get(i).findElement(By.tagName("h5")).getText();
			if(name_news.equals(news2)) {
				list_news.get(i).findElement(By.tagName("button")).click();
			}
		}
		


	}

}
