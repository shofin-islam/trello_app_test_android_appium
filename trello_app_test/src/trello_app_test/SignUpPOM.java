package trello_app_test;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SignUpPOM {
	
	AndroidDriver<MobileElement> driver;

    By userName = By.id("name");

    By userMail = By.id("user");

    By userPassword =By.id("password");

    By createButton = By.id("authenticate");
    
    By cancelButton = By.id("cancel");
    
    By signUpPageDescription = By.id("by_signing_up_you_agree");
    

    public SignUpPOM(AndroidDriver<MobileElement> driver){

        this.driver = driver;

    }

    public boolean createButtonStatus(){
    	System.out.println("createButtonStatus() executed");
        String status = driver.findElement(createButton).getAttribute("enabled");
        if (status.contains("true")) {
        	System.out.println("button status returned true!!");
			return true;
		}else {
			System.out.println("button status returned false!!");
			return false;
		}
    }
  
    public boolean create_button_after_credentials() throws InterruptedException {
    	System.out.println("create_button_after_credentials() executed");
    	setUserName("Shofin");
    	System.out.println("user name inserted");
    	Thread.sleep(1000);
    	setUserMail("ss@ss.com");
    	System.out.println("user email inserted");
    	Thread.sleep(1000);
    	setPassword("asdf1234");
    	System.out.println("user password inserted");
    	Thread.sleep(1000);
    	return createButtonStatus();
	}

    public boolean attemptSignUp(String name, String email, String password) throws InterruptedException {
    	
    	System.out.println("inside attempt signup");
    	setUserName(name);
    	System.out.println("name inserted");
		Thread.sleep(1000);
		setUserMail(email);
		System.out.println("email inserted");
		Thread.sleep(1000);
		setPassword(password);
		System.out.println("password inserted");
		Thread.sleep(1000);
		
		signUp();
		System.out.println("create button pressed");
		
		Thread.sleep(2000);
		try {
			String login_fail = driver.findElementById("button3").getText();
			if (login_fail.contains("LOG IN")) {
				System.out.println("alert "+login_fail);
				driver.pressKey(new KeyEvent(AndroidKey.BACK));
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		
	}
    public void setUserName(String strUserName){
    	driver.findElement(userName).clear();
        driver.findElement(userName).setValue(strUserName);;
    }
    
    public void setUserMail(String strMailAddrees){
    	driver.findElement(userMail).clear();
        driver.findElement(userMail).setValue(strMailAddrees);

   }
    
    public void setPassword(String strPassword){
    	driver.findElement(userPassword).clear();
        driver.findElement(userPassword).setValue(strPassword);
   }
    
    public void signUp(){

        driver.findElement(createButton).click();

  }
    
    public void cancelSingUp(){

        driver.findElement(cancelButton).click();

  }
    
   public String getsignUpPageDescription(){
	   return driver.findElement(signUpPageDescription).getText();
    }


}
