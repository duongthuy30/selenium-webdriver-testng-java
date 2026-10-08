package Exercise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Exe_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Select select;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

	}

	@Test
	public void TC_01_Select_Number() {		
		selectItemInCustomDropdown("number-button", "#number-menu>li", "1");
		selectItemInCustomDropdown("number-button", "#number-menu>li", "10");
		selectItemInCustomDropdown("number-button", "#number-menu>li", "19");
	}

	

	@Test
	public void TC_02_Select_Speed() {		
		selectItemInCustomDropdown("speed-button", "#speed-menu>li", "Slower");
		selectItemInCustomDropdown("speed-button", "#speed-menu>li", "Medium");
		selectItemInCustomDropdown("speed-button", "#speed-menu>li", "Fast");
	}

		
	
	@Test
	public void TC_03_Select_Title() {
		selectItemInCustomDropdown("salutation-button", "#salutation-menu>li", "Mr");
		selectItemInCustomDropdown("salutation-button", "#salutation-menu>li", "Dr");
		selectItemInCustomDropdown("salutation-button", "#salutation-menu>li", "Other");
	}
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.id(parentLocator)).click();
		sleepInSecond(3);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			System.out.println("Item text = " + actualTextItem);

			if (actualTextItem.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}}}
	
	public void enterItemInCustomDropdown(String editableLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(editableLocator)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			System.out.println("Item text = " + actualTextItem);

			if (actualTextItem.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}}}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	
		enterItemInCustomDropdown("input.search", "div.item>span","Bangladesh" );
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bangladesh");
	
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
		}
	}
}
