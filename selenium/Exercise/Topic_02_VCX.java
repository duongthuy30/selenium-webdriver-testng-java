package Exercise;

import java.time.Duration;
import java.util.List;

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

public class Topic_02_VCX {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://qualityx.com.vn");
	}

	public void TC_01_ValidateCurrentUrl() {
		// Login Page Url matching
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://qualityx.com.vn");

	}

	@Test
	public void Login_01_Tenant_Code() {

		// Empty
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).sendKeys("");
		driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

		// Verify dữ liệu nhập
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập mã công ty.']")).getText(),
				"Vui lòng nhập mã công ty.");

		// Nhập sai
//		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).sendKeys("VCX1");
//		driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

		// Verify dữ liệu nhập
//		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Không tìm thấy mã công ty này.']")).getText(),
//				"Không tìm thấy mã công ty này.");

		// Nhập đúng
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập mã công ty']")).sendKeys("VCX");
		driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

		// Verify dữ liệu nhập
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'VCX')]")).isDisplayed());

	}

	@Test
	public void Login_02_User_Password() {

		// Empty

//		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập
//		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập username.']")).getText(),
//				"Vui lòng nhập username.");
//		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Vui lòng nhập mật khẩu.']")).getText(),
//				"Vui lòng nhập mật khẩu.");

		// Nhập sai username
//		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).sendKeys("vcx_qa_tes");
//		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).sendKeys("Admin123!");
//		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập
//		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Không tìm thấy tài khoản.']")).getText(),
//				"Không tìm thấy tài khoản.");

		// Nhập sai password
//		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).clear();
//		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).clear();
//		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).sendKeys("vcx_qa_test1");
//		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).sendKeys("Admin");
//		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify dữ liệu nhập

//		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Sai mật khẩu.']")).getText(), "Sai mật khẩu.");

		// Nhập đúng
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Nhập username']")).sendKeys("vcx_qa_test1");
		driver.findElement(By.cssSelector("input[placeholder='Nhập mật khẩu']")).sendKeys("Admin123!");
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Chọn vào Chiến dịch
		driver.findElement(By.xpath("//span[text()='Chiến dịch']")).click();
		enterItemInCustomDropdown("//span[text()='Trạng thái']/following-sibling::div/button",
				"#reka-combobox-group-v-1-0-48>div", "Hoàn thành");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='1 đã chọn']")).getText(), "1 đã chọn");

		//Checkbox hiển thị tên campain
		driver.findElement(By.xpath("//span[text()='Hiển thị']/parent::button")).click();
		By TencpCheckbox = 	By.xpath("//div[@role='group']//span[text()='Tên Campaign']");
		driver.findElement(TencpCheckbox).click();
	Assert.assertEquals(driver.findElement(By.xpath("//div[@role='group']/div[1]")).getAttribute("aria-checked"), "false");
	
	// Chọn vào Báo cáo
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[href='/report']")));
			driver.findElement(By.xpath("//span[text()='Cột hiển thị']")).click();
			List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("div.flex-row"));
					for (WebElement checkbox : allCheckboxs) {
						if (!checkbox.isSelected()) {
							checkbox.click();
							sleepInSecond(1);
						}}
						
			By MaCG = By.cssSelector("button[aria-label='Mã cuộc gọi']");
			driver.findElement(MaCG).click();
			Assert.assertEquals(driver.findElement(MaCG).getAttribute("aria-checked"), "false");
			sleepInSecond(5);
			driver.findElement(By.cssSelector("button[aria-label='Close']")).click();
			
			// Verify 39 cột
			List<WebElement> cot = driver
					.findElements(By.cssSelector("thead.sticky>tr:nth-child(2)>th"));

			Assert.assertEquals(cot.size(), 39);
		
	}

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
			}
		}
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
