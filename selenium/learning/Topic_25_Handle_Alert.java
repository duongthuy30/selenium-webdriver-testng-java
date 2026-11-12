package learning;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Handle_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String autoITFirefox = projectPath +"\\autoIT\\authen_firefox.exe";
	JavascriptExecutor jsExecutor;
	Select select;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Accept_Alert() {
		// Accept alert -> chỉ accept ko cancel
		// alert("Toi la Accept alert");
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
       //Nó chỉ nên khởi tạo khi element/alert đó xuất hiện
		
		//Muốn thao tác được với Alert đang bật lên thì phải switch vào alert
	alert =	driver.switchTo().alert();
	
	//Verify title của alert
	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	
	//Accept cái alert này
	alert.accept();
	
	//Verify alert được accept thành công
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	
		
	}
	@Test
	public void TC_02_Confirm_Alert() {
		// Confirm alert -> được accept hoặc cancel
		// confirm("Toi la Confirm alert");
		//Nếu chạy cả TC 1 thì dùng câu này
	//	driver.navigate().refresh();
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		alert =	driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Cancel alert
		alert.dismiss();

		//Verify alert được cancel thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
		
		
		
	}
	@Test
	public void TC_03_Prompt_Alert() {
		// Prompt alert
		// prompt("Tôi là editable Alert","Auto");
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		alert =	driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String text = "Auto";
		
		alert.sendKeys(text);
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertTrue(isAlertPresent(),"Alert vẫn còn hiển thị sau khi bấm Cancel"
				);
		
		
		//Verify alert được accept thành công
				Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ text);
		
	}

	@Test
	public void TC_04_Authentication_Alert_Trick() {
//Authentication nhập user, password
		//Mở web ra là bắt nhập vào
		//Authentication ko dùng thư viện alert của selenium được
		
		
		//CÓ 2 cách
		//Cách 1 là dùng trick 
		//Selenium cho pass cái user/pass trực tiếp vào Url trước khi open nó ra
		//Ko bật alert lên 
		//All các OS/ Browser
		//Format: http(s)://Username:Password@domain
		
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		
				driver.get(url);
		
				//SO sánh tuyệt đối 2 giá trị bằng nhau 
	Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");			
		
		//So sánh tương đối trong case dữ liệu ko gọn
	Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
		
	}
	
	@Test
	public void TC_05_Authentication_Alert_Trick_Nagigate_From_Other_page() {
		String username = "admin";
		String password = "admin";
		
		
		driver.get("http://the-internet.herokuapp.com/");
		//Ko nên click vào cái link để nó show cái dialog ra
		//Nên get cái Url của link đó
		
	String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	
	//1 - Tách cái link này ra thành nhiều chuỗi
	String[] authenLinkArray = basicAuthenLink.split("//");
	//http
	//the-internet.herokuapp.com/basic_auth
	
	
	//2 - Lấy chuỗi cộng vào
	String newAuthenLinkUrl = authenLinkArray[0] + "//" + username + ":" +password + "@" + authenLinkArray[1];
	//http://the-internet.herokuapp.com/basic_auth
	
	driver.get(newAuthenLinkUrl);
	//So sánh tương đối trong case dữ liệu ko gọn
	Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
	
	}
	

	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException {
		String username ="admin";
		String password = "admin";
		//Trước khi mở Url lên
		//Cho nó execute cái file đó để chờ alert bật lên trước
		Runtime.getRuntime().exec(new String[] { autoITFirefox, username, password });
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
		//Chỉ có thể chạy trên windown thôi
		//Mac/Linux không run được 	
		
	}
	
	public boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
