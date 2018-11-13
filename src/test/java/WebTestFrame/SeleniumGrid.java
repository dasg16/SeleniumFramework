package WebTestFrame;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleniumGrid {

	@Test
	public void setDesiredCapabilities() throws MalformedURLException {

		// Define desired capablities
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setBrowserName("Chrome");
		dc.setPlatform(Platform.MAC);

		// Chrome option definition
		ChromeOptions option = new ChromeOptions();
		option.merge(dc);

		String hubURL = "http://10.0.0.121:4444/wd/hub";

		WebDriver driver = new RemoteWebDriver(new URL(hubURL), option);
		driver.get("https://www.google.com");

	}

}
