package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Setup_Evn {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://live.techpanda.org/index.php/customer/account/login");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		//Login Page Url matching
		String loginPageUrl= driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://live.techpanda.org/index.php/customer/account/login");
		driver.findElement(By.linkText("SEARCH TERMS"));
	}
     //HTML của element Email textbox
	//<input type="email" autocapitalize="off" autocorrect="off" spellcheck="false" name="login[username]" value="" id="email" class="input-text required-entry validate-email" title="Email Address">
	
	//HTML của element
	//input - tagname
	//attribute name - type autocapitalize autocorrect spellcheck name value id class title
	//attribute value - email off
	
	//Xpath
	//tagname[@attribute-name= 'attribute-value']
	//input[@type='email']
	//input[@autocapitalize='off']
	
	
	//@Test
	//public void TC_02_ValidatePageTitle() {
		//Login page title
	//	String loginPageTitle= driver.getTitle();
	//	Assert.assertEquals(loginPageTitle, "Facebook");
	//}

	//@Test
	//public void TC_03_LoginFormDisplayed() {
		//Login page displayed
	//	Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
	//}

	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}


//<a href="http://live.techpanda.org/index.php/catalogsearch/term/popular/" title="Search Terms">Search Terms</a>





