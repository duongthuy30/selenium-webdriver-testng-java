package learning;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
		
		//Verify button login
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("dung@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		//Verify background-color
		//rgb(201, 33, 39)> chuyển tử rgb sang hexa
	String loginButtonBackgroundHexa =	Color.fromString(driver.findElement(loginButtonBy).getCssValue("background-color")).asHex().toUpperCase();
	Assert.assertEquals(loginButtonBackgroundHexa, "#C92127");
		
	}
	
	@Test
	public void TC_02_Default_Radio_Checkbox() {	
		
	//Default : element hiển thị là thẻ input
		//Custom: element hiển thị ko phải là thẻ input> input bị ẩn 
		
		//1 - Chọn -CLick
		
		//Option 1
		
		//Option 2
		
		//Option 3
		
		
		//2 - Verify(chọn r hay chưa chọn thành công 
		//Chọn r> isSelected> True
		//Chưa chọn > isSelected> False
		
	}

}
