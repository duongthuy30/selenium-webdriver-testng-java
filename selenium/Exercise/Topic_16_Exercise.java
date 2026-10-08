package Exercise;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_16_Exercise {
	public class Topic_02_Find_Element {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");

		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
		}

		@BeforeMethod
		public void beforeMethod() {
		    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		}
		
		@Test
		public void Register_01_Empty_Data() {

			
			// Action
			driver.findElement(By.id("txtFirstname")).sendKeys("");
			driver.findElement(By.id("txtEmail")).sendKeys("");
			driver.findElement(By.id("txtCEmail")).sendKeys("");
			driver.findElement(By.id("txtPassword")).sendKeys("");
			driver.findElement(By.id("txtCPassword")).sendKeys("");
			driver.findElement(By.id("txtPhone")).sendKeys("");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

			// Verify dữ liệu(Actual data = Execpt data)

			Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
			Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
					"Vui lòng nhập lại địa chỉ email");
			Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
			Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
					"Vui lòng nhập lại mật khẩu");
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		}

		@Test
		public void Register_02_Invalid_Email() {
			driver.findElement(By.id("txtFirstname")).sendKeys("Thuy Duong");
			driver.findElement(By.id("txtEmail")).sendKeys("duong.gmailcom");
			driver.findElement(By.id("txtCEmail")).sendKeys("duong.gmailcom");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456");
			driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

			// Verify dữ liệu(Actual data = Execpt data)

			Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

		};

		@Test
		public void Register_03_Incorrect_Confirm_Email() {
			driver.findElement(By.id("txtFirstname")).sendKeys("Thuy Duong");
			driver.findElement(By.id("txtEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("duong1@gmail.com");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456");
			driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		}

		@Test
		public void Register_04_Password_Less_Than_6_Chars() {
			driver.findElement(By.id("txtFirstname")).sendKeys("Thuy Duong");
			driver.findElement(By.id("txtEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtPassword")).sendKeys("12345");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456");
			driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

			Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
					"Mật khẩu phải có ít nhất 6 ký tự");

		}

		@Test
		public void Register_05_Incorrect_Confirm_Password() {
			driver.findElement(By.id("txtFirstname")).sendKeys("Thuy Duong");
			driver.findElement(By.id("txtEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("12345");
			driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

			Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
					"Mật khẩu phải có ít nhất 6 ký tự");
		}

		@Test
		public void Register_06_Invald_Phone_Number() {
			driver.findElement(By.id("txtFirstname")).sendKeys("Thuy Duong");
			driver.findElement(By.id("txtEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtCEmail")).sendKeys("duong@gmail.com");
			driver.findElement(By.id("txtPassword")).sendKeys("123456");
			driver.findElement(By.id("txtCPassword")).sendKeys("123456");

			// <10 ký tự
			driver.findElement(By.id("txtPhone")).sendKeys("098765432");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
					"Số điện thoại phải từ 10-11 số.");

			// >10 ký tự
			driver.findElement(By.id("txtPhone")).clear();
			driver.findElement(By.id("txtPhone")).sendKeys("0987654322222");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
					"Số điện thoại phải từ 10-11 số.");

			// không bắt đầu = 0
			driver.findElement(By.id("txtPhone")).clear();
			driver.findElement(By.id("txtPhone")).sendKeys("987654321");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
					"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

		}

		@AfterClass
		public void afterClass() {
			driver.quit();
		}
	}
}
