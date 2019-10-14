package trello_app_test;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HomePagePOM {
	
	AndroidDriver<MobileElement> driver;

    By title = By.id("editing_toolbar_title");

    By cardName = By.id("card_name_edit_text");

    By confirmButton =By.id("confirm");

    By cardText = By.id("cardText");
    
    By cancelButton = By.id("cancel");

    public HomePagePOM (AndroidDriver<MobileElement> driver){

        this.driver = driver;

    }

    public String getTitle(){

       return driver.findElement(title).getText();

    }
    
    public void setCardName(String strCardName){

        driver.findElement(cardName).sendKeys(strCardName);

   }
    
    public void confirmCardName(){
        driver.findElement(confirmButton).click();
   }
    
    public void goToCardDetails(){
        driver.findElements(cardText).get(0).click();
  }
    
    public void cancelSingUp(){

        driver.findElement(cancelButton).click();

  }
    public String checkDashboard(){

       return driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView\r\n" + 
       		"").getText();
  }

}
