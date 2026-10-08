package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Exercise_Web_Browser_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Verify_Url() {
		// Truy cập vào url
		driver.get("http://live.techpanda.org/");

		// tìm-click My account
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

		// Verify url Login page
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

		// CLick Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		// Verify url Register page
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		// Truy cập vào url
		driver.get("http://live.techpanda.org/");

		// tìm-click My account
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

		// Verify title Login page
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		// CLick Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		// Verify title Register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03_Navigate_Function() {
		// Truy cập vào url
		driver.get("http://live.techpanda.org/");

		// tìm-click My account
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

		// CLick Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(3);

		// Verify url Register page
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");

		// Back lại Login page
		driver.navigate().back();

		// Verify url Login page
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

		// Forward tới Register page
		driver.navigate().forward();

		// Verify title Register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_Get_Source_Code_Page() {
		// Truy cập vào url
		driver.get("http://live.techpanda.org/");

		// tìm-click My account
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(3);

		// Verify Login Page chứa Text
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

		// CLick Create an account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(3);

		// Verify Register page chứa text
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

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
