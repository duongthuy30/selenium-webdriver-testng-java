package learning;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Handle_Windown_Tab {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");
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
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		//Vừa mở ra nó chỉ có 1 tab duy nhất
		//Lấy ra ID của tab 
	String formTabID =	driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		//Lúc này đã có 2 tab
		Set<String> allTabID = driver.getWindowHandles();
		//Lấy ra ID A và B
		
		//Dùng vòng lặp để duyệt qua từng ID đang có trong Set
		//Hàm này chỉ dùng khi có 2 tab
		for (String id : allTabID) {
			//id là 1 biến tạm dùng để duyệt qua từng giá trị trong vòng lặp	
			//Lần duyệt thứ 1
			// id: A
			//formTabID: (A)
			
			//Lần duyệt thứ 2
			//id: B
			//formTabID:(A)
			
			if (!id.equals(formTabID)) {
				//switch qua bằng ID
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
	
		Assert.assertEquals(driver.getTitle(), "Google");
		
		
	
	}
	
	@Test
	public void TC_02_Windown_ByID() {
		//Đang ở trang A
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		//Vừa mở ra nó chỉ có 1 tab duy nhất
		//Lấy ra ID của tab 
	String formTabID =	driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
switchToWindownByID(formTabID);
	//Đang ở trang B
		Assert.assertEquals(driver.getTitle(), "Google");
		String googleTabID = driver.getWindowHandle();
			
		//Quay về trang A
	switchToWindownByID(googleTabID);
	//Driver đang ở trang A
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	
	//Switch qua trang C
	
	}	
	
	@Test
	public void TC_03_Windown_ByTitle() {
		//Đang ở trang A
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
switchToWindownByTitle("Google");
	//Đang ở trang B
		Assert.assertEquals(driver.getTitle(), "Google");
		
			
		//Quay về trang A
	switchToWindownByTitle("Selenium WebDriver");
	
	//Driver đang ở trang A
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	sleepInSecond(3);
	//Switch qua trang C
		switchToWindownByTitle("Facebook");
		sleepInSecond(2);
		
		
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("auto");
		sleepInSecond(3);
		//Quay về trang A
		switchToWindownByTitle("Selenium WebDriver");
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		//Switch qua trang D
		switchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
	closeAllWindownWithoutParent(parentID);	
		
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
	
    //Với TH nhiều tab/windown
	// SỬ dụng title
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
