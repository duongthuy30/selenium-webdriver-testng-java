package learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_36_Handle_Wait_Element_Control_Status {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// findElement sẽ bị ảnh hưởng timout nếu implicitly có set
		// Sẽ tìm element trong 10s
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	// Synchronus: Đồng bộ
	// Asynchronus: Bất đồng bộ

	@Test
	public void TC_01_Visible_Displayed_Visibility() {

		driver.get("https://www.facebook.com/");

		// 1. Có trên UI(bắt buộc)
		// 1. Có trong HTML(bắt buộc)

		// Chờ cho email address textbox hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));

	}

	@Test
	public void TC_02_Invisible_UnDisplayed_Invisibility_1() {

		driver.get("https://www.facebook.com/");

		// 2. Không có trên UI(bắt buộc)
		// 1. Có trong HTML

		driver.findElement(By.xpath("//span[text()='Create new account']")).click();

		// Chờ cho Re-enter Email textbox ko hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("")));

	}

	@Test
	public void TC_03_Invisible_UnDisplayed_Invisibility_2() {

		driver.get("https://www.facebook.com/");

		// 2. Không có trên UI(bắt buộc)
		// 2. Không có trong HTML

		// Chờ cho Re-enter Email textbox ko hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("")));

	}

	@Test
	public void TC_05_Presence_1() {

		driver.get("https://www.facebook.com/");

		// 1. Có trên UI
		// 1. Có trong HTML(bắt buộc)

		// Chờ cho email address textbox presence trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email']")));

	}

	@Test
	public void TC_06_Presence_2() {

		driver.get("https://www.facebook.com/");

		// 1. Không có trên UI
		// 1. Có trong HTML(bắt buộc)

		driver.findElement(By.xpath("//span[text()='Create new account']")).click();

		// Chờ cho Re-enter Email textbox ko hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("")));

	}

	@Test
	public void TC_07_Staleness() {
		driver.get("https://www.facebook.com/");

		// 2. Không có trên UI(bắt buộc)
		// 2. Không có trong HTML

		driver.findElement(By.xpath("//span[text()='Create new account']")).click();

		// Phase 1: Element có trong DOM
		WebElement reEnterEmail = driver.findElement(By.name(""));

		// Thao tác với element khác làm cho element re-enter ko còn trong DOM nữa
		// Close popup đi
		driver.findElement(By.cssSelector("img.8idr")).click();

		// Chờ cho Re-enter Email textbox ko hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.stalenessOf(reEnterEmail));

	}


	
	
}
