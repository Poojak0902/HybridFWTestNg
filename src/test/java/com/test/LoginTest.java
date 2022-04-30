package com.test;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pom.LoginActions;

public class LoginTest extends Base {
	LoginActions login;
	
	
	@BeforeClass(alwaysRun=true)
	public void beforeclass() {
		 login= new LoginActions(driver);
	}

	
	
	
	@Test (priority =1,groups ={"Regression"})
  public void Loginbutton() {
	login.clicklogin();	
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	String actualTitle = driver.getTitle();
	Assert.assertTrue(actualTitle.contains("Log in - Shopify"), "This is not expected title"+actualTitle);
		
	}

@Test(priority =2,  dependsOnMethods ={"Loginbutton"}, groups ={"Regression"})
public void getstarted() {
	login.clickgetstarted();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	String actualTitle = driver.getTitle();
	Assert.assertTrue(actualTitle.contains("Sign up"), "This is not expected title"+actualTitle);
}
@Parameters({"email"})
@Test(priority =3,  dependsOnMethods ={"getstarted"}, groups ={"Regression"})
public void enterEmail(String email) {
	Assert.assertTrue(login.username.isEnabled(), "Email field is not enabled ");
	
	login.sendUn(email);
	Reporter.log("Email id is passed >> "+ email);
}
@Parameters({"firstName"})
@Test(priority =4, dependsOnMethods ={"enterEmail"})
public void enterfn(String fn) {
	login.sendfn(fn);
	Reporter.log("Firstname id is passed >> "+ fn);
}
@Parameters({"lastName"} )
@Test(priority =5,dependsOnMethods ={"enterfn"} )
public void enterln(String ln) {
	login.sendlastname(ln);
	Reporter.log("lastname id is passed >> "+ ln);
}
@Parameters({"password"})
@Test(priority =6,dependsOnMethods ={"enterln"})
public void enterpwd(String pwd) {
	login.sendpassword(pwd);
	Reporter.log("passwrod id is passed >> "+ pwd);
	
}
@Test(priority =7,dependsOnMethods ={"enterpwd"})
public void confirmpassword() {
	login.sendconfrimPwd();
	
	
}

@Parameters({"validemail"})
@Test(priority =8,  dependsOnMethods ={"confirmpassword"})
public void validenterEmail(String email) {
	login.sendUn(email);
	Reporter.log("valid Email id is passed >> "+ email);
}
@Parameters({"validfirstName"})
@Test(priority =9, dependsOnMethods ={"validenterEmail"})
public void validenterfn(String fn) {
	login.sendfn(fn);
	Reporter.log("valid Firstname id is passed >> "+ fn);
}
@Parameters({"validlastName"})
@Test(priority =10,dependsOnMethods ={"validenterfn"} )
public void validenterln(String ln) {
	login.sendlastname(ln);
	Reporter.log("valid lastname id is passed >> "+ ln);
}
@Parameters({"validpassword"})
@Test(priority =11,dependsOnMethods ={"validenterln"})
public void validenterpwd(String pwd) {
	login.sendpassword(pwd);
	Reporter.log("valid passwrod id is passed >> "+ pwd);
	
}
@Test(priority =12,dependsOnMethods ={"validenterpwd"})
public void validconfirmpassword() {
	login.sendconfrimPwd();
	
	
}

@Test(dataProvider="dp", priority=8)
public void jsonlogintest(String data) {
	//driver.navigate().refresh();
	String[] arr= data.split(",");
	/*
	 * login.email.clear(); login.email.sendKeys(arr[0]); login.firstname.clear();
	 * login.firstname.sendKeys(arr[1]); login.lastname.clear();
	 * login.lastname.sendKeys(arr[2]); login.password.clear();
	 * login.password.sendKeys(arr[3]);
	 */
System.out.println(arr);
	
}
@DataProvider(name ="dp")
public String[] readjsonfile() throws IOException, ParseException  {
	
	
	  JSONParser json = new JSONParser(); 
	  FileReader file = new FileReader(".\\testdata.json"); 
	  Object obj=json.parse(file);
	  
	  JSONObject jsonfile=(JSONObject)obj; 
	  JSONArray userdetailsArray=(JSONArray)jsonfile.get("userinformationform");
	  
	  String[] arr = new String[userdetailsArray.size()];
	  for (int i = 0; i <userdetailsArray.size(); i++) {
		  JSONObject users=(JSONObject)userdetailsArray.get(i); 
		  String email= (String)users.get("Email");
		  String fn= (String) users.get("Firstname"); 
		  String ln= (String) users.get("Lastname");
		  String pwd= (String) users.get("Password");
		  arr[i]=email+","+fn+","+ln+","+pwd+",";
	  
	  } return arr;
	  }



}
