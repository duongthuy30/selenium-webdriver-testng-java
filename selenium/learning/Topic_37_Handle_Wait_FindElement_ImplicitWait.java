package learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_37_Handle_Wait_FindElement_ImplicitWait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// findElement sẽ bị ảnh hưởng timout nếu implicitly có set
		// Sẽ tìm element trong 10s
		//implicitly wait set ở đâu nó sẽ apply từ đó trở xuống
		//Nếu bị gán lại sẽ dùng giá trị mới 
			
		//4 cách đều đúng chỉ cần drive = new FirefoxDriver là được 
		
		//1
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://live.techpanda.org/");
		driver.manage().window().maximize();
	
		
		
		//2
		driver.get("https://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		
		
		
		//3
		driver.manage().window().maximize();
		driver.get("https://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		
		//4
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://live.techpanda.org/");
		
		
		
		
	}

	@Test
	public void TC_01_FindElement() {
		// Thao tác 1 lần
//		driver.findElement(By.id("")).click();

		// Thao tác nhiều lần thì khai báo biến
//		WebElement emailTextbox = driver.findElement(By.id(""));
//		emailTextbox.click();

		// - Tìm thấy duy nhất 1 node
		// Tìm thấy và Thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout là 15s
		driver.findElement(By.id("email"));

		
		
		// - Tìm thấy nhiều hơn 1 node
		//Nó sẽ thao tác với node đầu tiên
		//Ko quan tâm các node còn lại
		//Trong trường hợp bắt sai locator thì nó sẽ tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("auto");

		
		
		// - Không tìm thấy node nào
        //Có cơ chế tìm lại nửa giây tìm lại 1 lần
		//Nếu trong thời gian đang tìm lại mà tìm thấy thì Pass
		//Nếu hết 15s mà vẫn ko tìm thấy element thì
		//+ Đánh fail TC tại step này + Ko chạy step tiếp theo
		//+Throw ra 1 ngoại lệ: NoSuchElementException 
		
		driver.findElements(By.cssSelector("input[type='check']"));
	}

	@Test
	public void TC_02_FindElements() {

		// Tìm thấy duy nhất 1 node
		// Tìm thấy và lưu nó vào list = 1 element
		// Vì tìm thấy nên ko cần chờ hết timeout 15s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number =" + elements.size());

		
		
		// - Tìm thấy nhiều hơn 1 node
		// Tìm thấy và lưu nó vào list = element tương ứng
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number =" + elements.size());

		
		
		// - Không tìm thấy node nào
		// Có cơ chế tìm lại nửa giây tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà tìm thấy thì Pass
		// Nếu hết 15s mà vẫn ko tìm thấy element
		// + Không đánh fail TC + vẫn chạy step tiếp theo
		// + Trả về 1 list rỗng
		
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number =" + elements.size());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
