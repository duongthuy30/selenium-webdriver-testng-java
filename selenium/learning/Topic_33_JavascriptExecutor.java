package learning;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_33_JavascriptExecutor {
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
		// mở tới URL
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);

//2. Kiểm tra domain
		String techPandaDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(techPandaDomain, "live.techpanda.org");

		// 3. Kiểm tra URL
		String techPandaUrl = (String) jsExecutor.executeScript("return document.URL;");
		Assert.assertEquals(techPandaUrl, "https://live.techpanda.org/");

		// 4. Click Mobile
		highlightElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(5);

		// 5. Thêm Samsung Galaxy vào giỏ hàng
		highlightElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		// clickToElementByJS("//a[text()='Samsung
		// Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		driver.findElement(
				By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"))
				.click();
		sleepInSecond(5);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.urlContains("/checkout/cart/"));

		// 6. Kiểm tra thông báo thêm giỏ hàng

		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		// 7. Click Customer Service

		highlightElementByJS("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);

		// 8. Kiểm tra Title
		Assert.assertEquals(getTitleByJS(), "Customer Service");

		// 9. Scroll đến ô Newsletter
		highlightElementByJS("//input[@id='newsletter']");
		scrollToElementOnTopByJS("//input[@id='newsletter']");
		sleepInSecond(5);

		// 10. Nhập email bằng JavaScript
		String emaiAddress = "afc" + generateRandomNumber() + "@hotmail.vn";

		sendkeyToElementByJS("//input[@id='newsletter']", emaiAddress);

	}

	@Test
	public void TC_02_Rode() {

	}

	// Hàm random
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	// 1. Execute JavaScript for browser
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	// 2. Get inner text of page
	public String getInnerTextByJS() {
		return jsExecutor.executeScript("return document.documentElement.innerText;").toString();
	}

	// 3. Verify expected text in page
	public boolean isExpectedTextInInnerText(String textExpected) {
		return getInnerTextByJS().contains(textExpected);
	}

	// 4. Scroll to bottom of page
	public void scrollToBottomPageByJS() {
		jsExecutor.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
	}

	// 5. Sleep in seconds
	public void sleepInSecond(int timeout) {
		try {
			Thread.sleep(timeout * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	// 6. Navigate to URL by JavaScript
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = arguments[0];", url);
	}

	// 7. Highlight an element
	public void highlightElementByJS(String locator) {
		WebElement element = getElement(locator);

		String originalStyle = element.getAttribute("style");

		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"border: 2px dashed red;");

		sleepInSecond(2);

		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				originalStyle == null ? "" : originalStyle);
	}

	// 8. Click an element
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	// 9. Get text of an element
	public String getElementTextByJS(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
	}

	// 10. Scroll element to top
	public void scrollToElementOnTopByJS(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	// 11. Scroll element to bottom
	public void scrollToElementOnDownByJS(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	// 12. Set an attribute in DOM
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {

		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getElement(locator),
				attributeName, attributeValue);
	}

	// 13. Remove an attribute in DOM
	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1]);", getElement(locator), attributeRemove);
	}

	// 14. Send keys to an element by JavaScript
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].value = arguments[1];", getElement(locator), value);
	}

	// 15. Get an attribute in DOM
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", getElement(locator),
				attributeName);
	}

	// 16. Get HTML5 validation message
	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	// 17. Verify image is loaded
	public boolean isImageLoaded(String locator) {
		return (Boolean) jsExecutor.executeScript("return arguments[0].complete && " + "arguments[0].naturalWidth > 0;",
				getElement(locator));
	}

	// 18. Get WebElement by XPath
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	// 19. Get title of page
	public String getTitleByJS() {
		return (String) jsExecutor.executeScript("return document.title;");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}