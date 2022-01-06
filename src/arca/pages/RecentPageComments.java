package arca.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

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



public class RecentPageComments extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(RecentPageComments.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";

	public RecentPageComments(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Comments");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Comments--------------------");
		captureScreenShot(ScreenShotsFilePath + "Comments.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Comments";
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



	// -------------------------------------------------------Add Comments to file-------------------------------------------------------

	public static void add_comments_to_file_recent() throws Exception {

		try {
			actions = new Actions(driver);

			
			driver.get(Home_Page);
			
			sendKeys("login_username", "ravovov767@onzmail.com");
			sendKeys("login_password", "Test@123");
			click("login_button");
			log.info("login success");
			
			
			click("recent_page");
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
			Thread.sleep(3000);
			

			WebElement elementLocator = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("file_right_click_add_comment");

			DateFormat dateFormat = new SimpleDateFormat("MMM/dd HH:mm");

			// get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted = dateFormat.format(date);
			
			String text_box_text = "Recent File Comment - A user is any person or role on your team with a unique email address."
					+ dateFormatted;

			sendKeys("add_comment_textarea",text_box_text);
			
			Thread.sleep(2000);
			log.info("Text box text ------>" + text_box_text);
			click("tag_save_button");

			// Comment added confirmation

			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(3000);

			
			
			if(getPageObject("starred_toast").getText().contains("Successfully saved the comment"))
			{
				click("toast_close");
				WebElement elementLocator1 = getPageObject("first_file_infolder");
				actions.contextClick(elementLocator1).perform();
				Thread.sleep(2000);
				click("file_right_click_add_comment");
				
				String added_comment= getPageObject("folder_comment_text").getText();
				
				log.info("Added comment ------>" + added_comment);
				
				assertEquals(text_box_text,added_comment);

				if (text_box_text==added_comment)
				

				log.info("--------------------------------------------Comment added successfully--------------------------------------------");

				captureScreenShot(ScreenShotsFilePath + "file_comment_saved_success.png");
				Thread.sleep(2000);

				ATUReports.add("File comment saved successfull", "File comment saved", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {

				log.error("Error in File add comment");
				captureScreenShot(ScreenShotsFilePath + "File_add_comment_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("File add comment failed", "File add comment", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in adding comment to File.", e);
			// log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "add_comment_to_File_failed.png");
			ATUReports.add("Add comment to File failed", "Add comment to File", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	// -------------------------------------------------------Edit Comments to folder-------------------------------------------------------

	public static void edit_comments_to_file_recent() throws Exception {

		try {
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
			

			WebElement elementLocator = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_folder_comments");

			Thread.sleep(2000);
			*/
			
			
			String added_comment= getPageObject("folder_comment_text").getText();
			log.info("Added comment ------>" + added_comment);
			
			click("comment_3dots");
			Thread.sleep(2000);
			click("edit_comment");
			Thread.sleep(2000);
			
			
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@class='media-body pull-left']//button[2]")));
			
			
			
			getPageObject("edit_comment_textarea").clear();

			// Enter folder name with date

			DateFormat dateFormat = new SimpleDateFormat("MMM/dd HH:mm");

			// get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted = dateFormat.format(date);
			
			String text_box_text = "Recent Edited - File Comment - A user is any person or role on your team with a unique email address."
					+ dateFormatted;

			sendKeys("edit_comment_textarea",text_box_text);
			

			click("edit_comment_save_button");
			Thread.sleep(2000);
			
			/*
			// Edit Comment confirmation - commented code due to no toast message
			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@class='media-body pull-left']//button[2]")));
			*/
			
			
			
			WebDriverWait wait22 = new WebDriverWait(driver, 60);
			wait22.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			
			if(getPageObject("starred_toast").getText().contains("Successfully edited the comment"))
			{
				click("toast_close");
				
				log.info("Edited comment ------>" + text_box_text);
				
				assertNotEquals(text_box_text,added_comment);
			
				if (text_box_text!=added_comment)
			

				log.info(
						"--------------------------------------------Comment edited successfully--------------------------------------------");
				Thread.sleep(2000);

				captureScreenShot(ScreenShotsFilePath + "file_comment_edited_success.png");
				Thread.sleep(2000);

				ATUReports.add("File comment edited successfull", "File comment edited", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {

				log.error("Error in File edit comment");
				captureScreenShot(ScreenShotsFilePath + "file_edit_comment_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("File edited comment failed", "File edited comment", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in editing comments to File.", e);
			// log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "edit_comment_to_file_failed.png");
			ATUReports.add("Edit comment to File failed", "Edit comment to File", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	// -------------------------------------------------------Delete Comments to folder-------------------------------------------------------

	public static void delete_comments_to_file_recent() throws Exception {

		try {

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
			
			
			
			
			WebElement elementLocator = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_folder_comments");
			*/
			Thread.sleep(2000);
			String first_folder_comment_text_before_delete = getPageObject("folder_comment_text").getText();

			Thread.sleep(2000);
			click("comment_3dots");
			Thread.sleep(2000);
			click("delete_comment");
			Thread.sleep(3000);

			/*
			WebDriverWait wait3 = new WebDriverWait(driver, 60);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/button[1]/img[1]")));
			*/
			String first_folder_comment_text_after_delete = getPageObject("folder_comment_text").getText();
			
			
			WebDriverWait wait22 = new WebDriverWait(driver, 60);
			wait22.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			
			if(getPageObject("starred_toast").getText().contains("Successfully removed the comment"))
			{
			
				click("toast_close");
				
				assertNotEquals(first_folder_comment_text_before_delete,first_folder_comment_text_after_delete);
		
				if (first_folder_comment_text_before_delete != first_folder_comment_text_after_delete) 
				
				
				log.info("--------------------------------------------Comment deleted successfully--------------------------------------------");
				Thread.sleep(2000);

				captureScreenShot(ScreenShotsFilePath + "file_comment_deleted_success.png");
				Thread.sleep(2000);

				ATUReports.add("File comment deleted successfull", "File comment deleted", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {
				click("toast_close");
				log.error("Error in File deleted comment");
				captureScreenShot(ScreenShotsFilePath + "file_deleted_comment_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("File deleted comment failed", "File deleted comment", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in deleting comments to File.", e);
			// log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "delete_comment_to_file_failed.png");
			ATUReports.add("deleted comment to File failed", "deleted comment to File", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
