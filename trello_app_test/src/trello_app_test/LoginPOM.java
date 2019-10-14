package trello_app_test;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginPOM {

	AndroidDriver<MobileElement> driver;

    By login_login = By.id("authenticate");
    By email_or_user_name = By.id("user");
    By login_password = By.id("password");
    
    public LoginPOM(AndroidDriver<MobileElement> driver){

        this.driver = driver;

    }

  

    public boolean loginButtonStatus(){

        String status = driver.findElement(login_login).getAttribute("enabled");
        if (status.contains("true")) {
			return true;
		}else {
			return false;
		}
    }
    
    public boolean login_button_after_credentials() {
    	setEmail_ToLogin("safi.cse13@gmail.com");
    	setPassword("asdf1234");
    	return loginButtonStatus();
	}
    
    public void setEmail_ToLogin(String strUserName){

        driver.findElement(email_or_user_name).setValue(strUserName);

    }
    
    public void setPassword(String strPassword){

        driver.findElement(login_password).setValue(strPassword);
   }
    
    public void login_with_valid_credentials(String email, String password){

    	setEmail_ToLogin(email);
    	setPassword(password);
        driver.findElement(login_login).click();
   }
    
}
