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
import org.openqa.selenium.JavascriptExecutor;
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



public class CollectionsPage extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(CollectionsPage.class.getName());
	public static String Home_Page = "https://ecsdev.solix.com/";
	public CollectionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Collections");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Collections--------------------");
		captureScreenShot(ScreenShotsFilePath + "Collections.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Collections";
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
	
	

	//-------------------------------------------------------Add files to collections-------------------------------------------------------


	public static void add_files_to_collection()
	{

		try
		{
			
			actions = new Actions(driver);
			
			
			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			sendKeys("login_password","Test@123");
			click("login_button");
			log.info("login success");
			

			click("sidebar_collections");
			
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),' Collections ')]")));
			
			click("collections_plusbutton");
			
			WebDriverWait wait51 = new WebDriverWait(driver, 120);
			wait51.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Create New Collection')]")));
			

			DateFormat dateFormat = new SimpleDateFormat("dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String collection_name = "Collection "+dateFormatted;
			
			sendKeys("collections_name_textarea",collection_name);
			click("tag_save_button_ok");
			
			WebDriverWait wait3 = new WebDriverWait(driver, 60);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			
			
			
				Thread.sleep(2000);
				click("toast_close");
				
				log.info("----Collection Created-----");
				

				WebElement all_folders1 = getPageObject("collection_blocks");
				List<WebElement> each_folder1=all_folders1.findElements(By.tagName("div"));

				for(int i=0;i<each_folder1.size();i++)
				{
					

					WebElement each_div =each_folder1.get(i);
					String selected_folder_name = each_div.getText();
					//log.info("--------------------"+selected_file_folder_name);
					if(selected_folder_name.contains(collection_name))
					{
						
						click("collection_view");
						Thread.sleep(2000);

						break;
						
						
					}
					
				}
			
			
				click("collections_plusbutton");
				Thread.sleep(2000);
				
				String collection_sidebar_first_filename=getPageObject("collection_first_file_name").getText();
				log.info("First file name--------------------"+collection_sidebar_first_filename);
				click("collection_first_file_checkbox");
				
				String collection_second_file_name=getPageObject("collection_second_file_name").getText();
				log.info("Second file name--------------------"+collection_second_file_name);
				click("collection_second_file_checkbox");
				
				String collection_third_file_name=getPageObject("collection_third_file_name").getText();
				log.info("Third file name--------------------"+collection_third_file_name);
				click("collection_third_file_checkbox");
				
				
				click("click_addto_collection");
				
				WebDriverWait wait31 = new WebDriverWait(driver, 60);
				wait31.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
				
				
				
					Thread.sleep(2000);
					click("toast_close");
					

					click("first_file");
					Thread.sleep(1000);
					String first_file_name=getPageObject("first_file").getText();
					log.info("first file name--->"+first_file_name);
					Thread.sleep(1000);
					
					
					click("second_file");
					Thread.sleep(1000);
					String second_file_name=getPageObject("second_file").getText();
					log.info("second file name--->"+second_file_name);
					Thread.sleep(1000);
					
					
					click("third_file");
					Thread.sleep(1000);
					String third_file_name=getPageObject("third_file").getText();
					log.info("third file name--->"+third_file_name);
					Thread.sleep(1000);
						
					
					if(first_file_name.contains(collection_sidebar_first_filename))
					{
						log.info("First file added to collection");
						ATUReports.add("Error in adding files to collection","Error in adding files to collection",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					
					
					
					if(second_file_name.contains(collection_second_file_name))
					{
						log.info("Second file added to collection");
						ATUReports.add("Error in adding files to collection","Error in adding files to collection",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					
					
					
					if(third_file_name.contains(collection_third_file_name))
					{
						log.info("Third file added to collection");
						ATUReports.add("Error in adding files to collection","Error in adding files to collection",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					
				
				log.info("--------------------------------------------Files added to Collections successfully--------------------------------------------");
				
				captureScreenShot(ScreenShotsFilePath + "files_added_to_collections_successfully.png");
				Thread.sleep(2000);
				ATUReports.add("Files added to Collections successfully","Files added to Collection",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Thread.sleep(2000);
			
				

		}

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in adding files to collection");	
			captureScreenShot(ScreenShotsFilePath + "error_in_adding_files_to_collection.png");
			ATUReports.add("Error in adding files to collection","Error in adding files to collection",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
