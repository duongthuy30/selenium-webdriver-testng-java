package learning;

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

public class Topic_39_Handle_ExpliciWait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_ExpicliciWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
        //Wait cho Date picker hiển thị
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.calendarContainer")));
		
		//Verify text khi ko có ngày nào được chọn
		By selectedDate = By.cssSelector("span.label");
		System.out.println(driver.findElement(selectedDate).getText());
		
		
		
		//Wait cho ngày 30 được phép click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='30']")));
		
		//Click vào ngày 30
		driver.findElement(By.xpath("//a[text()='30']")).click();
		//Wait cho hết loading
		expliciWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadAjaxLoadingPanel1']")));
		
		//Wait cho ngày vừa được click là được phép click trở lại
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.rcSelected")));

		Assert.assertEquals(driver.findElement(selectedDate).getText(), "Friday, October 30, 2026");
		
	}
	
	
	@Test
	public void TC_02_ExpliciWait() {
		driver.get("https://gofile.io");

		driver.findElement(By.cssSelector("a.group")).click();

		By createdFolderButton = By.cssSelector("div.mt-5 button[data-action='create-folder']");

		//Wait cho hiển thị button tạo Folder
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
