package javaTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_VCX {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qualityx.com.vn");
	}

	public void TC_01_ValidateCurrentUrl() {
		// Login Page Url matching
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://qualityx.com.vn");

	}

	@Test
	public void Login_01_Tenant_Code() {

		// Empty
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).sendKeys("");
		driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

		// Verify dữ liệu nhập
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập mã công ty.']")).getText(),
				"Vui lòng nhập mã công ty.");

		// Nhập sai
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).sendKeys("VCX1");
		driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

		// Verify dữ liệu nhập
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Không tìm thấy mã công ty này.']")).getText(),
				"Không tìm thấy mã công ty này.");

		// Nhập đúng
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).sendKeys("VCX");
		driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

		// Verify dữ liệu nhập
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'VCX')]")).isDisplayed());

	}

	@Test
	public void Login_02_User_Password() {

		// Empty

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập username.']")).getText(),
				"Vui lòng nhập username.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập mật khẩu.']")).getText(),
				"Vui lòng nhập mật khẩu.");

		// Nhập sai username
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).sendKeys("vcx_qa_tes");
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).sendKeys("Admin123!");
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Không tìm thấy tài khoản.']")).getText(),
				"Không tìm thấy tài khoản.");

		// Nhập sai password
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).sendKeys("vcx_qa_test1");
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).sendKeys("Admin");
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Sai mật khẩu.']")).getText(), "Sai mật khẩu.");

		// Nhập đúng
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).sendKeys("vcx_qa_test1");
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).sendKeys("Admin123!");
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập
		//Assert.assertEquals(driver.findElement(By.xpath("//span[@class='truncate']")).getText().trim(), "QA DT");

	}

	// @AfterClass
	// public void afterClass() {
	// driver.quit();
	// }
}
