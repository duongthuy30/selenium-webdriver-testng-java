package Exercise;

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

public class Topic_23_Exe_Handle_Defaul_Radio_Checkbox {
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
		driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
		
		By registerButtonBy = By.cssSelector("div[ht='click_register_mailSubmit'] div.hwid-disabled");

		
		Assert.assertTrue(driver.findElement(registerButtonBy).isEnabled());
		System.out.println();

		String loginButtonBackgroundHexa = Color
				.fromString(driver.findElement(registerButtonBy).getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(loginButtonBackgroundHexa, "#007DFF");

	}

	@Test
	public void TC_02_Default_Radio_Checkbox() {
		

	}

	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

//Select all 
		List<WebElement> allCheckboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for (WebElement checkbox : allCheckboxs) {
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
