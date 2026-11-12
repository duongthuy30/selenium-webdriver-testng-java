package learning;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
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

public class Topic_34_Handle_Sendkey_Upload_File {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	// Image name
	String vietnam = "Việt Nam.jpg";
	String thailan = "Thái Lan.png";
	String indonesia = "Indonesia.jpg";

	// Upload file folder
	//
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;

	// Image path
	String vietnamFilePath = uploadFileFolderPath + vietnam;
	String thailanFilePath = uploadFileFolderPath + thailan;
	String indonesiaFilePath = uploadFileFolderPath + indonesia;

	List<String> fileNames = getFileNameInFolder();

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
	public void TC_01_Sendkey_Upload_One_File() {
//Không cần bật Open file Dialog lên 
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Phải là input type= file mới sendkey được

		// Load file lên
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnamFilePath);

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
	public void TC_02_Upload_Multi_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		for (String fileName : fileNames) {
			driver.findElement(By.cssSelector("input[type='file']")).sendKeys(uploadFileFolderPath + fileName);
		}

		for (String fileName : fileNames) {
			Assert.assertTrue(
					driver.findElement(By.xpath("//p[class='name'] and text()='" + fileName + "']")).isDisplayed());
		}

	}

	@Test
	public void TC_03_Upload_Multi_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Nối đường dẫn các file lại với nhau
		// Mỗi file sẽ phân tách bằng ký tự xuống dòng
		// + "\n" +

		By uploadFile = By.cssSelector("input[type='file']");

		// Load file lên
		driver.findElement(uploadFile).sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);

	}

	// Lấy ra tên nhiều file
	public List<String> getFileNameInFolder() {
		File directoryPath = new File(uploadFileFolderPath);

		// List of all files and directories
		String contents[] = directoryPath.list();

		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < contents.length; i++) {
			System.out.println(contents[i]);
			fileNames.add(contents[i]);
		}
		return fileNames;
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
