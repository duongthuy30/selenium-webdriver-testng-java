package learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Handle_Random_Popup {
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
	public void TC_01_Random_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");
		sleepInSecond(8);

		WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-container"));

		// Phải handle 2 trường hợp
		// 1- Đang trong đợt hiển thị thì script phải đóng đi để chạy tiếp
		// 2- Hết đợt hiển thị thì script chạy luôn
		// Muốn test thử thì cố tính tắt popup đi trước khi gọi đến hàm check Display

		if (popupLayer.isDisplayed()) {
			// Close nó đi để chạy tiếp cái step tiếp theo
			driver.findElement(By.cssSelector("span.close_icon")).click();
		}

//Step tiếp theo
		driver.findElement(By.cssSelector("a.ios")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.download_popup_box")).isDisplayed());

	}

	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {
		driver.get("https://tiki.vn/");
		sleepInSecond(5);

		// 1- Đang trong đợt hiển thị thì script phải đóng đi để chạy tiếp
		// 2- Hết đợt hiển thị thì script chạy luôn
		
		List<WebElement> contentPopup = driver.findElements(By.cssSelector("div#VIP_BUNDLE"));
		
		//Nếu element> 0(tương ứng từ 1 trở lên)
		if (contentPopup.size() > 0 && contentPopup.get(0).isDisplayed()) {
			//Close popup
			driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
		}
		
		//Step tiếp theo
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[placeholder='Số điện thoại']")).isDisplayed());

	}

	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
