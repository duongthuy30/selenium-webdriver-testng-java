package learning;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.common.base.Function;


public class Topic_41_Handle_Wait_FluentWait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	long allTime = 15; // Second
	long pollingTime = 1000; // Milisecond

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@Test
	public void TC_01_Fluent() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Dùng fluentWait chỉ thay đổi tần suất check
		// Dùng cho các case đặc thù
//		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set tổng thời gian và tần số
//		fluentDriver.withTimeout(Duration.ofSeconds(5))
		// 1/3 giây check 1 lần
//		.pollingEvery(Duration.ofMillis(333))
//		.ignoring(NoSuchElementException.class);

		// Apply điều kiện
//	WebElement startButton = fluentDriver.until(new Function<WebDriver, WebElement>() {
//			@Override
//			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
//				return driver.findElement(By.cssSelector("div#start>button"));
//			}	
//		});

//	startButton.click();

		// Sau khi viết hàm thì dùng
		findElement("//div[@id='start']/button").click();

		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");

	}

	@Test
	public void TC_02_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdownTime = findElement("//div[@id='javascript_countdown_time']");

		fluentElement = new FluentWait<WebElement>(countdownTime);

		fluentElement.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);

		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return element.getText().endsWith("00");
			}
		});

	}


	
	
	// Khai báo thành hàm
	public WebElement findElement(String xpathLocator) {
		// Dùng fluentWait
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);

		// Apply điều kiện
		WebElement element = fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		return element;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
