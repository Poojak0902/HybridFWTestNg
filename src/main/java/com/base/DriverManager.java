package com.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {


	static WebDriver driver;

	public void launch(String browser, String url) {
			
		String path = System.getProperty("user.dir");
		System.out.println("This is local path"+ path);
		
		//String remainingpath = "\\drivers\\chromedriver.exe";
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", path+ "\\driver\\chromedriver.exe");
				driver= new ChromeDriver();
				
			}else if (browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver",path+"\\driver\\msedgedriver.exe");
				driver= new EdgeDriver();
				
			}else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",path+"\\driver\\geckodriver.exe");
				driver= new FirefoxDriver();
				
				
			}else {
				System.out.println("This browser paramenter is not correct >> give valid input");
				System.exit(0);
			}
			
			
			if (url!="") {
				driver.get(url);	
			}else {
				driver.get("about:blank");
			}
			//maximize  the window size
		  driver.manage().window().maximize();
		  
		  //wait until url is loaded
		  driver.manage().timeouts().implicitlyWait(5000,TimeUnit.SECONDS);
		  
		  //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		 

	}

		
		public void teardown() {
			 //driver.close();
			driver.quit();
		}
		
		public static WebDriver getdriver() {
			return driver;
			
		}
		





}
