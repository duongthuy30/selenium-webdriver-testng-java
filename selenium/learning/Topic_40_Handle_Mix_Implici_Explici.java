package learning;


import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_40_Handle_Mix_Implici_Explici {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Element_Found() {
		//Element có xuất hiện và ko cần chờ hết timeout
		//DÙ có set  loại wait thì ko ảnh hưởng
		
		
		//Implicit Wait chỉ apply cho việc findElement/findElements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Explicit wait: cho các điều kiện của element
				expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.facebook.com/");
		
		//Explicit 
		System.out.println("Thời gian bắt đầu của explicit:" + getTimestamp());
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
		System.out.println("Thời gian kết thúc của explicit:" + getTimestamp());
		
		//Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimestamp());
		driver.findElement(By.cssSelector("input[name='email']"));
		System.out.println("Thời gian kết thúc của implicit:" + getTimestamp());
	}
	
	
	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		

		//Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimestamp());
		try {
			driver.findElement(By.cssSelector("input[name='emails']"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của implicit:" + getTimestamp());
		}
		
	
	}
	
	
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		//3.1 - Implicit = Explicit
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://www.facebook.com/");
		
		//Implicit
		System.out.println("Thời gian bắt đầu của implicit:" + getTimestamp());
		try {
			driver.findElement(By.cssSelector("input[name='emails']"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của implicit:" + getTimestamp());
		}
		
		//Explicit 
				System.out.println("Thời gian bắt đầu của explicit:" + getTimestamp());
				try {
					expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='emails']")));
				} catch (Exception e) {
					System.out.println("Thời gian kết thúc của explicit:" + getTimestamp());
				}
				
	}
	
	
	
	public void TC_04_Element_Not_Found_Implicit_Explicit() {
		//3. - Implicit < Explicit
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(8));

		driver.get("https://www.facebook.com/");
		
		//Explicit 
				System.out.println("Thời gian bắt đầu của explicit:" + getTimestamp());
				try {
					expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='emails']")));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Thời gian kết thúc của explicit:" + getTimestamp());
				}
				
	}
	
	@Test
	public void TC_05_Element_Not_Found_Implicit_Explicit() {
		//3. - Implicit < Explicit
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://www.facebook.com/");
		
		//Explicit 
				System.out.println("Thời gian bắt đầu của explicit:" + getTimestamp());
				try {
					expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='emails']")));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Thời gian kết thúc của explicit:" + getTimestamp());
				}
				
	}
	
	@Test
	public void TC_06_Real() {
		
	}
	
	
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
		
		//Show ra time-stamp tại thời điểm gọi hàm này 
		//time-stamp = ngày giờ phút giây 
		public String getTimestamp() {
			Date date = new Date();
			return date.toString();
		}
		
	}
	
