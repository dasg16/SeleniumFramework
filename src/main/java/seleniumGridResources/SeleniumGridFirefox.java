package seleniumGridResources;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumGridFirefox {

	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/Untitled1");

		prop.load(fis);

		// Define desired capablities
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setBrowserName("firefox");
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
