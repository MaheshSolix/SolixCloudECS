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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import arca.common.Navigation;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class MyFilesReminders extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesReminders.class.getName());
	public static String Home_Page = "https://app.solixecsdev.com/login";

	public MyFilesReminders(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Reminders");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Reminders--------------------");
		captureScreenShot(ScreenShotsFilePath + "Reminders.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Reminders";
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

	private static void turnOffImplicitWaits() {
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	private static void turnOnImplicitWaits() {
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	public static void file_reminder() throws Exception
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
		if(selected_folder_name.contains("Reminders"))
		{
			
			actions.doubleClick(each_folder1.get(i)).perform();
			Thread.sleep(2000);

			break;
			
			
		}
		
	}

	
	WebDriverWait wait4 = new WebDriverWait(driver, 120);
	wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'All Files')]")));
	
	
	String file_name_before_rename=getPageObject("first_file_infolder").getText();
	log.info("------------------");
	log.info(file_name_before_rename);
	log.info("------------------");
	
	
	

	WebElement elementLocator1 = getPageObject("first_file_infolder");
	actions.contextClick(elementLocator1).perform();
	Thread.sleep(2000);
	click("add_reminder");
	Thread.sleep(2000);
	
	try
	{
		turnOffImplicitWaits();
	if(getPageObject("reminder_existance").isDisplayed())
	{
		WebElement ReminderTrashList = getPageObject("reminders_list");
		List<WebElement> EachReminder = ReminderTrashList.findElements(By.tagName("div"));
		
		for(int i=0;i<EachReminder.size();i++)
		{
			WebElement each_div =EachReminder.get(i).findElement(By.xpath("//div[@class='custom-modal-body bg-white']/div/div/div[3]/img[2]"));
			each_div.click();
			Thread.sleep(3000);
			click("reminder-delete_confirmation");
			
			break;
		}
		
	}
	
	}
	catch(Exception e)
	{
		log.info("There are no reminders.");
	}
		
	
		
	turnOnImplicitWaits();
	DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH-mm");

	//get current date time with Date()
	Date date = new Date();

	// Now format the date
	String dateFormatted= dateFormat.format(date);
	
	String reminder_name= "Reminder on "+dateFormatted;
	
	log.info("Existing reminder name: "+reminder_name);
	
	sendKeys("reminder_name",reminder_name);
	
	click("date_picker");
	Thread.sleep(2000);
	click("pick_today_date");
	Thread.sleep(2000);
	click("select_time_link");
	Thread.sleep(2000);
	click("active_hours");
	Thread.sleep(2000);
	click("active_minutes");
	Thread.sleep(2000);
	click("frequency_period_icon");
	Thread.sleep(2000);
	select("frequency_period_dropdown","visibletext","Daily");
	Thread.sleep(2000);
	click("create_reminder_button");
	
	
	
	
	
	WebDriverWait wait22 = new WebDriverWait(driver, 60);
	wait22.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
	Thread.sleep(2000);
	

	if(getPageObject("starred_toast").getText().contains("Successfully added reminder"))
	{
	
		String new_reminder_name = getPageObject("new_reminder_name").getText();
		
		log.info("New reminder name: "+new_reminder_name);
		
		if(reminder_name==new_reminder_name)
		{
			
		
		
		log.info("--------------------------------------------File Reminder created successfully--------------------------------------------");
		Thread.sleep(2000);

		captureScreenShot(ScreenShotsFilePath + "files_reminder_added.png");
		Thread.sleep(2000);
		
		ATUReports.add("files reminder","File Reminder created successfully",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}
	

	else
	{

		log.error("Error in files adding file reminder");	
		captureScreenShot(ScreenShotsFilePath + "failed_to_add_file_reminder.png");
		Thread.sleep(2000);
		ATUReports.add("files reminder failed","File Reminder failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
	}

	}
		
		
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in file reminder.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "files_reminder_Error.png");
			ATUReports.add("Files creating file reminder","Files reminder failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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





