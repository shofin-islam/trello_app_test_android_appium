package trello_app_test;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class WelcomeScreenPOM {
	
	AndroidDriver<MobileElement> driver;   
	
	By pageTitle = By.id("text");
	By loginButton = By.id("log_in_button");
	By sign_up_button = By.id("sign_up_button");
	
	@SuppressWarnings("rawtypes")
	TouchAction action;

    @SuppressWarnings("rawtypes")
	public WelcomeScreenPOM(AndroidDriver<MobileElement> driver){

        this.driver = driver;
        action=new TouchAction(driver);

    }  
    
    public void horizontalSwipe() {
		Dimension size = driver.manage().window().getSize();
		int width = (int) (size.getWidth());
		int height = (int) (size.getHeight());
		int y = height/2;
		int startX = (int) (width*0.80);
		int endX = (int) (width*0.20);
		for (int i = 0; i < 4; i++) {
		action.press(PointOption.point(startX,y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endX,y)).release().perform();
		}
	}
    
    public String getSliderTitle() {
    	return driver.findElement(pageTitle).getText();
	}
    
    public void loginButtonFunctionality() {
    	driver.findElement(loginButton).click();
	}
	
    public void signUpButtonFunctionality() {
    	driver.findElement(sign_up_button).click();
	}

}
