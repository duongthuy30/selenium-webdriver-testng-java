package learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Handle_Custom_Checkbox_Radio {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Custom_Checkbox() {
		// Custom checkbox/radio
		// - Đại diện cho thẻ là input ẩn
		// - Thẻ input ko click được phải dùng 1 thẻ khác để click
		// - Dùng thẻ input để verify(isSelected)
		driver.get("https://material.angular.dev/components/checkbox/examples");

		// Thẻ input của cái checkbox bị ẩn này có ấn được ko
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span//input")).click();

		// Hàm click yếu tố đầu tiên để click được phải visible
		// Yếu tố thứ 2 là clickAble

		// Hàm isSelected phải là thẻ input mới verifu được

		// Case 1: Thẻ input ko click được -> Fail
		// - thẻ input dùng Verify được ->Pass
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span//input")).click();

		// Case 2: Ko dùng thẻ input để click - thẻ span chứa text ->Pass
		// - Ko dùng thẻ input để verify -> Fail
//		By checkedCheckbox = By.xpath("//span[text()='Checked']");
//		driver.findElement(checkedCheckbox).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());

		// Case 3: Thỏa mãn hết điều kiện
		//Ko dùng thẻ input để click - thẻ span chứa text ->Pass
		// - thẻ input dùng Verify được ->Pass
		
		//Vấn đề: 1 element mà phải define tới 2 locator
		//Maitain code sẽ sinh ra nhiều phụ thuộc 
//		By checkedCheckboxText = By.xpath("//span[text()='Checked']");
//		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span//input");
//		driver.findElement(checkedCheckboxText).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());

		//Case 4:  Thỏa mãn hết điều kiện
		//Dùng thẻ input để click - thẻ span chứa text ->Pass(JavascriptExcutor)
		//JavascriptExcutor: ko quan tâm element bị che hay ko Vẫn click được
		// - thẻ input dùng Verify được ->Pass
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span//input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));		
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		
		By beforeRadioBy = By.xpath("//span[text()='Before']/preceding-sibling::span//input");
jsExecutor.executeScript("arguments[0].click();", driver.findElement(beforeRadioBy));		
		Assert.assertTrue(driver.findElement(beforeRadioBy).isSelected());
		
	}

	@Test
	public void TC_02_Custom_Radio() {

		driver.get("https://material.angular.dev/components/checkbox/examples");
		sleepInSecond(5);
		// Thẻ input của cái radio bị ẩn này có ấn được ko
		driver.findElement(By.xpath("//span[text()='After']/preceding-sibling::span//input")).click();
	}
		
		@Test
		public void TC_03_Google_Docs() {	
		//Case 5: có loại Radio/checkbox không phải thẻ input
				//Những framework mà các cty họ tự design để dùng chung
			driver.get("");
		//Checkbox
			
		By idCuocgoiCheckbox = 	By.xpath("//div[@role='group']//span[text()='ID cuộc gọi']");
			driver.findElement(idCuocgoiCheckbox).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='group']/div[1]")).getAttribute("aria-checked"), "true");
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
