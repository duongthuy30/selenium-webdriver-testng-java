package Exercise;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_36_Exe_Control_Status {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//findElement sẽ bị ảnh hưởng timout nếu implicitly có set 
		//Sẽ tìm element trong 10s 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}
	
	
	//Synchronus: Đồng bộ
	//Asynchronus: Bất đồng bộ
	
	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		
	}
}
