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



public class MyFilesCopy extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesCopy.class.getName());
	public static String Home_Page = "https://app.solixecsdev.com/login";
	public MyFilesCopy(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Copy");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Copy--------------------");
		captureScreenShot(ScreenShotsFilePath + "Copy.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Copy";
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


	public static void multiple_files_copy()
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
			click("recent_page");
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			*/
			
			WebElement elementLocator = getPageObject("home_allfiles");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_newfolder");
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String multiple_file_move_folder_name="Mul-file-copy "+dateFormatted;
			sendKeys("folder_name_input_field",multiple_file_move_folder_name);
			click("folder_ok_button");
			
			
			WebDriverWait wait111 = new WebDriverWait(driver, 60);
			wait111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']"))).getText().contains(multiple_file_move_folder_name);
			Thread.sleep(2000);
			
			click("toast_close");
			
			

			WebElement all_folders1 = getPageObject("folder_names");
			List<WebElement> each_folder1=all_folders1.findElements(By.tagName("div"));

			for(int i=0;i<each_folder1.size();i++)
			{


				WebElement each_div =each_folder1.get(i);
				String selected_folder_name = each_div.getText();
				//log.info("--------------------"+selected_folder_name);
				//if(selected_folder_name.contains("Add Mul files copy"))
				if(selected_folder_name.equals("Add Mul files copy"))
				{
					
					actions.doubleClick(each_folder1.get(i)).perform();
					Thread.sleep(2000);

					break;
					
					
				}
				
			}

			
			WebDriverWait wait4 = new WebDriverWait(driver, 120);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
			
			
			String all_files=getPageObject("all_selected_files").getText();
			log.info("------------------");
			log.info(all_files);
			log.info("------------------");
			click("file_one");
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("file_two");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("file_three");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(2000);
			
			
			

			WebElement elementLocator1 = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("file_right_click_copy_move");
			Thread.sleep(2000);
			
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cp-list-item'][1]")));
			Thread.sleep(2000);
			
			actions.doubleClick(getPageObject("copy_mydrive")).perform();
			
			
			WebDriverWait wait112 = new WebDriverWait(driver, 60);
			wait112.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/static/img/Files/folder.png']")));
			Thread.sleep(2000);
			
			WebElement copy_move_popup_all_files_folders = getPageObject("copy_move_popup_all_files_folders");
			List<WebElement> each_file_folder=copy_move_popup_all_files_folders.findElements(By.tagName("div"));

			for(int i=0;i<each_file_folder.size();i++)
			{


				WebElement each_div =each_file_folder.get(i);
				String selected_file_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_file_folder_name.equals(multiple_file_move_folder_name))
				{
					
					each_file_folder.get(i).click();
					
					//actions.doubleClick(each_file_folder.get(i)).perform();
					
					Thread.sleep(2000);
					click("copy_button");
					
					WebDriverWait wait2 = new WebDriverWait(driver, 60);
					wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
					
					

					Thread.sleep(8000);
					
					//if(getPageObject("starred_toast").getText().contains("Successfully moved"))
					
					String multiple_file_copy_toast = getPageObject("copy_toast").getText();
					
					log.info("----->"+multiple_file_copy_toast);
					
					String multiple_file_copy_toast_expected = "Successfully copied";
					
					log.info("----->"+multiple_file_copy_toast_expected);
					
					Thread.sleep(2000);
					click("toast_close");
					log.info("Toast closed");
					
					assertEquals(multiple_file_copy_toast,multiple_file_copy_toast_expected);
					
					
					
					break;
					
					
				}
				
			}
		

		
			
			
			Thread.sleep(2000);
			//click("goto_myfiles");
			driver.findElement(By.xpath("//div[@class='bc-nav']/ul[@class='nav']/li[1]")).click();
			//driver.get(Home_Page);
			WebDriverWait wait4111 = new WebDriverWait(driver, 120);
			wait4111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
			
			WebElement all_folders2 = getPageObject("folder_names");
			List<WebElement> each_folder2=all_folders2.findElements(By.tagName("div"));

			for(int i=0;i<each_folder2.size();i++)
			{


				WebElement each_div =each_folder2.get(i);
				String selected_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_folder_name.contains(multiple_file_move_folder_name))
				{
					
					actions.doubleClick(each_folder2.get(i)).perform();
					Thread.sleep(2000);

					break;
					
					
				}
				
			}
		
			
			WebDriverWait wait124 = new WebDriverWait(driver, 120);
			wait124.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
			
			
			
			String all_copied_files = getPageObject("all_copied_files").getText();
			log.info("Copied files are---------"+all_copied_files);
			log.info("All Selected files are---------"+all_files);
			assertEquals(all_copied_files,all_files);
			if(all_copied_files==all_files);
			
			{
			log.info("--------------------------------------------Multiple files are moved successfully--------------------------------------------");
			
			captureScreenShot(ScreenShotsFilePath + "multiple_files_moved_success.png");
			Thread.sleep(2000);
			ATUReports.add("Multiple files are moved successfully","Multiple files moved",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
	
	

	//-------------------------------------------------------multiple folders copy-------------------------------------------------------


	public static void multiple_folders_copy()
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
			click("recent_page");
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			*/
			
			
			WebElement elementLocator = getPageObject("home_allfiles");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_newfolder");
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String multiple_folder_move_folder_name="Mul-folders-copy "+dateFormatted;
			sendKeys("folder_name_input_field",multiple_folder_move_folder_name);
			click("folder_ok_button");
			
			
			WebDriverWait wait111 = new WebDriverWait(driver, 60);
			wait111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']"))).getText().contains(multiple_folder_move_folder_name);
			Thread.sleep(2000);
			
			click("toast_close");
			
		


			WebElement all_folders3 = getPageObject("folder_names");
			List<WebElement> each_folder3=all_folders3.findElements(By.tagName("div"));

			for(int i=0;i<each_folder3.size();i++)
			{


				WebElement each_div =each_folder3.get(i);
				String selected_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_folder_name.equals("Add Mul folders copy"))
				{
					
					actions.doubleClick(each_folder3.get(i)).perform();
					Thread.sleep(2000);

					break;
					
					
				}
				
			}

			
	
			WebDriverWait wait4 = new WebDriverWait(driver, 120);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']")));
			
			
			String all_folders=getPageObject("all_selected_folders").getText();
			log.info("------------------");
			log.info(all_folders);
			log.info("------------------");
			click("file_one");
			JsClick("selected_folder_checkbox");
			Thread.sleep(1000);
			click("file_two");
			Thread.sleep(1000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(1000);
			click("file_three");
			Thread.sleep(1000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(1000);
			
			

			WebElement elementLocator1 = getPageObject("file_one");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);
			click("file_right_click_copy_move");
			Thread.sleep(3000);
			
			
			
			actions.doubleClick(getPageObject("copy_mydrive")).perform();
			
			
			Thread.sleep(3000);
			
			WebElement copy_move_popup_all_files_folders = getPageObject("copy_move_popup_all_files_folders");
			List<WebElement> each_file_folder=copy_move_popup_all_files_folders.findElements(By.tagName("div"));

			for(int i=0;i<each_file_folder.size();i++)
			{


				WebElement each_div =each_file_folder.get(i);
				String selected_file_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_file_folder_name.equals(multiple_folder_move_folder_name))
				
				{
					
					each_file_folder.get(i).click();
					Thread.sleep(3000);
					click("copy_button");
					
					WebDriverWait wait2 = new WebDriverWait(driver, 60);
					wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));


					Thread.sleep(8000);
					
					//if(getPageObject("starred_toast").getText().contains("Successfully moved"))
					
					String multiple_file_copy_toast = getPageObject("copy_toast").getText();
					
					log.info("----->"+multiple_file_copy_toast);
					
					String multiple_file_copy_toast_expected = "Successfully copied";
					
					log.info("----->"+multiple_file_copy_toast_expected);
					
					Thread.sleep(2000);
					click("toast_close");
					log.info("Toast closed");
					
					assertEquals(multiple_file_copy_toast,multiple_file_copy_toast_expected);
					
					
					break;
					
					
				}
				
			}
		
			
			
			Thread.sleep(2000);
			click("goto_myfiles");
			//driver.get(Home_Page);
			WebDriverWait wait4111 = new WebDriverWait(driver, 120);
			wait4111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
			
			

			WebElement all_folders4 = getPageObject("folder_names");
			List<WebElement> each_folder4=all_folders4.findElements(By.tagName("div"));

			for(int i=0;i<each_folder4.size();i++)
			{


				WebElement each_div =each_folder4.get(i);
				String selected_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_folder_name.equals(multiple_folder_move_folder_name))
				{
					
					actions.doubleClick(each_folder4.get(i)).perform();
					Thread.sleep(2000);

					break;
					
					
				}
				
			}

			
			WebDriverWait wait124 = new WebDriverWait(driver, 120);
			wait124.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']")));
			
			
			
			String all_copied_folders = getPageObject("all_copied_folders").getText();
			log.info("Copyed files are---------"+all_copied_folders);
			log.info("All Selected files are---------"+all_folders);
			assertEquals(all_copied_folders,all_folders);
			if(all_copied_folders==all_folders);
			
			{
			log.info("--------------------------------------------Multiple folders are moved successfully--------------------------------------------");
			
			captureScreenShot(ScreenShotsFilePath + "multiple_folders_moved_success.png");
			Thread.sleep(2000);
			ATUReports.add("Multiple folders are moved successfully","Multiple folders moved",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(2000);
		
			}
			


		}
		
		

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple folders move",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_folders_move_failed_Error.png");
			ATUReports.add("multiple folders move failed","multiple folders move",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	
	
	
	
	
	

	
	

	//-------------------------------------------------------multiple files & Folders copy-------------------------------------------------------


	public static void multiple_files_folders_copy()
	{

		try
		{
			
			actions = new Actions(driver);
			
			
			
			
			
			/*
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
			
			
			WebElement elementLocator = getPageObject("home_allfiles");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			click("right_click_newfolder");
			
			

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String multiple_files_folders_move_folder_name="Mul-files-folders-copy "+dateFormatted;
			sendKeys("folder_name_input_field",multiple_files_folders_move_folder_name);
			click("folder_ok_button");
			Thread.sleep(2000);
			
			WebDriverWait wait412 = new WebDriverWait(driver, 60);
			wait412.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']"))).getText().contains(multiple_files_folders_move_folder_name);
			Thread.sleep(2000);
			
			click("toast_close");
			
			
			

			WebElement all_folders5 = getPageObject("folder_names");
			List<WebElement> each_folder5=all_folders5.findElements(By.tagName("div"));

			for(int i=0;i<each_folder5.size();i++)
			{


				WebElement each_div =each_folder5.get(i);
				String selected_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_folder_name.equals("Add Mul files folders copy"))
				{
					
					actions.doubleClick(each_folder5.get(i)).perform();
					Thread.sleep(2000);

					break;
					
					
				}
				
			}

			
			WebDriverWait wait124 = new WebDriverWait(driver, 120);
			wait124.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='folder']")));
			
			
			
			Thread.sleep(2000);
			
			String all_folders=getPageObject("all_selected_folders").getText();
			log.info("------------------");
			log.info(all_folders);
			log.info("------------------");
			
			

			String all_files=getPageObject("all_selected_files").getText();
			log.info("------------------");
			log.info(all_files);
			log.info("------------------");
			
			
			click("first_file_infolder");
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("second_file_infolder");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			click("third_file_infolder");
			Thread.sleep(1000);
			JsClick("selected_file_checkbox");
			Thread.sleep(1000);
			
			
			
			click("first_folder_infolder");
			JsClick("selected_folder_checkbox");
			Thread.sleep(1000);
			click("second_folder_infolder");
			Thread.sleep(1000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(1000);
			click("third_folder_infolder");
			Thread.sleep(1000);
			JsClick("selected_folder_checkbox");
			Thread.sleep(1000);
			
			
			
			
			
			WebElement elementLocator1 = getPageObject("first_file_infolder");
			actions.contextClick(elementLocator1).perform();
			Thread.sleep(2000);			
			click("file_right_click_copy_move");
			Thread.sleep(3000);
			
			
			actions.doubleClick(getPageObject("copy_mydrive")).perform();
			
			
			Thread.sleep(3000);
			
			WebElement copy_move_popup_all_files_folders1 = getPageObject("copy_move_popup_all_files_folders");
			List<WebElement> each_file_folder1=copy_move_popup_all_files_folders1.findElements(By.tagName("div"));

			for(int i=0;i<each_file_folder1.size();i++)
			{


				WebElement each_div1 =each_file_folder1.get(i);
				String selected_file_folder_name1 = each_div1.getText();
				log.info("--------------------"+selected_file_folder_name1);
				if(selected_file_folder_name1.equals(multiple_files_folders_move_folder_name))
				{
					
					each_file_folder1.get(i).click();
					Thread.sleep(2000);
					click("copy_button");
					
					WebDriverWait wait3 = new WebDriverWait(driver, 120);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));


					Thread.sleep(8000);
					
					//if(getPageObject("starred_toast").getText().contains("Successfully moved"))
					
					String multiple_file_copy_toast = getPageObject("copy_toast").getText();
					
					log.info("----->"+multiple_file_copy_toast);
					
					String multiple_file_copy_toast_expected = "Successfully copied";
					
					log.info("----->"+multiple_file_copy_toast_expected);
					
					Thread.sleep(2000);
					click("toast_close");
					log.info("Toast closed");
					
					assertEquals(multiple_file_copy_toast,multiple_file_copy_toast_expected);
					

					break;
					
					
				}
				
			}
		
			
			
			
			
			click("goto_myfiles");
			//driver.get(Home_Page);
			Thread.sleep(2000);
			
			WebDriverWait wait4111 = new WebDriverWait(driver, 120);
			wait4111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
			

			WebElement all_folders6 = getPageObject("folder_names");
			List<WebElement> each_folder6=all_folders6.findElements(By.tagName("div"));

			for(int i=0;i<each_folder6.size();i++)
			{


				WebElement each_div =each_folder6.get(i);
				String selected_folder_name = each_div.getText();
				//log.info("--------------------"+selected_file_folder_name);
				if(selected_folder_name.equals(multiple_files_folders_move_folder_name))
				{
					
					actions.doubleClick(each_folder6.get(i)).perform();
					Thread.sleep(2000);

					break;
					
					
				}
				
			}

			
			
			
			WebDriverWait wait1214 = new WebDriverWait(driver, 120);
			wait1214.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			
			String all_copied_folders = getPageObject("all_copied_folders").getText();
			log.info("Copyed folders are---------"+all_copied_folders);
			
			
			String all_copied_files = getPageObject("all_copied_files").getText();
			log.info("Copyed files are---------"+all_copied_files);
			
			String all_copied_files_folders = all_copied_folders+all_copied_files;
			String all_selected_files_folders = all_folders+all_files;
			
			assertEquals(all_copied_files_folders,all_selected_files_folders);
			if(all_copied_files_folders==all_selected_files_folders);
			//assertEquals(all_copied_folders,all_folders);
			//assertEquals(all_copied_files,all_files);
			//if((all_copied_folders==all_folders)&&(all_copied_files==all_files));
			
			{
			log.info("--------------------------------------------Multiple files & folders are copied successfully--------------------------------------------");
			
			captureScreenShot(ScreenShotsFilePath + "multiple_files_folders_copy_success.png");
			Thread.sleep(2000);
			ATUReports.add("Multiple files and folders are copied successfully","Multiple files & folders copy",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(2000);
			
			}

		}
		
		

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in multiple files & folders copy",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "multiple_files_folder_copy_failed_Error.png");
			ATUReports.add("multiple files & folders copy failed","multiple files & folders copy",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
