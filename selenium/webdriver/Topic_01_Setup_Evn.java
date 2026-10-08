package webdriver;

import java.time.Duration;
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

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://live.techpanda.org/index.php/customer/account/login");

	}
	// @BeforeClass
	// public void beforeClass() {
	// if (osName.startsWith("Windows")) {
	// Windows
	// System.setProperty("webdriver.chrome.driver", projectPath +
	// "\\browserDrivers\\chromedriver.exe");
	// }else {
	// Mac
	// System.setProperty("webdriver.chrome.driver", projectPath +
	// "/browserDrivers/chromedriver");

	// driver = new FirefoxDriver();
	// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	// driver.manage().window().maximize();
	// driver.get("https://live.techpanda.org/index.php/customer/account/login");
	// }

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Login Page Url matching
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://live.techpanda.org/index.php/customer/account/login");
		driver.findElement(By.linkText("SEARCH TERMS"));
		// driver.findElement(By.xpath("//input[@title='Email Address']"));
		driver.findElement(By.cssSelector("input[title='Email Address']"));

		// driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.id("email"));

		driver.findElement(By.name("login[username]"));

		driver.findElement(By.tagName("a"));
		driver.findElement(By.className("level0"));
		driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']"));
		// nên viết như này a[title='Search Terms']
		driver.findElement(By.className("button"));
		driver.findElement(By.name("send"));
		driver.findElement(By.id("send2"));
		driver.findElement(By.cssSelector("button[type='submit']"));
		// <a
		// href="http://live.techpanda.org/index.php/customer/account/forgotpassword/"
		// class="f-left">Forgot Your Password?</a>
		// driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/customer/account/forgotpassword']"))
		// nhưng nên viết thế này
		driver.findElement(By.xpath("//a[text()='Forgot Your Password?']"));

		// driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s
		// happened?\")]"));
	}
	// HTML của element Email textbox
	// <input type="email" autocapitalize="off" autocorrect="off" spellcheck="false"
	// name="login[username]" value="" id="email" class="input-text required-entry
	// validate-email" title="Email Address">

	// <a href="http://live.techpanda.org/index.php/mobile.html" class="level0
	// ">Mobile</a>
	// tagname - a
	// attribute name: href class
	// attribute value:

	// <button type="submit" class="button" title="Login" name="send"
	// id="send2"><span><span>Login</span></span></button>

	// HTML của element
	// input - tagname
	// attribute name - type autocapitalize autocorrect spellcheck name value id
	// class title
	// attribute value - email off

	// Xpath format: //tagname[@attribute-name= 'attribute-value']

	// Relatie xpath format: ////tagname[@attribute-name=
	// 'attribute-value']////tagname[@attribute-name= 'attribute-value']
	// input[@type='email'] 1/2> ko dùng
	// input[@autocapitalize='off'] 1/2> ko dùng
	// input[@autocorrect='off'] 1/2> ko dùng
	// input[@spellcheck='false'] 1/2> ko dùng
	// input[@class='input-text required-entry validate-email'] 1/2> ko dùng
	// input[@name='login[username]'] 1/1 >dùng
	// input[@id='email'] 1/1 >dùng
	// input[@title='Email Address'] 1/1 >dùng

	// CSS format: tagname[attribute-name='attribute-value']

	// Có 2 điều kiện
	// Kết hợp and hoặc or giữa 2 điều kiện
	// ra kết quả true hoặc false
	// boolean firstCondition = true;
	// boolean secondCondition = false;
	// boolean result;

	// AND nếu 1 trong 2 đk mà sai > sai
	// chỉ khi nào cả 2 đều đúng> đúng
	// ĐK1 = true
	// ĐK2 = false
	// result = DK1 && DK 2 = false

	// OR nếu 1 trong 2 đk mà đúng > đúng
	// ĐK1 = true
	// ĐK2 = false
	// result = DK1 && DK 2 = true

	// AND: && còn OR ||

	// @Test
	// public void TC_02_ValidatePageTitle() {
	// Login page title
	// String loginPageTitle= driver.getTitle();
	// Assert.assertEquals(loginPageTitle, "Facebook");
	// }

	// @Test
	// public void TC_03_LoginFormDisplayed() {
	// Login page displayed
	// Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
	// }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

//<a href="http://live.techpanda.org/index.php/catalogsearch/term/popular/" title="Search Terms">Search Terms</a>
