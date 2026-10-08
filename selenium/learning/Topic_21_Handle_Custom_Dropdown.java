package learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Handle_Custom_Dropdown {
	WebDriver driver;
	// Khởi tạo Chờ cho tất cả item con bên trong được load ra
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		// expliciWait Luôn luôn khởi tạo sau drive> vì cần giá trị drive để khởi tạo
		// Wait cho các element theo điều kiện có sẵn:
		// visible/invisble/presence/clickable
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));

//Ép kiểu tường minh trong Java
		jsExecutor = (JavascriptExecutor) driver;

		// Wait cho việc tìm element: findElement
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Defauft Dropdown
		// HTML code select - cha
		// option - con
		// Thư viện select của selenium sẽ thao tác với loại element này được
		// Các thẻ option nằm trong thẻ select

		// Custom Dropdown
		// HTML code ko phải select/option > ko thao tác được
		// không bắt buộc option nằm trong select
		// Bất kỳ dropdown nào cũng có hành vi như nhau

		// Các bước để chọn được 1 item trong dropdown
		// - Clip vào dropdown cho xổ hết tất cả item con bên trong ra
		// > DÙng click
		driver.findElement(By.id("number-button")).click();

		// - Chờ cho tất cả item con bên trong được load ra
		// > WebDriverWait > Chưa học
		// Truyền vào 1 By locator đại diện cho tất cả icon con
		// Lấy cái thẻ chứa text item
		// Case 1: Click step 1 ở trên xong >Xổ ra nó load hết ra luôn> Không cần wait
		// Case 2: dữ liệu nhiều thì nó load vài nghìn record> Lâu
		// Chưa load hết ra thì ko thể bấm vào để chọn được
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#number-menu li")));

		// - Tìm item mong muốn(Cuộn chuột xuống để tìm)
		// > Vòng lặp để lấy hết tất cả item rồi duyệt qua - getText từng cái

//Khai báo từng element thì rất là nhiều/tổng cộng bn cái/code nhiều
		// Lấy hết tấy cả các item ra lưu vào
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector("#number-menu li"));
		// 19 item trong này

		// Duyệt qua từng item> không dùng
		// allDropdownItems.get(0).getText();
		// allDropdownItems.get(1).getText();
		// allDropdownItems.get(2).getText();
		// allDropdownItems.get(3).getText();

		// Duyệt qua gọn thì dùng vòng lặp
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();

			// - Thấy item cần chọn thì click vào> so sánh với item mong muốn sau đó Click
			// vào
			if (actualTextItem.equals("5")) {
				item.click();
				// CHưa thoát ra vòng lặp(6-19)
				break; // Thoát ra vòng lặp thì 6-19 ko kiểm tra nữa
			}
		}

		// - Item này sẽ đổ dữ liệu vào dropdown
		// >verify chọn thành công

//		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "5");

		// Chọn sang item khác > phải viết lại hết
		// CHỌN ITEM 10
		selectItemInCustomDropdown("number-button", "#number-menu li", "1");
		// VERIFY ĐÃ CHỌN ITEM 10
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "1");

		// CHỌN ITEM 10
		selectItemInCustomDropdown("number-button", "#number-menu li", "10");
		// VERIFY ĐÃ CHỌN ITEM 5
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "10");

		// CHỌN ITEM 15
		selectItemInCustomDropdown("number-button", "#number-menu li", "19");
		// VERIFY ĐÃ CHỌN ITEM 15
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(), "19");
// Với case chạy 1>10>19 đang bị fail
		// Khi vừa mở page ra chưa có item nào có trong HTML
		// Khi user thao tác lên thì các item này mới load ra
		// Sửu dụng javascriptExecutor> chạy được các câu lệnh Javascript trong code của
		// mình

		// - Item này sẽ đổ dữ liệu vào dropdown
		// >verify chọn thành công
		// Cách verify dữ liệu sau khi chọn xong:
		// không cố định cho bất kỳ loại dropdown nào
		// tùy thuộc vào HTML của dropdown đó
		// Detect được cái dropdown này có sự thay đổi trước và sau khi chọn

	}

	// Viết thành hàm
	// Ko được truyền cái gì cố định> truyền qua tham số
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(3);

		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			System.out.println("Item text = " + actualTextItem);

			if (actualTextItem.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				// arguments[0].scrollIntoView(true) > scroll đến mép trên của element
				// arguments[0].scrollIntoView(false) > scroll đến mép dưới của element
				// var newsLetterTextbox =
				// $$("#newsletter")[0];newsLetterTextbox.scrollIntoView(true);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	// không có sẵn nên phải tự viết code để handle
	// Dropdown này nằm trong phần học Background
	// Nên bài hiện tại là tìm hiểu và học dần

//Khi run test script cần theo dõi cách nó chạy/theo dõi nó thao tác đến đâu 

	@Test
	public void TC_02_JQuery() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("i.dropdown", "div.item>span", "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
	}

	@Test
	public void TC_03_Vue() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		// Với Defaul dropdown vẫn dùng hàm được
		// Nhưng khi verify dữ liệu thì dùng
		Assert.assertTrue(driver.findElement(By.xpath("")).isSelected());
		
		
		//CHọn nhiều option 1 lúc

		String osName = System.getProperty("os.name");
		Keys key = null;

		if (osName.contains("Windows")) {
		    key = Keys.CONTROL;
		} else {
		    key = Keys.COMMAND;
		}	
		key.name();
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
	}
	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}

