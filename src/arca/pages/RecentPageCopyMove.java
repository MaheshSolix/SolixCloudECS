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



public class RecentPageCopyMove extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesTags.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";
	public RecentPageCopyMove(WebDriver driver) {
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



	//-------------------------------------------------------multiple File copy-------------------------------------------------------


	public static void recent_page_multiple_files_copy()
	{

		try
		{
			
			actions = new Actions(driver);
			

			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			
			WebElement elementLocator = getPageObject("home_allfiles");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_newfolder");
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String multiple_file_copy_folder_name="Recent file copy "+dateFormatted;
			sendKeys("folder_name_input_field",multiple_file_copy_folder_name);
			click("folder_ok_button");
			
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']"))).getText().contains(multiple_file_copy_folder_name);
			Thread.sleep(2000);
			
			click("recent_page");
			
			WebDriverWait wait5 = new WebDriverWait(driver, 60);
			wait5.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
			Thread.sleep(3000);
			
			
			
			
			click("first_file_infolder");
			Thread.sleep(1000);
			String first_file_name = getPageObject("first_file_infolder").getText();
			log.info("First file name----->"+first_file_name);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			
			
			
			click("second_file_infolder");
			Thread.sleep(1000);
			String second_file_name = getPageObject("second_file_infolder").getText();
			log.info("First file name----->"+second_file_name);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			
			
			click("third_file_infolder");
			Thread.sleep(1000);
			String third_file_name = getPageObject("third_file_infolder").getText();
			log.info("First file name----->"+third_file_name);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			

			WebElement elementLocator1 = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("file_right_click_copy_move");
			Thread.sleep(2000);
			
			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/static/img/Files/folder.png']")));
			Thread.sleep(2000);
			
			WebElement copy_move_popup_all_files_folders = getPageObject("copy_move_popup_all_files_folders");
			List<WebElement> each_file_folder=copy_move_popup_all_files_folders.findElements(By.tagName("div"));

			for(int i=0;i<each_file_folder.size();i++)
			{


				WebElement each_div =each_file_folder.get(i);
				String selected_file_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_file_folder_name.contains(multiple_file_copy_folder_name))
				{
					
					each_file_folder.get(i).click();
					Thread.sleep(2000);
					click("copy_button");
					
					WebDriverWait wait3 = new WebDriverWait(driver, 60);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
					
					if(getPageObject("starred_toast").getText().contains("Successfully copied"));
					Thread.sleep(2000);
					click("toast_close");
					log.info("Toast closed");

					Thread.sleep(2000);
					break;
					
					
				}
				
			}
			
			
			click("goto_myfiles");
			Thread.sleep(2000);
			
			WebDriverWait wait6 = new WebDriverWait(driver, 60);
			wait6.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
			Thread.sleep(3000);
			
			WebElement myfiles_all_files_folders = driver.findElement(By.xpath("//div[@class='folder']/div[@class='row']"));
			List<WebElement> myfiles_each_file_folder=myfiles_all_files_folders.findElements(By.tagName("div"));

			for(int i=0;i<myfiles_each_file_folder.size();i++)
			{


				WebElement each_div =myfiles_each_file_folder.get(i);
				String selected_file_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_file_folder_name.contains(multiple_file_copy_folder_name))
				{
					
					//each_file_folder.get(i).click();
					actions.doubleClick(myfiles_each_file_folder.get(i)).perform();
					Thread.sleep(2000);
				
					WebDriverWait wait3 = new WebDriverWait(driver, 60);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
					
					break;
					
					
				}
				
			}
			

			click("first_file_infolder");
			Thread.sleep(1000);
			String first_file_name_aftercopy = getPageObject("first_file_infolder").getText();
			log.info("First file name----->"+first_file_name_aftercopy);
			Thread.sleep(1000);
			
			
			
			click("second_file_infolder");
			Thread.sleep(1000);
			String second_file_name_aftercopy = getPageObject("second_file_infolder").getText();
			log.info("First file name----->"+second_file_name_aftercopy);
			Thread.sleep(1000);
			
			
			click("third_file_infolder");
			Thread.sleep(1000);
			String third_file_name_aftercopy = getPageObject("third_file_infolder").getText();
			log.info("First file name----->"+third_file_name_aftercopy);
			Thread.sleep(1000);
			

			
			if((first_file_name==first_file_name_aftercopy)&&(second_file_name==second_file_name_aftercopy)&&(third_file_name==third_file_name_aftercopy));
			
			{
			log.info("--------------------------------------------Multiple files are copied successfully--------------------------------------------");
			
			captureScreenShot(ScreenShotsFilePath + "multiple_files_copy_success.png");
			Thread.sleep(2000);
			ATUReports.add("Multiple files are copied successfully","Multiple files copy",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(2000);
		
			}
			
			

					
					


		}
		
		

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple files copy",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_files_copy_failed_Error.png");
			ATUReports.add("multiple files copy failed","multiple files copy",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	


	//-------------------------------------------------------multiple File copy-------------------------------------------------------


	public static void recent_page_multiple_files_move()
	{

		try
		{
			
			actions = new Actions(driver);
			

			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			
			WebElement elementLocator = getPageObject("home_allfiles");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_newfolder");
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String multiple_file_copy_folder_name="Recent file move "+dateFormatted;
			sendKeys("folder_name_input_field",multiple_file_copy_folder_name);
			click("folder_ok_button");
			
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']"))).getText().contains(multiple_file_copy_folder_name);
			Thread.sleep(2000);
			
			click("recent_page");
			
			WebDriverWait wait5 = new WebDriverWait(driver, 60);
			wait5.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
			Thread.sleep(3000);
			
			
			
			
			click("first_file_infolder");
			Thread.sleep(1000);
			String first_file_name = getPageObject("first_file_infolder").getText();
			log.info("First file name----->"+first_file_name);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			
			
			
			click("second_file_infolder");
			Thread.sleep(1000);
			String second_file_name = getPageObject("second_file_infolder").getText();
			log.info("First file name----->"+second_file_name);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			
			
			click("third_file_infolder");
			Thread.sleep(1000);
			String third_file_name = getPageObject("third_file_infolder").getText();
			log.info("First file name----->"+third_file_name);
			getPageObject("selected_file_checkbox").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			

			WebElement elementLocator1 = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("file_right_click_copy_move");
			Thread.sleep(2000);
			
			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/static/img/Files/folder.png']")));
			Thread.sleep(2000);
			
			WebElement copy_move_popup_all_files_folders = getPageObject("copy_move_popup_all_files_folders");
			List<WebElement> each_file_folder=copy_move_popup_all_files_folders.findElements(By.tagName("div"));

			for(int i=0;i<each_file_folder.size();i++)
			{


				WebElement each_div =each_file_folder.get(i);
				String selected_file_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_file_folder_name.contains(multiple_file_copy_folder_name))
				{
					
					each_file_folder.get(i).click();
					Thread.sleep(2000);
					click("move_button");
					
					WebDriverWait wait3 = new WebDriverWait(driver, 60);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
					
					//if(getPageObject("starred_toast").getText().contains("Successfully moved"));
					
					String toast_message = getPageObject("starred_toast").getText();
					Assert.assertTrue(toast_message.contains("Successfully moved"));
					
					Thread.sleep(2000);
					click("toast_close");
					log.info("Toast closed");

					Thread.sleep(2000);
					break;
					
					
				}
				
			}
			
			
			click("goto_myfiles");
			Thread.sleep(2000);
			
			WebDriverWait wait6 = new WebDriverWait(driver, 60);
			wait6.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
			Thread.sleep(3000);
			
			WebElement myfiles_all_files_folders = driver.findElement(By.xpath("//div[@class='folder']/div[@class='row']"));
			List<WebElement> myfiles_each_file_folder=myfiles_all_files_folders.findElements(By.tagName("div"));

			for(int i=0;i<myfiles_each_file_folder.size();i++)
			{


				WebElement each_div =myfiles_each_file_folder.get(i);
				String selected_file_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_file_folder_name.contains(multiple_file_copy_folder_name))
				{
					
					//each_file_folder.get(i).click();
					actions.doubleClick(myfiles_each_file_folder.get(i)).perform();
					Thread.sleep(2000);
				
					WebDriverWait wait3 = new WebDriverWait(driver, 60);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bc-nav']")));
					
					break;
					
					
				}
				
			}
			

			click("first_file_infolder");
			Thread.sleep(1000);
			String first_file_name_aftercopy = getPageObject("first_file_infolder").getText();
			log.info("First file name----->"+first_file_name_aftercopy);
			Thread.sleep(1000);
			
			
			
			click("second_file_infolder");
			Thread.sleep(1000);
			String second_file_name_aftercopy = getPageObject("second_file_infolder").getText();
			log.info("First file name----->"+second_file_name_aftercopy);
			Thread.sleep(1000);
			
			
			click("third_file_infolder");
			Thread.sleep(1000);
			String third_file_name_aftercopy = getPageObject("third_file_infolder").getText();
			log.info("First file name----->"+third_file_name_aftercopy);
			Thread.sleep(1000);
			

			
			if((first_file_name==first_file_name_aftercopy)&&(second_file_name==second_file_name_aftercopy)&&(third_file_name==third_file_name_aftercopy));
			
			{
			log.info("--------------------------------------------Multiple files are moved successfully--------------------------------------------");
			
			captureScreenShot(ScreenShotsFilePath + "multiple_files_moved_successfully.png");
			Thread.sleep(2000);
			ATUReports.add("Multiple files are moved successfully","Multiple files move",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(2000);
		
			}
			
			

					
					


		}
		
		

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple files move",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_files_move_failed_Error.png");
			ATUReports.add("multiple files move failed","multiple files move",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
