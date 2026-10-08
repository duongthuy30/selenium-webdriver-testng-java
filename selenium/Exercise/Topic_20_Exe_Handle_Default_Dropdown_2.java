package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Exe_Handle_Default_Dropdown_2 {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	// Khai báo + khởi tạo
	String name, email;
	Select select;

//Khai báo
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		name = "dungnt";
		email = "dungnt1@yopmail.com";
	}

	@Test
	public void Register_Account() {
		// I - Action
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.xpath("//input[@name='name']/following-sibling::input[1]")).sendKeys(email);
		driver.findElement(By.xpath("//button[text()='Signup']")).click();

		driver.findElement(By.id("id_gender2")).click();

		Assert.assertEquals(driver.findElement(By.id("name")).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);

		driver.findElement(By.id("password")).sendKeys("Abcd@1234");

		select = new Select(driver.findElement(By.id("days")));
		// Chọn day
		select.selectByVisibleText("20");

		select = new Select(driver.findElement(By.id("months")));
		// Chọn month
		select.selectByVisibleText("October");

		select = new Select(driver.findElement(By.id("years")));
		// Chọn year
		select.selectByVisibleText("2000");

		//driver.findElement(By.id("newsletter")).click();
	//	driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("first_name")).sendKeys("Dung");
		driver.findElement(By.id("last_name")).sendKeys("Nguyen");
		driver.findElement(By.id("address1")).sendKeys("12 Hoan Kiem");

		select = new Select(driver.findElement(By.id("country")));
		select.selectByVisibleText("Singapore");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Singapore");

		driver.findElement(By.id("state")).sendKeys("Ba Dinh");
		driver.findElement(By.id("city")).sendKeys("Ha Noi");
		driver.findElement(By.id("zipcode")).sendKeys("100000");
		driver.findElement(By.id("mobile_number")).sendKeys("0000000000");

		driver.findElement(By.xpath("//button[text()='Create Account']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//b[text()='Account Created!']")).getText(),
				"ACCOUNT CREATED!");

		driver.findElement(By.xpath("//a[text()='Continue']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Logged in as')]")).getText().contains(name));
		driver.findElement(By.className("fa-trash-o")).click();
		Assert.assertEquals(driver.findElement(By.className("text-center")).getText(), "ACCOUNT DELETED!");
		driver.findElement(By.xpath("//a[text()='Continue']")).click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
