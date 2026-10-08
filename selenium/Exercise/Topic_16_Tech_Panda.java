package Exercise;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Tech_Panda {
	public class Topic_02_Find_Element {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");

		@BeforeClass
		public void beforeClass() {

			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();
			driver.get("https://live.techpanda.org/index.php/customer/account/login");
		}

		@Test
		public void Register_01_Empty_Data() {
			// Action
			driver.get("https://live.techpanda.org/index.php/customer/account/create/");
			driver.findElement(By.id("firstname")).sendKeys("");
			driver.findElement(By.id("lastname")).sendKeys("");
			driver.findElement(By.id("email_address")).sendKeys("");
			driver.findElement(By.id("password")).sendKeys("");
			driver.findElement(By.id("confirmation")).sendKeys("");

			driver.findElement(By.xpath("//button[@title='Register']")).click();

			// Verify dữ liệu(Actual data = Execpt data)

			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='firstname']+div")).getText(),
					"This is a required field.");
			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='lastname']+div")).getText(),
					"This is a required field.");
			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='email_address']+div")).getText(),
					"This is a required field.");
			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='password']+div")).getText(),
					"This is a required field.");
			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='confirmation']+div")).getText(),
					"This is a required field.");

		}

		@Test
		public void Register_02_Invalid_Email() {
			driver.get("https://live.techpanda.org/index.php/customer/account/create/");
			driver.findElement(By.id("firstname")).sendKeys("Dương");
			driver.findElement(By.id("lastname")).sendKeys("Nguyễn");
			driver.findElement(By.id("email_address")).sendKeys("duong@gmailcom");
			driver.findElement(By.id("password")).sendKeys("123456");
			driver.findElement(By.id("confirmation")).sendKeys("123456");

			driver.findElement(By.xpath("//button[@title='Register']")).click();

			// Verify dữ liệu(Actual data = Execpt data)

			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='email_address']+div")).getText(),
					"Please enter a valid email address. For example johndoe@domain.com.");

		};

		@Test
		public void Register_03_Password_Less_Than_6_Chars() {
			driver.get("https://live.techpanda.org/index.php/customer/account/create/");
			driver.findElement(By.id("firstname")).sendKeys("Dương");
			driver.findElement(By.id("lastname")).sendKeys("Nguyễn");
			driver.findElement(By.id("email_address")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("password")).sendKeys("12345");
			driver.findElement(By.id("confirmation")).sendKeys("12345");

			driver.findElement(By.xpath("//button[@title='Register']")).click();

			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='password']+div")).getText(),
					"Please enter 6 or more characters without leading or trailing spaces.");

		}

		@Test
		public void Register_05_Incorrect_Confirm_Password() {
			driver.get("https://live.techpanda.org/index.php/customer/account/create/");
			driver.findElement(By.id("firstname")).sendKeys("Dương");
			driver.findElement(By.id("lastname")).sendKeys("Nguyễn");
			driver.findElement(By.id("email_address")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("password")).sendKeys("123456");
			driver.findElement(By.id("confirmation")).sendKeys("12345");

			driver.findElement(By.xpath("//button[@title='Register']")).click();

			Assert.assertEquals(driver.findElement(By.cssSelector("input[id='confirmation']+div")).getText(),
					"Please make sure your passwords match.");
		}

		@AfterClass
		public void afterClass() {
			driver.quit();
		}
	}
}
