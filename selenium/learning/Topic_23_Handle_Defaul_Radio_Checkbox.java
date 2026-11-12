package learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Handle_Defaul_Radio_Checkbox {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");

		// Verify button login
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());

		driver.findElement(By.id("login_username")).sendKeys("dung@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");

		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());

		// Verify background-color
		// rgb(201, 33, 39)> chuyển tử rgb sang hexa
		String loginButtonBackgroundHexa = Color
				.fromString(driver.findElement(loginButtonBy).getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(loginButtonBackgroundHexa, "#C92127");

	}

	@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Default : element hiển thị là thẻ input
		// Custom: element hiển thị ko phải là thẻ input> input bị ẩn

		// 1 - Chọn -CLick
		// Checkbox
		driver.findElement(By.cssSelector("input[value='Emotional Disorder']")).click();
		driver.findElement(By.cssSelector("input[value='Digestive Problems']")).click();
		driver.findElement(By.cssSelector("input[value='Venereal Disease']")).click();
		sleepInSecond(3);
		// Radio
		driver.findElement(By.cssSelector("input[value='5+ days']")).click();
		driver.findElement(By.cssSelector("input[value=\"I don't have a diet plan\"]")).click();
		driver.findElement(By.cssSelector("input[value='3-4 glasses/day']")).click();
		sleepInSecond(3);

		// 2 - Verify(chọn r hay chưa chọn thành công
		// Chọn r> isSelected> True
		// Chưa chọn > isSelected> False
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Emotional Disorder']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Digestive Problems']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Venereal Disease']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='5+ days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value=\"I don't have a diet plan\"]")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='3-4 glasses/day']")).isSelected());

		// 3- Bỏ chọn(De-select) ->Click
		// Checkbox
		driver.findElement(By.cssSelector("input[value='Emotional Disorder']")).click();
		driver.findElement(By.cssSelector("input[value='Digestive Problems']")).click();
		driver.findElement(By.cssSelector("input[value='Venereal Disease']")).click();
		sleepInSecond(3);
		// Radio
		driver.findElement(By.cssSelector("input[value='5+ days']")).click();
		driver.findElement(By.cssSelector("input[value=\"I don't have a diet plan\"]")).click();
		driver.findElement(By.cssSelector("input[value='3-4 glasses/day']")).click();
		sleepInSecond(3);

		// 2 - Verify(chọn r hay chưa chọn thành công
		Assert.assertFalse(driver.findElement(By.cssSelector("input[value='Emotional Disorder']")).isSelected());
		Assert.assertFalse(driver.findElement(By.cssSelector("input[value='Digestive Problems']")).isSelected());
		Assert.assertFalse(driver.findElement(By.cssSelector("input[value='Venereal Disease']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='5+ days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value=\"I don't have a diet plan\"]")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='3-4 glasses/day']")).isSelected());

	}

	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

//Select all 
		List<WebElement> allCheckboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
//29 checkbox
		for (WebElement checkbox : allCheckboxs) {
			// VỚi case mở vào đã chọn sẵn 1 checkbox
			// Thì cần check ĐK
			// !checkbox.isSelected() nghĩa là checkbox chưa được tích.
			// Nếu chưa tích thì click để tích.
			// Nếu đã tích rồi thì không làm gì.
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}

//Verify
		for (WebElement checkbox : allCheckboxs) {
			Assert.assertTrue(checkbox.isSelected());
		}

		// De-select all
		for (WebElement checkbox : allCheckboxs) {
			if (checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			//Tối ưu verify luôn 
			Assert.assertFalse(checkbox.isSelected());
		}

		

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
