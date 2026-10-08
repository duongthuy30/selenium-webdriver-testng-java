package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;
	@Test
	public void TC_01() {
		
		//3 hàm assert hay dùng
		//kiểm tra tính đúng đắn của dữ liệu
		
		//1- Kiểm tra dữ liệu mình mong muốn là Đúng
		//Email textbox hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		//2- Kiểm tra dữ liệu mình mong muốn là Sai
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());

		
		//3- Kiểm tra dữ liệu mình mong muốn với dữ liệu thực tế là BẰNG NHAU
	
		//Tuyệt đối 2 cái bằng nhau
		Assert.assertEquals(driver.findElement(By.id("Search")).getAttribute("placeholder"), "Search...");
		
		//Tương đối
		String benefitText = driver.findElement(By.cssSelector("ul.benefit")).getText();
		Assert.assertTrue(benefitText.contains("Faster checkout"));
	}
}
