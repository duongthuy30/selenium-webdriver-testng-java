package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Exe_Handle_Shadow_DOM {
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
	public void TC_01_Shadow_DOM() {
		driver.get("https://automationfc.github.io/shadow-dom");
		// Bước 1: Tìm Shadow Host bên ngoài
		WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));

		// Bước 2: Lấy ra ranh giới Shadow Root
		SearchContext shadowRoot = shadowHost.getShadowRoot();

		// Bước 3: Tìm phần tử nằm sâu bên trong Shadow DOM thông qua shadowRoot
		WebElement infoSpan = shadowRoot.findElement(By.cssSelector("span.info"));

		// Sử dụng bình thường
		String text = infoSpan.getText();
		System.out.println(text);
		
		//Lấy ra checkbox chưa chọn 
		WebElement checkbox = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
		
		Assert.assertFalse(checkbox.isSelected());
			
		
	}
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
	
