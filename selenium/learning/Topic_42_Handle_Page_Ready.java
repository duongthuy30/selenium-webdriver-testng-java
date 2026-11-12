package learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_42_Handle_Page_Ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		action = new Actions(driver);
	}

	@Test
	public void TC_01_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		// Click xong chuyển qua trang mới
		Assert.assertTrue(isPageLoadedSuccess());

		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Assign Leave']")).getText(), "Assign Leave");

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		// Click xong chuyển qua trang mới
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.xpath("//label[text()='Employee Name']//parent::div//following-sibling::div//input"))
				.sendKeys("Charles");
		driver.findElement(By.cssSelector("button.orangehrm-left-space")).click();
		// Load lại 1 phần trang
		Assert.assertTrue(isPageLoadedSuccess());

		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Charles ']")).isDisplayed());
	}

	@Test
	public void TC_02_TestProject() {
		driver.get("https://blog.testproject.io");

		// Handle popup
		// Nếu có thì ấn đóng
		if (driver.findElement(By.cssSelector("")).isDisplayed()) {
			driver.findElement(By.cssSelector("")).click();
		}

		Assert.assertTrue(isPageLoadedSuccess());

		// Hover chuột vào Search
		action.moveToElement(driver.findElement(By.cssSelector(""))).perform();
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.cssSelector("")).sendKeys("");
		driver.findElement(By.cssSelector("")).click();
		Assert.assertTrue(isPageLoadedSuccess());

		List<WebElement> postTitles = driver.findElements(By.cssSelector(""));
		for (WebElement title : postTitles) {
			String postTitleText = title.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(postTitleText.contains("keyword"));
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

//Viết hàm  chờ page load
	public boolean isPageLoadedSuccess() {

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		jsExecutor = (JavascriptExecutor) driver;

		// Chờ jQuery hoàn thành nếu website có sử dụng
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor
						.executeScript("return (typeof window.jQuery === 'undefined') || (jQuery.active === 0);");
			}
		};

		// Chờ document tải xong
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

}
