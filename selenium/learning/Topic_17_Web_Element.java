package learning;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;

	@BeforeClass
	public void beforeClass() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	// thời gian chờ tìm element

	@Test
	public void TC_01_Define_Element() {
		//Muốn thao tác được với Element thì phải tìm Element trước
		//Sau đó mới áp dụng hành vi vào cho Element
		
		//1 -  Tìm Element
		//2 - VỚi loại Locator gì
		//3 - Tương tác lên/ Kiểm tra nó
		driver.findElement(By.id(""));
		//WebElement các hàm thao tác với element
		//get: trả ra các kiểu dữ liệu > verify dữ liệu > luôn có kiểu trả về
		//X: có thể define theo từng biến 
		//is :  luôn luôn trả về boolean> verify dữ liệu
		//void: trả về các action > tương tác > không cần trả về
		
		//Muốn thao tác trực tiếp lên element thì ko cần khai báo biến
		
	//Thao tác 2 lần thì cần khai báo biến để tránh lặp lại
		//VD
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("duong@gmail");
		driver.findElement(By.id("email")).isDisplayed();
		
		//nên làm
		WebElement emailTextbox =  driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("duong@gmail.com");
		emailTextbox.isDisplayed();
	}
		
		@Test
		public void TC_02_Element_Method() {
			WebElement element = driver.findElement(By.id(""));
		
		element.clear();             //hay dùng
		//Xóa dữ liệu trong những field cho phép nhập
		//Luôn luôn clear hết dữ liệu trước khi thao tác
		//Textbox/TextArea/ Editable Dropdown
		
		element.sendKeys("");
		//Nhập dữ liệu trong các field cho phép
		element.sendKeys(Keys.ENTER);             //hay dùng
		
		element.findElement(By.className("")); 
		//thực tế ko dùng vì lâu 
		
		element.getAttribute("placeholder");             //hay dùng
		
		driver.findElement(By.id("Search")).getAttribute("placeholder");
		//Lấy ra giá trị của placeholder
		
		//Không khai báo biến = Verify trực tiếp
		Assert.assertEquals(driver.findElement(By.id("Search")).getAttribute("placeholder"), "Search...");
		
		//Khai báo biến để dùng nhiều lần
		String searchTextboxPlaceholderValueString = driver.findElement(By.id("Search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextboxPlaceholderValueString, "Search...");
		//hàm assert là của testNG
		
		element.getCssValue("background-color");             //hay dùng
		//GUI: Font/Size/Color/Pixel
		
		element.getLocation();
		element.getRect();
		element.getSize();
		//GUI: Position/Location/Size of element
		
		element.getScreenshotAs(null);             //hay dùng
		//Học trong Frameword
		
		element.getTagName();
		String emailTextboxTagnameString = element.getTagName();
		//output của element này  sẽ là input của element khác
		
		//Truyền 1 biến vào trong 1 chuỗi
		driver.findElement(By.xpath("//" + emailTextboxTagnameString + "[@id='advice-validate-password']"));
		//Chuỗi thứ 1 + biến + chuỗi thứ 2 
		
		element.getText();             //hay dùng
		//Lấy ra text của element hiện tại 
		//Text của những element con bên trong 
		
		element.isDisplayed();             //hay dùng
		//kiểm tra element hiển thị hay ko
		//Hiển thị: ng dùng nhìn thấy được/có kích thước cụ thể 
		//Mong muốn hiển thị thì assertTrue
		//Không hiển thị thì assertFalse
		//Áp dụng cho tất cả element
		
		element.isEnabled();             //hay dùng
		//Mong muốn 1 element có thể thao tác được lên hoặc ko
		//Áp dụng cho tất cả element
		//Ngược lại là Disable
		//Mong muốn thao tác được thì assertTrue
		//Không thao tác thì assertFalse
		
		element.isSelected();             //hay dùng
		//Mong muốn 1 element đã được chọn hay chưa
		//Áp dụng với 1 vài loại element: Checkbox/radio button/ defaul dropdown
		//Phải chọn vào element bấm ko lấy text 
		
		element.click();             //hay dùng
		//Click vào 1 element
		// Button/Link/Checkbox/Image/Icon/Radio
		
		action.clickAndHold(element).moveToElement(element).perform();
       //Click lên slider và giữ chuột
		//Sau đó kéo slider này tới 1 tọa độ/ kéo tới 1 element khác 
		
		element.submit();
		//Giống hành vi Enter ở các form 
		//không cần ấn button gửi
		
		//text() trong Xpath lấy text dưới HTML
		//getText sẽ lấy text trên UI
		
		
		
	}
}
