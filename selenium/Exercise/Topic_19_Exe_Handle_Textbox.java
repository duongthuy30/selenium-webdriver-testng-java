package Exercise;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Exe_Handle_Textbox {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	// Khai báo + khởi tạo
	String emailAddress, firstname, lastname, password;
	JavascriptExecutor jsExecutor ;

//Khai báo
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://live.techpanda.org/");
		emailAddress = "johndeep" + generateRandomNumber() + "@hotmail.com";
		firstname = "David";
		lastname = "Nguyen";
		password = "Abcd123";

	}

	@Test
	public void Register_Account() {
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Register']")).click();

		WebElement name = driver.findElement(By.xpath("//div[@class='box-content']/p"));
		Assert.assertTrue(name.getText().contains("David Nguyen"));
		WebElement email = driver.findElement(By.xpath("//div[@class='box-content']/p"));
		Assert.assertTrue(email.getText().contains(emailAddress));

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.cssSelector("h2[class='product-name'] a[title='Samsung Galaxy']")).click();
		driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
		driver.findElement(By.cssSelector("input#Quality\\ 1_1")).click();
		driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good");
		driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Review");

		driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
				"Your review has been accepted for moderation.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
