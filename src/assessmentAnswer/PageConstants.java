package assessmentAnswer;

import org.openqa.selenium.By;

public class PageConstants {

	String exePath = "D:\\software downloads\\chromedriver.exe";
	String baseUrl = "https://www.clearscore.com";
	String expectedCookieMsg = "We use cookies to improve your experience.";
	String getLoginEndpoint = "/account/login";
	By cookieNotification = By.cssSelector("div.websiteCookieNotice--2LLwd >p");
	By noProblemBtn = By.cssSelector(".actionItems--33SND >button");

}
