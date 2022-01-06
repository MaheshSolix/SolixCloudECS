package arca.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import arca.common.Navigation;
import arca.pages.BusinessPremiumSignup;
import arca.pages.BusinessStandardSignup;
import arca.pages.CollectionsPage;
import arca.pages.CollectionsPageShare;
import arca.pages.MyFilesComments;
import arca.pages.MyFilesCopy;
import arca.pages.MyFilesDelete;
import arca.pages.MyFilesDownload;
import arca.pages.MyFilesMove;
import arca.pages.MyFilesReminders;
import arca.pages.MyFilesStarred;
import arca.pages.HomePage;
import arca.pages.Signup;
import arca.pages.PermanentDelete;
import arca.pages.PersonalPremiumSignup;
import arca.pages.PersonalSignUp;
import arca.pages.PlusButtonUpload;
import arca.pages.RecentPageCollections;
import arca.pages.RecentPageComments;
import arca.pages.RecentPageCopyMove;
import arca.pages.RecentPageDelete;
import arca.pages.RecentPageShare;
import arca.pages.RecentPageTags;
import arca.pages.Retention;
import arca.pages.RightClickUpload;
import arca.pages.Search;
import arca.pages.ShareFileWithUser;
import arca.pages.SourceManager;
import arca.pages.MyFilesTags;
import arca.pages.LegalHold;
import arca.pages.MyFilesCollections;
import arca.pages.Users;
import arca.pages.Groups;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testrecorder.ATUTestRecorder;




@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class })



public class BrowserHandlings{
	
	private static WebDriver driver;
	private static Logger log=Logger.getLogger(BrowserHandlings.class.getName());

	HomePage mb;	
	PersonalSignUp mb1;
	PersonalPremiumSignup mb2;
	BusinessStandardSignup mb3;
	BusinessPremiumSignup mb4;
	Signup mb5;
	RightClickUpload mb6;
	MyFilesStarred mb7;
	MyFilesTags mb8;
	MyFilesComments mb9;
	MyFilesDelete mb10;
	MyFilesDownload mb100;
	MyFilesCopy mb11;
	MyFilesMove mb12;
	RecentPageComments mb13;
	RecentPageTags mb14;
	RecentPageCopyMove mb15;
	ShareFileWithUser mb16;
	MyFilesCollections mb17;
	RecentPageCollections mb18;
	CollectionsPage mb19;
	RecentPageShare mb20;
	RecentPageDelete mb21;
	CollectionsPageShare mb22;
	MyFilesReminders mb23;
	
	
	ATUTestRecorder recorder;
	
	
	
	public void setUp()
	{
		
		try
		{   
		
			
			FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("network.proxy.type", 0);               
            driver = new FirefoxDriver();
			
			
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        
	}

	 public void openSpecificBrowser(String browserName) throws MalformedURLException
	 {
		 	String remoteWebDriverUrl="";
		 	if(browserName.toLowerCase().contains("remote"))
		 	{
		 		remoteWebDriverUrl=browserName.substring(browserName.indexOf('.')+1);
		 		browserName=browserName.split("\\.")[0];
		 		System.out.println("remoteWebDriverUrl:"+remoteWebDriverUrl);
		 	}
		 	String chromeDriverExt=".exe";
		 	String typeOfOs=System.getProperty("os.name").toLowerCase();
		 	if(typeOfOs.equals("linux"))
		 	chromeDriverExt="";
		 	
		 	
		 	System.out.println("browserName:"+browserName);
			switch(browserName.toLowerCase())
			{
			
			
				case "firefox":
				driver=new FirefoxDriver();
				break;
				case "chrome":
				
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+System.getProperty("file.separator")+"chromedriver"+chromeDriverExt);
				driver = new ChromeDriver();
		       
				
				
		        
		        break;
				case "ie":System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+System.getProperty("file.separator")+"IEDriverServer.exe");
		        //driver = new InternetExplorerDriver();
				driver = new EdgeDriver();
		        break;
				case "remotefirefox":
		        driver = new RemoteWebDriver(new URL(remoteWebDriverUrl),DesiredCapabilities.firefox());
		        break;
				case "remotechrome":
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+System.getProperty("file.separator")+"chromedriver.exe");	
			    driver = new RemoteWebDriver(new URL(remoteWebDriverUrl),DesiredCapabilities.chrome());
			    break;
				case "remoteie":
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+System.getProperty("file.separator")+"IEDriverServer.exe");
			    driver = new RemoteWebDriver(new URL(remoteWebDriverUrl),DesiredCapabilities.internetExplorer());
		        break;
			}
			
	 }

	
