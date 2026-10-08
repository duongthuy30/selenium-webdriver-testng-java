package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Element {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Displayed() {
		// Step 1 - Truy cập vào trang
		driver.get("https://automationfc.github.io/basic-form/");

		// Step 2 - Khai báo trường emailTextbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));

		// Step 3 - Kiểm tra hiển thị thì nhập text và in ra
		// Kiểm tra không hiển thị thì in ra
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");

		} else {
			System.out.println("Email textbox is not displayed");
		}

		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 is displayed");

		} else {
			System.out.println("Age under 18 is not displayed");
		}

		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (name5Text.isDisplayed()) {
			System.out.println("Name 5 text is displayed");

		} else {
			System.out.println("Name 5 text is not displayed");
		}

	}

	// @Test
	public void TC_02_Enabled() {
		// Step 1 - Truy cập vào trang
		driver.get("https://automationfc.github.io/basic-form/");
		// Step 2 - Khai báo trường emailTextbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		// Step 3 - Kiểm tra enable
		Assert.assertTrue(emailTextbox.isEnabled());

		WebElement password = driver.findElement(By.id("disable_password"));
		Assert.assertFalse(password.isEnabled());

	}

	// @Test
	public void TC_03_Selected() {
		// Step 1 - Truy cập vào trang
		driver.get("https://automationfc.github.io/basic-form/");
		// Step 2 - Khai báo trường emailTextbox
		WebElement under18 = driver.findElement(By.id("under_18"));
		// Step 3 - Kiểm tra select
		under18.click();
		Assert.assertTrue(under18.isSelected());

		driver.findElement(By.id("over_18")).click();
		Assert.assertFalse(under18.isSelected());

	}

	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("duong@gmail.com");
		driver.findElement(By.id("email")).sendKeys(Keys.TAB);
		String username = driver.findElement(By.id("new_username")).getAttribute("value");

		Assert.assertEquals(username, "duong@gmail.com");

		WebElement password = driver.findElement(By.id("new_password"));

		// nhập số
		password.sendKeys("123");
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']//span[text()='One number']")).isDisplayed());

		// nhập uppercase
		password.clear();
		password.sendKeys("AUTO");
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']//span[text()='One uppercase character']")).isDisplayed());

		// nhập lowercase
		password.clear();
		password.sendKeys("auto");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']//span[text()='One lowercase character']")).isDisplayed());

		// nhập special
		password.clear();
		password.sendKeys("!@#");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']//span[text()='One special character']")).isDisplayed());

		// nhập > 8 ký tự
		password.clear();
		password.sendKeys("123454444");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']//span[text()='8 characters minimum']")).isDisplayed());

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
	} //đợi cho step này chạy xong rồi mới tới step khác
	}
}
