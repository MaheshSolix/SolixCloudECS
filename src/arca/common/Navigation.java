package arca.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Navigation {
	

private static WebDriver driver;
private static Logger log=Logger.getLogger(Navigation.class.getName());
public static final String DataFilePath =  System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator");
public static final String ScreenShotsFilePath =  System.getProperty("user.dir") + System.getProperty("file.separator") + "screenshots" + System.getProperty("file.separator");
public static String pagePath = "arca.pages.";
private static Properties objRepMenu = new Properties();
public static Properties objRepPage = new Properties();
//public static String arca_login_button = "arca_login_button";
public static String select_language;
public static String URL="https://arcaqa.solix.com/";


static FluentWait<WebDriver> wait=null;

private static String status;
public static String getStatus() {
	return status;
}
public static void setStatus(String status) {
	Navigation.status = status;
}

private static String activity;

public static String getActivity() {
	return activity;
}
public static void setActivity(String activity) {
	Navigation.activity = activity;
}

private static String executionON;

public static String getExecutionON() {
	return executionON;
}

public static void setExecutionON(String executionON) {
	Navigation.executionON = executionON;
}


public static WebDriver getDriver() {
	return driver;
}


public void setDriver(WebDriver driver)  {
	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Navigation.driver = driver;
	
	
	 FileInputStream objPageFile = null;
	try {
		objPageFile = new FileInputStream(DataFilePath + "ObjectRepositoryPage.properties");
	
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
		 try {
		objRepPage.load(objPageFile);
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}


public Navigation(WebDriver driver)  {

	// TODO Auto-generated constructor stub
	this.setDriver(driver);
}
public Navigation()  {
	// TODO Auto-generated constructor stub
	
		
}

//public void HomePageLoginClick()
//{
//
//	String arca_login_button = "arca_login_button";
//	driver.findElement(By.xpath(objRepPage.getProperty(arca_login_button))).click();
//}


public WebDriver goToPage(String menuType) throws IOException, InterruptedException {
	 
	
	getMenuObject(menuType).click();
	Thread.sleep(1000);

return getDriver();

}

public WebDriver goToPage(String menuType, String menuLevel1) throws IOException, InterruptedException {
	 
	
	getMenuObject(menuType).click();
	Thread.sleep(1000);
	getMenuObject(menuLevel1).click();
	Thread.sleep(1000);

	
return getDriver();

}


public WebDriver goToPage(String menuType, String menuLevel1,String menuLevel2) throws IOException, InterruptedException {
 
	getMenuObject(menuType).click();
	Thread.sleep(1000);
	getMenuObject(menuLevel1).click();
	Thread.sleep(1000);
	getMenuObject(menuLevel2).click();	

	
	
return getDriver();

}

 
 
	public WebDriver goToPage(String menuType, String menuLevel1,String menuLevel2,String menuLevel3) throws InterruptedException  {

		getMenuObject(menuType).click();
		Thread.sleep(1000);
		getMenuObject(menuLevel1).click();
		Thread.sleep(1000);
		getMenuObject(menuLevel2).click();
		Thread.sleep(1000);
		getMenuObject(menuLevel3).click();
	 	    return getDriver();
	   }
	
	
	public WebDriver goToPage(String menuType, String menuLevel1,String menuLevel2,String menuLevel3, String menuLevel4) throws IOException, InterruptedException {

		getMenuObject(menuType).click();
		Thread.sleep(1000);
		getMenuObject(menuLevel1).click();
		Thread.sleep(1000);
		try {
			String controlMinus=Keys.chord(Keys.CONTROL,Keys.SUBTRACT);
			getMenuObject(menuLevel2).sendKeys(controlMinus);
			getMenuObject(menuLevel2).sendKeys(controlMinus);
			getMenuObject(menuLevel2).sendKeys(controlMinus);
			getMenuObject(menuLevel2).sendKeys(controlMinus);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		getMenuObject(menuLevel2).click();
		Thread.sleep(1000);
		getMenuObject(menuLevel3).click();
		Thread.sleep(1000);
		getMenuObject(menuLevel4).click();
		try {
			String controlPlus=Keys.chord(Keys.CONTROL,Keys.ADD);
			driver.findElement(By.xpath("//a")).sendKeys(controlPlus);
			driver.findElement(By.xpath("//a")).sendKeys(controlPlus);
			driver.findElement(By.xpath("//a")).sendKeys(controlPlus);
			driver.findElement(By.xpath("//a")).sendKeys(controlPlus);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	  	    return getDriver();
	   }
	
	
	
	
	
	public static WebElement getMenuObject(String xpathVal) {
	
		try{
			if (xpathVal.startsWith("Top")){
				System.out.println("in getmenuobject top part");
				try {
					//Keep the mouse over the menu 
					//In some browsers, the Top menu does not stay as long as we want
					Thread.sleep(4000);
					Actions action = new Actions(driver);
					System.out.println("passed action");
					System.out.println("value"+objRepMenu.getProperty(xpathVal));
					WebElement hoverTo = driver.findElement(By.xpath(objRepMenu.getProperty(xpathVal)));     
					System.out.println("after hoverTo");
					action.moveToElement(hoverTo).build().perform();
					Thread.sleep(2000);
					} catch (Exception e) {
						System.out.println("in catch");
				      log.info(e);
					}      
		        return driver.findElement(By.xpath(objRepMenu.getProperty(xpathVal)));
			} else {
				getInstance();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(objRepMenu.getProperty(xpathVal))));
		 }
		}
		catch(Exception ex)
		{
			captureScreenShot(ScreenShotsFilePath + xpathVal+"Error_"+Math.random()+".png");
		}
		
		return driver.findElement(By.xpath(objRepMenu.getProperty(xpathVal)));
		
	}
	public static WebElement getPageObjectById(String idVal) {
		try {
			getInstance();
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.id(objRepPage.getProperty(idVal))));
		return driver.findElement(By.id(objRepPage.getProperty(idVal)));
		} catch (Exception e){
			captureScreenShot(ScreenShotsFilePath + idVal+"elementError.png");
			log.error("Error in finding element Element :" + idVal + " Element Xpath : " + objRepPage.getProperty(idVal), e);
			return null;
			
		}
		
	}
	public static WebElement getPageObjectByName(String nameVal) {
		try {
			getInstance();
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.name(objRepPage.getProperty(nameVal))));
		return driver.findElement(By.name(objRepPage.getProperty(nameVal)));
		} catch (Exception e){
			captureScreenShot(ScreenShotsFilePath + nameVal+"elementError.png");
			log.error("Error in finding element Element : " + nameVal + " Element Xpath : " + objRepPage.getProperty(nameVal), e);
			return null;
			
		}
		
	}
	public static WebElement getPageObjectByLinkText(String linkTextVal) {
		try {
			getInstance();
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.linkText(objRepPage.getProperty(linkTextVal))));
		return driver.findElement(By.linkText(objRepPage.getProperty(linkTextVal)));
		} catch (Exception e){
			captureScreenShot(ScreenShotsFilePath + linkTextVal+"elementError.png");
			log.error("Error in finding element Element : " + linkTextVal + " Element Xpath : " + objRepPage.getProperty(linkTextVal), e);
			return null;
		}
		
	}
	
	 /*added by Pradeep*/
	 private static long timeOutInSeconds=30;
	 @SuppressWarnings("deprecation")
	public static FluentWait<WebDriver> getInstance(String ...setTimeOut) {
		 
		 wait=new WebDriverWait(driver,timeOutInSeconds).pollingEvery(2,TimeUnit.SECONDS);
	      /*if(wait == null) {
	    	  System.out.println("creating wait object");
	    	  wait=new WebDriverWait(driver,timeOutInSeconds).pollingEvery(10,TimeUnit.SECONDS);
	      }
	      else
	      {
	    	  log.info("Using existing wait object for other elements");
	      }*/
	      return wait;
	   }
	 
	 /*ended by pradeep*/
	 
	public static WebElement getPageObject(String xpathVal)
	{
		
		System.out.println(objRepPage.getProperty(xpathVal));
		try 
		{
			getInstance();
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(objRepPage.getProperty(xpathVal))));
		    return driver.findElement(By.xpath(objRepPage.getProperty(xpathVal)));
		}
		catch (Exception e)
		{
			try 
			{
				log.info("Error in finding element Element : " + xpathVal + " Element Xpath : " + objRepPage.getProperty(xpathVal));
				Thread.sleep(1000L);
				log.info("Trying after one second ..  : " + xpathVal + " Element Xpath : " + objRepPage.getProperty(xpathVal));
				try 
				{
					return driver.findElement(By.xpath(objRepPage.getProperty(xpathVal)));
				}
				catch (Exception e2) 
				{
					captureScreenShot(ScreenShotsFilePath + xpathVal+"elementError.png");
					log.error("Error in finding element Element : " + xpathVal + " Element Xpath : " + objRepPage.getProperty(xpathVal), e2);
					return null;
				}
				
			} 
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
		
			captureScreenShot(ScreenShotsFilePath + xpathVal+"elementError.png");
			log.error("Error in finding element Element : " + xpathVal + " Element Xpath : " + objRepPage.getProperty(xpathVal), e);
	    	return null;		
		}	
	}
	
	
	//for part object repositories that has a variable in the middle
		public static WebElement getPageObject(String xpathVal_part1,String xpathVal_part2)
		{
			String xpath_part1=null;
			String xpath_part2=null;
			try 
			{
				xpath_part1 = objRepPage.getProperty(xpathVal_part1);
				xpath_part2 = objRepPage.getProperty(xpathVal_part2);
				getInstance();
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(xpath_part1 + xpath_part2)));
			    return driver.findElement(By.xpath(xpath_part1 + xpath_part2));
			}
			catch (Exception e)
			{
				try 
				{
					log.info("Error in finding element Element :" + xpathVal_part1  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part1));
					log.info("Error in finding element Element :" + xpathVal_part2  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part2));
					Thread.sleep(1000L);
					log.info("Trying after one second .... :" + xpathVal_part1  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part1));
					log.info("Trying after one second .... :" + xpathVal_part2  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part2));
				
					try 
					{
						return driver.findElement(By.xpath(xpath_part1 + xpath_part2));
					}
					catch (Exception e2) 
					{
						captureScreenShot(ScreenShotsFilePath + xpathVal_part1+"elementError.png");
						log.info("Error in finding element Element :" + xpathVal_part1  + "Element Xpath : " + objRepPage.getProperty(xpathVal_part1));
						log.info("Error in finding element Element :" + xpathVal_part2  + "Element Xpath : " + objRepPage.getProperty(xpathVal_part2));
						return null;
					}
					
				} catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			
				captureScreenShot(ScreenShotsFilePath + xpathVal_part1+"elementError.png");
				log.error("Error in finding element Element :" + xpathVal_part1 + "Element Xpath : " + objRepPage.getProperty(xpathVal_part1), e);
		    	return null;		
			}	
		}

	
	//for part object repositories that has a variable in the middle
	public static WebElement getPageObject(String xpathVal_part1,String var, String xpathVal_part2)
	{
		String xpath_part1=null;
		String xpath_part2=null;
		try 
		{
			xpath_part1 = objRepPage.getProperty(xpathVal_part1);
			xpath_part2 = objRepPage.getProperty(xpathVal_part2);
			getInstance();
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(xpath_part1 + var + xpath_part2)));
		    return driver.findElement(By.xpath(xpath_part1 + var + xpath_part2));
		}
		catch (Exception e)
		{
			try 
			{
				log.info("Error in finding element Element :" + xpathVal_part1  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part1));
				log.info("Error in finding element Element :" + xpathVal_part2  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part2));
				Thread.sleep(1000L);
				log.info("Trying after one second .... :" + xpathVal_part1  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part1));
				log.info("Trying after one second .... :" + xpathVal_part2  + " Element Xpath : " + objRepPage.getProperty(xpathVal_part2));
			
				try 
				{
					return driver.findElement(By.xpath(xpath_part1 + var + xpath_part2));
				}
				catch (Exception e2) 
				{
					captureScreenShot(ScreenShotsFilePath + xpathVal_part1+"elementError.png");
					log.info("Error in finding element Element :" + xpathVal_part1  + "Element Xpath : " + objRepPage.getProperty(xpathVal_part1));
					log.info("Error in finding element Element :" + xpathVal_part2  + "Element Xpath : " + objRepPage.getProperty(xpathVal_part2));
					return null;
				}
				
			} catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
		
			captureScreenShot(ScreenShotsFilePath + xpathVal_part1+"elementError.png");
			log.error("Error in finding element Element :" + xpathVal_part1 + "Element Xpath : " + objRepPage.getProperty(xpathVal_part1), e);
	    	return null;		
		}	
	}

	
	public static void select(String file_type,String byWhat,String value,Object... placeHolderValuesForXpath) throws Exception
	{
		
		String xpathName="";
		try
		{
			
			getInstance();
			file_type=objRepPage.getProperty(file_type);
			xpathName=file_type;
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(file_type)));
			
			switch(byWhat.toLowerCase())
			{
				case "value":new Select(driver.findElement(By.xpath(xpathName))).selectByValue(value);
				break;
				case "visibletext":new Select(driver.findElement(By.xpath(xpathName))).selectByVisibleText(value);
		        break;
				case "index":new Select(driver.findElement(By.xpath(xpathName))).selectByIndex(Integer.parseInt(value));
		        break;
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			log.info("Error in Selecting Element :" + xpathName + " Element Xpath : " + file_type);
			captureScreenShot(ScreenShotsFilePath + xpathName+"selectError.png");
		}	
		
	}
	

	
	
	public static void click(String xpathVal,Object... placeHolderValuesForXpath) throws Exception
	{
		
		String xpathName=xpathVal;
		xpathVal=objRepPage.getProperty(xpathVal);
	
		
		try 
		{
			if(xpathVal.startsWith("/")||xpathVal.startsWith("html")||xpathVal.startsWith("./"))
			{
				getInstance();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(xpathVal)));
				driver.findElement(By.xpath(xpathVal)).click();
				
			}
			else
			{
				getInstance();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.id(xpathVal)));
				driver.findElement(By.id(xpathVal)).click();
			}
		
		} 
		catch (Exception e)
		{
			try 
			{
					log.info("Error in clicking element Element :" + xpathName + " Element Xpath : " + xpathVal);
					Thread.sleep(1000L);
					log.info("Trying after one second ..  :" + xpathName + " Element Xpath : " + xpathVal);
					try 
					{
						driver.findElement(By.xpath(xpathVal));
					}
					catch (Exception e2) 
					{
						captureScreenShot(ScreenShotsFilePath + xpathName+"elementError.png");
						log.error("Error in finding element Element :" + xpathName + "Element Xpath : " + xpathVal, e2);	
		//				log.error("Error in finding element Element :" + xpathVal + "Element Xpath : " + objRepPage.getProperty(xpathVal), e2);	
						throw new Exception("Error in finding element Element : " + xpathName + " Element Xpath : " + xpathVal, e2);
					}
				
			} 
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
	
			captureScreenShot(ScreenShotsFilePath + xpathName+"elementError.png");
			log.error("Error in finding element Element : " + xpathName + " Element Xpath : " + xpathVal, e);
		}	
		
		
		
		
	}	

	
	
	public static void JsClick(String xpathVal,Object... placeHolderValuesForXpath) throws Exception
	{
		
		String xpathName=xpathVal;
		xpathVal=objRepPage.getProperty(xpathVal);
		
		try 
		{
			if(xpathVal.startsWith("/")||xpathVal.startsWith("html")||xpathVal.startsWith("./"))
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath(xpathVal)));
				
			}
			else
			{
				getInstance();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id(xpathVal)));
			}
		
		} 
		catch (Exception e)
		{
			try 
			{
					log.info("Error in clicking element Element :" + xpathName + " Element Xpath : " + xpathVal);
					Thread.sleep(1000L);
					log.info("Trying after one second ..  :" + xpathName + " Element Xpath : " + xpathVal);
					try 
					{
						//driver.findElement(By.xpath(xpathVal));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath(xpathVal)));
					}
					catch (Exception e2) 
					{
						captureScreenShot(ScreenShotsFilePath + xpathName+"elementError.png");
						log.error("Error in finding element Element :" + xpathName + "Element Xpath : " + xpathVal, e2);	
		//				log.error("Error in finding element Element :" + xpathVal + "Element Xpath : " + objRepPage.getProperty(xpathVal), e2);	
						throw new Exception("Error in finding element Element : " + xpathName + " Element Xpath : " + xpathVal, e2);
					}
				
			} 
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
	
			captureScreenShot(ScreenShotsFilePath + xpathName+"elementError.png");
			log.error("Error in finding element Element : " + xpathName + " Element Xpath : " + xpathVal, e);
		}	
		
		
		
		
	}
	
	public static void sendKeys(String xpathVal, String keyVal,Object... placeHolderValuesForXpath) throws Exception
	{
		String xpathName=xpathVal;
		xpathVal=objRepPage.getProperty(xpathVal);
		
		try 
		{
			if(xpathVal.startsWith("/")||xpathVal.startsWith("html")||xpathVal.startsWith("./"))
			{
				getInstance();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(xpathVal)));
				driver.findElement(By.xpath(xpathVal)).sendKeys(keyVal);
			}
			else
			{
				getInstance();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.id(xpathVal)));
				driver.findElement(By.id(xpathVal)).sendKeys(keyVal);
			}
		
		} catch (Exception e)
		{
			try 
			{
				log.info("Error in sendkeys element Element : " + xpathName + " Element Xpath : " + xpathVal);
				Thread.sleep(1000L);
				log.info("Trying after one second ..  : " + xpathName + " Element Xpath : " + xpathVal);
				try 
				{
					driver.findElement(By.xpath(xpathVal));
				}
				catch (Exception e2) 
				{
					captureScreenShot(ScreenShotsFilePath + xpathName+"elementError.png");
					log.error("Error in finding element Element : " + xpathName + "Element Xpath : " + xpathVal, e2);	
					   throw new Exception("Error in finding element Element :" + xpathName + "Element Xpath : " + xpathVal, e2);
				}
				
			} catch (InterruptedException e1) 
			{
				e1.printStackTrace();
			}
			captureScreenShot(ScreenShotsFilePath + xpathName+"elementError.png");
			log.error("Error in finding element Element : " + xpathName + " Element Xpath : " + xpathVal, e);	
		}
	}
	
	
	
	public static void captureScreenShot(String filePath) {
		  File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		     try {
		   FileUtils.copyFile(scrFile, new File(filePath));
		   log.info("Generating Screenshot at : " + filePath);
		  } catch (IOException e) {
		  
			  log.info("Generating error in captureScreenshot method");
		   e.printStackTrace();
		   
		  }
	}

	    @SuppressWarnings("rawtypes")
		public <T> Object executeMethod(String sheetName, String testcaseId,Object pageData) {
	    	 Object nextPageObj = null;
	    	Class[] paramString = new Class[1];
	    
	    	try {
				paramString[0] = Class.forName(pagePath+sheetName+"$"+sheetName + "Data");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //replace with LoginPageData
	    	System.out.println("paramString[]" + paramString[0]);
	 
	    	try {
	     		//LOAD Class with class name and initialize the driver with webdriver.
	    		Class<?> pageClass = Class.forName(pagePath + sheetName);
				Constructor<?> pageConstructor = pageClass.getConstructor(new Class[] {WebDriver.class});
				//create page Object with class
				Object pageObj = pageConstructor.newInstance(driver);
			//	Method m = cls.getDeclaredMethod("loginUsing", );
					
				Method testcaseMethod = pageClass.getDeclaredMethod(testcaseId, paramString[0]);
				 nextPageObj = testcaseMethod.invoke(pageObj, pageData);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return nextPageObj;
	    }
}





