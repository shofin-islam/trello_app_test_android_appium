package trello_app_test;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

public class Execute {

static AndroidDriver<MobileElement> driver;
	
	SignUpPOM objSignUp;
	HomePagePOM objHome;
	WelcomeScreenPOM objWelcome;
	LoginPOM objLogin;
	
	// Sign up credentials
	String uName = "Shofin";
	int m=14;
	String password="srbdgat@1234";
	
	// valid account credentials
	String vEmail = "srbdgat@gmail.com";
	String vpassword="srbdgat@1234";
	
	@BeforeTest

    public void setup() throws MalformedURLException, InterruptedException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"ABCDEFG");
	    capabilities.setCapability("appPackage", "com.trello");
		capabilities.setCapability("appActivity","com.trello.home.HomeActivity"); 

	   driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	   Thread.sleep(1000);
	   objWelcome = new WelcomeScreenPOM(driver);
	   objLogin = new LoginPOM(driver);
	   objSignUp = new SignUpPOM(driver);
	   objHome = new HomePagePOM(driver);

    }
	
	@Test(priority=0)

    public void test_swipe_functionality() throws InterruptedException{
		objWelcome.horizontalSwipe();	
		Thread.sleep(2000);
		String title = objWelcome.getSliderTitle();
		
		Assert.assertEquals(title, "Team up");
   }
	
	@Test(priority=1)
	
	public void login_button_action_test() throws InterruptedException {
		objWelcome.loginButtonFunctionality();
		Thread.sleep(2000);
		Boolean loginButtonStatus = objLogin.loginButtonStatus();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Assert.assertFalse(loginButtonStatus);
	}
	
@Test(priority=2)
	
	public void signup_button_action_test() throws InterruptedException {
		objWelcome.signUpButtonFunctionality();
		Thread.sleep(2000);
		Assert.assertFalse(objSignUp.createButtonStatus());
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

// sign up page test case starting here

		@Test(priority=3)
		public void create_button_should_enable_after_credentials() throws InterruptedException {
			objWelcome.signUpButtonFunctionality();
			System.out.println("Sign up button clicked");
			Assert.assertTrue(objSignUp.create_button_after_credentials());
		  }

		@Test(priority=4)
		public void test_sign_up_functionality() throws InterruptedException{
		
//     Already in the sign up screen
		String dashboard_titile;
		for (int i=m ; i > 0; i++) {
			System.out.println("Loop started with the value"+i);
			String uEmail = "srbdgat+"+i+"@mail.com";
			if(objSignUp.attemptSignUp(uName, uEmail, password)) {
				System.out.println("sign up success");
				Thread.sleep(2000);
				dashboard_titile = objHome.getTitle();
				dashboard_titile = dashboard_titile.replaceAll("\\Åc", "");
				driver.findElementByClassName("android.widget.ImageButton").click();
				driver.findElementByAccessibilityId("Navigate up").click();
				test_logout_functionality();
				Assert.assertEquals(dashboard_titile, "Add card");
				return;
			}else {
				System.out.println("sign up failed"+i);
			}	
		}	
		System.out.println("failed after loop");
		Assert.assertEquals("Check Home Page", "Add card");
}
	
	
	@Test(priority=5)	
	public void test_login_functionality() throws InterruptedException {
		objWelcome.loginButtonFunctionality();
		objLogin.login_with_valid_credentials(vEmail, vpassword);
		Thread.sleep(5000);
		Assert.assertEquals(objHome.checkDashboard(), "Boards");
	}	

	@Test(priority=6)
	public void test_logout_functionality() throws InterruptedException {
//		objWelcome.loginButtonFunctionality();
		driver.findElementByAccessibilityId("Open Drawer").click();
		Thread.sleep(3000);
		driver.findElementsByXPath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup").get(4).click();
//		driver.findElementByAndroidUIAutomator("text(\"Settings\")").click();	
		Thread.sleep(3000);
//		java.util.List<WebElement> list = driver.findElementsByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout");
//		list.get(2).click();
		
		MobileElement logout = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Log out\"));"));
		System.out.println("logout location : "+logout.getLocation());
		Thread.sleep(1000);
		logout.click();
		Thread.sleep(2000);
	}
	
}
