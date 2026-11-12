package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_38_Exe_Handle_Wait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Apply 15s cho các điều kiện/ trạng thái cụ thể
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_ExpliciWait() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.calendarContainer")));

		By selectedDate = By.cssSelector("span.label");
		System.out.println(driver.findElement(selectedDate).getText());

		driver.findElement(By.xpath("//a[text()='29']")).click();

		expliciWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadAjaxLoadingPanel1']")));

		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.rcSelected")));

		Assert.assertEquals(driver.findElement(selectedDate).getText(), "Thursday, October 29, 2026");
	}

	@Test
	public void TC_02_ExpliciWait() {
		driver.get("https://gofile.io");

		driver.findElement(By.cssSelector("a.group")).click();

		By createdFolderButton = By.cssSelector("div.mt-5 button[data-action='create-folder']");

		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(createdFolderButton));

		driver.findElement(createdFolderButton).click();
		WebElement folderName = expliciWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.input")));
		folderName.sendKeys("Selenium");

		driver.findElement(By.cssSelector("button[data-action='1']")).click();

		By messageLocator = By.xpath("//p[contains(.,'Selenium')]");

		// Chờ thông báo tạo folder thành công
		expliciWait.until(ExpectedConditions.textToBePresentInElementLocated(messageLocator, "created"));

		// Lấy thông báo sau khi đã cập nhật
		String actualMessage = driver.findElement(messageLocator).getText();

		System.out.println("Message: " + actualMessage);

		// Verify
		Assert.assertTrue(actualMessage.contains("created"));

		// Verify folder đã xuất hiện
		WebElement folder = expliciWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Selenium']")));

		Assert.assertTrue(folder.isDisplayed());

	}

}
