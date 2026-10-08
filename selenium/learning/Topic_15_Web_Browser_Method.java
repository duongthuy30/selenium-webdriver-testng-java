package learning;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Web_Browser_Method {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	// thời gian chờ tìm element

	@Test
	public void TC_01_Method() {
		driver.close();
		// close browser hoặc tab
		// nếu có 1 tab thì là đóng browser
		// nếu có nhiều tab thì sẽ đóng tab hiện tại"

		driver.quit();                                 //hay dùng
		// đóng browser luôn ko quan tâm bn tab

		driver.get("");                                 //hay dùng
		// mở 1 url

		driver.findElement(By.id(""));                  //hay dùng
		// tìm 1 element

		driver.findElements(By.id(""));                  //hay dùng
		// tìm nhiều element"

		driver.getCurrentUrl();
		// lấy ra url của page hiện tại

		driver.getPageSource();
		// lấy ra source code của page hiện tại
		// > verify 1 cách tương đối thông tin khó lấy element"
		// dùng Assert.assertTrue (driver.getPageSource().contains(""));

		driver.getTitle();
		// lấy title page hiện tại

		driver.getWindowHandle();                         //hay dùng
		// xử lý windown/tab
		// lấy ra ID của tab/windown đang active"

		driver.getWindowHandles();                        //hay dùng
		// xử lý windown/tab
		// lấy ra ID của tất cả các tab/windown"

		driver.manage().deleteAllCookies();
		// lưu lại các phiên đăng nhập/session/dữ liệu

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));             // hay dùng
		// chờ cho page load thành công
		// > không cần vì driver.get đã bao gồm cả wait"

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
		// chờ cho 1 javascript thực thi
		// > không cần vì có hàm khác đã có"

		driver.manage().window().fullscreen();
		// full màn hình

		driver.manage().window().maximize();                  //hay dùng
        //mở rộng 

		driver.manage().window().setPosition(new Point(0, 0));
		// set vị trí của browser so với độ phân giải màn hình

		driver.manage().window().getPosition();

		driver.manage().window().setSize(new Dimension(0, 0));
		// thực tế ko dùng: Test GUI/
		// mở browser với kích thước bao nhiêu> test reponsive

		driver.manage().window().getSize();

		// Tracking history tốt hơn
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://alada.vn/tai-khoan");
		// mở ra 1 trang web
		// ko dùng

		// Alert
		driver.switchTo().alert();                             //hay dùng

		// Frame/Iframe
		driver.switchTo().frame(0);                            //hay dùng

		// Windown/Tab
		driver.switchTo().window(projectPath);                  // hay dùng

	}
}
