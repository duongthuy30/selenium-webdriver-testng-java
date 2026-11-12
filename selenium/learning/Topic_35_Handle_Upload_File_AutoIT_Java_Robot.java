package learning;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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

public class Topic_35_Handle_Upload_File_AutoIT_Java_Robot {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String firefoxSinglePath = projectPath + "\\autoIT\\firefoxUploadOneTime.exe";
	String chromeSinglePath = projectPath + "\\autoIT\\chromeUploadOneTime.exe";
	String firefoxMultiPath = projectPath + "\\autoIT\\firefoxUploadMultiple.exe";
	String chromeMultiPath = projectPath + "\\autoIT\\chromeUploadMultiple.exe";

	JavascriptExecutor jsExecutor;

	// Image name
	String vietnam = "Việt Nam.jpg";
	String thailan = "Thái Lan.png";
	String indonesia = "Indonesia.jpg";

	// Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;

	// Image path
	String vietnamFilePath = uploadFileFolderPath + vietnam;
	String thailanFilePath = uploadFileFolderPath + thailan;
	String indonesiaFilePath = uploadFileFolderPath + indonesia;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

	}

	// Hàm sendkey cuea selenium webElement
	// Tool AutoIT để script và export ra file exe sau đó nhúng vào script
	// Thư viện Java Robot

	@Test
	public void TC_01_Upload_One_File_AutoIT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);

		// Upload file bằng AutoIT
		Runtime.getRuntime().exec(new String[] { firefoxSinglePath, vietnamFilePath });

		// Verify tên ảnh đã up lên
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Việt Nam.jpg']")).isDisplayed());

		// Chưa thực hiện upload

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(5);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());

	}

	@Test
	public void TC_0_Upload_Multi_File_AutoIT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);

		// Upload file bằng AutoIT
		Runtime.getRuntime()
				.exec(new String[] { firefoxMultiPath, vietnamFilePath, thailanFilePath, indonesiaFilePath });

		// Verify tên ảnh đã up lên
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Việt Nam.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Thái Lan.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Indonesia.jpg']")).isDisplayed());

		// Chưa thực hiện upload

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(5);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Java_Robot() throws AWTException {
		// Load file bằng Robot
		// Giả lập hành vi copy path của file
		// Giả lập hành vi paste vào Open Dialog

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		loadFileByRobot(vietnamFilePath);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Việt Nam.jpg']")).isDisplayed());

		// Chưa thực hiện upload

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(5);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());

	}

	
	public void loadFileByRobot(String filepath) throws AWTException {
		
		StringSelection select = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// Load file
		Robot robot = new Robot();
		sleepInSecond(1);

		// Nhan xuong Ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha Ctrl + V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);

		// Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
