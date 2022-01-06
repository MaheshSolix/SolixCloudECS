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



public class MyFilesComments extends Navigation {

	static WebDriver driver;
	static Actions actions;

	private static Logger log = Logger.getLogger(MyFilesComments.class.getName());
	public static String Home_Page = "https://app.solixecsdev.com/login";

	public MyFilesComments(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Comments");
		setIndexPageDescription();
		setAuthorInfoForReports();
		log.info("--------------------Comments--------------------");
		captureScreenShot(ScreenShotsFilePath + "Comments.png");
	}

	private void setAuthorInfoForReports() {
		ATUReports.setAuthorInfo("Mahesh", Utils.getCurrentTime(),"2.0");
	}

	private void setIndexPageDescription() {
		ATUReports.indexPageDescription = "Comments";
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


		
		
		
		
		
		
		
		
			//-------------------------------------------------------Add Comments to folder-------------------------------------------------------

			public static void add_comments_to_folder() throws Exception
			{

				try
				{
					actions = new Actions(driver);
					
					
					driver.get(Home_Page);
					
					sendKeys("login_username","ravovov767@onzmail.com");
					
					click("login_next");
					
					WebDriverWait wait = new WebDriverWait(driver, 60);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));
					
					sendKeys("login_password","Test@123");
					
					click("login_button");
					log.info("login success");
					
					
					
					WebElement elementLocator = getPageObject("first_folder_infolder");
					actions.contextClick(elementLocator).perform();
					Thread.sleep(2000);
					click("right_click_folder_comments");
					
					
					/*
					// ----------- Commented code - Start
					WebElement folder_container = getPageObject("folder_section");
					List<WebElement> each_folder_tag=folder_container.findElements(By.tagName("div"));

					List<WebElement> all_folders_list = new ArrayList <> ();

					for(int i=0;i<each_folder_tag.size();i++)
					{
					
					
						WebElement div =each_folder_tag.get(i);
						String selected_folder_name = each_folder_tag.get(i).getText();
						log.info("Selected folder name is "+selected_folder_name);
						actions.contextClick(div).perform();
						click("comments_link");
						log.info("clicked on comments");
						break;
					}
					
					//Enter folder name with date

					DateFormat dateFormat = new SimpleDateFormat("MMM/dd HH:mm");

					//get current date time with Date()
					Date date = new Date();

					// Now format the date
					String dateFormatted= dateFormat.format(date);
										
						//Keyword 1
						getPageObject("comments_text_area").sendKeys("Folder Comment - A user is any person or role on your team with a unique email address."+dateFormatted);
						click("comments_save_button");
						Thread.sleep(3000);
						
						WebElement comments_container = driver.findElement(By.xpath("//div[@class='comments-list']"));
						List<WebElement> each_comment_tag=comments_container.findElements(By.tagName("div"));

						for(int i=0;i<each_comment_tag.size();i++)
						{
							WebElement div =each_comment_tag.get(i);
							String selected_comment_text = each_comment_tag.get(i).getText();
							log.info("This comment added to the folder---->"+selected_comment_text);
							
						
						if(comments_container.getText().contains(selected_comment_text))
						{
							log.info("--------------------------------------------Comment added to the folder successfully--------------------------------------------");
							captureScreenShot(ScreenShotsFilePath + "comment_added_to_the_folder.png");
							Thread.sleep(2000);
							ATUReports.add("comment added to the folder successfull","Comment added to the folder",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							break;
						}
						
						else
						{

							log.error("Error in adding comment to folder");	
							captureScreenShot(ScreenShotsFilePath + "add_comment_to_folder_failed.png");
							ATUReports.add("Add comment to folder failed","Add comment to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							break;
						}
						}
						// ----------- Commented code - End
						 */

					
					DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH:mm");

					//get current date time with Date()
					Date date = new Date();

					// Now format the date
					String dateFormatted= dateFormat.format(date);
					
					
					String text_box_text= "Folder Comment - A user is any person or role on your team with a unique email address."+dateFormatted;
					sendKeys("add_comment_textarea",text_box_text);
					
					Thread.sleep(2000);
					log.info("Text box text ------>"+text_box_text);
					click("tag_save_button");
					
					//Comment added confirmation
					
					WebDriverWait wait3 = new WebDriverWait(driver, 60);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
					Thread.sleep(3000);
					
					if(getPageObject("starred_toast").getText().contains("Successfully saved the comment"))
					{
					
						Thread.sleep(2000);
						click("toast_close");
						
						
						if(getPageObject("folder_comment_text").getText().contains(text_box_text))
						
						
						log.info("--------------------------------------------Comment added successfully--------------------------------------------");
						
						captureScreenShot(ScreenShotsFilePath + "folder_comment_saved_success.png");
						Thread.sleep(2000);
						
						ATUReports.add("Folder comment saved successfull","Single file comment saved",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
						
					}


					else
					{

						log.error("Error in Folder add comment");	
						captureScreenShot(ScreenShotsFilePath + "Folder_add_comment_failed_Error.png");
						Thread.sleep(2000);
						ATUReports.add("Folder add comment failed","Folder add comment",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
					}


				}
				catch (Exception e) {

					System.out.println("errror at: ");
					e.printStackTrace();
					log.error("Error in adding comment to folder.",e);	
					//log.fatal(e.getMessage(),e);
					captureScreenShot(ScreenShotsFilePath + "add_comment_to_folder_failed.png");
					ATUReports.add("Add comment to folder failed","Add comment to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}



			}
			
			
			
			
			//-------------------------------------------------------Edit Comments to folder-------------------------------------------------------

			public static void edit_comments_to_folder() throws Exception
			{

				try
				{
					actions = new Actions(driver);
					
					
					/*
					driver.get(Home_Page);
					
					sendKeys("login_username","ravovov767@onzmail.com");
					
					click("login_next");
					
					WebDriverWait wait = new WebDriverWait(driver, 60);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));
					
					sendKeys("login_password","Test@123");
					
					click("login_button");
					log.info("login success");
					
					*/
							
							Thread.sleep(2000);
							click("comment_3dots");
							Thread.sleep(2000);
							click("edit_comment");
							
							Thread.sleep(2000);
							
							getPageObject("edit_comment_textarea").clear();
							
							//Enter folder name with date

							DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH:mm");

							//get current date time with Date()
							Date date = new Date();

							// Now format the date
							String dateFormatted= dateFormat.format(date);
							
							sendKeys("edit_comment_textarea","Edited - Folder Comment - A user is any person or role on your team with a unique email address."+dateFormatted);
							String text_box_text= "Edited - Folder Comment - A user is any person or role on your team with a unique email address."+dateFormatted;
							
							sendKeys("edit_comment_textarea",text_box_text);
							Thread.sleep(1000);
							click("edit_comment_save_button");
							Thread.sleep(2000);
							
							WebDriverWait wait2 = new WebDriverWait(driver, 60);
							wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
							
							
							
							if(getPageObject("folder_comment_text").getText().contains(text_box_text))
							{
							
								
						
									
								log.info("--------------------------------------------Comment edited successfully--------------------------------------------");
								Thread.sleep(2000);
								
								captureScreenShot(ScreenShotsFilePath + "folder_comment_edited_success.png");
								Thread.sleep(2000);
								
								ATUReports.add("folder comment edited successfull","folder comment edited",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
								
							}


							else
							{

								log.error("Error in folder edit comment");	
								captureScreenShot(ScreenShotsFilePath + "folder_edit_comment_failed_Error.png");
								Thread.sleep(2000);
								ATUReports.add("folder edited comment failed","folder edited comment",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
							}
							
							
					/*
					//----------Commented code - start
					WebElement comments_container = driver.findElement(By.xpath("//div[@class='comments-list']"));
					List<WebElement> each_comment_tag=comments_container.findElements(By.tagName("div"));

					for(int i=0;i<each_comment_tag.size();i++)
					{
						WebElement div =each_comment_tag.get(i);
						break;
						
					}
					Thread.sleep(2000);
					getPageObject("added_comments_toggle").click();
					getPageObject("comment_edit").click();
					getPageObject("comment_edit_text_area").sendKeys(" Additionally added comment text.");
					click("edit_comment_save_button");
					Thread.sleep(3000);
					WebElement comments_container1 = driver.findElement(By.xpath("//div[@class='comments-list']"));
					List<WebElement> each_comment_tag1=comments_container1.findElements(By.tagName("div"));

					for(int j=0;j<each_comment_tag.size();j++)
					{
						WebElement div1 =each_comment_tag1.get(j);
						String selected_comment_text1 = each_comment_tag1.get(j).getText();
						log.info("Edited comment added to the folder---->"+selected_comment_text1);
					
					if(comments_container1.getText().contains(selected_comment_text1))
					{
						log.info("--------------------------------------------Edited comment saved for the folder successfully--------------------------------------------");
						captureScreenShot(ScreenShotsFilePath + "comment_added_to_the_folder.png");
						Thread.sleep(2000);
						ATUReports.add("edited comment added to the folder successfull","edited comment added to the folder",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						break;
					}

					else
					{

						log.error("Error in edit comment to folder");	
						captureScreenShot(ScreenShotsFilePath + "edit_comment_to_folder_failed.png");
						ATUReports.add("edit comment to files failed","edit comment to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						break;
					}
					}
					//----------Commented code - end
					*/
					
					
				}
				catch (Exception e) {

					System.out.println("errror at: ");
					e.printStackTrace();
					log.error("Error in editing comments to folder.",e);	
					//log.fatal(e.getMessage(),e);
					captureScreenShot(ScreenShotsFilePath + "edit_comment_to_folder_failed.png");
					ATUReports.add("Edit comment to folder failed","Edit comment to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}



			}
			
			

		
			//-------------------------------------------------------Delete Comments to folder-------------------------------------------------------

				public static void delete_comments_to_folder() throws Exception
				{

					try
					{
						
						actions = new Actions(driver);
						
						
						/*
						driver.get(Home_Page);
						
						sendKeys("login_username","ravovov767@onzmail.com");
						
						click("login_next");
						
						WebDriverWait wait = new WebDriverWait(driver, 60);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));
						
						sendKeys("login_password","Test@123");
						
						click("login_button");
						log.info("login success");
						
						
						WebElement elementLocator = getPageObject("first_folder_infolder");
						actions.contextClick(elementLocator).perform();
						Thread.sleep(2000);
						click("right_click_folder_comments");
						*/
						
						
						Thread.sleep(2000);
						String first_folder_comment_text_before_delete = getPageObject("folder_comment_text").getText();
								
						Thread.sleep(2000);
						click("comment_3dots");
						Thread.sleep(2000);
						click("delete_comment");
						Thread.sleep(3000);
						
						WebDriverWait wait2 = new WebDriverWait(driver, 60);
						wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
						
						
						
						String first_folder_comment_text_after_delete = getPageObject("folder_comment_text").getText();

						if(first_folder_comment_text_before_delete!= first_folder_comment_text_after_delete)
						{
						
							
					
							click("toast_close");
							log.info("--------------------------------------------Comment deleted successfully--------------------------------------------");
							Thread.sleep(2000);
							
							captureScreenShot(ScreenShotsFilePath + "folder_comment_deleted_success.png");
							Thread.sleep(2000);
							
							ATUReports.add("folder comment deleted successfull","folder comment deleted",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							click("close_comment_pop");
							
						}


						else
						{
							
							log.error("Error in folder deleted comment");	
							captureScreenShot(ScreenShotsFilePath + "folder_deleted_comment_failed_Error.png");
							Thread.sleep(2000);
							ATUReports.add("folder deleted comment failed","folder deleted comment",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							click("close_comment_pop");
						}
						
								
						/*		
						WebElement comments_container = driver.findElement(By.xpath("//div[@class='comments-list']"));
						List<WebElement> each_comment_tag=comments_container.findElements(By.tagName("div"));

						for(int i=0;i<each_comment_tag.size();i++)
						{
							WebElement div =each_comment_tag.get(i);
							String selected_comment_text = each_comment_tag.get(i).getText();
						
						
							getPageObject("added_comments_toggle").click();
							getPageObject("delete_comment").click();
							Thread.sleep(2000);
							
							if(comments_container.getText().contains(selected_comment_text))
							{
								log.error("Error in deleting comment to folders");	
								click("close_comment_popup");
								captureScreenShot(ScreenShotsFilePath + "delete_comment_to_folders_failed.png");
								ATUReports.add("delete comment to folders failed","delete comment to folders",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
								break;
								
							}

							else
							{

								log.info("--------------------------------------------deleted comment for the folders successfully--------------------------------------------");
								click("close_comment_popup");
								captureScreenShot(ScreenShotsFilePath + "deleted_comment_to_the_folder.png");
								Thread.sleep(2000);
								ATUReports.add("deleted comment to the folder successfull","deleted comment to the folder",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								
								break;
							}
						
						
						}
						*/
						
						
						
						

					}
					catch (Exception e) {

						System.out.println("errror at: ");
						e.printStackTrace();
						log.error("Error in deleting comments to folder.",e);	
						//log.fatal(e.getMessage(),e);
						captureScreenShot(ScreenShotsFilePath + "delete_comment_to_folder_failed.png");
						ATUReports.add("deleted comment to folder failed","deleted comment to folder",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}

				}
			
	// -------------------------------------------------------Add Comments to file-------------------------------------------------------

	public static void add_comments_to_file() throws Exception {

		try {
			actions = new Actions(driver);
			
			
			/*
			driver.get(Home_Page);
			
			sendKeys("login_username","ravovov767@onzmail.com");
			
			click("login_next");
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row form-group mb-2']")));
			
			sendKeys("login_password","Test@123");
			
			click("login_button");
			log.info("login success");
			*/
			
			
			
			
			
			//click("toast_close");
			Thread.sleep(1000);
			click("recent_page");
			Thread.sleep(2000);
			click("goto_myfiles");
			WebDriverWait wait5 = new WebDriverWait(driver, 120);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Folders')]")));
			
			
			
			
			
			
			//click("toast_close");
			
			WebElement elementLocator5 = getPageObject("first_file");
			actions.contextClick(elementLocator5).perform();
			Thread.sleep(2000);
			click("file_right_click_add_comment");

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH:mm");

			// get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted = dateFormat.format(date);

			sendKeys("add_comment_textarea",
					"File Comment - A user is any person or role on your team with a unique email address."
							+ dateFormatted);
			String text_box_text = "File Comment - A user is any person or role on your team with a unique email address."
					+ dateFormatted;
			Thread.sleep(2000);
			log.info("Text box text ------>" + text_box_text);
			click("tag_save_button");

			// Comment added confirmation

			WebDriverWait wait11 = new WebDriverWait(driver, 60);
			wait11.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			Thread.sleep(3000);

			if (getPageObject("starred_toast").getText().contains("Successfully saved the comment")) {

				{
				Thread.sleep(2000);
				click("toast_close");
				Thread.sleep(2000);
				log.info("-----------Success toast displayed----------------");

				}
				
				/*
				WebElement elementLocator1 = getPageObject("first_file");
				actions.contextClick(elementLocator1).perform();
				Thread.sleep(2000);
				click("file_right_click_add_comment");
				*/
				
				if (getPageObject("folder_comment_text").getText().contains(text_box_text))
				{
					log.info("--------------------------------------------Comment added successfully--------------------------------------------");

				captureScreenShot(ScreenShotsFilePath + "file_comment_saved_success.png");
				Thread.sleep(2000);

				ATUReports.add("File comment saved successfull", "File comment saved", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}

			else {

				log.error("Error in File add comment");
				captureScreenShot(ScreenShotsFilePath + "File_add_comment_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("File add comment failed", "File add comment", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in adding comment to File.", e);
			// log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "add_comment_to_File_failed.png");
			ATUReports.add("Add comment to File failed", "Add comment to File", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	// -------------------------------------------------------Edit Comments to folder-------------------------------------------------------

	public static void edit_comments_to_file() throws Exception {

		try {
			actions = new Actions(driver);
			// driver.get(Home_Page);

			// sendKeys("login_username","ravovov767@onzmail.com");
			// sendKeys("login_password","Test@123");
			// click("login_button");
			// log.info("login success");

			// WebElement elementLocator = getPageObject("first_folder");
			// actions.contextClick(elementLocator).perform();
			// Thread.sleep(2000);
			// click("right_click_folder_comments");

			Thread.sleep(2000);
			click("comment_3dots");
			Thread.sleep(2000);
			click("edit_comment");

			getPageObject("edit_comment_textarea").clear();

			// Enter folder name with date

			DateFormat dateFormat = new SimpleDateFormat("MMM-dd HH:mm");

			// get current date time with Date()
			Date date = new Date();

			// Now format the date
			String dateFormatted = dateFormat.format(date);

			sendKeys("edit_comment_textarea",
					"Edited - File Comment - A user is any person or role on your team with a unique email address."
							+ dateFormatted);
			String text_box_text = "Edited - File Comment - A user is any person or role on your team with a unique email address."
					+ dateFormatted;

			click("edit_comment_save_button");
			Thread.sleep(2000);

			// Edit Comment confirmation - commented code due to no toast message
			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			
			if (getPageObject("folder_comment_text").getText().contains(text_box_text)) {

				log.info(
						"--------------------------------------------Comment edited successfully--------------------------------------------");
				Thread.sleep(2000);

				captureScreenShot(ScreenShotsFilePath + "file_comment_edited_success.png");
				Thread.sleep(2000);

				ATUReports.add("File comment edited successfull", "File comment edited", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {

				log.error("Error in File edit comment");
				captureScreenShot(ScreenShotsFilePath + "file_edit_comment_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("File edited comment failed", "File edited comment", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in editing comments to File.", e);
			// log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "edit_comment_to_file_failed.png");
			ATUReports.add("Edit comment to File failed", "Edit comment to File", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	// -------------------------------------------------------Delete Comments to folder-------------------------------------------------------

	public static void delete_comments_to_file() throws Exception {

		try {

			actions = new Actions(driver);
			// driver.get(Home_Page);

			// sendKeys("login_username","ravovov767@onzmail.com");
			// sendKeys("login_password","Test@123");
			// click("login_button");
			// log.info("login success");

			// WebElement elementLocator = getPageObject("first_folder");
			// actions.contextClick(elementLocator).perform();
			// Thread.sleep(2000);
			// click("right_click_folder_comments");

			Thread.sleep(2000);
			String first_folder_comment_text_before_delete = getPageObject("folder_comment_text").getText();

			Thread.sleep(2000);
			click("comment_3dots");
			Thread.sleep(2000);
			click("delete_comment");
			Thread.sleep(3000);

			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Vue-Toastification__container bottom-left']")));
			
			String first_folder_comment_text_after_delete = getPageObject("folder_comment_text").getText();

			if (first_folder_comment_text_before_delete != first_folder_comment_text_after_delete) {
				
				click("toast_close");
				log.info(
						"--------------------------------------------Comment deleted successfully--------------------------------------------");
				Thread.sleep(2000);

				captureScreenShot(ScreenShotsFilePath + "file_comment_deleted_success.png");
				Thread.sleep(2000);

				ATUReports.add("File comment deleted successfull", "File comment deleted", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			else {
				click("toast_close");
				log.error("Error in File deleted comment");
				captureScreenShot(ScreenShotsFilePath + "file_deleted_comment_failed_Error.png");
				Thread.sleep(2000);
				ATUReports.add("File deleted comment failed", "File deleted comment", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {

			System.out.println("errror at: ");
			e.printStackTrace();
			log.error("Error in deleting comments to File.", e);
			// log.fatal(e.getMessage(),e);
			captureScreenShot(ScreenShotsFilePath + "delete_comment_to_file_failed.png");
			ATUReports.add("deleted comment to File failed", "deleted comment to File", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
