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
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import arca.common.Navigation;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testrecorder.ATUTestRecorder;



public class Retention extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(Retention.class.getName());

	public Retention(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Retention");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Retention--------------------");
		captureScreenShot(ScreenShotsFilePath + "retention.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Retention";
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



	//-------------------------------------------------------Add Retention-------------------------------------------------------



	public static void add_retention() throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			
			
		

			
			//click("retention_link");
			driver.get("https://arcaqa.solix.com/?#/admin-console/governance/retention");
			log.info("Clicked on retention");
			
			Thread.sleep(4000);
			click("add_retention");
			log.info("Clicked on add retention button");
			Thread.sleep(2000);
			
			//Enter folder name with date

			DateFormat dateFormat = new SimpleDateFormat("MMM/dd HH:mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			
			sendKeys("retention_policy_name",dateFormatted);
			Thread.sleep(2000);
			
			
			sendKeys("retention_policy_description","retention description "+dateFormatted);
			
			
			select("retenton_time_period","visibletext","30 Days");
			Thread.sleep(2000);
			/*
			Thread.sleep(5000);
			Select ret_time_period=new Select(getPageObjectById("retenton_time_period"));
			ret_time_period.selectByVisibleText("30 Days");
			WebElement applicable_to_selected_value = ret_time_period.getFirstSelectedOption();
			String applicable_to_value_text = applicable_to_selected_value.getText();
			log.info("Selected retenton_time_period drop down value is : "+applicable_to_value_text);
			*/
			
			
			select("action_at_end_of_retention_period","visibletext","None");
			Thread.sleep(2000);
			/*
			Select ret_action_at_end_of_retention_period=new Select(getPageObjectById("action_at_end_of_retention_period"));
			ret_action_at_end_of_retention_period.selectByVisibleText("User(s)");
			WebElement lh_apply_to_selected_value = ret_action_at_end_of_retention_period.getFirstSelectedOption();
			String lh_apply_to_selected_value_text = lh_apply_to_selected_value.getText();
			log.info("Selected apply_policy_to drop down value is : "+lh_apply_to_selected_value_text);
			*/
			
			select("ret_email_notificaton","visibletext","Admin, Owner");
			Thread.sleep(2000);
			select("ret_applicable_to","visibletext","Existing content only");
			Thread.sleep(2000);
			
			select("ret_apply_policy_to","visibletext","User(s)");
			Thread.sleep(2000);
			
			
			click("save_apply_button");
			log.info("clicked on save & apply button");
			
			WebDriverWait wait = new WebDriverWait (driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='toast-text']")));
			Thread.sleep(4000);
			
			String retention_table_first_name = getPageObject("ret_first_name").getText();
			Thread.sleep(4000);
			
			log.info("Retention table first name: "+retention_table_first_name);
			
				if(retention_table_first_name.contains(dateFormatted))
				{
					log.info("Retention added successfull");
					Thread.sleep(2000);
					ATUReports.add("Retention added successfull","Add retention",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("Add retention failed");
					ATUReports.add("Add retention failed","Add retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}

		}


		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in add retention.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "add_retention_Error.png");
			ATUReports.add("add retention failed","add retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}
	
	//-------------------------------------------------------Search Retention-------------------------------------------------------



			public static void search_retention() throws Exception
			{

				try
				{
					actions = new Actions(driver);

				//Enter folder name with date

					DateFormat dateFormat = new SimpleDateFormat("MMM");

					//get current date time with Date()
					Date date = new Date();

					// Now format the date
					String dateFormatted= dateFormat.format(date);
					
					sendKeys("search_retention",dateFormatted);
					Thread.sleep(2000);
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					
					WebElement retention_table = driver.findElement(By.xpath("//div[@class='tab-pane active']//tbody"));
					List<WebElement> each_table_tr_tag=retention_table.findElements(By.tagName("tr"));
					
					for(int i=0;i<each_table_tr_tag.size();i++)
					{

						WebElement tr =each_table_tr_tag.get(i);

						String selected_tr = each_table_tr_tag.get(i).getText();
						log.info("Selected folder name is "+selected_tr);
						
						if(selected_tr.contains(dateFormatted))
						{
							log.info("Retention search has the word");
							Thread.sleep(2000);
							ATUReports.add("Retention search successfull","Retention search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							
						}
						else
						{
							log.info("Retention search does'nt have the word");
							ATUReports.add("Retention search failed","Retention search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							
						}
						
					}
					
					/*
					String retention_table_first_name = getPageObject("ret_first_name").getText();
					
					log.info("--------------------"+retention_table_first_name);
					
						
					click("retention_3_dots");
					log.info("Clicked 3 dots");
					
					click("view_retention_policy");
					log.info("Clicked view retention");
					
						String retention_title = getPageObject("view_retention_title").getText();
						
						log.info("--------------------"+retention_title);
						
						
						if(retention_title.contains(retention_table_first_name))
						{
							log.info("This retention has the word");
							Thread.sleep(2000);
							ATUReports.add("View retention successfull","View retention",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							
						}
						else
						{
							log.info("This retention does'nt have the word");
							ATUReports.add("View retention failed","View retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							
						}
*/
					

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

	//-------------------------------------------------------View Retention-------------------------------------------------------



		public static void view_retention() throws Exception
		{

			try
			{
				actions = new Actions(driver);

				
				
				String retention_table_first_name = getPageObject("ret_first_name").getText();
				
				log.info("--------------------"+retention_table_first_name);
				
					
				click("retention_3_dots");
				log.info("Clicked 3 dots");
				
				click("view_retention_policy");
				log.info("Clicked view retention");
				
					String retention_title = getPageObject("view_retention_title").getText();
					
					log.info("--------------------"+retention_title);
					
					
					if(retention_title.contains(retention_table_first_name))
					{
						log.info("This retention has the word");
						Thread.sleep(2000);
						ATUReports.add("View retention successfull","View retention",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					else
					{
						log.info("This retention does'nt have the word");
						ATUReports.add("View retention failed","View retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
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

		//-------------------------------------------------------Apply Retention-------------------------------------------------------



				public static void apply_retention() throws Exception
				{

					try
					{
						actions = new Actions(driver);

					
						
						String retention_table_first_name = getPageObject("ret_first_name").getText();
						
						log.info("--------------------"+retention_table_first_name);
						
							
						click("retention_3_dots");
						log.info("Clicked 3 dots");
						
						click("apply_retention");
						log.info("Clicked applys retention");
						
						
						

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
				
				
				//-------------------------------------------------------Edit Retention-------------------------------------------------------



				public static void edit_retention() throws Exception
				{

					try
					{
						actions = new Actions(driver);


						click("legal_hold_breadcrumb");
						log.info("Clicked on legal_hold_breadcrumb");
						Thread.sleep(4000);
						
						String retention_table_first_name = getPageObject("ret_first_name").getText();
						
						log.info("Selected retention is: "+retention_table_first_name);
						
							
						click("retention_3_dots");
						log.info("Clicked 3 dots");
						
						click("edit_retention");
						log.info("Clicked edit retention");
						
						
						
						DateFormat dateFormat = new SimpleDateFormat("HH:mm");

						//get current date time with Date()
						Date date = new Date();

						// Now format the date
						String dateFormatted= dateFormat.format(date);
						getPageObjectById("retention_policy_name").clear();
						
						Thread.sleep(2000);
						
						String retention_edited_text = "Retention Edit "+dateFormatted;
						log.info("Retention edited text is: "+retention_edited_text);
						
						sendKeys("retention_policy_name",retention_edited_text);
						click("retention_apply_button");
						Thread.sleep(2000);
						
						
						
						Thread.sleep(4000);
						
						String after_edit_retention_table_first_name = getPageObject("ret_first_name").getText();
						Thread.sleep(4000);
						
						log.info("after retention edit : "+after_edit_retention_table_first_name);
						
							if(after_edit_retention_table_first_name.contains(retention_edited_text))
							{
								log.info("Edit retention Success");
								ATUReports.add("Edit retention successfull","Edit retention",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							else
							{
								log.info("Edit retention failed");
								ATUReports.add("Edit retention failed","Edit retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
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

				//-------------------------------------------------------Retire Retention-------------------------------------------------------



				public static void retire_retention() throws Exception
				{

					try
					{
						actions = new Actions(driver);

						
						String retention_table_first_name = getPageObject("ret_first_name").getText();
						
						log.info("Selected retention is: "+retention_table_first_name);
						
							
						click("retention_3_dots");
						log.info("Clicked 3 dots");
						
						click("retire_retention");
						log.info("Clicked retire retention");
						Thread.sleep(4000);
						
						click("retire_retention_ok_button");
						log.info("Clicked retire retention ok button");
						
						Thread.sleep(2000);
						
						WebDriverWait wait = new WebDriverWait (driver, 20);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tab-pane active']//table")));
						
						
						String after_retire_retention_table_first_name = getPageObject("ret_first_name").getText();
						
						
						log.info("after retire first retention name is : "+after_retire_retention_table_first_name);
						
							if(after_retire_retention_table_first_name.contains(retention_table_first_name))
							{
								log.info("Retire retention failed");
								ATUReports.add("Edit retention failed","Edit retention",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
								
							}
							else
							{
								log.info("Retire retention success");
								ATUReports.add("Edit retention successfull","Edit retention",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
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
