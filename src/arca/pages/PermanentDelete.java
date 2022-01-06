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



public class PermanentDelete extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(PermanentDelete.class.getName());

	public PermanentDelete(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Permanent Delete Files");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Permanent Delete Files--------------------");
		captureScreenShot(ScreenShotsFilePath + "permanent_delete_files.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Permanent Delete Files";
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


	//-------------------------------------------------------File Permanent Delete-------------------------------------------------------

	

	public static void permanent_file_delete() throws Exception
	{

		try
		{
			
			getPageObject("toggle_icon").click();
			
			getPageObject("left_side_bar_files").isDisplayed();
			getPageObject("left_side_bar_files").click();
			
			getPageObject("left_side_bar_delete").isDisplayed();
			getPageObject("left_side_bar_delete").click();
			
			actions = new Actions(driver);

			WebElement trash_file_container = getPageObject("trash_files_section");
			List<WebElement> each_trash_file_tag=trash_file_container.findElements(By.tagName("div"));

			List<WebElement> trash_all_files_list = new ArrayList <> ();

			for(int i=0;i<each_trash_file_tag.size();i++)
			{
			
			
				WebElement div =each_trash_file_tag.get(i);
				String selected_trash_file_name = each_trash_file_tag.get(i).getText();
				log.info("Selected trash file name is "+selected_trash_file_name);
				actions.contextClick(div).perform();
				click("permanent_delet_link");
				log.info("clicked on permanent delete");
				
				click("popup_permanent_delete_button");
				log.info("clicked on popup permanent delete button");
				Thread.sleep(3000);
				//WebDriverWait wait = new WebDriverWait(driver, 300);
				//WebElement delete_alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='toast-text']"))); 
				//log.info(delete_alert.getText());
				if(trash_file_container.getText().contains(selected_trash_file_name))
				{
					log.error("Error in file permanent delete");
					captureScreenShot(ScreenShotsFilePath + "file_permanent_delete_failed.png");
					Thread.sleep(2000);
					ATUReports.add("File permanent delete failed","File permanent delete",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
				
				else
				{

					log.info("--------------------------------------------File deleted permanently--------------------------------------------");
					captureScreenShot(ScreenShotsFilePath + "permanent_file_delete_success.png");
					Thread.sleep(2000);
					ATUReports.add("Permanent File delete successfull","Permanent File delete",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}

			}

		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in file permanent delete",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "file_permanent_delete_Error.png");
			ATUReports.add("File permanent delete failed","File permanent delete",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}

	

	//-------------------------------------------------------Folder Permanent Delete-------------------------------------------------------

	

	public static void permanent_folder_delete() throws Exception
	{

		try
		{
			
			actions = new Actions(driver);

			WebElement trash_folders_container = getPageObject("trash_folders_section");
			List<WebElement> each_trash_folder_tag=trash_folders_container.findElements(By.tagName("div"));

			List<WebElement> trash_all_folders_list = new ArrayList <> ();

			for(int i=0;i<each_trash_folder_tag.size();i++)
			{
			
			
				WebElement div =each_trash_folder_tag.get(i);
				String selected_trash_folder_name = each_trash_folder_tag.get(i).getText();
				log.info("Selected trash folder name is "+selected_trash_folder_name);
				actions.contextClick(div).perform();
				click("permanent_delet_link");
				log.info("clicked on permanent delete");
				
				click("popup_permanent_delete_button");
				log.info("clicked on popup permanent delete button");
				Thread.sleep(3000);
				//WebDriverWait wait = new WebDriverWait(driver, 300);
				//WebElement delete_alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='toast-text']"))); 
				//log.info(delete_alert.getText());
				if(trash_folders_container.getText().contains(selected_trash_folder_name))
				{
					log.error("Error in folder permanent delete");
					captureScreenShot(ScreenShotsFilePath + "folder_permanent_delete_failed.png");
					Thread.sleep(2000);
					ATUReports.add("folder permanent delete failed","folder permanent delete",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
				
				else
				{

					log.info("--------------------------------------------folder deleted permanently--------------------------------------------");
					captureScreenShot(ScreenShotsFilePath + "permanent_folder_delete_success.png");
					Thread.sleep(2000);
					ATUReports.add("Permanent folder delete successfull","Permanent folderdelete",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}

			}

		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in folder permanent delete",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "folder_permanent_delete_Error.png");
			ATUReports.add("folder permanent delete failed","folder permanent delete",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
