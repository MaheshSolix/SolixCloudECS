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



public class MyFilesTags extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesTags.class.getName());
	public static String Home_Page = "https://app.solixecsdev.com/login";
	public MyFilesTags(WebDriver driver) {
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

	//-------------------------------------------------------New code - Add Tags to multiple Folders-------------------------------------------------------


	public static void add_tag_multiple_folders()
	{

		try
		{
			
			actions = new Actions(driver);
			
			/*
			click("toast_close");
			Thread.sleep(1000);
			click("recent_page");
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			*/
			
			driver.get(Home_Page);

			sendKeys("login_username", "ravovov767@onzmail.com");

			click("login_next");

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));

			sendKeys("login_password", "Test@123");

			click("login_button");
			log.info("login success");
			
			
			String tag_folders=getPageObject("folder_names").getText();
			log.info("------------------");
			log.info(tag_folders);
			log.info("------------------");
			
			
			
			click("first_folder_infolder");
			Thread.sleep(2000);
			JsClick("selected_folder_checkbox");
			click("second_folder_infolder");
			Thread.sleep(2000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(2000);
			click("third_folder_infolder");
			Thread.sleep(2000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(2000);
			
			
			
			
			/*
			click("first_folder_infolder");
			Thread.sleep(3000);
			getPageObject("selected_folder_checkbox").click();
			Thread.sleep(1000);
			click("second_folder_infolder");
			Thread.sleep(3000);
			getPageObject("selected_folder_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_folder_infolder");
			Thread.sleep(1000);
			getPageObject("selected_folder_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			*/
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			
			
			WebElement elementLocator = getPageObject("first_folder_infolder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
			sendKeys("add_tag_input_field","Tag1 "+dateFormatted);
			Thread.sleep(1000);
			sendKeys("add_tag_input_field",",");
			Thread.sleep(1000);
			sendKeys("add_tag_input_field","Tag2 "+dateFormatted);
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait111 = new WebDriverWait(driver, 60);
			wait111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				Thread.sleep(2000);
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
	
	
	
	//-------------------------------------------------------New code - Remove Tags to multiple Folders-------------------------------------------------------


	public static void remove_tag_multiple_folders()
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
			*/
			
			
			Thread.sleep(1000);
			click("recent_page");
			Thread.sleep(2000);
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			
			
			
			
			String tag_folders=getPageObject("folder_names").getText();
			log.info("------------------");
			log.info(tag_folders);
			log.info("------------------");
			
			
			
			
			
			WebElement elementLocator = getPageObject("first_folder_infolder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
			Thread.sleep(1000);
			
			String added_tags = getPageObject("added_tags").getText();
			log.info("Added tags ----->"+added_tags);
			
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE);
			
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(3000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				click("toast_close");
				
				WebElement elementLocator1 = getPageObject("first_folder_infolder");
				actions.contextClick(elementLocator1).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				WebDriverWait wait2 = new WebDriverWait(driver, 60);
				wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
				Thread.sleep(3000);
				
				String updated_tags = getPageObject("added_tags").getText();
				
				log.info("Updated tags ----->"+updated_tags);
				
				if(!(updated_tags==added_tags))
				click("toast_close");
				log.info("--------------------------------------------multiple folder tag updated successfully--------------------------------------------");
				Thread.sleep(3000);
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folder_tag_updated_success.png");
				Thread.sleep(2000);
				
				ATUReports.add("multiple folder tag updated successfull","multiple folder tag updated",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
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
			
		}

	}
	
	

	//-------------------------------------------------------New code - Add Tags to multiple File-------------------------------------------------------


	public static void add_tag_multiple_files()
	{

		try
		{
			
			actions = new Actions(driver);
			
		
			driver.get(Home_Page);

			sendKeys("login_username", "ravovov767@onzmail.com");

			click("login_next");

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));

			sendKeys("login_password", "Test@123");

			click("login_button");
			log.info("login success");
			
			
			/*
			Thread.sleep(1000);
			click("recent_page");
			Thread.sleep(2000);
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			*/
			
			String tag_files=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(tag_files);
			log.info("------------------");
			
			
			click("first_file");
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("second_file");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("third_file");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(2000);
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			
			
			WebElement elementLocator1 = getPageObject("first_file");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
			sendKeys("add_tag_input_field","Tag1 "+dateFormatted);
			Thread.sleep(1000);
			sendKeys("add_tag_input_field",",");
			Thread.sleep(1000);
			sendKeys("add_tag_input_field","Tag2 "+dateFormatted);
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait108 = new WebDriverWait(driver, 60);
			wait108.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				Thread.sleep(2000);
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


	public static void remove_tag_multiple_files()
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
			*/
			
			
			String tag_files=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(tag_files);
			log.info("------------------");
			
			
			
			Thread.sleep(1000);
			click("recent_page");
			Thread.sleep(2000);
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			
			

			
			
			
			WebElement elementLocator2 = getPageObject("first_file");
			actions.contextClick(elementLocator2).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
			Thread.sleep(1000);
			
			String added_tags = getPageObject("added_tags").getText();
			log.info("Added tags ----->"+added_tags);
			
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE);
			
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(3000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				click("toast_close");
				Thread.sleep(2000);
				WebElement elementLocator1 = getPageObject("first_file");
				actions.contextClick(elementLocator1).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				WebDriverWait wait2 = new WebDriverWait(driver, 60);
				wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
				Thread.sleep(3000);
				
				String updated_tags = getPageObject("added_tags").getText();
				click("toast_close");
				log.info("Updated tags ----->"+updated_tags);
				
				if(!(updated_tags==added_tags))
				
				log.info("--------------------------------------------multiple folder tag updated successfully--------------------------------------------");
				Thread.sleep(3000);
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folder_tag_updated_success.png");
				Thread.sleep(2000);
				
				ATUReports.add("multiple folder tag updated successfull","multiple folder tag updated",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			
				
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
			
		}

	}
	
	
	
	
	//------------------------------------------------------Add Tags to multiple folders and files-------------------------------------------------------

	public static void add_tag_multiple_folders_files()
	{

		try
		{
			
			actions = new Actions(driver);
			
			Thread.sleep(1000);
			click("recent_page");
			Thread.sleep(2000);
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			
			
			/*
			driver.get(Home_Page);

			sendKeys("login_username", "ravovov767@onzmail.com");

			click("login_next");

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));

			sendKeys("login_password", "Test@123");

			click("login_button");
			log.info("login success");
			*/
			
			
			String tag_folders=getPageObject("folder_names").getText();
			log.info("------------------");
			log.info(tag_folders);
			log.info("------------------");
			
			
			String tag_files=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(tag_files);
			log.info("------------------");
			

			click("first_folder_infolder");
			Thread.sleep(2000);
			JsClick("selected_folder_checkbox");
			click("second_folder_infolder");
			Thread.sleep(2000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(2000);
			click("third_folder_infolder");
			Thread.sleep(2000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(2000);
			
			
			
			click("first_file");
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("second_file");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("third_file");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(2000);
			
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			
			
			WebElement elementLocator = getPageObject("first_folder_infolder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
			sendKeys("add_tag_input_field","Tag1 "+dateFormatted);
			Thread.sleep(1000);
			sendKeys("add_tag_input_field",",");
			Thread.sleep(1000);
			sendKeys("add_tag_input_field","Tag2 "+dateFormatted);
			
			
			String tag1 = "Tag1 "+dateFormatted;
			String tag2 = "Tag2 "+dateFormatted;
			
			
			String added_tags = tag1+tag2;
			log.info("Added tags ----->"+added_tags);
			
			
			click("tag_save_button");
			
		
			
			
			//Starred confirm
			WebDriverWait wait104 = new WebDriverWait(driver, 60);
			wait104.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				Thread.sleep(2000);
				click("toast_close");
				Thread.sleep(2000);

				
				click("recent_page");
				click("goto_myfiles");
				WebDriverWait wait50 = new WebDriverWait(driver, 120);
				wait50.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
				
				
				WebElement elementLocator1 = getPageObject("first_folder_infolder");
				actions.contextClick(elementLocator1).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				
				
				WebDriverWait wait2 = new WebDriverWait(driver, 60);
				wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
				Thread.sleep(3000);
				
				String updated_tags = getPageObject("added_tags").getText();
				click("toast_close");
				log.info("Updated tags ----->"+updated_tags);
				
				if((updated_tags==added_tags))
				
				
					
				click("toast_close");
				
				click("recent_page");
				click("goto_myfiles");

				WebElement elementLocator2 = getPageObject("first_file");
				actions.contextClick(elementLocator2).perform();
				Thread.sleep(3000);
				click("right_click_add_tag");
				
				

				WebDriverWait wait3 = new WebDriverWait(driver, 60);
				wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
				Thread.sleep(3000);
				
				String updated_tags2 = getPageObject("added_tags").getText();
				click("toast_close");
				log.info("Updated tags ----->"+updated_tags2);
				
				if((updated_tags2==added_tags))
				
				
				log.info("--------------------------------------------multiple folder & files tag saved successfully--------------------------------------------");
				Thread.sleep(3000);
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_files_tag_saved_success.png");
				Thread.sleep(2000);
				
				ATUReports.add("multiple folder & files tag saved successfull","multiple folder & files tags saved",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
			}


			else
			{

				log.error("Error in multiple folder & files add tags");	
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_files_add_tags_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("multiple folder & files add tags failed","multiple folder & files add tags",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}



		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple folder & files add tags",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folders_files_add_tags_failed_Error.png");
			ATUReports.add("multiple folder & files add tags failed","multiple folder & files add tags",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	

	
	
	/*

	//-------------------------------------------------------Remove Tags to multiple Folders and Files-------------------------------------------------------


	public static void remove_tag_multiple_folders_files()
	{

		try
		{
			
			actions = new Actions(driver);
			
			
			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			
			
			
			String tag_folders=getPageObject("folder_names").getText();
			log.info("------------------");
			log.info(tag_folders);
			log.info("------------------");
			
			
			String tag_files=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(tag_files);
			log.info("------------------");
			

			click("first_folder_infolder");
			getPageObject("selected_folder_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("second_folder_infolder");
			Thread.sleep(1000);
			getPageObject("selected_folder_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_folder_infolder");
			Thread.sleep(1000);
			getPageObject("selected_folder_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			
			
			click("first_file");
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("second_file");
			Thread.sleep(1000);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_file");
			Thread.sleep(1000);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(2000);
			

			
			
			
			WebElement elementLocator = getPageObject("first_folder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_add_tag");
			Thread.sleep(2000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
			Thread.sleep(1000);
			
			String added_tags = getPageObject("added_tags").getText();
			log.info("Added tags ----->"+added_tags);
			
			getPageObject("add_tag_input_field").sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE);
			
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(3000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully updated the tags"))
			{
			
				click("toast_close");
				
				WebElement elementLocator1 = getPageObject("first_folder");
				actions.contextClick(elementLocator1).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				WebDriverWait wait2 = new WebDriverWait(driver, 60);
				wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
				Thread.sleep(3000);
				
				String updated_tags = getPageObject("added_tags").getText();
				Thread.sleep(2000);
				log.info("Updated tags ----->"+updated_tags);
				
				if(!(updated_tags==added_tags))
				click("toast_close");
				
				
				driver.navigate().refresh();
				

				
				WebElement elementLocator2 = getPageObject("first_file");
				actions.contextClick(elementLocator2).perform();
				Thread.sleep(2000);
				click("right_click_add_tag");
				
				WebDriverWait wait3 = new WebDriverWait(driver, 60);
				wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ti-tag ti-valid']")));
				Thread.sleep(3000);
				
				String updated_tags2 = getPageObject("added_tags").getText();
				Thread.sleep(2000);
				log.info("Updated tags ----->"+updated_tags);
				
				if(!(updated_tags2==added_tags))
				click("toast_close");
				
				
				
				log.info("--------------------------------------------multiple folder & files tags updated successfully--------------------------------------------");
				Thread.sleep(3000);
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_files_tags_updated_success.png");
				Thread.sleep(2000);
				
				ATUReports.add("multiple folder & files tags updated successfull","multiple folder & files tags updated",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				driver.navigate().refresh();
				
			}


			else
			{
				click("toast_close");
				log.error("Error in multiple folder & files update tags");	
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_files_update_tags_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("multiple folder & files updated tags failed","multiple folder & files update tags",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}



		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple folder & files update tags",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folders_files_update_tags_failed_Error.png");
			ATUReports.add("multiple folder & files update tags failed","multiple folder & files update tags",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			driver.navigate().refresh();
		}

	}
		
	
	
	
	
	//-------------------------------------------------------Old code - Add Tags to Folders-------------------------------------------------------

		public static void add_tags_to_folders() throws Exception
		{

			try
			{
				actions = new Actions(driver);

				WebElement folder_container = driver.findElement(By.xpath("//div[@class='row foldersSection']"));
				List<WebElement> each_folder_tag=folder_container.findElements(By.tagName("div"));

				List<WebElement> all_folders_list = new ArrayList <> ();

				for(int i=0;i<each_folder_tag.size();i++)
				{
				
				
					WebElement div =each_folder_tag.get(i);
					String selected_folder_name = each_folder_tag.get(i).getText();
					log.info("Selected file name is "+selected_folder_name);
					actions.contextClick(div).perform();
					Thread.sleep(2000);
					click("right_click_tags");
					log.info("clicked on tags");
					break;
				}
									
					//Keyword 1
					getPageObject("tags_text_field").sendKeys("files");
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_ENTER);
					
					//Keyword 2
					getPageObject("tags_text_field").sendKeys("folders");
					robot.keyPress(KeyEvent.VK_ENTER);
					
					//Keyword 3
					getPageObject("tags_text_field").sendKeys("retention");
					robot.keyPress(KeyEvent.VK_ENTER);
					
					//Keyword 4
					getPageObject("tags_text_field").sendKeys("Legal Hold");
					robot.keyPress(KeyEvent.VK_ENTER);
					
					//Keyword 5
					getPageObject("tags_text_field").sendKeys("This is the long testing tag");
					robot.keyPress(KeyEvent.VK_ENTER);
					
					click("tag_save_button");
					getPageObject("tag_success_alert").isDisplayed();
					
					click("tags_close_button");
					log.info("Tags popup closed");
					
					click("details_icon");
					log.info("Clicked on information icon");
					
					
					
					for(int i=0;i<each_folder_tag.size();i++)
					{
						
					WebElement div =each_folder_tag.get(i);
					div.click();
					log.info("Selected, tags created folder");
					Thread.sleep(3000);
					break;
					}
					String details_created_tags_text = getPageObject("details_tags_text").getText();
					
					//String details_created_tags_text1 = getPageObject("details_tags_text").getText();
					log.info(details_created_tags_text);
					click("close_details_icon");
					if(details_created_tags_text.contains("retention"))
					{
						log.info("--------------------------------------------Tags added to the folder--------------------------------------------");
						captureScreenShot(ScreenShotsFilePath + "tags_added_to_the_folder.png");
						Thread.sleep(2000);
						ATUReports.add("Tags added to the folder successfull","Tags added to the folder",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					
					else
					{

						log.error("Error in adding tags to folder");	
						captureScreenShot(ScreenShotsFilePath + "add_tag_folder_failed.png");
						ATUReports.add("Add tags to folder failed","Add tags to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
					}
					

			}
			catch (Exception e) {

				System.out.println("errror at: ");
				e.printStackTrace();
				log.error("Error in adding tags to folder.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "add_tag_folder_failed.png");
				ATUReports.add("Add tags to folder failed","Add tags to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}
		*/

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
