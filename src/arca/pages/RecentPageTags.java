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



public class RecentPageTags extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesTags.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";
	public RecentPageTags(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Tags");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Tags--------------------");
		captureScreenShot(ScreenShotsFilePath + "Tags.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Tags";
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



	//-------------------------------------------------------New code - Add Tags to multiple File-------------------------------------------------------


	public static void add_tag_multiple_files_recent()
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
			
			
			
			click("first_file_infolder");
			Thread.sleep(1000);
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
			
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			
			
			WebElement elementLocator1 = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
			String tag1 = "Tag1 "+dateFormatted;
			String tag2 = "Tag2 "+dateFormatted;
			
			sendKeys("add_tag_input_field",tag1);
			Thread.sleep(1000);
			sendKeys("add_tag_input_field",",");
			Thread.sleep(1000);
			sendKeys("add_tag_input_field",tag2);
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				Thread.sleep(2000);
				click("toast_close");
				
				
				WebElement elementLocator2 = getPageObject("first_file_infolder");
				actions.contextClick(elementLocator2).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				String added_first_tag = getPageObject("first_tag").getText();
				String added_second_tag = getPageObject("second_tag").getText();
				
				if((tag1==added_first_tag)&&(tag2==added_second_tag))
					
				Thread.sleep(1000);
				click("toast_close");
				
				click("goto_myfiles");
				
				click("recent_page");
				
				WebDriverWait wait1 = new WebDriverWait(driver, 60);
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
				Thread.sleep(3000);
				
				WebElement elementLocator3 = getPageObject("second_file_infolder");
				actions.contextClick(elementLocator3).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				
				if((tag1==added_first_tag)&&(tag2==added_second_tag))
					
				Thread.sleep(1000);
				click("toast_close");
				
				log.info("--------------------------------------------multiple folder tag saved successfully--------------------------------------------");
				Thread.sleep(3000);
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folder_tag_saved_success.png");
				Thread.sleep(2000);
				
				ATUReports.add("multiple folder tag saved successfull","multiple folder tag saved",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
			}


			else
			{

				log.error("Error in multiple folder add tag");	
				captureScreenShot(ScreenShotsFilePath + "multiple_folder_add_tag_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("multiple folder add tag failed","multiple folder add tag",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}



		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple folder add tag",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folder_add_tag_failed_Error.png");
			ATUReports.add("multiple folder add tag failed","multiple folder add tag",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	
	

	//-------------------------------------------------------New code - Remove Tags to multiple files-------------------------------------------------------


	public static void remove_tag_multiple_files_recent()
	{

		try
		{
			
			actions = new Actions(driver);
			
			/*
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
			*/
			

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
			
			

			
			
			
			WebElement elementLocator2 = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator2).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
			Thread.sleep(1000);
			
			String added_first_tag = getPageObject("first_tag").getText();
			String added_second_tag = getPageObject("second_tag").getText();
			
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE);
			
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait3 = new WebDriverWait(driver, 60);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(3000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				click("toast_close");
				
				WebElement elementLocator3 = getPageObject("first_file_infolder");
				actions.contextClick(elementLocator3).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				String updated_first_tag = getPageObject("first_tag").getText();
				String updated_second_tag = getPageObject("second_tag").getText();
				
				if((updated_first_tag==added_first_tag)&&(updated_second_tag!=added_second_tag))
					
				Thread.sleep(1000);
				click("toast_close");
				
				click("goto_myfiles");
				
				click("recent_page");
				
				WebDriverWait wait2 = new WebDriverWait(driver, 60);
				wait2.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
				Thread.sleep(3000);
				
				WebElement elementLocator4 = getPageObject("second_file_infolder");
				actions.contextClick(elementLocator4).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				
				if((updated_first_tag==added_first_tag)&&(updated_second_tag!=added_second_tag))
					
				Thread.sleep(1000);
				click("toast_close");
				
				log.info("--------------------------------------------multiple folder tag updated successfully--------------------------------------------");
				Thread.sleep(3000);
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folder_tag_updated_success.png");
				Thread.sleep(2000);
				
				ATUReports.add("multiple folder tag updated successfull","multiple folder tag updated",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				driver.navigate().refresh();
				
			}


			else
			{
				click("toast_close");
				log.error("Error in multiple folder update tag");	
				captureScreenShot(ScreenShotsFilePath + "multiple_folder_update_tag_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("multiple folder updated tag failed","multiple folder updated tag",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}



		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple folder update tag",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folder_update_tag_failed_Error.png");
			ATUReports.add("multiple folder update tag failed","multiple folder update tag",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			driver.navigate().refresh();
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
