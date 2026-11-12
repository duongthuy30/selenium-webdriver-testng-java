package learning;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Handle_Fixed_Popup {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Fixed_Popup_In_DOM() {
		
		//Khi mở page ra thì popup có trong DOM chưa
		//Mới mở ra có trong DOM luôn
		//Mở ra không có trong DOM
		//Thao tác lên  cái element nào đó thì popup xuất hiện > có trong DOM
		//Đóng popup lại thì có còn trong DOM hay ko
		
		// Popup có sẵn trong DOM nó có cách làm khác nhau
		//Selenium nếu element không có trong DOM thì ko có findElement được
		//Không dùng hàm isDissplayed kiểm tra  hàm có xuất hiện hay ko
		
		//Fixed popup: cố định và luôn có trong DOM dù có hiển thị hay ko
		
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		
		WebElement loginPopup = driver.findElement(By.cssSelector("div[role='dialog']"));
		
		//Verify login popup displayed
		Assert.assertTrue(loginPopup.isDisplayed());
		
		driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("auto");
		driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("auto");
		driver.findElement(By.xpath("//div[@class='auth-form']//button[text()='Đăng nhập']")).click();
				
		//Click đóng popup
	driver.findElement(By.cssSelector("button.close-btn")).click();

	WebDriverWait wait =
		    new WebDriverWait(driver, Duration.ofSeconds(5));
//Verify popup đóng 
		Assert.assertTrue(
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(
		        By.cssSelector("div[role='dialog']"))));
}
	
	@Test
	public void TC_02_Fixed_Popup_In_DOM() {
		driver.get("https://skills.kynaenglish.vn/dang-nhap");
		
		WebElement loginPopup = driver.findElement(By.cssSelector("div.right"));
		Assert.assertTrue(loginPopup.isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("auto");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("auto");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
	}
	
	@Test
	public void TC_03_Fixed_Popup_Not_In_DOM() {
		//Ko cố định trong DOM
		//Hiển thị thì có trong DOM
		//Mất đi thì ko còn trong DOM
		driver.get("https://ngoaingu24h.vn/");
				
		//Khi mới mở trang lên popup không có trong DOM nên sẽ ko findElement được
		//thì dùng findElements và List để tìm thấy 0 element
		//Và chờ hết timeout của Wait nhưng ko có đánh fail testcase và cx ko show Exception
		List<WebElement> loginPopup = driver.findElements(By.cssSelector("div[role='dialog']"));
		
		//Verify không hiển thị 
		//0 element được tìm thấy
		Assert.assertEquals(loginPopup.size(), 0);
		
		//Click vào button đăng nhập để show popup
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		
		//Verify login popup displayed ( Single element: findElement)
		Assert.assertTrue(driver.findElement(By.cssSelector("div[role='dialog']")).isDisplayed());
		
		//Verify hiển thị (Multiple element: findElement)
		loginPopup = driver.findElements(By.cssSelector("div[role='dialog']"));
		Assert.assertEquals(loginPopup.size(), 1);
		
		//CLick để đóng popup
		driver.findElement(By.cssSelector("button.close-btn")).click();
		sleepInSecond(3);
		
		//Verify ko hiển thị
		//Phải find lại element thì mới cập nhật giá trị
		loginPopup = driver.findElements(By.cssSelector("div[role='dialog']"));
		Assert.assertEquals(loginPopup.size(), 0);
		
	}

	
	
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}