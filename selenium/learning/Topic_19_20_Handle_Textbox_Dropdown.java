package learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_20_Handle_Textbox_Dropdown {

	//với textarea xuống dòng thì nhập
	//sendkey("auto/n")
	//kiểm tra dữ liệu nhập ở textbox/textarea
	//thường sẽ nằm trong attribute value 
	//Nếu nó nằm trong 1 attribute thì dùng getAttribute
	//Nếu nó nằm trong 1 text thì dùng getText

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;


	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01_() {
		//Defauft Dropdown
		//HTML code select - cha
		// option - con
		//Thư viện select của selenium sẽ thao tác cới loại element này được 
		
		//Dropdown date month year
		//khi nào dùng để thao tác với element thì mới khởi tạo
		select = new Select(driver.findElement(By.cssSelector("div.col-3 select.select2-hidden-accessible")));
		//Thao tác với hàm 
		//<li class="select2-results__option select2-results__option--highlighted" id="select2-subject-g5-result-ic8k-1" role="treeitem" aria-selected="false">1</li>
		//<li class="select2-results__option" id="select2-subject-g5-result-6hbh-2" role="treeitem" aria-selected="false">2</li>
		//<li class="select2-results__option" id="select2-subject-g5-result-2x44-3" role="treeitem" aria-selected="false">3</li>
		
		//index: thứ tự của thẻ > hợp date time 
	//	select.selectByIndex(10);    > không dùng, dễ thay đổi khi thêm sửa xóa 
		
		//value: Giá trị của thuộc tính value
	//	select.selectByValue("10");    > không dùng 
		
		//text: item text > nên dùng
		//nếu như truyền giá trị gì trong TC thì sẽ dùng giá trị đó verify lại sau khi script bị fail
		//Trường hợp thêm sửa xóa trong dropdown thì ko bị fail
		select.selectByContainsVisibleText("10");
		select.selectByVisibleText("10");    //sử dụng nhiều nhất 
		
		//bỏ chọn> sử dụng với dropdown cho chọn nhiều 
		//với dropdown chọn 1 thì không cần dùng
		select.deselectByIndex(0);
		select.deselectByValue("");
		select.deselectByVisibleText("");
		
		select.deselectAll();
		//bỏ chọn tất cả
		
		select.getAllSelectedOptions();
		//kiểm tra tất cả các dữ liệu đã chọn
		
		select.isMultiple();       //sử dụng nhiều
		//kiểm tra xem 1 dropdown được chọn nhiều hay ko
			
		
		//Kiểm tra khi chọn  rồi thì có chọn đúng cái đã chọn ko
		//gettext ra xem đúng ko
		select.getFirstSelectedOption();      //sử dụng nhiều
		
		select.getOptions();
		//kiểm tra 1 dropdown có bn item 
		
		

	
	
	}
	
	
	
	
}
