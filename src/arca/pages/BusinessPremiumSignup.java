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

public class BusinessPremiumSignup extends Navigation {
	

	WebDriver driver;
 	
private static Logger log = Logger.getLogger(BusinessPremiumSignup.class.getName());
public static String Home_Page = "https://arcaqa.solix.com";
	public BusinessPremiumSignup(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("ARCA Business Premium Signup Pages");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Entered Business Premium Signup Page--------------------");
		captureScreenShot(ScreenShotsFilePath + "Individual_Business_Standard_SignupPage_success.png");
	}
	
	 private void setAuthorInfoForReports() {
	        ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	 }

	 private void setIndexPageDescription() {
	        ATUReports.indexPageDescription = "Business Premium Signup Page";
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
	 
	
	public void bussiness_premium_signup() throws Exception
	{
		try {
		ReadExcel readExcel = new ReadExcel(DataFilePath + "LogInPageData.xls","Sheet1");
        String generic_email = readExcel.readCell(0,1);
        String first_name = readExcel.readCell(1,1);
        String last_name = readExcel.readCell(2,1);
        String password = readExcel.readCell(3,1);
        String confirm_password = readExcel.readCell(4,1);
        String official_generic_email = readExcel.readCell(5,1);
        String incorrect_password = readExcel.readCell(6,1);
        String num_of_users = readExcel.readCell(7,1);
        String password_greater_than_12 = readExcel.readCell(8,1);
        bussiness_premium_signup(generic_email, num_of_users, first_name, last_name, password, confirm_password, official_generic_email, incorrect_password, password_greater_than_12);
        //log.info("Run Business Standard signup");
        
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
		}
		
        //return new BusinessPremiumSignup(driver);
	}
	
	public void bussiness_premium_signup(String signup_email, String signup_num_of_users, String signup_first_name, String signup_last_name, String signup_password, String signup_confirm_password, String official_email, String incorrect_signup_password, String signup_password_greter_than_12) throws Exception 
	{
		try {

		driver.get(Home_Page);
		getPageObject("business_plans").click();
		getPageObject("business_premium_free_trial_button").click();
			
		Thread.sleep(2000);	
		getPageObject("individual_plans_personal_signup_logo").isDisplayed();//Logo Display
		log.info("Logo Displayed");
		
		getPageObject("individual_plans_personal_signup_logo_text").isDisplayed();//Logo text Display
		log.info("Logo text Displayed");
		
		getPageObject("individual_plans_personal_signup_logo_slogan").isDisplayed();//Slogan beside logo Display
		log.info("Slogan beside logo Displayed");	
		
		//Login button Display
		getPageObject("individual_plans_personal_signup_logo_login_button").isDisplayed();
		log.info("Login Button Displayed");	
		
			
		// Business Standard plan Signup
		String business_premium_plan = getPageObject("signup_hearder").getText();
		Assert.assertTrue(business_premium_plan.contains("Start your 30-day free trial now!"));
		log.info("Start your 30-day free trial now! ---- Heading Displayed");
		
		// Choose plan default selected value
		Select business_premium_plan_select = new Select(getPageObject("personal_signup_form_dropdown"));
		WebElement selected_value = business_premium_plan_select.getFirstSelectedOption();
		String business_premium_default_value = selected_value.getText();
		System.out.println(business_premium_default_value);
		Assert.assertTrue(business_premium_default_value.contains("Business - Premium"));		
		log.info("Business premium plan - default selected drop down value is - **Business - Premium**");
		Thread.sleep(2000);
		
		getPageObject("num_of_users").isDisplayed();
		log.info("The Number of users field displyed");
		
		// yearly billing frequency default selected
		WebElement billing_frequency_default_value= getPageObject("billing_yearly");
		String yearly_bill_text=getPageObject("billing_yearly").getText();
		Assert.assertTrue(yearly_bill_text.contains("Billed Yearly"));
		String Att_valiue=billing_frequency_default_value.getAttribute("class");
		Assert.assertTrue(Att_valiue.contains("billingFrequency billingYearlyActive"));
		log.info("Business standard Signup page default selected value is - **Billed Yearly**");
		Thread.sleep(2000);
		
		// monthly billing 
		String monthly_bill_text=getPageObject("billing_monthly").getText();
		Assert.assertTrue(monthly_bill_text.contains("Bill Monthly"));

		
		//Click on Start Free Trial button without entering data to check alert messages
		getPageObject("signup_button").click();
		log.info("Start Free Trial Button Clicked");
		
		String number_of_users=getPageObject("num_of_users_alert").getText();
		Assert.assertTrue(number_of_users.contains("The Number of users field is required"));
		log.info("The Number of users field is required alert displyed");
		
		String email_alert=getPageObject("email_required_alert").getText();
		Assert.assertTrue(email_alert.contains("The Email field is required"));
		log.info("The Email field is required alert displyed");
				
		String first_name_alert=getPageObject("first_name_required_alert").getText();
		Assert.assertTrue(first_name_alert.contains("The First Name field is required"));
		log.info("First name required alert displyed");
		
		String last_name_alert=getPageObject("last_name_required_alert").getText();
		Assert.assertTrue(last_name_alert.contains("The Last Name field is required"));
		log.info("Last name required alert displyed");
		
		String password_alert=getPageObject("password_required_alert").getText();
		Assert.assertTrue(password_alert.contains("The Password field is required"));
		log.info("Password required alert displyed");
		
		
		

		//incorrect data
		sendKeys("signup_email_id",signup_email);
		sendKeys("signup_first_name",signup_first_name);
		sendKeys("signup_last_name",signup_last_name);
		sendKeys("signup_password",signup_password_greter_than_12);

		String password_greter_than_12_alert=getPageObject("password_greater_than_12").getText();
		Assert.assertTrue(password_greter_than_12_alert.contains("The Password field may not be greater than 12 characters"));
		log.info("The Password field may not be greater than 12 characters alert displyed");
		
		getPageObject("signup_password").clear();
		sendKeys("signup_password",signup_password);
		sendKeys("signup_confirm_password",incorrect_signup_password);
		
		String signup_email_alert=getPageObject("non_gerneric_email_alert").getText();
		Assert.assertTrue(signup_email_alert.contains("Please provide a non-generic email"));
		
		String password_notmatch_alert=getPageObject("password_not_match_alert").getText();
		Assert.assertTrue(password_notmatch_alert.contains("The Confirm the password confirmation does not match"));
		
		/* need to enable after text fixed
		click("signup_button");
		
		String confirm_password_alert=getPageObject("confirm_password_alert").getText();
		Assert.assertTrue(confirm_password_alert.contains("The Confirm password field is required"));
		log.info("The Confirm password field is required alert displyed");
		*/
		
		getPageObject("signup_email_id").clear();
		getPageObject("signup_first_name").clear();
		getPageObject("signup_last_name").clear();
		getPageObject("signup_password").clear();
		getPageObject("signup_confirm_password").clear();
		
		
		//Form Fillup
		sendKeys("signup_email_id",official_email);
		sendKeys("num_of_users",signup_num_of_users);
		sendKeys("signup_first_name",signup_first_name);
		sendKeys("signup_last_name",signup_last_name);
		sendKeys("signup_password",signup_password);

		sendKeys("signup_confirm_password",signup_confirm_password);
		//getPageObject("signup_button").click();

		
		ATUReports.add("Individual business premium signup successfull","Business premium signup",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in finding message in business premium",e);	
	    	log.fatal(e.getMessage(),e);
	    	captureScreenShot(ScreenShotsFilePath + "Business_Premium_SignupPage-PageError.png");
	    	ATUReports.add("Business premium signup failed","Busines premium signup",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		}
		
		
		//return new BusinessPremiumSignup(driver);

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
