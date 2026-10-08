package Exercise;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Exe_Handle_Textbox_2 {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	// Khai báo + khởi tạo
	String username1, password1, firstname, lastname, username2, password2, confirmpassword, number;
	JavascriptExecutor jsExecutor;

//Khai báo
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		username1 = "Admin";
		password1 = "admin123";
		firstname = "Duong";
		lastname = "Nguyen";
		username2 = "duongnt123";
		password2 = "Abcd@1234";
		confirmpassword = "Abcd@1234";
		number = "4389-0922-7893";
	}

	@Test
	public void Register_Account() {
		driver.findElement(By.name("username")).sendKeys(username1);
		driver.findElement(By.name("password")).sendKeys(password1);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		driver.findElement(By.cssSelector("span.oxd-switch-input")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input"))
				.sendKeys(username2);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input"))
				.sendKeys(password2);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input"))
				.sendKeys(confirmpassword);

		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);

		WebElement employeeID = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeID.clear();
		employeeID.sendKeys("0987");
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//parent::div/button")).click();
		driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input"))
				.sendKeys(number);
		driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys("Data real people");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.cssSelector("button.oxd-icon-button i.bi-pencil-fill")).click();

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement numberInput = wait2.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")));

		wait.until(ExpectedConditions.attributeToBe(numberInput, "value", number));

		Assert.assertEquals(numberInput.getAttribute("value"), number);

		Assert.assertEquals(
				driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("_value"),
				"Data real people");

		driver.findElement(By.cssSelector("span.oxd-userdropdown-tab")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

		driver.findElement(By.name("username")).sendKeys(username2);
		driver.findElement(By.name("password")).sendKeys(password2);
		driver.findElement(By.cssSelector("button[type='submit']")).click();

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
