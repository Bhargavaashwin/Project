package testCases;

//import java.time.Duration;
//import java.util.UUID;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC_001_AccountRegisterTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****** Starting TC_001 ******");
		
		try
		{
		 HomePage hp = new HomePage(driver);
		 hp.clickMyAccount();
		 logger.info("Clicked on MyAccount Link");
		 
		 hp.clickRegister();
		 logger.info("Clicked on Register link");
		   
		 AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		 
		 logger.info("Providing customer details..");
		 regpage.setFirstName(randomString().toUpperCase());
		 regpage.setLastName(randomString().toUpperCase());
		 regpage.setEmail(randomString()+"@gmail.com");
		 regpage.setTelephone(randomNumber());
		 
		String password = randomAlphaNumeric();
		 
		 regpage.setPassword(password);
		 regpage.setConfirmPassword(password);
		 
		 regpage.setPrivacyPolicy();
		 regpage.clickContinue();
		 
		 logger.info("Validating expected message..");
		 String confgmsg =regpage.getConfirmationMsg();
		 Assert.assertEquals(confgmsg, "Your Account Has Been Created!");
	}
	catch(Exception e)
		{
		logger.error("Test failed");
		logger.debug("Debug logs..");
		Assert.fail();
		}
		logger.info("****** Finished TC_001 ******");
}



}






