package Tech;

import java.time.Duration;
import java.util.Random;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Multiple_Server {
	WebDriver driver;
	String employeeID;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		switch (browserName) {
		case "chrome":
			driver = new ChromeDriver();
			break;
case "firefox":
	driver = new FirefoxDriver();
	break;
		default:
			System.out.println("Browser name is not valid.");
			break;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@DataProvider(name = "employee")
	public Object[][] employeeData() {
		return new Object[][] {
				{ "Peter", "Crouch", "peter" + new Random().nextInt(9999) + "@hotmail.com", "Testing111###" },
				{ "John", "Terry", "john" + new Random().nextInt(9999) + "@hotmail.com", "Testing111###" },
				{ "Luka", "Modric", "luka" + new Random().nextInt(9999) + "@hotmail.com", "Testing111###" } };
	}
@Parameters("server")
	@Test(dataProvider = "employee")
	public void TC_02_OrangeHRM(String firstName, String lastName, String emailAddress, String password)
			throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// Login
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

		// Wait for all Loading Icon disappear
		Assert.assertTrue(isLoadingIconDisappear());

		// Verify Dashboard page displayed
		Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

		// Click to PIM link
		driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
		Assert.assertTrue(isLoadingIconDisappear());

		// Verify PIM page displayed
		Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

		// Add Employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		Assert.assertTrue(isLoadingIconDisappear());

		// Create new Employee
		driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

		employeeID = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getDomProperty("value");

		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
				.sendKeys(emailAddress);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
				.sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
				.sendKeys(password);

		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		Thread.sleep(2000);

		// Verify Success Message displayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());

		// Loading icon at Add Employee page
		Assert.assertTrue(isLoadingIconDisappear());

		// Loading Personal Details page
		Assert.assertTrue(isLoadingIconDisappear());

		// Personal Details page
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),
				firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),
				lastName);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
						.getDomProperty("value"),
				employeeID);

		// Logout
		driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	private Boolean isLoadingIconDisappear() {
		// Explicit: áp dụng cho các trạng thái của element (hiển thị/ ko hiển thị (ẩn)/
		// clickable/ selectable/..)
		return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
				.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
	}

	private String getServerUrl(String serverName) {
		switch (serverName) {
		case "DEV":
			serverName = "";
			break;
		case "TEST":
			serverName = "";
			break;
		case "STAGGING":
			serverName = "";
			break;
		default:
			System.out.println("Server Name is not valid");
			break;
		}
		return serverName;
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
}
