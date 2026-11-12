package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_33_Exe_JavascriptExecutor {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JsExecutor() {

		// Step 01 - Open URL by JS
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

		// Step 02 - Click Submit by JS
		clickToElementByJS("//input[@type='submit']");

		// Verify Name validation message
		String nameMessage = getElementValidationMessage("//input[@id='fname']");

		Assert.assertEquals(nameMessage, "Please fill out this field.");

		// Step 03 - Enter Name by JS
		sendkeyToElementByJS("//input[@id='fname']", "Dam Dao");

		// Click Submit by JS
		clickToElementByJS("//input[@type='submit']");

		// Verify Password validation message
		String passwordMessage = getElementValidationMessage("//input[@name='pass']");

		Assert.assertEquals(passwordMessage, "Please fill out this field.");
	}

	
	
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = arguments[0];", url);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].value = arguments[1];", getElement(locator), value);
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public void sleepInSecond(int timeout) {
		try {
			Thread.sleep(timeout * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
