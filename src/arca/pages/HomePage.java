package arca.pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import arca.common.Navigation;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,	MethodListener.class })
public class HomePage extends Navigation {

	WebDriver driver;

	
	public static void Setup() {
		  
		   System.setProperty("atu.reporter.config", System.getProperty("user.dir") + System.getProperty("file.separator") +"atu.properties");
		   DOMConfigurator.configure("log4j.xml");
		}
	


	private static Logger log = Logger.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("ARCA Home Page");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Entered home Page--------------------");
		captureScreenShot(ScreenShotsFilePath + "HomePage_success.png");
	}
	
	 private void setAuthorInfoForReports() {
	        ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	 }

	 private void setIndexPageDescription() {
	        ATUReports.indexPageDescription = "Home Page";
	 }
	 
	 
		private String value;
		private String main_header;
		private String personal_plan;
		private String personal_premium_plan;
		private String standard_plan;
		private String business_premium_plan;
		private String business_enterprise_plan;
		private String have_questions_button_options;
		private String business_plans;
		private String individual_plans;
		public static String Home_Page = "https://ecsdev.solix.com/";

	public String getMain_header() {
		return main_header;
	}

	public void setMain_header(String main_header) {
		this.main_header = main_header;
	}

	public String getPersonal_plan() {
		return personal_plan;
	}

	public void setPersonal_plan(String personal_plan) {
		this.personal_plan = personal_plan;
	}

	public String getPersonal_premium_plan() {
		return personal_premium_plan;
	}

	public void setPersonal_premium_plan(String personal_premium_plan) {
		this.personal_premium_plan = personal_premium_plan;
	}

	public String getStandard_plan() {
		return standard_plan;
	}

	public void setStandard_plan(String standard_plan) {
		this.standard_plan = standard_plan;
	}

	public String getBusiness_premium_plan() {
		return business_premium_plan;
	}

	public void setBusiness_premium_plan(String business_premium_plan) {
		this.business_premium_plan = business_premium_plan;
	}

	public String getBusiness_enterprise_plan() {
		return business_enterprise_plan;
	}

	public void setBusiness_enterprise_plan(String business_enterprise_plan) {
		this.business_enterprise_plan = business_enterprise_plan;
	}

	public String getHave_questions_button_options() {
		return have_questions_button_options;
	}

	public void setHave_questions_button_options(String have_questions_button_options) {
		this.have_questions_button_options = have_questions_button_options;
	}

	public String getBusiness_plans() {
		return business_plans;
	}

	public void setBusiness_plans(String business_plans) {
		this.business_plans = business_plans;
	}

	public String getIndividual_plans() {
		return individual_plans;
	}

	public void setIndividual_plans(String individual_plans) {
		this.individual_plans = individual_plans;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public void HomePage_Load() throws Exception {
		
		try {

		driver.get(Home_Page);
		log.info("Home Page Loaded");
		
		getPageObject("arca_logo").isDisplayed();//Logo Display
		log.info("Logo Displayed");
		
		
		
		//Login button Display
		getPageObject("arca_login_button").isDisplayed();
		log.info("Login Button Displayed");

		

		
		
		getPageObject("individual_plans_personal_signup").click();
		log.info("individual plans personal free trial button clicked");
		
		
		ATUReports.add("Home page successfull","Homepage",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		
		}
		
		catch (Exception e) {
			
			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in finding message in LogOutPage",e);	
	    	log.fatal(e.getMessage(),e);
	    	captureScreenShot(ScreenShotsFilePath + "HomePageError.png");
	    	ATUReports.add("Homepage failed","Homepage",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		
		//return new HomePage(driver);

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