//added by pradeep
   public static String appendText;
   
	@Parameters("browserName")
	@BeforeTest
	public void beforetest(@Optional("browserName") String browserName) throws Exception
	{
		openSpecificBrowser(browserName); 
		DOMConfigurator.configure("log4j.xml");
		System.setProperty("atu.reporter.config", System.getProperty("user.dir") + System.getProperty("file.separator") + "atu.properties");
	  	ATUReports.setWebDriver(driver);
	  	setRunDescription();
	  	ATUReports.setTestCaseReqCoverage("ARCA Home Page");
	  	setAuthorInfoForReports();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS); 
		
	 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();


	
	}
	
	   private void setAuthorInfoForReports() {
	       ATUReports.setAuthorInfo("ARCA", Utils.getCurrentTime(),"1.0");
	    }
		private void setRunDescription() {
		       ATUReports.currentRunDescription = "Executing Home Page";
		}
		


		
		/*
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority= 1) public void Home_Pages_Load() throws Exception { { mb =new
		 * HomePage(driver); mb.HomePage_Load();
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority = 2) public void personal_signUp() throws Exception { {
		 * 
		 * mb1 = new PersonalSignUp(driver); mb1.individual_personal_signup();
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority = 3) public void personal_premium_signup() throws Exception {
		 * {
		 * 
		 * mb2 = new PersonalPremiumSignup(driver);
		 * mb2.individual_personal_premium_signup();
		 * 
		 * } }
		 * 
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority = 4) public void business_standard_signup() throws Exception {
		 * {
		 * 
		 * mb3 = new BusinessStandardSignup(driver); mb3.bussiness_standard_signup();
		 * 
		 * } }
		 * 
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority = 5) public void business_premium_signup() throws Exception {
		 * {
		 * 
		 * mb4 = new BusinessPremiumSignup(driver); mb4.bussiness_premium_signup();
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority = 6) public void login_page() throws Exception { {
		 * 
		 * mb5 = new Signup(driver); mb5.Signup_ecs();
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * //@Test(enabled=false)
		 * 
		 * @Test(priority = 7) public void rightclick_createfolder() throws Exception {
		 * {
		 * 
		 * mb6 = new RightClickUpload(driver);
		 * RightClickUpload.right_click_create_folder();
		 * 
		 * } }
		 * 
		 * 
		
		
		
		// @Test(enabled=false)
		@Test(priority = 8)
		public void rightclick_singlefileupload() throws Exception {
			{

				mb6 = new RightClickUpload(driver);
				RightClickUpload.right_click_single_file_upload();

			}
		}
		
		*/ 
		
		
		
		/*
		// @Test(enabled=false)
		
		
				@Test(priority = 10)
				public void addcomments_tofolder() throws Exception {
					{
						mb9 = new MyFilesComments(driver);
						MyFilesComments.add_comments_to_folder();
						
					}
				}
				
				@Test(priority = 11)
				public void editcomments_tofolder() throws Exception {
					{

						
						MyFilesComments.edit_comments_to_folder();
						

					}
				}
				
				
				@Test(priority = 12)
				public void deletecomments_tofolder() throws Exception {
					{

						
						MyFilesComments.delete_comments_to_folder();
						
				

					}
				}
				
				
				//----------------Commented-------------
				
				
				@Test(priority = 13)
				public void addcomments_tofile() throws Exception {
					{
						mb9 = new MyFilesComments(driver);
						MyFilesComments.add_comments_to_file();
		

					}
				}

				@Test(priority = 14)
				public void editcomments_tofile() throws Exception {
					{

						MyFilesComments.edit_comments_to_file();

					}
				}
				
				
				@Test(priority = 15)
				public void deletecomments_tofile() throws Exception {
					{

						MyFilesComments.delete_comments_to_file();

					}
				}
				*/
		
		
			
		/*
		
		// @Test(enabled=false)
		@Test(priority = 16)
		public void add_tags_multiple_folders() throws Exception {
			{

				mb8 = new MyFilesTags(driver);
				MyFilesTags.add_tag_multiple_folders();
		

			}
		}
		
		
		// @Test(enabled=false)
				@Test(priority = 17)
				public void remove_tags_multiple_folders() throws Exception {
					{

						MyFilesTags.remove_tag_multiple_folders();
				

					}
				}
				*/
				
		
		/*
				// @Test(enabled=false)
				@Test(priority = 18)
				public void add_tags_multiple_files() throws Exception {
					{
						mb8 = new MyFilesTags(driver);
						MyFilesTags.add_tag_multiple_files();

					}
				}
				
				
				// @Test(enabled=false)
				@Test(priority = 19)
				public void remove_tags_multiple_files() throws Exception {
					{

						MyFilesTags.remove_tag_multiple_files();

					}
				}
				
				
				@Test(priority = 20)
				public void multiple_files_folders_addtags() throws Exception {
					{
						
						MyFilesTags.add_tag_multiple_folders_files();
					
					}
				}
				*/
				
				
				
				
				/*
				@Test(priority = 21)
				public void multiple_files_folders_removetags() throws Exception {
					{
					
						MyFilesTags.remove_tag_multiple_folders_files();
					}
				}
				*/
		
				
				/*
				
				// @Test(enabled=false)
				@Test(priority = 22)
				public void multiple_folders_starred() throws Exception {
					{

						mb7 = new MyFilesStarred(driver);
						MyFilesStarred.multiple_folders_add_to_starred();
						
					}
				}
				
				
				// @Test(enabled=false)
				@Test(priority = 23)
				public void multiple_folders_unstarred() throws Exception {
					{

						MyFilesStarred.multiple_folders_un_starred();
					}
				}

				
				// @Test(enabled=false)
				@Test(priority = 24)
				public void multiple_files_starred() throws Exception {
					{

						MyFilesStarred.multiple_files_add_to_starred();
					
					}
				}
		
				// @Test(enabled=false)
				@Test(priority = 25)
				public void multiple_files_unstarred() throws Exception {
					{

						MyFilesStarred.multiple_files_un_starred();

					}
				}
				*/
				
				
				
				
				
		
		
			/*
				@Test(priority = 26)
				public void multiple_files_copy() throws Exception {
					{
						mb11 = new MyFilesCopy(driver);
						MyFilesCopy.multiple_files_copy();
						
					}
				}
				
				
				
				@Test(priority = 27)
				public void multiple_folders_copy() throws Exception {
					{
						
						MyFilesCopy.multiple_folders_copy();
						
					}
				}
				
				
				
				@Test(priority = 28)
				public void multiple_files_folders_copy() throws Exception {
					{
						
						MyFilesCopy.multiple_files_folders_copy();
						
					}
				}
				
				
				
				
		
		@Test(priority = 29)
		public void multiple_files_move() throws Exception {
			{
				mb12 = new MyFilesMove(driver);
				//MyFilesMove.multiple_files_move();
				
			}
		}
		
		@Test(priority = 30)
		public void multiple_folders_move() throws Exception {
			{
				
				//MyFilesMove.multiple_folders_move();
				
			}
		}

		@Test(priority = 31)
		public void multiple_file_folders_move() throws Exception {
			{
				
				MyFilesMove.multiple_files_folders_move();
				
			}
		}
		
		
		
		
				@Test(priority = 32)
				public void myfiles_collection() throws Exception {
					{
						mb17 = new MyFilesCollections(driver);
						MyFilesCollections.add_files_to_collection();
						
					}
				}
	
		 
		
				
		@Test(priority = 36)
		public void sharefile_with_user_editpermission() throws Exception {
			{
				mb16 = new ShareFileWithUser(driver);
				//ShareFileWithUser.share_data_with_user_edit_permission();
				ShareFileWithUser.share_data_with_user_edit_permission_confirmation();
				//ShareFileWithUser.share_data_with_user_commenter_permission();
				//ShareFileWithUser.share_data_with_user_commenter_permission_confirmation();
				
			}
		}	
		
			
				
		
		
		// @Test(enabled=false)
		@Test(priority = 37)
		public void delete_files() throws Exception {
			{

				mb10 = new MyFilesDelete(driver);
				MyFilesDelete.mul_file_delete();

			}
		}
		
		
		
		
		@Test(priority = 38)
		public void restore_files() throws Exception {
			{

				
				MyFilesDelete.mul_files_restore();
			
			}
		}
	
		@Test(priority = 39)
		public void delete_folders() throws Exception {
			{

				MyFilesDelete.mul_folders_delete();

			}
		}
		
		
		@Test(priority = 40)
		public void restore_folders() throws Exception {
			{

				MyFilesDelete.mul_folders_restore();

			}
		}
		
		
		@Test(priority = 39)
		public void delete_files_folders() throws Exception {
			{
				mb10 = new MyFilesDelete(driver);
				MyFilesDelete.mul_files_folders_delete();

			}
		}
		
		
		@Test(priority = 40)
		public void restore_files_folders() throws Exception {
			{

				MyFilesDelete.mul_files_folders_restore();

			}
		}
		
		
		
		
		@Test(priority = 41)
		public void files_download() throws Exception {
			{

				mb100 = new MyFilesDownload(driver);
				MyFilesDownload.mul_file_download();

			}
		}
		
		
		@Test(priority = 42)
		public void folders_download() throws Exception {
			{

				MyFilesDownload.mul_folders_download();

			}
		}
		
		
		@Test(priority = 43)
		public void files_folders_download() throws Exception {
			{

				MyFilesDownload.mul_files_folders_download();

			}
		}
		
		*/
		@Test(priority = 44)
		public void files_reminder() throws Exception {
			{
				mb23 = new MyFilesReminders(driver);
				MyFilesReminders.file_reminder();

			}
		}
		
		
		
		/*
		//----------------------Recent----------------		
		@Test(priority = 32)
				public void recent_page_comment_add() throws Exception {
					{
						mb13 = new RecentPageComments(driver);
						RecentPageComments.add_comments_to_file_recent();

						
					}
				}	
		
		
		@Test(priority = 32)
		public void recent_page_comment_edit() throws Exception {
			{
				mb13 = new RecentPageComments(driver);
				RecentPageComments.edit_comments_to_file_recent();

				
			}
		}	
		
		
		@Test(priority = 32)
		public void recent_page_comment_delete() throws Exception {
			{
				mb13 = new RecentPageComments(driver);
				RecentPageComments.delete_comments_to_file_recent();
				
			}
		}	
		
		
	

				@Test(priority = 33)
				public void recent_page_tags_add() throws Exception {
					{
						mb14 = new RecentPageTags(driver);
						RecentPageTags.add_tag_multiple_files_recent();
						
						
					}
				}	
				
				
				@Test(priority = 34)
				public void recent_page_tags_remove() throws Exception {
					{
					
						RecentPageTags.remove_tag_multiple_files_recent();
						
					}
				}	
				
			
		@Test(priority = 35)
		public void recent_page_tags_copy() throws Exception {
			{
				mb15 = new RecentPageCopyMove(driver);
				RecentPageCopyMove.recent_page_multiple_files_copy();
				
				
			}
		}	
		
		
		
		@Test(priority = 35)
		public void recent_page_move() throws Exception {
			{
				mb15 = new RecentPageCopyMove(driver);
				
				RecentPageCopyMove.recent_page_multiple_files_move();
				
			}
		}	
		
		
		
		
		
		@Test(priority = 37)
		public void recent_page_collections() throws Exception {
			{
				mb18 = new RecentPageCollections(driver);
				RecentPageCollections.add_files_to_collection();

			}
			}

		
		@Test(priority = 39)
		public void recent_page_share() throws Exception {
			{
				mb20 = new RecentPageShare(driver);
				RecentPageShare.share_data_with_user_edit_permission();
				//RecentPageShare.share_data_with_user_edit_permission_confirmation();
				
			}
		}	
			
		@Test(priority = 40)
		public void recent_page_trash() throws Exception {
			{
				mb21 = new RecentPageDelete(driver);
				RecentPageDelete.mul_file_delete_recent();
				
			}
		}
		
	//----------------------Recent-End----------------		
	
		
		
		
		
		
		//----------------------Collections-Start----------------		
		
		@Test(priority = 38)
		public void collections_page() throws Exception {
			{
				mb19 = new CollectionsPage(driver);
				CollectionsPage.add_files_to_collection();

				
			}
		}	
		
		
		
		
		@Test(priority = 41)
		public void Collections_page_share() throws Exception {
			{
				mb22 = new CollectionsPageShare(driver);
				CollectionsPageShare.share_data_with_user_edit_permission();
				CollectionsPageShare.share_data_with_user_edit_permission_confirmation();
				
			}
		}
		//----------------------Collections-End----------------	
		
		
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


		/*	
		//@Test(enabled=false)
		@Test(priority = 9)
		public void rightclick_bulkfileupload() throws Exception {
			{


				RightClickUpload.right_click_bulk_file_upload();

			}
		}



		//@Test(enabled=false)
		@Test(priority = 10)
		public void rightclick_folderupload() throws Exception {
			{


				RightClickUpload.right_click_folder_upload();

			}
		}






		//@Test(enabled=false)
		@Test(priority = 11)
		public void plusbutton_fileupload() throws Exception {
			{
				mb7 = new PlusButtonUpload(driver);
				PlusButtonUpload.plus_button_create_folder();


			}
		}



		//@Test(enabled=false)
		@Test(priority = 12)
		public void plusbutton_singlefileupload() throws Exception {
			{


				PlusButtonUpload.plus_button_single_file_upload();

			}
		}	

		//@Test(enabled=false)
		@Test(priority = 13)
		public void plusbutton_bulkfileupload() throws Exception {
			{



				PlusButtonUpload.plus_button_bulk_file_upload();

			}
		}



		//@Test(enabled=false)
		@Test(priority = 14)
		public void plusbutton_folderupload() throws Exception {
			{


				PlusButtonUpload.plus_button_folder_upload();



			}
		}


		
		

		


		//@Test(enabled=false)
		@Test(priority = 23)
		public void foldersearch() throws Exception {
			{

				
				mb12 = new Search(driver);

				Search.folder_search();


			}
		}

	
		@Test(priority = 24)
		public void filesearch() throws Exception {
			{


				Search.file_search();

			}
		}



		@Test(priority = 25)
		public void foldertagsearch() throws Exception {
			{


				Search.folder_tag_search();

			}
		}

		@Test(priority = 26)
		public void filetagsearch() throws Exception {
			{

				Search.file_tag_search();

			}
		}



		@Test(priority = 27)
		public void filetypesearch_photosimages() throws Exception {
			{

				Search.file_type_search_photos_images();

			}
		}

		@Test(priority = 28)
		public void filetypesearch_pdf() throws Exception {
			{

				Search.file_type_search_pdf();

			}
		}


		@Test(priority = 29)
		public void filetypesearch_documents() throws Exception {
			{

				Search.file_type_search_documents();

			}
		}

*/

		/*

		//@Test(enabled=false, retryAnalyzer=Retry.class)
		@Test(priority = 30)
		public void add_LegalHold() throws Exception {
			{
				
				
				//DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
				 // Date date = new Date();
				  
				 // recorder = new ATUTestRecorder("D:\\eclipse-workspace\\ARCA_Automation\\TCRecord","add_LegalHold-"+dateFormat.format(date),false);
				  
				 // recorder.start(); 
				  
				
				
				mb13 = new LegalHold(driver);

				//LegalHold.add_legal_hold();
				

			    
			}
		}

		

		@Test(priority = 31)
		public void search_LegalHold() throws Exception {
			{

				//LegalHold.search_legal_hold();

			}
		}

		@Test(priority = 32)
		public void view_LegalHold() throws Exception {
			{

				LegalHold.view_legal_hold();

			}
		}


		@Test(priority = 33)
		public void removeapply_LegalHold() throws Exception {
			{

				LegalHold.user_remove_apply_hold();

			}
		}


		@Test(priority = 34)
		public void edit_LegalHold() throws Exception {
			{

				
				LegalHold.edit_legal_hold();

			}
		}



		@Test(priority = 35)
		public void delete_LegalHold() throws Exception {
			{

				LegalHold.delete_legal_hold();
				
				recorder.stop();
				Thread.sleep(5000);
			}
		}

		*/
		
		/*

		@Test(priority = 36)
		public void add_retention() throws Exception {
			{
				DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
				  Date date = new Date();
				  
				  recorder = new ATUTestRecorder("D:\\eclipse-workspace\\ARCA_Automation\\TCRecord","add_retention-"+dateFormat.format(date),false);
				  
				  recorder.start();  

				mb14 = new Retention(driver);

				Retention.add_retention();


			}
		}


		@Test(priority = 36)
		public void search_retention() throws Exception {
			{

				Retention.search_retention();

			}
		}

		@Test(priority = 37)
		public void view_retention() throws Exception {
			{

				Retention.view_retention();

			}
		}


		@Test(priority = 38)
		public void apply_retention() throws Exception {
			{

				//Retention.apply_retention();

			}
		}



		@Test(priority = 39)
		public void edit_retention() throws Exception {
			{

				Retention.edit_retention();

			}
		}

		@Test(priority = 40)
		public void retire_retention() throws Exception {
			{

				Retention.retire_retention();
				recorder.stop();

			}
		}


		
		@Test(priority = 41)
		public void adding_user() throws Exception {
			{
				  
				login_cred();
				mb15 = new Users(driver);

				//Users.add_user();
				

			}
		}
		
		@Test(priority = 42)
		public void SearchUser() throws Exception {
			{
				  
				
				
				Users.search_user();
			

			}
		}
		
		@Test(priority = 43)
		public void ViewUser() throws Exception {
			{
				
				
				Users.view_user();
				

			}
		}
		
		
		@Test(priority = 44)
		public void EditUser() throws Exception {
			{
				  
			
				Users.edit_user();
				

			}
		}
		
		@Test(priority = 45)
		public void SuspendUser() throws Exception {
			{
				  
				
				Users.suspend_user();

			}
		}
		
		@Test(priority = 46)
		public void ResumeUser() throws Exception {
			{
				  
				
				Users.resume_user();

			}
		}
		


		
	@Test(priority = 41)
		public void adding_group() throws Exception {
			{
				  
				login_cred();
				
				mb16 = new Groups(driver);

				Groups.add_group();
				//Groups.add_user_to_group();
				//Groups.view_group();
				Groups.edit_group();
				Groups.delete_group();
				

			}
		}

	
	@Test(priority = 42)
	public void adding_group() throws Exception {
		{
			  
			login_cred();
			
			mb17 = new SourceManager(driver);

			SourceManager.add_source();
			
			

		}
	}



	


		//@Test(enabled=false)
		@Test(priority = 15)
		public void delet_a_folder() throws Exception {
			{

				mb8 = new DeleteFiles(driver);
				DeleteFiles.folder_delete();

			}
		}


		
		//@Test(enabled=false)
				@Test(priority = 16)
				public void delete_a_file() throws Exception {
					{

					
						DeleteFiles.file_delete();

					}
				}
*/

				/*

		//@Test(enabled=false)
		@Test(priority = 17)
		public void Permanent_FileDelete() throws Exception {
			{


				mb10 = new PermanentDelete(driver);
				PermanentDelete.permanent_file_delete();

			}
		}

		//@Test(enabled=false)
				@Test(priority = 17)
				public void Permanent_FolderDelete() throws Exception {
					{


						PermanentDelete.permanent_folder_delete();

					}
				}

		 */

	@AfterClass
   public void after() throws Exception
   {
   	driver.close();
   	driver.quit();
   	log.info("Browser Closing");
   	log.info("Driver Closing");
   }

  

}
