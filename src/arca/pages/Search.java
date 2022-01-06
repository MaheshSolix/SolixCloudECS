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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import arca.common.Navigation;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class Search extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(Search.class.getName());

	public Search(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Search Files");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Search Files--------------------");
		captureScreenShot(ScreenShotsFilePath + "search_files.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Search Files";
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



	//-------------------------------------------------------Folder Search-------------------------------------------------------



	public static void folder_search() throws Exception
	{

		try
		{
			actions = new Actions(driver);

			WebElement my_element = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='searchInput']//input")));

			my_element.sendKeys("Bariven");
			my_element.sendKeys(Keys.ENTER); 
			Thread.sleep(3000);

			click("details_icon");
			List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='filesfolders col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
			List<String> all_files_list = new ArrayList <> ();


			for(WebElement each_file : file_container) {

				each_file.click();
				Thread.sleep(1000);

				String details_bar_without_hover_file_name = getPageObject("full_file_name").getText();

				if(details_bar_without_hover_file_name.contains("..."))
				{
					for(int i=0;i<20;i++)
					{
						WebElement details_bar_file_name_hover = getPageObject("full_file_name");
						actions.moveToElement(details_bar_file_name_hover).build().perform();

						if(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed())
						{
							String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
							if(details_bar_with_hover_file_name.contains("Bariven"))
							{
								log.info("This folder name has searched word");
								Thread.sleep(2000);
								ATUReports.add("Folder search successfull","Folder search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							else
							{
								log.info("This folder name does'nt have searchd word");
								ATUReports.add("Folder search failed","Folder search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							//all_folders_list.add(element);
							System.out.println(details_bar_with_hover_file_name);
							System.out.println("----------------------------------");
							Thread.sleep(3000);
							all_files_list.add(details_bar_with_hover_file_name);

							break;
						}

					
					}
					}

					String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
					if(details_bar_with_hover_file_name.contains("Bariven"))
					{
						log.info("This folder name has searched word");
						Thread.sleep(2000);
						ATUReports.add("folder search successfull","folder search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					else
					{
						log.info("This folder name does'nt have searchd word");
						ATUReports.add("folder search failed","folder search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					//all_folders_list.add(element);
					System.out.println(details_bar_with_hover_file_name);
					System.out.println("----------------------------------");
					Thread.sleep(3000);

			
					all_files_list.add(details_bar_without_hover_file_name);
					Thread.sleep(3000);
				}

			
	

			
			System.out.println("Total number of folders : "+all_files_list.size());
			System.out.println(all_files_list);

			
	}

		


		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in searching folder.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "folder_search_Error.png");
			ATUReports.add("folder search failed","folder search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}



	//-------------------------------------------------------File Search-------------------------------------------------------



	public static void file_search() throws Exception
	{

		try
		{
			
			actions = new Actions(driver);
			WebElement my_element = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='searchInput']//input")));

			my_element.sendKeys("Bariven");
			my_element.sendKeys(Keys.ENTER); 
			Thread.sleep(3000);
			

			click("details_icon");
			List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='fileDisplay recentFilesDashboard col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
			List<String> all_files_list = new ArrayList <> ();


			for(WebElement each_file : file_container) {

				each_file.click();
				Thread.sleep(1000);

				String details_bar_without_hover_file_name = getPageObject("full_file_name").getText();

				if(details_bar_without_hover_file_name.contains("..."))
				{
					for(int i=0;i<20;i++)
					{
						WebElement details_bar_file_name_hover = getPageObject("full_file_name");
						actions.moveToElement(details_bar_file_name_hover).build().perform();
						
						
						
						if(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed())
						{
							String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
							
							if(details_bar_with_hover_file_name.contains("Bariven"))
							{
								
								log.info("This file name has searched word"+details_bar_with_hover_file_name);
								Thread.sleep(2000);
								ATUReports.add("File search successfull","File search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
								
							}
							
							else
							{
								log.info("This file name does'nt have searchd word"+details_bar_with_hover_file_name);
								ATUReports.add("File search failed","File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							
							//all_folders_list.add(element);
							System.out.println(details_bar_with_hover_file_name);
							System.out.println("----------------------------------");
							Thread.sleep(3000);
							all_files_list.add(details_bar_with_hover_file_name);
							break;
							
						}

					}
				
				

					//String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
					if(details_bar_without_hover_file_name.contains("Bariven"))
					{
						log.info("This file name has searched word"+details_bar_without_hover_file_name);
						Thread.sleep(2000);
						ATUReports.add("File search successfull","File search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					else
					{
						log.info("This file name does'nt have searchd word"+details_bar_without_hover_file_name);
						ATUReports.add("File search failed","File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					//all_folders_list.add(element);
					System.out.println(details_bar_without_hover_file_name);
					System.out.println("----------------------------------");
					Thread.sleep(3000);
					

				}
			

				else
				{
					System.out.println(details_bar_without_hover_file_name);
					all_files_list.add(details_bar_without_hover_file_name);
					Thread.sleep(3000);
				}

			
			
	
				

			
			System.out.println("Total number of files : "+all_files_list.size());
			System.out.println(all_files_list);


			}

		}
	
		

		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in searching file.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "file_search_Error.png");
			ATUReports.add("File search failed","File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	


	//-------------------------------------------------------Folder Tag Search-------------------------------------------------------



	public static void folder_tag_search() throws Exception
	{

		try
		{
			actions = new Actions(driver);

			click("search_arrow_down");
			Thread.sleep(2000);
			getPageObject("tag_search").sendKeys("retention");
			click("search_button");


			Thread.sleep(3000);

			click("details_icon");
			List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='filesfolders col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
			List<String> all_folder_tags_list = new ArrayList <> ();


			for(WebElement each_file : file_container) {

				each_file.click();
				Thread.sleep(1000);

				String details_bar_folder_tag_name = getPageObject("details_tags_text").getText();

				if(details_bar_folder_tag_name.contains("retention"))
				{
					log.info("This folder name has searched tag");
					Thread.sleep(2000);
					ATUReports.add("Folder tag search successfull","Folder tag search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				}
				else
				{
					log.info("This folder name does'nt have searchd tag");
					ATUReports.add("Folder tag search failed","Folder tag search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				}
				//all_folders_list.add(element);
				System.out.println(details_bar_folder_tag_name);
				System.out.println("----------------------------------");
				Thread.sleep(3000);
				all_folder_tags_list.add(details_bar_folder_tag_name);

			}

			System.out.println("Total number of folders with tags : "+all_folder_tags_list.size());
			System.out.println(all_folder_tags_list);
		}




		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in searching file.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "file_search_Error.png");
			ATUReports.add("File search failed","File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}



	//-------------------------------------------------------File Tag Search-------------------------------------------------------


	public static void file_tag_search() throws Exception
	{

		try
		{
			actions = new Actions(driver);

			click("details_icon");
			List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='fileDisplay recentFilesDashboard col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
			List<String> all_file_tags_list = new ArrayList <> ();


			for(WebElement each_file : file_container) {

				each_file.click();
				Thread.sleep(1000);

				String details_bar_file_tag_name = getPageObject("details_tags_text").getText();


				if(details_bar_file_tag_name.contains("retention"))
				{
					log.info("This file name has searched word");
					Thread.sleep(2000);
					ATUReports.add("file tag search successfull","file tag search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				}
				else
				{
					log.info("This file name does'nt have searchd word");
					ATUReports.add("file tag search failed","file tag search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				}
				//all_folders_list.add(element);
				System.out.println(details_bar_file_tag_name);
				System.out.println("----------------------------------");
				Thread.sleep(3000);
				all_file_tags_list.add(details_bar_file_tag_name);

			}

			System.out.println("Total number of files with tags : "+all_file_tags_list.size());
			System.out.println(all_file_tags_list);



		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in searching file.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "file_search_Error.png");
			ATUReports.add("File search failed","File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}




	//-------------------------------------------------------Photo & Image File Type Search-------------------------------------------------------
	
	public static void file_type_search_photos_images() throws Exception
	{

		try
		{
			actions = new Actions(driver);

			click("search_arrow_down");
			Thread.sleep(3000);
			Select s=new Select(getPageObjectById("file_type_drop_down"));
			s.selectByVisibleText("Photos & image");
			WebElement selected_value = s.getFirstSelectedOption();
			String selected_value_text = selected_value.getText();
			log.info("Selected drop down value is : "+selected_value_text);
			click("search_button");
			Thread.sleep(5000);

			click("details_icon");
			List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='fileDisplay recentFilesDashboard col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
			List<String> all_files_list = new ArrayList <> ();


			for(WebElement each_file : file_container) {

				each_file.click();
				Thread.sleep(1000);

				String details_bar_without_hover_file_name = getPageObject("full_file_name").getText();

				if(details_bar_without_hover_file_name.contains("..."))
				{
					for(int i=0;i<20;i++)
					{
						WebElement details_bar_file_name_hover = getPageObject("full_file_name");
						actions.moveToElement(details_bar_file_name_hover).build().perform();

						if(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed())
						{
							String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
							if(details_bar_with_hover_file_name.contains(".png") || details_bar_with_hover_file_name.contains(".jpg") || details_bar_with_hover_file_name.contains(".jpeg") || details_bar_with_hover_file_name.contains(".gif"))
							{
								log.info("Selected file is a photo or image file: "+details_bar_with_hover_file_name);
								Thread.sleep(2000);
								ATUReports.add("mouse hover photo & image search successfull","mouse hover photo & image search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							else
							{
								log.info("Selected file is not a photo or image file: "+details_bar_with_hover_file_name);
								ATUReports.add("mouse hover photo & image search failed","mouse hover photo & image search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							//all_folders_list.add(element);
							//System.out.println(details_bar_with_hover_file_name);
							System.out.println("----------------------------------");
							Thread.sleep(3000);
							all_files_list.add(details_bar_with_hover_file_name);

							break;
						}

					
					//all_folders_list.add(element);
					System.out.println(details_bar_without_hover_file_name);
					System.out.println("----------------------------------");
					Thread.sleep(3000);

				}
				}

				else
				{
					if(details_bar_without_hover_file_name.contains(".png") || details_bar_without_hover_file_name.contains(".jpg") || details_bar_without_hover_file_name.contains(".jpeg") || details_bar_without_hover_file_name.contains(".gif"))
					{
						log.info("Selected file is a photo or image file: "+details_bar_without_hover_file_name);
						Thread.sleep(2000);
						ATUReports.add("Without mouse hover photo & image search successfull","Without mouse hover photo & image search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					else
					{
						log.info("Selected file is not a photo or image file: "+details_bar_without_hover_file_name);
						ATUReports.add("Without mouse hover photo & image search failed","Without mouse hover photo & image search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					
					
					System.out.println(details_bar_without_hover_file_name);
					all_files_list.add(details_bar_without_hover_file_name);
					Thread.sleep(3000);
				}



				/*

				if(all_file_names.contains(" "))
				{
					List<String> files_with_space = new ArrayList<String>();
					files_with_space.add(all_file_names);
					System.out.println(files_with_space);
					System.out.println("------------------------------");
				}
				else 
				{
					List<String> files_without_space = new ArrayList<String>();
					files_without_space.add(all_file_names);
					System.out.println(files_without_space);
					System.out.println("------------------------------");
				}

				 */

			}
			System.out.println("Total number of photo & image files : "+all_files_list.size());
			System.out.println(all_files_list);




		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in photo & image file search.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "photo_image_file_search_Error.png");
			ATUReports.add("photo & image File search failed","photo & imag File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}


	//-------------------------------------------------------Pdf File Type Search-------------------------------------------------------
	
	public static void file_type_search_pdf() throws Exception
	{

		try
		{
			actions = new Actions(driver);

			click("search_arrow_down");
			Thread.sleep(5000);
			Select s=new Select(getPageObjectById("file_type_drop_down"));
			s.selectByVisibleText("PDFs");
			WebElement selected_value = s.getFirstSelectedOption();
			String selected_value_text = selected_value.getText();
			log.info("Selected drop down value is : "+selected_value_text);
			click("search_button");
			Thread.sleep(3000);

			click("details_icon");
			List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='fileDisplay recentFilesDashboard col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
			List<String> all_files_list = new ArrayList <> ();


			for(WebElement each_file : file_container) {

				each_file.click();
				Thread.sleep(1000);

				String details_bar_without_hover_file_name = getPageObject("full_file_name").getText();

				if(details_bar_without_hover_file_name.contains("..."))
				{
					for(int i=0;i<20;i++)
					{
						WebElement details_bar_file_name_hover = getPageObject("full_file_name");
						actions.moveToElement(details_bar_file_name_hover).build().perform();

						if(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed())
						{
							String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
							if(details_bar_with_hover_file_name.contains(".pdf"))
							{
								log.info("Selected file is a pdf files: "+details_bar_with_hover_file_name);
								Thread.sleep(2000);
								ATUReports.add("mouse hover pdf search successfull","mouse hover pdf search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							else
							{
								log.info("Selected file is not a pdf files: "+details_bar_with_hover_file_name);
								ATUReports.add("mouse hover pdf search failed","mouse hover pdf search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							//all_folders_list.add(element);
							//System.out.println(details_bar_with_hover_file_name);
							System.out.println("----------------------------------");
							Thread.sleep(3000);
							all_files_list.add(details_bar_with_hover_file_name);

							break;
						}


				}
				}

				else
				{
					if(details_bar_without_hover_file_name.contains(".pdf"))
					{
						log.info("Selected file is a pdf files: "+details_bar_without_hover_file_name);
						Thread.sleep(2000);
						ATUReports.add("Without mouse hover pdf search successfull","Without mouse hover photo & image search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					else
					{
						log.info("Selected file is not a pdf files: "+details_bar_without_hover_file_name);
						ATUReports.add("Without mouse hover pdf search failed","Without mouse hover pdf search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					
					System.out.println(details_bar_without_hover_file_name);
					all_files_list.add(details_bar_without_hover_file_name);
					Thread.sleep(3000);
				}



				/*

				if(all_file_names.contains(" "))
				{
					List<String> files_with_space = new ArrayList<String>();
					files_with_space.add(all_file_names);
					System.out.println(files_with_space);
					System.out.println("------------------------------");
				}
				else 
				{
					List<String> files_without_space = new ArrayList<String>();
					files_without_space.add(all_file_names);
					System.out.println(files_without_space);
					System.out.println("------------------------------");
				}

				 */

			}
			System.out.println("Total number of pdf files : "+all_files_list.size());
			System.out.println(all_files_list);




		}
		catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in pdf file search.",e);	
			//log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "pdf_file_search_Error.png");
			ATUReports.add("PDF File search failed","PDF File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}



	}

	
	//------------------------------------------------------- Documents File Type Search-------------------------------------------------------
	
		public static void file_type_search_documents() throws Exception
		
		{

			try
			{
				actions = new Actions(driver);

				click("search_arrow_down");
				Thread.sleep(5000);
				Select s=new Select(getPageObjectById("file_type_drop_down"));
				s.selectByVisibleText("Documents");
				WebElement selected_value = s.getFirstSelectedOption();
				String selected_value_text = selected_value.getText();
				log.info("Selected drop down value is : "+selected_value_text);
				click("search_button");
				Thread.sleep(3000);

				click("details_icon");
				List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='fileDisplay recentFilesDashboard col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
				List<String> all_files_list = new ArrayList <> ();


				for(WebElement each_file : file_container) {

					each_file.click();
					Thread.sleep(1000);

					String details_bar_without_hover_file_name = getPageObject("full_file_name").getText();

					if(details_bar_without_hover_file_name.contains("..."))
					{
						for(int i=0;i<20;i++)
						{
							WebElement details_bar_file_name_hover = getPageObject("full_file_name");
							actions.moveToElement(details_bar_file_name_hover).build().perform();

							if(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed())
							{
								String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
								if(details_bar_with_hover_file_name.contains(".doc") || details_bar_with_hover_file_name.contains(".docx"))
								{
									log.info("Selected file is a document file: "+details_bar_with_hover_file_name);
									Thread.sleep(2000);
									ATUReports.add("mouse hover document search successfull","mouse hover document search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

								}
								else
								{
									log.info("Selected file is not a document file: "+details_bar_with_hover_file_name);
									ATUReports.add("mouse hover document search failed","mouse hover document search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

								}
								//all_folders_list.add(element);
								//System.out.println(details_bar_with_hover_file_name);
								System.out.println("----------------------------------");
								Thread.sleep(3000);
								all_files_list.add(details_bar_with_hover_file_name);

								break;
							}
							


					}
					}

					else
					{

						if(details_bar_without_hover_file_name.contains(".doc") || details_bar_without_hover_file_name.contains(".docx"))
						{
							log.info("Selected file is a document file: "+details_bar_without_hover_file_name);
							Thread.sleep(2000);
							ATUReports.add("Without mouse hover document search successfull","Without mouse hover document search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

						}
						else
						{
							log.info("Selected file is not a document file: "+details_bar_without_hover_file_name);
							ATUReports.add("Without mouse hover document search failed","Without mouse hover document search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

						}
						//all_folders_list.add(element);
						//System.out.println(details_bar_without_hover_file_name);
						System.out.println("----------------------------------");
						Thread.sleep(3000);
						
						//System.out.println(details_bar_without_hover_file_name);
						all_files_list.add(details_bar_without_hover_file_name);
						Thread.sleep(3000);
					}



					/*

					if(all_file_names.contains(" "))
					{
						List<String> files_with_space = new ArrayList<String>();
						files_with_space.add(all_file_names);
						System.out.println(files_with_space);
						System.out.println("------------------------------");
					}
					else 
					{
						List<String> files_without_space = new ArrayList<String>();
						files_without_space.add(all_file_names);
						System.out.println(files_without_space);
						System.out.println("------------------------------");
					}

					 */

				}
				System.out.println("Total number of document files : "+all_files_list.size());
				System.out.println(all_files_list);




			}
			catch (Exception e) {

				System.out.println("errror at: ");
				e.printStackTrace();
				log.error("Error in document file search.",e);	
				//log.fatal(e.getMessage(),e);
				captureScreenShot(ScreenShotsFilePath + "pdf_file_search_Error.png");
				ATUReports.add("document File search failed","document File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}



		}
	
	
		
		//------------------------------------------------------- Spreadsheets File Type Search-------------------------------------------------------
		
			public static void file_type_search_spreadsheets() throws Exception
			
			{

				try
				{
					actions = new Actions(driver);

					click("search_arrow_down");
					Thread.sleep(5000);
					Select s=new Select(getPageObjectById("file_type_drop_down"));
					s.selectByVisibleText("Spreedsheets");
					WebElement selected_value = s.getFirstSelectedOption();
					String selected_value_text = selected_value.getText();
					log.info("Selected drop down value is : "+selected_value_text);
					click("search_button");
					Thread.sleep(3000);

					click("details_icon");
					List<WebElement> file_container = driver.findElements(By.xpath("//div[@class='fileDisplay recentFilesDashboard col-sm-6 col-md-6 col-lg-6 col-xl-3 col-12']"));
					List<String> all_files_list = new ArrayList <> ();


					for(WebElement each_file : file_container) {

						each_file.click();
						Thread.sleep(1000);

						String details_bar_without_hover_file_name = getPageObject("full_file_name").getText();

						if(details_bar_without_hover_file_name.contains("..."))
						{
							for(int i=0;i<20;i++)
							{
								WebElement details_bar_file_name_hover = getPageObject("full_file_name");
								actions.moveToElement(details_bar_file_name_hover).build().perform();

								if(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed())
								{
									String details_bar_with_hover_file_name = getPageObject("full_file_name_popup").getText();
									if(details_bar_with_hover_file_name.contains(".csv") || details_bar_with_hover_file_name.contains(".xls") || details_bar_with_hover_file_name.contains(".xlsx"))
									{
										log.info("Selected file is a Spreadsheet file: "+details_bar_with_hover_file_name);
										Thread.sleep(2000);
										ATUReports.add("mouse hover spreadsheet search successfull","mouse hover spreadsheet search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

									}
									else
									{
										log.info("Selected file is not a spreadsheet file: "+details_bar_with_hover_file_name);
										ATUReports.add("mouse hover spreadsheet search failed","mouse hover spreadsheet search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

									}
									//all_folders_list.add(element);
									//System.out.println(details_bar_with_hover_file_name);
									System.out.println("----------------------------------");
									Thread.sleep(3000);
									all_files_list.add(details_bar_with_hover_file_name);

									break;
								}
								


						}
						}

						else
						{

							if(details_bar_without_hover_file_name.contains(".csv") || details_bar_without_hover_file_name.contains(".xls") || details_bar_without_hover_file_name.contains(".xlsx"))
							{
								log.info("Selected file is a spreadsheet file: "+details_bar_without_hover_file_name);
								Thread.sleep(2000);
								ATUReports.add("Without mouse hover spreadsheet search successfull","Without mouse hover spreadsheet search",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							else
							{
								log.info("Selected file is not a spreadsheet file: "+details_bar_without_hover_file_name);
								ATUReports.add("Without mouse hover spreadsheet search failed","Without mouse hover spreadsheet search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

							}
							//all_folders_list.add(element);
							//System.out.println(details_bar_without_hover_file_name);
							System.out.println("----------------------------------");
							Thread.sleep(3000);
							
							//System.out.println(details_bar_without_hover_file_name);
							all_files_list.add(details_bar_without_hover_file_name);
							Thread.sleep(3000);
						}



						/*

						if(all_file_names.contains(" "))
						{
							List<String> files_with_space = new ArrayList<String>();
							files_with_space.add(all_file_names);
							System.out.println(files_with_space);
							System.out.println("------------------------------");
						}
						else 
						{
							List<String> files_without_space = new ArrayList<String>();
							files_without_space.add(all_file_names);
							System.out.println(files_without_space);
							System.out.println("------------------------------");
						}

						 */

					}
					System.out.println("Total number of spreadsheet files : "+all_files_list.size());
					System.out.println(all_files_list);




				}
				catch (Exception e) {

					System.out.println("errror at: ");
					e.printStackTrace();
					log.error("Error in spreadsheet file search.",e);	
					//log.fatal(e.getMessage(),e);
					captureScreenShot(ScreenShotsFilePath + "spreadsheetfile_search_Error.png");
					ATUReports.add("spreadsheet File search failed","spreadsheet File search",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
