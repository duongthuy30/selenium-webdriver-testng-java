package learning;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_User_Interaction {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String jsFileContent;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		// Hàm ko có tham số là mặc định dùng cho Browser -> Ko dùng
		// Hàm có tham số:

//Webelement cx có hàm click: ko có hover chuột đến để click
		// Action cx có hàm click: hover chuột đến rồi click
//	action.click(driver.findElement(By.xpath(""))).perform();

		// ClickAndHold: click và giữ chuột
		// contextClick: click chuột phải
		// doubleClick: click chuột trái 2 lần
		// drapAndDrop: kéo thả từ nguồn tới đích ->hay dùng
		// drapAndDropBy: kéo thả từ nguồn tới tọa độ
		// moveToElement: cũng dùng tọa độ -> Hay dùng nhất
		// sendKeys
		// build: áp dụng nhiều action lại với nhau
		// release: nhả chuột trái -> dùng cho hàm clickAndHold
		// perform: dùng với tất cả sự kiện -> phải có thì mới chạy

		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

		action.moveToElement(ageTextbox).perform();
		sleepInSecond(2);

		// Verify tooltip bằng cách debug - timeout
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

		// Khi chạy script có liên quan đến thư viện action thì ko được dùng chuột với
		// bàn phím vì sẽ bị tranh chấp sự kiện với script

	}

	@Test
	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");
		WebElement kidLink = driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"));
		action.moveToElement(kidLink).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']")))
				.perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());

	}

	@Test
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Nguyên tắc AAA: Arrange(Pre-condition)(Before Class) - Action(Click, Sendkey,
		// Select) - Assert(Verify)

		// Store all 30 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		// CHọn từ 1>11
		// Click và giữ chuột tại số 1
		action.clickAndHold(allNumbers.get(0))
				// Hover chuột tới số 11
				.moveToElement(allNumbers.get(10))
				// Nhả chuột trái
				.release()
				// Thực thi các action
				.perform();

		// Truyền lại element sau khi chọn
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		// Verify xem đã chọn thành công chưa
		Assert.assertEquals(allNumbers.size(), 9);
	}

	@Test
	public void TC_04_Click_And_Select_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// Store all 30 element
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

		// Ctrl + chuột trái để chọn nhiều
		// Nhấn phím Ctrl
		action.keyDown(Keys.CONTROL).perform();

//Chọn 5 số
		action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(7))
				.click(allNumbers.get(10)).perform();

//Nhả Ctrl ra
		action.keyUp(Keys.CONTROL).perform();

		// Truyền lại element sau khi chọn
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		// Verify xem đã chọn thành công chưa
		Assert.assertEquals(allNumbers.size(), 5);

	}

	@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Chạy như này thì bị lỗi vì element cần phải scroll xuống mới hiện
		// Nhưng chạy trên Chrome được
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		// MovetoElement khác scroll element
		// element vẫn phải nằm trong viewpoint mới move tới được
//		action.moveToElement(doubleClickButton).perform();

		// Scroll tới element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);

		action.doubleClick(doubleClickButton).perform();

		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).getText(),
				"Hello Automation Guys!");

	}

	@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));

		action.contextClick(rightClickButton).perform();

		WebElement quitButton = driver.findElement(By.xpath("//span[text()='Quit']"));

		Assert.assertTrue(quitButton.isDisplayed());
		action.moveToElement(quitButton).perform();
		Assert.assertTrue(driver
				.findElement(By.cssSelector(
						"li.context-menu-item.context-menu-icon.context-menu-icon-quit.context-menu-hover"))
				.isDisplayed());
		action.click(quitButton).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		Assert.assertFalse(quitButton.isDisplayed());
	}

	@Test
	public void TC_07_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement drapElement = driver.findElement(By.id("draggable"));
		WebElement dropElement = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(drapElement, dropElement).perform();
		Assert.assertEquals(dropElement.getText(), "You did great!");

	}

	@Test
	public void TC_08_Drag_And_Drop_HTML5_Javascript() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		jsFileContent = getContentFile(projectPath + "\\dragAndDrop\\drag_and_drop_helper.js");

		// Chỉ work với CSS(không dùng xpath)
		// Với HTML 5 hàm kéo thả không sử dụng được

		jsFileContent = jsFileContent + "$('div#column-a').simulateDragDrop({ dropTarget: 'div#column-b'});";
//Kéo A qua B 
		jsExecutor.executeScript(jsFileContent);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

		// Kéo B qua A
		jsExecutor.executeScript(jsFileContent);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
	}

	@Test
	public void TC_09_Drag_And_Drop_HTML5_Java_Robot_Offset() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		// Có thể dùng xpath và Css
		// Những case khó bắt buộc dùng xpath
		// DÙng bằng tọa độ thì độ phân giải màn hình thay đổi thì tọa độ cx thay đổi
		// OS khác nhau thì chạy/setting khác nhau
		// Run trên các CI tool tỉ lệ fail cao - vì ko mở giao diện lên
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

		// Kéo B qua A
		dragAndDropHTML5ByXpath("//div[@id='column-b']", "//div[@id='column-a']");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation = new Point(sourceLocation.x + 20 + xCentreSource, sourceLocation.y + 110 + yCentreSource);

		targetLocation = new Point(targetLocation.x + 20 + xCentreTarget, targetLocation.y + 110 + yCentreTarget);

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

}
