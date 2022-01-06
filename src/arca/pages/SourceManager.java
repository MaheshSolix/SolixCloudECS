package arca.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import arca.common.Navigation;
import arca.common.ReadExcel;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testrecorder.ATUTestRecorder;



public class SourceManager extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(SourceManager.class.getName());

	public SourceManager(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Source Manager");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Source Manager--------------------");
		captureScreenShot(ScreenShotsFilePath + "source_manager.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Source Manager";
	}

	/*
	public class signupData
	{

		private String email;
		private String first_name;
		private String last_name;
		private String password;

		public signupData(String email, String first_name, String last_name,String password) throws IOException 
			{
		setEmail(email);
		setFirst_name(first_name);
		setLast_name(last_name);
		setPassword(password);	
			}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getFirst_name() {
			return first_name;
		}


		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}


		public String getLast_name() {
			return last_name;
		}


		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}

	}


	 */



	//-------------------------------------------------------Add Source Manager-------------------------------------------------------

	public static void add_source() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Sheet4");
        String host_name = readExcel.readCell(0,1);
        String source_port = readExcel.readCell(1,1);        
        String source_user_name = readExcel.readCell(2,1);
        String source_password = readExcel.readCell(3,1);
        add_source_manager(host_name, source_port, source_user_name,source_password);
        //log.info("Run Individual personal signup");
        
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
       // return new PersonalSignUp(driver);
	}
	
	


	public static void add_source_manager(String sm_host_name, String sm_source_port, String sm_source_user_name, String sm_source_password) throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			getPageObject("profile_icon").click();
			log.info("Clicked on profile icon");
			Thread.sleep(2000);
			click("admin_console");
			log.info("Clicked on admin console");
			
			click("data_migration_link");
			log.info("Clicked on data_migration_link");
			Thread.sleep(2000);

			
			
			click("source_manager");
			click("add_source_manager");
			
			//Enter folder name with date

			DateFormat dateFormat = new SimpleDateFormat("HH:mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			String source_manager_name = "Source Manager "+dateFormatted;
			
		
			sendKeys("source_manager_name",source_manager_name);
			sendKeys("source_manager_description","Source Manager description "+dateFormatted);
			sendKeys("source_manager_tag","Source Manager tag");
			select("source_type","visibletext","FTP");
			sendKeys("ip_address",sm_host_name);
			//sendKeys("port",sm_source_port);
			sendKeys("source_user_name",sm_source_user_name);
			sendKeys("source_password",sm_source_password);
			
			click("test_connection_button");
			
			WebDriverWait wait = new WebDriverWait (driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert connectionTrue alert-success']")));
			Thread.sleep(2000);

			click("add_source_manager_button");
			log.info("Clicked on add source manager button");
			Thread.sleep(3000);
			
			String source_table_first_name = getPageObject("source_table_first_name").getText();
			log.info(source_table_first_name);
			
				if(source_table_first_name.contains(source_manager_name))
				{
					log.info("Source added successfull");
					Thread.sleep(2000);
					ATUReports.add("Source added successfull","Add Source",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("Add Source failed");
					ATUReports.add("Add Source failed","Add Source",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}

		}


		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in add retention.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "add_retention_Error.png");
			ATUReports.add("add retention failed","add retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}
	
	


	public static void captureScreenShot(String filePath) {
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
			log.info("Generating Screenshot at : " + filePath);
		} catch (IOException e) {

			log.info("Generating error in captureScreenshot method");
			e.printStackTrace();

		}
	}





}
