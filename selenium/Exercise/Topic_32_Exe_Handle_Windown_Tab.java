package Exercise;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Exe_Handle_Windown_Tab {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");
	Alert alert;
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
	public void TC_01_Windown() {
		driver.get("https://live.techpanda.org/");
		sleepInSecond(3);
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		
		
		switchToWindownByID(parentID);
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		String mobilePageID = driver.getWindowHandle();
		
		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		sleepInSecond(2);
		
		switchToWindownByID(mobilePageID);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = driver.switchTo().alert();
		alert.accept();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The comparison list was cleared.");				
	}
	
	
	@Test
	public void TC_02_Windown_ByID() {
		driver.get("https://courses.dce.harvard.edu/");
		String homePageID = driver.getWindowHandle();
		
	driver.findElement(By.cssSelector("a[data-action='login']")).click();
	
	switchToWindownByID(homePageID);
	sleepInSecond(3);
	
	driver.findElement(By.xpath("//span[text()='Log in with HarvardKey']")).click();
	driver.findElement(By.cssSelector("button[type='submit']")).click();
	sleepInSecond(3);
	String loginPageID = driver.getWindowHandle();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[text()='This field cannot be left blank']")).getText(), "This field cannot be left blank");
	driver.close();
	
	switchToWindownByID(loginPageID);
	sleepInSecond(3);
	
	driver.findElement(By.cssSelector("button.sam-wait__close")).click();
		
			
	driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("auto");
	driver.findElement(By.cssSelector("button#search-button")).click();
			
			Assert.assertEquals(driver.findElement(By.className("result__title")).getText(), "Deep Learning");
	}	
	
	@Test
	public void TC_03_Windown_ByTitle() {
		//Đang ở trang A
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
	
	}
	
	
	//Chỉ dùng cho duy nhất 2 tab/windown
	public void switchToWindownByID(String parentID) {
		//Lấy ra tất cả các ID của tab hoặc Windown đang có
		Set<String> allTabID = driver.getWindowHandles();
		//DÙng vòng lặp để duyệt qua
		for (String id : allTabID) {
			//Nếu có ID nào khác parentID 
			if (!id.equals(parentID)) {
				//switch vào
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
	}
	
   
	public void switchToWindownByTitle(String expectedPageTitle) {
		// Lấy ra tất cả các ID của tab hoặc Windown đang có
		Set<String> allTabID = driver.getWindowHandles();
		// DÙng vòng lặp để duyệt qua từng ID
		for (String id : allTabID) {
			// Switch vào từng tab/windown trước
			driver.switchTo().window(id);
			// Lấy ra title của page đã switch vào
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.equals(expectedPageTitle)) {
				// Thoát khỏi vòng lặp
				break;
			}
		}
	}
	
	
	public void closeAllWindownWithoutParent(String parentID) {
		//Lấy ra tất cả các ID của tab hoặc Windown đang có
				Set<String> allTabID = driver.getWindowHandles();
				//DÙng vòng lặp để duyệt qua
				for (String id : allTabID) {
					if (!id.equals(parentID)) {
						driver.switchTo().window(id);
						//đóng cái tab đang active
						driver.close();
						
					//đóng cả cái browser
						//drive.quit;
					}
				}		
				//Vẫn còn lại parentID
				driver.switchTo().window(parentID);
	}

	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
