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



public class MyFilesDownload extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesDownload.class.getName());
	public static String Home_Page = "https://app.solixecsdev.com/login";
	

	public MyFilesDownload(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Download Files");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Download Files--------------------");
		captureScreenShot(ScreenShotsFilePath + "download_files.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Download Files";
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

	

	public static void mul_file_download() throws Exception
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
			
			
			


	WebElement all_folders1 = getPageObject("folder_names");
	List<WebElement> each_folder1=all_folders1.findElements(By.tagName("div"));

	for(int i=0;i<each_folder1.size();i++)
	{


		WebElement each_div =each_folder1.get(i);
		String selected_folder_name = each_div.getText();
		//log.info("--------------------"+selected_file_folder_name);
		if(selected_folder_name.contains("Download"))
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
	
	

	WebElement elementLocator1 = getPageObject("first_file_infolder");
	actions.contextClick(elementLocator1).perform();
	Thread.sleep(2000);
	click("right_click_download");
	Thread.sleep(2000);
	
	WebDriverWait wait22 = new WebDriverWait(driver, 60);
	wait22.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
	Thread.sleep(2000);
	

	if(getPageObject("starred_toast").getText().contains("Depending on the file/folder size, download might take some time."))
	{
		
		//---------------Mul Files Download Cofirmation------------------
		click("toast_close");
		
	
		String all_download_files=getPageObject("all_selected_files").getText();
		log.info("------------------");
		log.info(all_download_files);
		log.info("------------------");
		
		
		
		log.info("--------------------------------------------file Successfully downloaded--------------------------------------------");
		Thread.sleep(2000);

		captureScreenShot(ScreenShotsFilePath + "files_downloaded.png");
		Thread.sleep(2000);
		
		ATUReports.add("files downloaded","files downloaded",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		
	}


	else
	{

		log.error("Error in files download");	
		captureScreenShot(ScreenShotsFilePath + "files_download_failed_Error.png");
		Thread.sleep(2000);
		ATUReports.add("files download failed","files download",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
	}


		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in downloading files.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "files_download_Error.png");
			ATUReports.add("Files download failed","Files download failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}

	
	
	
	

	//-------------------------------------------------------Mul Folders Delete-------------------------------------------------------

	

	public static void mul_folders_download() throws Exception
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
			
		
	
	String all_folders=getPageObject("all_selected_folders").getText();
	log.info("------------------");
	log.info(all_folders);
	log.info("------------------");
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
	
	
	

	WebElement elementLocator1 = getPageObject("first_folder_infolder");
	actions.contextClick(elementLocator1).perform();
	Thread.sleep(2000);
	click("right_click_download");
	Thread.sleep(2000);
	
	WebDriverWait wait = new WebDriverWait(driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
	Thread.sleep(2000);
	

	if(getPageObject("starred_toast").getText().contains("Depending on the file/folder size, download might take some time."))
	{
	
		//---------------Mul folder download Cofirmation------------------
		
		click("toast_close");
			
		
		WebDriverWait wait41 = new WebDriverWait(driver, 120);
		wait41.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
		
		
		String all_trash_folders=getPageObject("all_selected_folders").getText();
		log.info("------------------");
		log.info(all_trash_folders);
		log.info("------------------");
		
		
	
		
		log.info("--------------------------------------------folders Successfully downloaded--------------------------------------------");
		Thread.sleep(2000);

		captureScreenShot(ScreenShotsFilePath + "folders_downloaded.png");
		Thread.sleep(2000);
		
		ATUReports.add("folders downloaded","folders downloaded",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		
	}


	else
	{

		log.error("Error in folders download");	
		captureScreenShot(ScreenShotsFilePath + "folders_download_failed.png");
		Thread.sleep(2000);
		ATUReports.add("folders download failed","folders download",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
	}


		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in downloading folders.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "folders_download_Error.png");
			ATUReports.add("folders download failed","folders download failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}



//-------------------------------------------------------Mul Folders Delete-------------------------------------------------------



public static void mul_files_folders_download() throws Exception
{

	try
	{
		actions = new Actions(driver);
		
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
		
		click("goto_myfiles");
		
		WebDriverWait wait4 = new WebDriverWait(driver, 120);
		wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));

		
		WebElement all_folders1 = getPageObject("folder_names");
		List<WebElement> each_folder1=all_folders1.findElements(By.tagName("div"));

		for(int i=0;i<each_folder1.size();i++)
		{


			WebElement each_div =each_folder1.get(i);
			String selected_folder_name = each_div.getText();
			//log.info("--------------------"+selected_file_folder_name);
			if(selected_folder_name.contains("Download"))
			{
				
				actions.doubleClick(each_folder1.get(i)).perform();
				Thread.sleep(2000);

				break;
				
				
			}
			
		}




WebDriverWait wait44 = new WebDriverWait(driver, 120);
wait44.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));


String all_folders=getPageObject("all_selected_folders").getText();
log.info("------------------");
log.info(all_folders);
log.info("------------------");
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



WebElement elementLocator1 = getPageObject("first_file_infolder");
actions.contextClick(elementLocator1).perform();
Thread.sleep(2000);
click("right_click_download");
Thread.sleep(2000);

WebDriverWait wait22 = new WebDriverWait(driver, 60);
wait22.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
Thread.sleep(2000);


if(getPageObject("starred_toast").getText().contains("Depending on the file/folder size, download might take some time."))
{
	
	//---------------Mul Files Download Cofirmation------------------
	click("toast_close");
	

	String all_download_files=getPageObject("all_selected_files").getText();
	log.info("------------------");
	log.info(all_download_files);
	log.info("------------------");
	
	
	
	log.info("--------------------------------------------multiple files and folders Successfully downloaded--------------------------------------------");
	Thread.sleep(2000);

	captureScreenShot(ScreenShotsFilePath + "files_folders_downloaded.png");
	Thread.sleep(2000);
	
	ATUReports.add("files and folders downloaded","files and folders downloaded",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	
	
}


else
{

	log.error("Error in files and folders download");	
	captureScreenShot(ScreenShotsFilePath + "files_folders_download_failed_Error.png");
	Thread.sleep(2000);
	ATUReports.add("files and folders download failed","files and folders download",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	
}


	}
	catch (Exception e) {

		System.out.println("errror at: ");
		e.printStackTrace();
		log.error("Error in downloading files and folders.",e);	
		//log.fatal(e.getMessage(),e);
		captureScreenShot(ScreenShotsFilePath + "files_folders_download_Error.png");
		ATUReports.add("Files and folders download failed","Files and folders download failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
	
	
	