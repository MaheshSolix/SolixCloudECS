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



public class RightClickUpload extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(RightClickUpload.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";
	public RightClickUpload(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("ARCA Right Click Upload");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Right Click Upload--------------------");
		captureScreenShot(ScreenShotsFilePath + "Right_click_upload.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Right Click Upload";
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



	//-------------------------------------------------------single file upload-------------------------------------------------------

	public static void right_click_single_file_upload()
	{

		try
		{
			
			actions = new Actions(driver);

			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			
			
			
			WebElement elementLocator = getPageObject("right_click_text");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/span[1]")).click();
			//click("upload_single_file");
			Runtime.getRuntime().exec("E:\\Solix ECS\\AutoIT\\RightClickSingleFileUpload.exe");
			Thread.sleep(10000);

			//Single file Upload confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[1]/span[1]/img[1]")));



			if(getPageObject("upload_confirm").getText().contains("Uploaded"))
			{
			
				log.info("--------------------------------------------file uploaded successfully--------------------------------------------");
				Thread.sleep(2000);
				captureScreenShot(ScreenShotsFilePath + "single_file_upload_success.png");
				Thread.sleep(2000);
				ATUReports.add("Single file upload successfull","Single file upload",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				
			}

			else
			{

				log.error("Error in uploading single file");	
				captureScreenShot(ScreenShotsFilePath + "single_file_upload_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("single file upload failed","single file upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}

			//View file details
			WebElement elementLocator1 = getPageObject("first_file");
			click("first_file",elementLocator1);
			click("details_icon");//open file details
			Thread.sleep(3000);
			String file_details= getPageObject("file_details").getText();
			log.info("------------File Details Start------------");
			log.info(file_details);
			log.info("------------File Details End------------");
			click("details_icon");//close file details
		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in uploading single file",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "single_file_upload_failed_Error.png");
			ATUReports.add("single file upload failed","single file upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	


	/*

	//-------------------------------------------------------Bulk file upload-------------------------------------------------------

	public static void right_click_bulk_file_upload()
	{

		try
		{
			
			//WebElement clickon_empty_area = driver.findElement(By.xpath("//img[@class='emptyAreaImg']"));
			//actions.contextClick(clickon_empty_area).perform();

			WebElement clickon_all_files_text = driver.findElement(By.xpath("//h5[@class='foldersSubheading']"));
			actions.contextClick(clickon_all_files_text).perform();
			Thread.sleep(2000);
			getPageObject("right_click_bulk_upload").click();

			Runtime.getRuntime().exec("D:\\eclipse-workspace\\ARCA_Automation\\AutoIT\\RightClickFileBulkUpload.exe");
			Thread.sleep(5000);

			//Bulk file Upload confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='uploadingbox']//ul//li//span//img")));


			if(getPageObject("check_upload_error_status").getText().contains("Uploaded"))
			{
			log.info("--------------------------------------------Right click bulk files uploaded successfully--------------------------------------------");
			Thread.sleep(3000);
			captureScreenShot(ScreenShotsFilePath + "Bulk_file_upload_success.png");
			Thread.sleep(2000);
			click("upload_popup_close");
			log.info("Upload popup closed");
			ATUReports.add("Bulk file upload successfull","Bulk file upload",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}
				
			else
				{

					log.error("Error in uploading bluk files");	
					captureScreenShot(ScreenShotsFilePath + "right_click_bulk_file_upload_failed.png");
					Thread.sleep(2000);
					click("upload_popup_close");
					log.info("Upload popup closed");
					ATUReports.add("Bulk file file upload failed","Bulk file upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}


				
		}


			
			
			WebElement bulk_uploadUL= driver.findElement(By.xpath("//div[@class='uploadingbox']/ul"));
			List<WebElement> uploadFilesList=bulk_uploadUL.findElements(By.tagName("li"));
			log.info("--------------------------------------------"+uploadFilesList.size()+" Files are selected to upload--------------------------------------------");
			List<WebElement> successfully_uploaded_files = new ArrayList <> ();




			for(int i=0;i<uploadFilesList.size();i++)
			{	

				WebElement li =uploadFilesList.get(i);
				WebDriverWait wait = new WebDriverWait(driver, 300);

				
				if(getPageObject("check_upload_error_status").getText().contains("Error in Uploading"))
				{

					log.error("Error in uploading bluk files");	
					captureScreenShot(ScreenShotsFilePath + "right_click_bulk_file_upload_failed.png");
					Thread.sleep(2000);
					click("upload_popup_close");
					log.info("Upload popup closed");
					ATUReports.add("Bulk file file upload failed","Bulk file upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}


				else
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='uploadingbox']//ul//li//span//img"))); 
					
					Assert.assertTrue(driver.findElement(By.xpath("//div[@class='uploadingbox']//ul//li//span//img")).isDisplayed());

					if (li.findElement(By.xpath("//div[@class='uploadingbox']//ul//li//span//img")).isDisplayed()) {				     

						System.out.println(+i+1+" File uploaded succesful.");
						successfully_uploaded_files.add(li);
					}

				}
				log.info("--------------------------------------------"+successfully_uploaded_files.size()+" files uploaded successfully--------------------------------------------");
				Thread.sleep(3000);
				captureScreenShot(ScreenShotsFilePath + "Bulk_file_upload_success.png");
				Thread.sleep(2000);
				click("upload_popup_close");
				log.info("Upload popup closed");
				ATUReports.add("Bulk file upload successfull","Bulk file upload",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	
			}
		}
	

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in uploading bulk files.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "bulk_file_upload_Error.png");
			ATUReports.add("bulk file upload failed","bulk file upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}

*/

/*
	
	
	//-------------------------------------------------------right click folder upload-------------------------------------------------------



	public static void right_click_folder_upload()
	{

		try
		{
			//WebElement clickon_empty_area = driver.findElement(By.xpath("//img[@class='emptyAreaImg']"));
			//actions.contextClick(clickon_empty_area).perform();
			WebElement clickon_all_files_text1 = driver.findElement(By.xpath("//h5[@class='foldersSubheading']"));
			actions.contextClick(clickon_all_files_text1).perform();
			Thread.sleep(2000);
			getPageObject("right_click_folder_upload").click();
			Runtime.getRuntime().exec("D:\\eclipse-workspace\\ARCA_Automation\\AutoIT\\RightClickFolderUpload.exe");
			Thread.sleep(5000);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			System.out.println("TAB Keyboard event performed");
			Thread.sleep(5000);


			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("ENTER Keyboard event performed");
			Thread.sleep(30000);


			//right click folder Upload confirm
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='uploadingbox']//ul//li//span//img")));

			
			if(getPageObject("check_upload_error_status").getText().contains("Uploaded"))
			{
				
				log.info("--------------------------------------------Folder uploaded successfully--------------------------------------------"); 
				Thread.sleep(2000);
				captureScreenShot(ScreenShotsFilePath + "Folder_upload_success.png");
				Thread.sleep(2000);
				click("upload_popup_close");
				log.info("Upload popup closed");
				ATUReports.add("Folder upload successfull","Folder upload",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else
			{

				log.error("Error in uploading right click folder");	
				captureScreenShot(ScreenShotsFilePath + "right_click_folder_upload_Error.png");
				Thread.sleep(2000);
				click("upload_popup_close");
				log.info("Upload popup closed");
				ATUReports.add("Right click folder upload failed","Right click folder upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	
			}


			
		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Folder Upload Failed",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "folder_upload_Error.png");
			ATUReports.add("Folder upload failed","Folder upload",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}
	
	*/
	
	/*

	//-------------------------------------------------------Right Click create folder-------------------------------------------------------

	public static void right_click_create_folder() throws Exception
	{

		try
		{
		actions = new Actions(driver);

		driver.get(Home_Page);
		
		sendKeys("login_username","mahesh.naloju@solix.com");
		sendKeys("login_password","Test@123");
		click("login_button");
		log.info("login success");
		
		
		
		WebElement elementLocator = getPageObject("right_click_text");
		actions.contextClick(elementLocator).perform();
		click("right_click_create_folder");
		Thread.sleep(2000);



		//Enter folder name with date

		DateFormat dateFormat = new SimpleDateFormat("MMM/dd HH:mm");

		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String dateFormatted= dateFormat.format(date);
		sendKeys("ente_folder_name",dateFormatted);
		
		Thread.sleep(1000);
		//Ok button click to create folder
		click("folder_ok_button");
		Thread.sleep(1000);
		
		
		WebElement folder_container = getPageObject("folder_section");
		List<WebElement> each_folder_tag=folder_container.findElements(By.tagName("div"));


		for(int i=0;i<each_folder_tag.size();i++)
		{
			WebElement div =each_folder_tag.get(i);
			
			String folder_name = div.getText();
			

			if(folder_name.contains(dateFormatted))
			{

				Actions folder_actions = new Actions(driver);
				folder_actions.doubleClick(div).perform();
				System.out.println("clicked on "+dateFormatted+" folder");
				Thread.sleep(5000);
				break;




			}

		}
		

	}
		
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in creating folder",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "folder_creation_Error.png");
			ATUReports.add("error creating folder","Folder creation with date",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
