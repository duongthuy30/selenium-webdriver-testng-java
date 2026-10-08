package Exercise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Exe_Handle_Default_Dropdown {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	// Khai báo + khởi tạo
	Select select;

//Khai báo
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://rode.com/en-au/support/where-to-buy");
	}

	@Test
	public void Select_Dropdown() {
		// I - Action

		// Khởi tạo cho cái Country
		select = new Select(driver.findElement(By.id("country")));
		// Chọn Country
		select.selectByVisibleText("Vietnam");

		// Kiểm tra chọn được hay chưa

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		Assert.assertFalse(select.isMultiple());

		driver.findElement(By.id("map_search_query")).sendKeys("HO CHI MINH");

		driver.findElement(By.xpath("//button[text()='Search']")).click();
		// Verify 16 dealer
		List<WebElement> dealers = driver
				.findElements(By.xpath("//h3[normalize-space()='Dealers']/following-sibling::div[1]/div"));

		Assert.assertEquals(dealers.size(), 16);
	
		
		

		// Khởi tạo cho cái Month
		// select = new Select(driver.findElement(By.cssSelector("div.col-9
		// select.select2-hidden-accessible")));
		// Chọn Month
		// select.selectByVisibleText("11");
		// Kiểm tra nó có Multi
		// Assert.assertFalse(select.isMultiple());

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
