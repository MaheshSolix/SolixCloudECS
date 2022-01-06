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



public class MyFilesStarred extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesStarred.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";
	public MyFilesStarred(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("ARCA Right Click Options");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Right Click Options--------------------");
		captureScreenShot(ScreenShotsFilePath + "Right_click_options.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Right Click Options";
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



	//-------------------------------------------------------Right Click Options - Add to starred Multiple folders-------------------------------------------------------


	public static void multiple_folders_add_to_starred()
	{

		try
		{
			
			
			//Single file starred
			/*
			WebElement elementLocator = getPageObject("first_file");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_starred");
			Thread.sleep(2000);
			click("first_file");
			
			//Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if(getPageObject("starred_toast").getText().contains("Successfully starred"))
			{
			
				log.info("--------------------------------------------file starred successfully--------------------------------------------");
				Thread.sleep(2000);
				click("toast_close");
				captureScreenShot(ScreenShotsFilePath + "single_file_starred_success.png");
				Thread.sleep(2000);
				ATUReports.add("Single file starred successfull","Single file starred",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
			}


			else
			{

				log.error("Error in single file starred");	
				captureScreenShot(ScreenShotsFilePath + "single_file_starred_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("single file starred failed","single file starred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}

			*/
			
			actions = new Actions(driver);
			/*
			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			Thread.sleep(2000);
			*/
			
			String starred_folders=getPageObject("folder_names").getText();
			log.info("------------------");
			log.info(starred_folders);
			log.info("------------------");
			click("first_folder");
			driver.findElement(By.xpath("//*[@id='folder.1004108']/div/div/article/div/input")).sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("second_folder");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='folder.1004109']/div/div/article/div/input")).sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_folder");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='folder.1004110']/div/div/article/div/input")).sendKeys(Keys.SPACE);
			Thread.sleep(2000);
			WebElement elementLocator = getPageObject("first_folder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_folder_starred");


			// Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if (getPageObject("starred_toast").getText().contains("Successfully starred")) {
				
				Thread.sleep(2000);
				click("toast_close");
				
				click("goto_starred");
				WebDriverWait wait1 = new WebDriverWait(driver, 60);
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
				String starred_page_folders=getPageObject("starred_folder_names").getText();
				log.info("------------------");
				log.info(starred_page_folders);
				log.info("------------------");
				if(starred_page_folders.contains(starred_folders));
				
				log.info(
						"--------------------------------------------Multiple folders starred successfully--------------------------------------------");
				
				captureScreenShot(ScreenShotsFilePath + "single_file_starred_success.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple folders starred successfull", "Multiple folders starred", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}

			else {

				log.error("Error in Multiple folders starred");
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_starred_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple folders starred failed", "Multiple folders starred", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in Multiple folders add to starred",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folders_add_to_starred_failed_Error.png");
			ATUReports.add("Multiple folders add to starred failed","Multiple folders add to starred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	
	
	//-------------------------------------------------------Folder Right Click Options - un-starred-------------------------------------------------------


	public static void multiple_folders_un_starred()
	{

		try
		{
			/*
			
			
			WebElement elementLocator1 = getPageObject("first_file");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("right_click_starred");
			
			
			//Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			
			Thread.sleep(2000);
			if(getPageObject("starred_toast").getText().contains("Successfully un-starred"))
			{
			
				log.info("--------------------------------------------file un-starred successfully--------------------------------------------");
				Thread.sleep(2000);
				click("toast_close");
				captureScreenShot(ScreenShotsFilePath + "single_file_un-starred_success.png");
				Thread.sleep(2000);
				ATUReports.add("Single file un-starred successfull","Single file un-starred",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
			}


			else
			{

				log.error("Error in single file un-starred");	
				captureScreenShot(ScreenShotsFilePath + "single_file_un-starred_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("single file un-starred failed","single file un-starred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
			*/
			
			actions = new Actions(driver);
			
			click("goto_myfiles");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			Thread.sleep(2000);
			
			String starred_folders=getPageObject("folder_names").getText();
			log.info("------------------");
			log.info(starred_folders);
			log.info("------------------");
			
			
			
			click("first_folder");
			driver.findElement(By.xpath("//*[@id='folder.1004108']/div/div/article/div/input")).sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("second_folder");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='folder.1004109']/div/div/article/div/input")).sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_folder");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='folder.1004110']/div/div/article/div/input")).sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			WebElement elementLocator = getPageObject("first_folder");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(1000);
			click("right_click_folder_starred");
			
			
			Thread.sleep(2000);
			if(getPageObject("starred_toast").getText().contains("Successfully un-starred"))
			{
			
				Thread.sleep(2000);
				click("toast_close");
				
				
				click("goto_starred");
				WebDriverWait wait1 = new WebDriverWait(driver, 60);
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(text(),'No starred files or folders')]")));
			
				log.info("--------------------------------------------Multiple folders un-starred successfully--------------------------------------------");
				
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_un-starred_success.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple folders un-starred successfull","Multiple folders un-starred",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
			}


			else
			{

				log.error("Error in Multiple folders un-starred");	
				captureScreenShot(ScreenShotsFilePath + "multiple_folders_un-starred_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple folders un-starred failed","Multiple folders un-starred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}


		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in Multiple folders add to starred",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folders_add_to_starred_failed_Error.png");
			ATUReports.add("Multiple folders add to starred failed","Multiple folders add to starred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	
	
	
	


	//-------------------------------------------------------Right Click Options - Add to starred Multiple files-------------------------------------------------------


	public static void multiple_files_add_to_starred()
	{

		try
		{
			
			
			click("goto_myfiles");
			actions = new Actions(driver);
			
			/*
			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			Thread.sleep(2000);
			*/
			

			String starred_file_names=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(starred_file_names);
			log.info("------------------");
			click("first_file");
			getPageObject("first_file_check_box").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("second_file");
			Thread.sleep(1000);
			getPageObject("second_file_check_box").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_file");
			Thread.sleep(1000);
			getPageObject("third_file_check_box").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			WebElement elementLocator = getPageObject("first_file");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(1000);
			click("right_click_folder_starred");


			// Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if (getPageObject("starred_toast").getText().contains("Successfully starred")) {
				
				Thread.sleep(2000);
				click("toast_close");
				
				click("goto_starred");
				WebDriverWait wait1 = new WebDriverWait(driver, 60);
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
				String starred_page_files=getPageObject("starred_file_names").getText();
				log.info("------------------");
				log.info(starred_page_files);
				log.info("------------------");
				if(starred_page_files.contains(starred_file_names));
				
				log.info(
						"--------------------------------------------Multiple files starred successfully--------------------------------------------");
				
				captureScreenShot(ScreenShotsFilePath + "multiple_file_starred_success.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple files starred successfull", "Multiple files starred", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {

				log.error("Error in Multiple files starred");
				captureScreenShot(ScreenShotsFilePath + "multiple_files_starred_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple files starred failed", "Multiple files starred", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in Multiple files add to starred",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_files_add_to_starred_failed_Error.png");
			ATUReports.add("Multiple files add to starred failed","Multiple files add to starred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	
	


	//-------------------------------------------------------Right Click Options - un starred Multiple files-------------------------------------------------------


	public static void multiple_files_un_starred()
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
			Thread.sleep(2000);
			*/
			
			click("goto_myfiles");
			String starred_file_names=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(starred_file_names);
			log.info("------------------");
			
			
			click("first_file");
			getPageObject("first_file_check_box").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("second_file");
			Thread.sleep(1000);
			getPageObject("second_file_check_box").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			click("third_file");
			Thread.sleep(1000);
			getPageObject("third_file_check_box").sendKeys(Keys.SPACE);
			Thread.sleep(1000);
			
			
			WebElement elementLocator = getPageObject("first_file");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(1000);
			click("right_click_folder_starred");


			// Starred confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if (getPageObject("starred_toast").getText().contains("Successfully un-starred")) {
				
				Thread.sleep(2000);
				click("toast_close");
				
				click("goto_starred");
				WebDriverWait wait1 = new WebDriverWait(driver, 60);
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(text(),'No starred files or folders')]")));
				
				
				
				log.info(
						"--------------------------------------------Multiple files un-starred successfully--------------------------------------------");
				
				captureScreenShot(ScreenShotsFilePath + "multiple_file_unstarred_success.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple files unstarred successfull", "Multiple files unstarred", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {

				log.error("Error in Multiple files unstarred");
				captureScreenShot(ScreenShotsFilePath + "multiple_files_unstarred_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("Multiple files unstarred failed", "Multiple files unstarred", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in Multiple files unstarred",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_files_unstarred_failed_Error.png");
			ATUReports.add("Multiple files unstarred failed","Multiple files unstarred",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
