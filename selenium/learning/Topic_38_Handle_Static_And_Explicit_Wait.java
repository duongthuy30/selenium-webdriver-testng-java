package learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_38_Handle_Static_And_Explicit_Wait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//Apply 15s cho các điều kiện/ trạng thái cụ thể
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	// Static wait: Thread.sleep là 1 hàm của Java
	// Cho phép tạm dừng trong 1 thời gian set
	//Chỉ sử dụng cho các case đặc thù
	//Sử dụng để switch windown
	
	//ImplicitWait: wait ngầm ko cho trạng thái hoặc điều kiện cụ thể - wait cho việc tìm element
	
	//ExpliciWait: Wait tường minh - có trạng thái, điều kiện 
	
	
	

	@Test
	public void TC_01_Not_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Thiếu thời gian để cho 1 element tiếp theo hoặt động được
		sleepInSecond(3);

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_02_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Đủ thời gian để cho 1 element tiếp theo hoặt động được
		sleepInSecond(5);

		
		
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_More_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Thừa thời gian để cho 1 element tiếp theo hoặt động được
		sleepInSecond(10);


		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Not_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(3));
		
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Thiếu thời gian để cho 1 element tiếp theo hoặt động được
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}	
	
	
	@Test
	public void TC_05_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Thiếu thời gian để cho 1 element tiếp theo hoặt động được
//		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		
		//Có thể dùng cách wait hết loading 
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}	
	
	
	@Test
	public void TC_06_More_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(50));
		
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Thiếu thời gian để cho 1 element tiếp theo hoặt động được
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}	
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
		
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
