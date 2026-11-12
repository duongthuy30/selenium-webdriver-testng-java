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

public class Topic_31_Handle_Frame_Iframe {

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
	public void TC_01_Iframe_Kyna() {
		//A
	driver.get("https://kyna.vn//");
	
	//switch to
	//alert -> học rồi
	//Frame/Iframe
	//Windowns/Tab
	
	//Lấy ra iframe ở index nào
	//Nếu như thêm/xóa bớt iframe đi thì ko tìm được
//	driver.switchTo().frame(0);
	
	//ID/Name : Iframe phải có ID hoặc Name
//	driver.switchTo().frame("");
	
	//WebElement: all case > dùng cái này
	//A -> B
	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
	
	//B
	String facebookLikeNumber = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
	
	Assert.assertEquals(facebookLikeNumber, "166K Likes");
	
	//B ->A lại
	driver.switchTo().defaultContent();
	
	//A ->C
	driver.switchTo().frame("cs_chat-iframe");
	
	//Ko support nhảy từ iframe B qua iframe C (2 iframe thuộc A)
	//C(Element thuộc C)
	driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
	sleepInSecond(5);
	driver.findElement(By.cssSelector("input.input_name")).sendKeys("Auto");
	
	//C ->A
	driver.switchTo().defaultContent();
	
	//A( Element thuộc A)
	String keyword = "Excel";
	driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
	
driver.findElement(By.cssSelector("button.search-button")).click();
	sleepInSecond(5);
	
	//Verify 
	List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
	
	//Verify bằng sô lượng
	Assert.assertEquals(courseName.size(), 10);
	
	for (WebElement course : courseName) {
		//Course name contain keyword
		Assert.assertTrue(course.getText().contains(keyword));
	}
	
	}
	
	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
driver.switchTo().frame("login_page");	

driver.findElement(By.name("fldLoginUserId")).sendKeys("auto");
driver.findElement(By.cssSelector("a.login-btn")).click();
sleepInSecond(3);

Assert.assertTrue(driver.findElement(By.id("fldPasswordDispID")).isDisplayed());

driver.findElement(By.id("fldPasswordDispID")).sendKeys("auto");

	}
	
	@Test
	public void TC_03_() {
		
		
	}
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
}
