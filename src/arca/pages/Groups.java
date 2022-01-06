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
import arca.common.ReadExcel;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class Groups extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(Groups.class.getName());

	public Groups(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Groups");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Users--------------------");
		captureScreenShot(ScreenShotsFilePath + "Groups.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Groups";
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

	


	//-------------------------------------------------------Add Groups-------------------------------------------------------


	public static void add_group() throws Exception
	{

		try
		{
			actions = new Actions(driver);
			Thread.sleep(2000);
			getPageObject("profile_icon").click();
			log.info("Clicked on profile icon");
			Thread.sleep(2000);
			click("admin_console");
			log.info("Clicked on admin console");
			
			driver.findElement(By.xpath("//li[contains(text(),'Users & Groups')]")).click();
			driver.findElement(By.xpath("//a[@class='nav-link']")).click();
			
			click("groups_button");
			
			//Enter folder name with date

			DateFormat dateFormat = new SimpleDateFormat("HH:mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			String groupname = "Group "+dateFormatted;
			
			sendKeys("group_name",groupname);
			sendKeys("group_description","Group description "+dateFormatted);
			
			Thread.sleep(2000);
			
			click("group_save_button");
			Thread.sleep(4000);
			
			String group_name = getPageObject("created_group_title").getText();
			
		
				
				if(group_name.contains(groupname))
				{
					log.info("Group created successfully");
					Thread.sleep(2000);
					ATUReports.add("Add Group successfull","Add Group",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("Add group failed");
					ATUReports.add("Add Group failed","Add Group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				
			

			}
			catch (Exception e) {

				System.out.println("error at: ");
				e.printStackTrace();
				log.error("Error in adding group.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "add_group_failed.png");
				ATUReports.add("Add group failed","Add group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}
	
	
	//-------------------------------------------------------add members to the group-------------------------------------------------------

	
	
	
	public static void add_user_to_group() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Sheet3");
        String first_user = readExcel.readCell(0,1);
        String second_user = readExcel.readCell(0,2);        
        String third_user = readExcel.readCell(0,3);
        add_users(first_user, second_user, third_user);
        //log.info("Run Individual personal signup");
        
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
       // return new PersonalSignUp(driver);
	}
	
	
	public static void add_users(String first_user_name, String second_user_name, String third_user_name) throws Exception
	{

		try
		{
			actions = new Actions(driver);
			sendKeys("add_or_search_group",first_user_name);
			
			click("add_member_link");
			Thread.sleep(5000);
			click("add_button");
			Thread.sleep(5000);
			
			String added_group_user = getPageObject("group_user_first_name").getText();
			log.info(added_group_user);
			
				if(added_group_user.contains(first_user_name))
				{
					log.info("User added to the group successful");
					Thread.sleep(2000);
					ATUReports.add("User added to the group successfull","Add user to group",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("add user to group failed");
					ATUReports.add("add user to the group  failed","Add user to group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
			
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
	
	//-------------------------------------------------------View Group-------------------------------------------------------


		public static void view_group() throws Exception
		{

			try
			{
				actions = new Actions(driver);
				click("groups_breadcrumb");
				
				String groups_table_first_name = getPageObject("groups_first_name").getText();
				log.info(groups_table_first_name);
				
				
				click("group_3dots");
				click("view_group");
				
				
				String view_group_header = getPageObject("view_group_name_header").getText();
				log.info(view_group_header);
				
					if(view_group_header.contains(groups_table_first_name))
					{
						log.info("View group successful");
						Thread.sleep(2000);
						ATUReports.add("View group successfull","View group",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					else
					{
						log.info("View group failed");
						ATUReports.add("View group failed","View group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					
				
				
				

			}
		

			catch (Exception e) {

				System.out.println("errror at: ");
				e.printStackTrace();
				log.error("Error in View group.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "view_group_Error.png");
				ATUReports.add("view group failed","view group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}
			
		//-------------------------------------------------------Edit Group-------------------------------------------------------


				public static void edit_group() throws Exception
				{

					try
					{
						actions = new Actions(driver);
						
						click("groups_breadcrumb");
						log.info("Clicked on groups_breadcrumb");
						Thread.sleep(4000);
						click("group_3dots");
						click("edit_group");
						
						
						//Enter folder name with date

						DateFormat dateFormat = new SimpleDateFormat("HH:mm");

						//get current date time with Date()
						Date date = new Date();

						// Now format the date
						String dateFormatted= dateFormat.format(date);
						String groupname = "Group Edited"+dateFormatted;
						
						sendKeys("edit_group_name",groupname);
						sendKeys("edit_group_description","Group description Edited"+dateFormatted);
						
						
						click("edit_group_save_button");
						
						String edit_group_header = getPageObject("view_group_name_header").getText();
						
						
						
						
							if(edit_group_header.contains(groupname))
							{
								
								
								log.info("Edit group successful");
								Thread.sleep(2000);
								ATUReports.add("Edit group successfull","Edit group",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							else
							{
								log.info("Edit group failed");
								ATUReports.add("Edit group failed","Edit group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
	

					}
				

					catch (Exception e) {

						System.out.println("errror at: ");
						e.printStackTrace();
						log.error("Error in Edit group.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "edit_group_Error.png");
						ATUReports.add("edit group failed","edit group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}



				}
						
	
				//-------------------------------------------------------Delete Group-------------------------------------------------------


				public static void delete_group() throws Exception
				{

					try
					{
						actions = new Actions(driver);
						click("groups_breadcrumb");
						Thread.sleep(3000);
						String groups_table_first_name = getPageObject("groups_first_name").getText();
						log.info(groups_table_first_name);
						
						click("group_3dots");
						click("delete_group");
						Thread.sleep(2000);
						click("delete_group_button");
						Thread.sleep(4000);
						
						String after_delete_groups_table_first_name = getPageObject("groups_first_name").getText();
						log.info(after_delete_groups_table_first_name);
			
						
						
							if(!after_delete_groups_table_first_name.contains(groups_table_first_name))
							{
								
								
								log.info("delete group successful");
								Thread.sleep(2000);
								ATUReports.add("delete group successfull","delete group",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							else
							{
								log.info("delete group failed");
								ATUReports.add("delete group failed","delete group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
	

					}
				

					catch (Exception e) {

						System.out.println("errror at: ");
						e.printStackTrace();
						log.error("Error in Edit group.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "edit_group_Error.png");
						ATUReports.add("edit group failed","edit group",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
