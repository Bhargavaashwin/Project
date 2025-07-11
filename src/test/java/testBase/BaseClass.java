package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;    //Log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
	//Loading config.properties file
	FileReader file = new FileReader("./src//test//resources//config.properties");
	p = new Properties();
	p.load(file);
	
	
		logger = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{ 
		case "chrome" : driver = new ChromeDriver();  break;
		case "firefox" : driver = new FirefoxDriver();  break;
		case "edge" : driver = new EdgeDriver(); break;
		default : System.out.println("Invalid browser name..");   return;
		}
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));  //Reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() 
	{
	driver.quit();	
	}
	
	public String randomString() 
	{
		 return UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", "").substring(0, 5);
	}
	public String randomNumber()
	 {
		 return String.valueOf((int) (Math.random() *9000) + 10000);
				 
	 }
	 public String randomAlphaNumeric()
	 {
		 return UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "").substring(0, 6);
				 
	 }
	 public String captureScreen(String tname) throws IOException {

			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
				
			return targetFilePath;

		}

}
