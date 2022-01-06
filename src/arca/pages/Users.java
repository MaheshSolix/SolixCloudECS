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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import arca.common.Navigation;
import arca.common.ReadExcel;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class Users extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(Users.class.getName());

	public Users(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Users");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Users--------------------");
		captureScreenShot(ScreenShotsFilePath + "Users.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Users";
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

	


	//-------------------------------------------------------Add User-------------------------------------------------------

	public static void add_user() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Sheet2");
        String first_name = readExcel.readCell(0,1);
        String last_name = readExcel.readCell(1,1);        
        String email_address = readExcel.readCell(2,1);
        user(first_name, last_name, email_address);
        //log.info("Run Individual personal signup");
        
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
       // return new PersonalSignUp(driver);
	}
	public static void user(String user_first_name, String user_last_name, String user_email_address) throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			
			click("add_user");
			log.info("Clicked on add_user");
			
			sendKeys("first_name",user_first_name);
			
			sendKeys("last_name",user_last_name);
			
			sendKeys("email_address",user_email_address);
			click("add_user_button");
			Thread.sleep(4000);

			}
			catch (Exception e) {

				System.out.println("errror at: ");
				e.printStackTrace();
				log.error("Error in adding user.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "add_user_failed.png");
				ATUReports.add("Add user failed","Add user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}
	
	
	
	
	//-------------------------------------------------------Search User-------------------------------------------------------


	public static void search_user() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Sheet2");
        String search_word = readExcel.readCell(3,1);
        user_search(search_word);
       
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
       // return new PersonalSignUp(driver);
	}
	public static void user_search(String user_search_excel) throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			

			sendKeys("user_search",user_search_excel);
			
			
			Thread.sleep(7000);
			
			WebElement usear_table = driver.findElement(By.xpath("//div[@class='tab-pane active']//tbody"));
			List<WebElement> each_table_tr_tag=usear_table.findElements(By.tagName("tr"));
			
			for(int i=0;i<each_table_tr_tag.size();i++)
			{

				WebElement tr =each_table_tr_tag.get(i);

				String selected_row = each_table_tr_tag.get(i).getText();
				log.info("Selected row: "+selected_row);
				
				if(selected_row.contains(user_search_excel))
				{
					log.info("Selected row has searched user name");
					Thread.sleep(2000);
					ATUReports.add("Retention search successfull","Retention search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("Selected row doesnot has searched user name");
					ATUReports.add("Retention search failed","Retention search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				
			}
			
			

		}
	

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in view retention.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "view_retention_Error.png");
			ATUReports.add("view retention failed","view retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}
	
	

	//-------------------------------------------------------View User-------------------------------------------------------


	public static void view_user() throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			getPageObject("user_search").clear();
			Thread.sleep(4000);
			
			String user_table_first_name = getPageObject("user_first_name").getText();
			log.info(user_table_first_name);
			
			
			click("user_3dots");
			click("user_view");
			Thread.sleep(4000);
			
			String user_heading=getPageObject("view_user_heading").getText();
			

				if(user_table_first_name.contains(user_heading))
				{
					log.info("View user successful");
					Thread.sleep(2000);
					ATUReports.add("View user successfull","View user",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("View user failed");
					ATUReports.add("View user failed","View user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				
			
			
			

		}
	

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in View user.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "view_user_Error.png");
			ATUReports.add("view user failed","view user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}
	
	
	//-------------------------------------------------------Edit User-------------------------------------------------------


		public static void edit_user() throws Exception
		{

			try
			{
				actions = new Actions(driver);
				
				Thread.sleep(4000);
				click("users_breadcrumb");
				Thread.sleep(6000);
				click("user_3dots");
				click("edit_user");
				Thread.sleep(4000);
				
				
				getPageObject("last_name").clear();
				
				
				
				sendKeys("last_name"," Edited");
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//div[@class='formComponent']//div[@class='row']//div[1]//button")).click();
				
				
				Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
				String edited_user_name=getPageObject("view_user_heading").getText();
				Thread.sleep(3000);

					if(edited_user_name.contains(" Edited"))
					{
						log.info("Edit user successful");
						Thread.sleep(2000);
						ATUReports.add("Edit user successfull","Edit user",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					else
					{
						log.info("Edit user failed");
						ATUReports.add("Edit user failed","Edit user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					
				
				
				

			}
		

			catch (Exception e) {

				System.out.println("errror at: ");
				e.printStackTrace();
				log.error("Error in Edit user.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "Edit_user_Error.png");
				ATUReports.add("Edit user failed","Edit user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}
		
		
		//-------------------------------------------------------Suspend User-------------------------------------------------------


				public static void suspend_user() throws Exception
				{

					try
					{
						actions = new Actions(driver);
						
						click("users_breadcrumb");
						Thread.sleep(6000);
						String user_status=getPageObject("user_status").getText();
						log.info(user_status);
						

						
						click("user_3dots");
						
						click("suspend_resume_user");
						Thread.sleep(4000);
						String user_status_after_clicking_on_suspend=getPageObject("user_status").getText();
				
							if(user_status_after_clicking_on_suspend.contains("InActive"))
							{
								getPageObject("user_status").click();
								String view_user_status=getPageObject("view_user_status").getText();
								if(view_user_status.contains("InActive"))
								{
								log.info("Suspend user successful");
								Thread.sleep(2000);
								ATUReports.add("Suspend user successfull","Suspend user",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								}
							}
							else
							{
								log.info("Suspend user failed");
								ATUReports.add("Suspend user failed","Suspend user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							
						
						
						

					}
				

					catch (Exception e) {

						System.out.println("errror at: ");
						e.printStackTrace();
						log.error("Error in Suspend user.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "Suspend_user_Error.png");
						ATUReports.add("Suspend user failed","Suspend user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}



				}
				
//-------------------------------------------------------Resume User-------------------------------------------------------

				public static void resume_user() throws Exception
				{

					try
					{
						actions = new Actions(driver);
						
						
						Thread.sleep(3000);
						click("resume_user");
						Thread.sleep(5000);
						
						String view_user_status=getPageObject("view_user_status").getText();
						
						
							if(view_user_status.contains("Active"))
							{
								getPageObject("users_breadcrumb").click();
								String user_status=getPageObject("user_status").getText();
								if(user_status.contains("Active"))
								{
								log.info("Resume user successful");
								Thread.sleep(2000);
								ATUReports.add("Resume user successfull","Resume user",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								}
							}
							else
							{
								log.info("Resume user failed");
								ATUReports.add("Resume user failed","Resume user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
	

					}
				

					catch (Exception e) {

						System.out.println("error at: ");
						e.printStackTrace();
						log.error("Error in Resume user.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "Resume_user_Error.png");
						ATUReports.add("Resume user failed","Resume user",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}



				}
				
				
//-------------------------------------------------------Add to group-------------------------------------------------------

				public static void add_to_group() throws Exception
				{

					try
					{
						actions = new Actions(driver);
						
						click("user_3dots");
						click("user_add_to_group");
						
						Select group_select = new Select(driver.findElement(By.xpath("//select[@class='custom-select']")));
						group_select.selectByIndex(0);
						
						String selected_drop_down_text = group_select.getFirstSelectedOption().getText();
						log.info(selected_drop_down_text);
						
						click("user_add_group_ok_button");
						
						Thread.sleep(3000);
						
						click("user_3dots");
						click("user_view");
						
						
						String all_groups_list = getPageObject("user_added_groups_list").getText();
						
				
						
							if(all_groups_list.contains(selected_drop_down_text))
							{
								
								log.info("User added to the group successful");
								Thread.sleep(2000);
								ATUReports.add("User added to the group successful","Add user to group",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							else
							{
								log.info("Add user to group failed");
								ATUReports.add("Add user to group failed","Add user to group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
	

					}
				

					catch (Exception e) {

						System.out.println("error at: ");
						e.printStackTrace();
						log.error("Error in adding user to group.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "add_user_to_group_Error.png");
						ATUReports.add("Add user to group failed","Add user to group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
