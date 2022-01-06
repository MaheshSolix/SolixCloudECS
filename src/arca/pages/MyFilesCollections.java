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



public class MyFilesCollections extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesCollections.class.getName());
	public static String Home_Page = "https://app.solixecsdev.com/login";
	public MyFilesCollections(WebDriver driver) {
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

			sendKeys("login_username", "ravovov767@onzmail.com");

			click("login_next");

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));

			sendKeys("login_password", "Test@123");

			click("login_button");
			log.info("login success");
			
			
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			
			
			String collection_selected_files=getPageObject("file_name").getText();
			log.info("------------------");
			log.info(collection_selected_files);
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
			Thread.sleep(1000);
			
			

			WebElement elementLocator = getPageObject("first_file");
			actions.contextClick(elementLocator).perform();
			Thread.sleep(2000);
			actions.moveToElement(getPageObject("right_click_collections")).perform();
			Thread.sleep(2000);
			click("right_click_create_collection");
			Thread.sleep(2000);
			
			
			DateFormat dateFormat = new SimpleDateFormat("dd HH-mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			String collection_name = "Collection "+dateFormatted;
			
			sendKeys("collections_name_textarea",collection_name);
			click("tag_save_button");
			
			//Starred confirm
			WebDriverWait wait114 = new WebDriverWait(driver, 60);
			wait114.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(2000);
			
			if(getPageObject("starred_toast").getText().contains("Added file(s) to collection successfully"))
			{
			
				Thread.sleep(2000);
				click("toast_close");
				
				click("collections_right_sidebar");
				
				WebDriverWait wait15 = new WebDriverWait(driver, 120);
				wait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='file-block col'][1]")));
				
				Thread.sleep(2000);
				
				click("first_collection");
				Thread.sleep(2000);
				
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
				
				
				
				/*
				 * 
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("scroll(0, -250);");
				
				
				WebElement collection_page_allcollections = getPageObject("all_collections");
				List<WebElement> each_collection=collection_page_allcollections.findElements(By.tagName("div"));

				for(int i=0;i<each_collection.size();i++)
				{


					WebElement each_div =each_collection.get(i);
					String selected_folder_name = each_div.getText();
					//log.info("--------------------"+selected_file_folder_name);
					if(selected_folder_name.contains(collection_name))
					{
						
						each_collection.get(i).findElement(By.xpath("//span")).click();
						log.info("------------------Clicked on Span view");
						Thread.sleep(2000);

						break;
						
						
					}
					
				}
				*/
				
				click("collection_view");

				WebDriverWait wait16 = new WebDriverWait(driver, 120);
				wait16.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='files']/div[@class='row']/div[1]")));
				
				String files_in_added_collection=getPageObject("collection_added_files").getText();
				log.info("------------------");
				log.info(files_in_added_collection);
				log.info("------------------");
				
				log.info("Selected collection files---------"+collection_selected_files);
				log.info("Added collection files---------"+files_in_added_collection);
				Thread.sleep(3000);
				//assertEquals(collection_selected_files,files_in_added_collection);
				if(collection_selected_files.contains(files_in_added_collection));
				
				{
				log.info("--------------------------------------------Files added to Collections successfully--------------------------------------------");
				
				captureScreenShot(ScreenShotsFilePath + "files_added_to_collections_successfully.png");
				Thread.sleep(2000);
				ATUReports.add("Files added to Collections successfully","Files added to Collection",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Thread.sleep(2000);
			
				}
				
				
			}


			else
			{

				log.error("Error in adding files to collection");	
				captureScreenShot(ScreenShotsFilePath + "error_in_adding_files_to_collection.png");
				Thread.sleep(2000);
				ATUReports.add("Error in adding files to collection","Error in adding files to collection",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}



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
