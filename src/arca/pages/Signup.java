package arca.pages;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import arca.common.Navigation;
import arca.common.ReadExcel;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class Signup extends Navigation {

	WebDriver driver;
 	
private static Logger log = Logger.getLogger(Signup.class.getName());
public static String Home_Page = "https://ecsdev.solix.com/";
	public Signup(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("ARCA Login Page");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Entered Login Page--------------------");
		captureScreenShot(ScreenShotsFilePath + "Login_page.png");
	}
	
	 private void setAuthorInfoForReports() {
	        ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	 }

	 private void setIndexPageDescription() {
	        ATUReports.indexPageDescription = "Login Page";
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
	 
	 /*
	public void Login_page() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Sheet1");
        String business_email = readExcel.readCell(0,1);
        String first_name = readExcel.readCell(1,1);
        String last_name = readExcel.readCell(2,1);        
        String password = readExcel.readCell(3,1);
        String confirm_password = readExcel.readCell(4,1);
        String official_generic_email = readExcel.readCell(5,1);
        String incorrect_password = readExcel.readCell(6,1);
        String password_greater_than_12 = readExcel.readCell(8,1);
        
        //log.info("Run Individual personal signup");
        
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
       // return new PersonalSignUp(driver);
	}
	*/
	
	public void Signup_ecs() throws Exception 
	{
		try {
		


			ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Signup");
			String business_name = readExcel.readCell(0,1);
			String business_email = readExcel.readCell(1,1);
			String first_name_excel = readExcel.readCell(2,1);        
			String last_name_excel = readExcel.readCell(3,1);
			String password_excel = readExcel.readCell(4,1);
			String confirm_password_excel = readExcel.readCell(5,1);
	

			driver.get(Home_Page);



			click("create_account");
			log.info("clicked on create account link");

			//Signup_form

			sendKeys("business_name",business_name);
			sendKeys("work_email",business_email);
			sendKeys("first_Name",first_name_excel);
			sendKeys("last_Name",last_name_excel);
			sendKeys("enter_password",password_excel);
			sendKeys("confirm_password",confirm_password_excel);
			click("start_free_trial");
			
			click("login_link");
			
			log.info("verification email sent");
			//verify-email-pending
			
			
			//first-time-login
			
			sendKeys("login_username",business_email);
			sendKeys("login_password",password_excel);
			click("login_button");
			log.info("login success");
			
			
			//payment-plans-readexcel
			
			String name_oncard_excel = readExcel.readCell(6,1);
			String cc_number_excel = readExcel.readCell(7,1);
			String cvc_excel = readExcel.readCell(8,1);        
			String expiry_excel = readExcel.readCell(9,1);
			String zip_code_excel = readExcel.readCell(10,1);
			
			//payment-plan-form
			sendKeys("payee_name",name_oncard_excel);
			Thread.sleep(2000);
			
			driver.switchTo().frame(0);
			sendKeys("card_number",cc_number_excel);
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
			driver.switchTo().frame(1);
			sendKeys("card_cvc",cvc_excel);
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
			driver.switchTo().frame(2);
			sendKeys("card_expiry",expiry_excel);
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
			driver.switchTo().frame(3);
			sendKeys("postal_code",zip_code_excel);
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
			click("select_country");
			Thread.sleep(2000);
			select("select_country","value","US");
			Thread.sleep(2000);
			log.info("country selected");
			
			click("start_free_trial_button");
			log.info("Clicked on free trial button");
			Thread.sleep(4000);

			
			//company-setup-form
			sendKeys("phone_number","7896543786");
			sendKeys("company_city","California");
			click("company_location");
			select("company_location","value","US");
			sendKeys("number_of_employees","5231");
			click("next_button");
			log.info("Company setup succesful");
			
			ATUReports.add("Login successfull","Login page",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in finding message in login page",e);	
	    	log.fatal(e.getMessage(),e);
	    	captureScreenShot(ScreenShotsFilePath + "LoginPage-PageError.png");
	    	ATUReports.add("Login failed","Login Page",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		
		
		//return new PersonalSignUp(driver);

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
