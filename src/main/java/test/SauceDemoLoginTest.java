package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.SauceDemoInventoryPage;
import Pages.SauceDemoLoginPage;
import util.ExcelUtil;
public class SauceDemoLoginTest {
SauceDemoLoginPage loginPage;
WebDriver driver;

	//Log4j configuration
	private static final Logger log = LogManager.getLogger(SauceDemoLoginTest.class);
	
	@Test
	public void loginTest(){
		
		log.info("Verifying successful login.");
		
		loginPage = new SauceDemoLoginPage(driver);
		
		//Should fetch the username and password from external test data files
		SauceDemoInventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
		String expectedProductLabel = "Products";
		String actualProductLabel = inventoryPage.getProductLabel();
		
		log.info("expectedProductLabel-" + expectedProductLabel + " and actualProductLabel - " + actualProductLabel);
		Assert.assertEquals(expectedProductLabel, actualProductLabel);
	}
	
	
	@DataProvider(name = "tempTestData")
	public Object[][] tempTestData() throws Exception {

		String[][] testData = ExcelUtil.getExcelDataIn2DArray("testData//loginTestData.xlsx", "loginSheet");
		return testData;
	}
	
	//Test to demo data provider functionality
	@Test(dataProvider = "tempTestData")
	public void tempTest(String userId, String pwd) {
		System.out.println(userId + " - " + pwd);
	}
}
