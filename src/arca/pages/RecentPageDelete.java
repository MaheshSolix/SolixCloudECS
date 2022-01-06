package arca.pages;

import static org.testng.Assert.assertEquals;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import arca.common.Navigation;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class RecentPageDelete extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(RecentPageDelete.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";

	public RecentPageDelete(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Recent Delete Files");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Recent Delete Files--------------------");
		captureScreenShot(ScreenShotsFilePath + "Recent_delete_files.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Recent Delete Files";
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

	

	
	//-------------------------------------------------------Mul File Delete-------------------------------------------------------

	

	public static void mul_file_delete_recent() throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			
			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			
			
			click("recent_page");
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
			Thread.sleep(3000);

	
	String all_files=getPageObject("all_selected_files").getText();
	log.info("------------------");
	log.info(all_files);
	log.info("------------------");
	click("first_file_infolder");
	getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
	Thread.sleep(1000);
	click("second_file_infolder");
	Thread.sleep(1000);
	getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
	Thread.sleep(1000);
	click("third_file_infolder");
	Thread.sleep(1000);
	getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
	Thread.sleep(1000);
	
	

	WebElement elementLocator1 = getPageObject("first_file_infolder");
	actions.contextClick(elementLocator1).perform();
	Thread.sleep(2000);
	click("file_move_to_trash");
	Thread.sleep(2000);
	
	WebDriverWait wait11 = new WebDriverWait(driver, 60);
	wait11.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
	Thread.sleep(2000);
	

	if(getPageObject("starred_toast").getText().contains("Successfully moved to trash"))
	{
	
		//---------------Mul Files Delete Cofirmation------------------
		click("toast_close");
		
		click("trash");
		
		WebDriverWait wait41 = new WebDriverWait(driver, 120);
		wait41.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
		
		
		String all_trash_files=getPageObject("all_selected_files").getText();
		log.info("------------------");
		log.info(all_trash_files);
		log.info("------------------");
		
		
		assertEquals(all_files,all_trash_files);
		if(all_files==all_trash_files);
		
		
		log.info("--------------------------------------------file Successfully moved to trash--------------------------------------------");
		Thread.sleep(2000);

		captureScreenShot(ScreenShotsFilePath + "files_moved_to_trash.png");
		Thread.sleep(2000);
		
		ATUReports.add("files moved to trash","files moved to trash",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		
	}


	else
	{

		log.error("Error in files moved to trash");	
		captureScreenShot(ScreenShotsFilePath + "files_moved_to_trash_failed_Error.png");
		Thread.sleep(2000);
		ATUReports.add("files moved to trash failed","files moved to trash",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
	}


		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in deleting files.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "files_delete_Error.png");
			ATUReports.add("Files delete failed","Files delete failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
