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
import arca.common.ReadExcel;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class LegalHold extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(LegalHold.class.getName());

	public LegalHold(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Legal Hold");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Legal Hold--------------------");
		captureScreenShot(ScreenShotsFilePath + "legal_hold.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Legal Hold";
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



	//-------------------------------------------------------Add Legal Hold-------------------------------------------------------



	public static void add_legal_hold() throws Exception
	{

		try
		{
			actions = new Actions(driver);

			getPageObject("profile_icon").click();
			log.info("Clicked on profile icon");
			Thread.sleep(2000);
			click("admin_console");
			log.info("Clicked on admin console");
			Thread.sleep(2000);
			click("governance");
			log.info("Clicked on governance");
			Thread.sleep(2000);
			click("legal_hold");
			log.info("Clicked on legal hold");
			Thread.sleep(2000);
			click("add_legal_hold_button");
			log.info("Clicked on add legal hold button");
			Thread.sleep(2000);
			

			//Enter folder name with date

			DateFormat dateFormat = new SimpleDateFormat("MMM/dd HH:mm");

			//get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted= dateFormat.format(date);
			sendKeys("legal_hold_name",dateFormatted);
			sendKeys("legal_hold_description","legal hold description "+dateFormatted);
			
			Select lh_applicable_to=new Select(getPageObjectById("applicable_to"));
			lh_applicable_to.selectByVisibleText("Existing content only");
			WebElement applicable_to_selected_value = lh_applicable_to.getFirstSelectedOption();
			String applicable_to_value_text = applicable_to_selected_value.getText();
			log.info("Selected applicable_to drop down value is : "+applicable_to_value_text);
			
			
			Select lh_apply_to=new Select(getPageObjectById("apply_policy_to"));
			lh_apply_to.selectByVisibleText("User(s)");
			WebElement lh_apply_to_selected_value = lh_apply_to.getFirstSelectedOption();
			String lh_apply_to_selected_value_text = lh_apply_to_selected_value.getText();
			log.info("Selected apply_policy_to drop down value is : "+lh_apply_to_selected_value_text);
			click("search_user");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@placeholder='Search Users']")).sendKeys("mahesh");
			Thread.sleep(4000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			log.info("Down arrow pressed");
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			log.info("Enter key pressed");
			
			
			robot.keyPress(KeyEvent.VK_TAB);
			log.info("TAB key prssed");
			
			
			robot.keyPress(KeyEvent.VK_ENTER);
			log.info("Enter key prssed");
			
			/*
			click("click_on_form");
			log.info("clicked on form");
			
			click("legal_hold_apply_button");
			log.info("clicked on legal_hold_apply_button");
			*/
			
			Thread.sleep(4000);
			
			String legal_hold_first_table_name = getPageObject("legal_hold_table_first_name").getText();
			
			if(legal_hold_first_table_name.contains(dateFormatted))
			{
				log.info("Legal hold created succesfully");
				Thread.sleep(2000);
				ATUReports.add("legal hold created succesfully","legal hold created succesfully",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
			else
			{
				log.info("Add legal hold faild");
				ATUReports.add("Add legal hold faild","Add legal hold faild",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
			
			Thread.sleep(3000);
			
			
				}
				catch (Exception e) {

					System.out.println("errror at: ");
					e.printStackTrace();
					log.error("Error in add legal hold.",e);	
					//log.fatal(e.getMessage(),e);
					captureScreenShot(ScreenShotsFilePath + "add_legal_hold_Error.png");
					ATUReports.add("add legal hold failed","add legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}



			}
	

	//-------------------------------------------------------Search Legal Hold-------------------------------------------------------
	public static void search_legal_hold() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Legalhold");
        String search_legal_hold_name = readExcel.readCell(0,1);
        search_legal_hold_excel(search_legal_hold_name);
        //log.info("Run Individual personal signup");
        
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
       // return new PersonalSignUp(driver);
	}


	public static void search_legal_hold_excel(String search_legal_hold_text) throws Exception
	{

		try
		{
			actions = new Actions(driver);
			driver.get("https://arcaqa.solix.com/#/admin-console/governance/legal-hold");
			Thread.sleep(5000);
			
			WebElement my_element = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search Legal holds']")));
			Thread.sleep(5000);
			my_element.sendKeys(search_legal_hold_text);
			my_element.sendKeys(Keys.ENTER); 
			Thread.sleep(5000);
			
			WebElement legal_hold_list = getPageObject("legal_hold_table_body");
			List<WebElement> each_legal_hold=legal_hold_list.findElements(By.tagName("tr"));

			for(int i=0;i<each_legal_hold.size();i++)
			{


				WebElement tr =each_legal_hold.get(i);
				String selected_legal_hold_name = each_legal_hold.get(i).getText();
				log.info("--------------------"+selected_legal_hold_name);
				if(selected_legal_hold_name.contains(search_legal_hold_text))
				{
					log.info("This legal hold has searched word");
					Thread.sleep(2000);
					ATUReports.add("legal hold search successfull","legal hold search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
				else
				{
					log.info("This legal hold does'nt have searchd word");
					ATUReports.add("legal hold search failed","legal hold search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
				
				

			}

		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in search legal hold.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "searchlegal_hold_Error.png");
			ATUReports.add("search legal hold failed","search legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}


	//-------------------------------------------------------View Legal Hold-------------------------------------------------------



	public static void view_legal_hold() throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			driver.get("https://arcaqa.solix.com/#/admin-console/governance/legal-hold");
			Thread.sleep(5000);
			
			String legal_hold_table_first_name = getPageObject("legal_hold_table_first_name").getText();
			
			log.info("--------------------"+legal_hold_table_first_name);
			
				
			click("legal_hold_3_dots");
			log.info("Clicked 3 dots");
			
			click("view_legal_hold");
			log.info("Clicked view legal hold");
			
				String legal_hold_titel = getPageObject("view_legal_hold_title").getText();
				
				log.info("--------------------"+legal_hold_titel);
				
				
				if(legal_hold_titel.contains(legal_hold_table_first_name))
				{
					log.info("View legal hold successful");
					Thread.sleep(2000);
					ATUReports.add("View legal hold successfull","View legal hold",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}
				else
				{
					log.info("View legal hold Failed");
					ATUReports.add("View legal hold failed","View legal hold ",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					
				}

			}
		
			
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in view legal hold.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "viewlegal_hold_Error.png");
			ATUReports.add("view legal hold failed","view legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}

	
	//-------------------------------------------------------user add or remove  Hold-------------------------------------------------------



	public static void user_remove_apply_hold() throws Exception
	{

		try
		{
			actions = new Actions(driver);
			
			
			
			click("remove_hold");
			log.info("Clicked remove hold");
			
			Thread.sleep(3000);
			
			WebDriverWait wait = new WebDriverWait (driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='toast-text']")));
			
			
			
			String apply_hold_text = getPageObject("remove_hold").getText();
			
			if(apply_hold_text.contains("Apply hold"))
			{
				log.info("User remove hold success");
				Thread.sleep(2000);
				ATUReports.add("User remove hold successfull","User remove hold",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
			else
			{
				log.info("Remove hold failed");
				ATUReports.add("User remove hold failed","User remove hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
				
			//-------------User apply hold---------
			
			Thread.sleep(3000);
			
			click("apply_hold");
			
			Thread.sleep(3000);
			
			String remove_hold_text = getPageObject("apply_hold").getText();
			
			
			if(remove_hold_text.contains("Remove hold"))
			{
				log.info("User apply hold success");
				Thread.sleep(2000);
				ATUReports.add("User apply hold successfull","User apply hold",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
			else
			{
				log.info("User apply hold failed");
				ATUReports.add("User apply hold failed","User apply hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			}
			
			
		
		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in add or remove hold.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "deletelegal_hold_Error.png");
			ATUReports.add("delete legal hold failed","delete legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}

		
		
	//-------------------------------------------------------Edit Legal Hold-------------------------------------------------------



		public static void edit_legal_hold() throws Exception
		{

			try
			{
				actions = new Actions(driver);
				
				driver.findElement(By.xpath("//span[contains(text(),'Edit')]")).click();
				log.info("Clicked on edit link");
				Thread.sleep(2000);
				
				/*
				
				click("legal_hold_3_dots");
				log.info("Clicked 3 dots");
				
				click("edit_legal_hold");
				log.info("Clicked edit legal hold");
				Thread.sleep(2000);
				
				*/
				//Enter folder name with date
				

				DateFormat dateFormat = new SimpleDateFormat("HH:mm");

				//get current date time with Date()
				Date date = new Date();

				// Now format the date
				String dateFormatted= dateFormat.format(date);
				getPageObjectById("legal_hold_name").clear();
				
				Thread.sleep(2000);
				
				String legal_hold_edited_text = "Legal Hold Edit "+dateFormatted;
				log.info("Legal hold edited text is: "+legal_hold_edited_text);
				driver.findElement(By.xpath("//input[@class='form-control form-control']")).sendKeys(legal_hold_edited_text);
				driver.findElement(By.xpath("//span[contains(text(),'Edit legal hold')]")).click();
				//sendKeys("legal_hold_name",legal_hold_edited_text);
				Thread.sleep(2000);
				
				
				click("legal_hold_apply_button");
				log.info("clicked on apply button");
				
				
				Thread.sleep(6000);
				
				String after_edit_legal_hold_table_first_name = getPageObject("legal_hold_table_first_name").getText();
				Thread.sleep(4000);
				
				log.info("--------------------"+after_edit_legal_hold_table_first_name);
				
					if(after_edit_legal_hold_table_first_name.contains(legal_hold_edited_text))
					{
						log.info("Edit legal hold Success");
						ATUReports.add("Edit legal hold successfull","Edit legal hold",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}
					else
					{
						log.info("Edit legal hold failed");
						ATUReports.add("Edit legal hold failed","Edit legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}


				}
			
				
			catch (Exception e) {

				System.out.println("errror at: ");
				e.printStackTrace();
				log.error("Error in edit legal hold.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "editlegal_hold_Error.png");
				ATUReports.add("Edit legal hold failed","Edit legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}

			
		//-------------------------------------------------------delete Legal Hold-------------------------------------------------------



				public static void delete_legal_hold() throws Exception
				{

					try
					{
						actions = new Actions(driver);
						
						driver.get("https://arcaqa.solix.com/#/admin-console/governance/legal-hold");
						Thread.sleep(2000);
						
						String befor_delete_legal_hold_table_first_name = getPageObject("legal_hold_table_first_name").getText();
						
						log.info("Deleted legal hold is: "+befor_delete_legal_hold_table_first_name);
						
						
						click("legal_hold_3_dots");
						log.info("Clicked 3 dots");
						
						click("delete_legal_hold");
						log.info("Clicked delete legal hold");
						Thread.sleep(2000);
						
						click("delete_legal_hold_button");
						log.info("Clicked delete legal hold button");
						
						
						
						
						Thread.sleep(4000);
						
						String after_delete_legal_hold_table_first_name = getPageObject("legal_hold_table_first_name").getText();
						Thread.sleep(4000);
						
						log.info("after delettion first legal hold name is:"+after_delete_legal_hold_table_first_name);
						
							if(!after_delete_legal_hold_table_first_name.contains(befor_delete_legal_hold_table_first_name))
							{
								log.info("delete legal hold Success");
								Thread.sleep(2000);
								ATUReports.add("delete legal hold successfull","delete legal hold",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							else
							{
								log.info("delete legal hold failed");
								ATUReports.add("delete legal hold failed","delete legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}


						}
					
						
					catch (Exception e) {

						System.out.println("errror at: ");
						e.printStackTrace();
						log.error("Error in delete legal hold.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "deletelegal_hold_Error.png");
						ATUReports.add("delete legal hold failed","delete legal hold",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
