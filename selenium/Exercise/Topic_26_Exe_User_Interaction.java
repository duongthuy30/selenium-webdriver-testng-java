package Exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Exe_User_Interaction {
	WebDriver driver;
	Actions action;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String autoITFirefox = projectPath + "\\autoITAuthentication\\authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://demoqa.com/tool-tips");
		sleepInSecond(3);

		WebElement toolTipButton = driver.findElement(By.cssSelector("button#toolTipButton"));

		action.moveToElement(toolTipButton).perform();

		WebElement toolTipText = driver.findElement(By.xpath("//div[text()='You hovered over the Button']"));

		Assert.assertEquals(toolTipText.getText(), "You hovered over the Button");

	}

	@Test
	public void TC_02_Hover() {
		driver.get("https://www.practiceqaautomation.com/selenium");
		WebElement mainItem2 = driver.findElement(By.xpath("//button[text()='Apps ']"));
		action.moveToElement(mainItem2).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//span[text()='Nova Shop']")))
				.perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h1//span[text()='Nova Shop']")).isDisplayed());

	}

	@Test
	public void TC_03_Hover() {
		driver.get("");

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
