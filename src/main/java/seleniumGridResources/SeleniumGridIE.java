package seleniumGridResources;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumGridIE {

	public WebDriver setDesiredCapabilities() throws MalformedURLException {

		// Define desired capablities
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setBrowserName("internet explorer");
		dc.setPlatform(Platform.WINDOWS);

		// Chrome option definition
		ChromeOptions option = new ChromeOptions();
		option.merge(dc);

		String hubURL = "http://10.0.0.121:4444/wd/hub";

		WebDriver driver = new RemoteWebDriver(new URL(hubURL), option);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}
}
