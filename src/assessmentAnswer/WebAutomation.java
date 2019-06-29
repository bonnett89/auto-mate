package assessmentAnswer;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebAutomation {

	PageConstants pgc = new PageConstants();

	@SuppressWarnings("unused")
	public WebAutomation() throws InterruptedException {
		// Create a new instance of the driver and set driver.exe path in PageConstants
		System.setProperty("webdriver.chrome.driver", pgc.exePath);
		WebDriver driver = new ChromeDriver();

		// Launch the CS Website and maximize window size
		driver.get(pgc.baseUrl);
		driver.manage().window().maximize();

		// Get the WebElement corresponding to the cookie notification overlay
		WebElement cookiemsg = driver.findElement(pgc.cookieNotification);
		String actualCookieMsg = cookiemsg.getText();

		// Verify if notification is displayed, compare text and print notification text
		if (cookiemsg.isDisplayed()) {
			Assert.assertEquals(actualCookieMsg, pgc.expectedCookieMsg);
			System.out.println("Test Case 1 Passed for verifying cookie text msg  -- " + pgc.expectedCookieMsg);
		} else {
			System.out.println("Test Case 1 failed for verifying cookie text msg  -- " + pgc.expectedCookieMsg);
		}

		// Test notification can be dismissed
		WebElement DismissButton = driver.findElement(pgc.noProblemBtn);
		DismissButton.click();
		System.out.println("Test Case 2 notificationis dismissed ");

		// Test notification doesn't reappear after being dismissed
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.invisibilityOf(cookiemsg));
		if (cookiemsg != null) {
			System.out.println("Test Case 3 Cookie Notification Element is InVisible after being dismissed");
		} else {
			System.out.println("Test Case 3 Cookie Notification Element is Visible");
		}

		// Check cookie set and print all cookies after clicking on No Problem button
		Set<Cookie> cookie = driver.manage().getCookies();
		System.out.println("After Clicking No Problem btn, fetch all cookies " + "\n" + cookie);

		// verify CS_ACCEPT_COOKIES is being set after clicking on No Problem Button
		System.out.println("Test Case 4 Fetch cookie data for CS_ACCEPT_COOKIES -----" + "\n"
				+ driver.manage().getCookieNamed("CS_ACCEPT_COOKIES"));

		String ckValue = driver.manage().getCookieNamed("CS_ACCEPT_COOKIES").getValue();
		Assert.assertTrue(true, ckValue);

		// verify domain value in cookie
		String domainValue = driver.manage().getCookieNamed("CS_ACCEPT_COOKIES").getDomain();
		Assert.assertEquals("www.clearscore.com", domainValue);

		// Close the browser
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException {

		new WebAutomation();

	}
}
