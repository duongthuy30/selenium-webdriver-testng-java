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

public class Topic_21_Exe_Handle_Custom_Dropdown_2 {
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

	}

	@Test
	public void TC_01_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	
		enterItemInCustomDropdown("input.search", "div.item>span","Bangladesh" );
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bangladesh");
	
	}	
	

	@Test
	public void TC_02_Huawei() {
		driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
		driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown'] > span.hwid-select-text")).click();
	
		enterItemInCustomDropdown("//input[@ht='input_emailregister_search']", "ul.hwid-list-module.hwid-alpla-list li > span.list-item-text","Bangladesh" );
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Bangladesh']")).getText(), "Bangladesh");
	
	}	
	

	@Test
	public void TC_03_Finpeace() {
		driver.get("https://sps.finpeace.vn/tools/sktccn");
	
		enterItemInCustomDropdown("gender", "div[title='Nam']","Nam" );
		enterItemInCustomDropdown("job_id", "div#job_id_list+div>div>div>div>div","Công nghệ thông tin" );
		selectItemInCustomDropdown("//span[text()='Chọn tình trạng hôn nhân']/parent::div", "div#married_status_list+div>div>div>div>div","Độc thân, chưa có con" );

	
	}	
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.xpath(parentLocator)).click();
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
		driver.findElement(By.xpath(editableLocator)).sendKeys(expectedTextItem);
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
