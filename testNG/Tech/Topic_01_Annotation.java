package Tech;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_01_Annotation {

	@Test
	public void Register() {
		System.out.println("Register");
	}

	@Test
	public void Login() {
		System.out.println("Login");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("after Method");
	}

//  @DataProvider
//  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
//    };
//  }
	@BeforeClass
	public void beforeClass() {
		System.out.println("before Class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("after Class");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("before Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("after Test");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before Suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("after Suite");
	}

	@BeforeGroups
	public void beforeGroup() {
		System.out.println("before Groups");
	}

	@AfterGroups
	public void afterGroup() {
		System.out.println("after Groups");
	}
}
