package com.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.base.DriverManager;
import com.utils.ReadProperties;

public class Base {
	static WebDriver driver;
	DriverManager dm= new DriverManager();
	ReadProperties readProp;
	Properties prop ;
	String filename="C:\\Automation\\Workspace\\HybridFW\\src\\test\\java\\com\\resources\\config.properties";
	
/**
 * If running in parallel then change for suite to Before Test 
 * @param br
 * @param url
 */

  
  @Parameters({"browser", "url"})
  @BeforeTest(alwaysRun=true)
  public void beforeTest(String br, String url) { 
	  // readProp = new ReadProperties(); // prop=readProp.readProperties(filename); 
	  //System.out.println("This is will run at Before Suite level");
	  //dm.launch(prop.getProperty("browser"), prop.getProperty("url"));
	  //dm.launch(prop.getProperty(br), prop.getProperty(url));
	  //above is woth prop file and nw this is with parameters for parallel exe 
	  dm.launch(br, url);
  driver=DriverManager.getdriver(); 
  }
 

	
	/*
	 * @BeforeSuite public void beforeTest(String br, String url) { readProp = new
	 * ReadProperties(); prop=readProp.readProperties(filename);
	 * System.out.println("This is will run at Before Suite level");
	 * dm.launch(prop.getProperty("browser"), prop.getProperty("url"));
	 * 
	 * driver=DriverManager.getdriver(); }
	 */

  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is will run at After suite level");
	  //dm.teardown();
  }

}
