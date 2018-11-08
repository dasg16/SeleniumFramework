package WebTestFrame;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CoursesPage;
import pageObjects.LandingPage;
import resources.base;

public class validateCoursePageLinks extends base {

	@BeforeTest
	public void initialization() throws IOException {

		driver = initializeDriver();

	}

	@BeforeClass
	public void setURL() {

		driver.get(prop.getProperty("url"));
		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();

	}

	@Test
	public void validateLinkResponses() throws MalformedURLException, IOException {

		CoursesPage cp = new CoursesPage(driver);

		cp.getCourseButton().click();

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		int count = cp.getLinks().size();
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			System.out.println(cp.getLinks().get(i).getAttribute("href"));
			if (cp.getLinks().get(i).getAttribute("href") != null
					&& (!(cp.getLinks().get(i).getAttribute("href").contains("tel:")))
					&& (!(cp.getLinks().get(i).getAttribute("href").contains("mailto:")))) {

				activeLinks.add(cp.getLinks().get(i));
			}

		}

		System.out.println(activeLinks.size());

		for (int i = 0; i < activeLinks.size(); i++) {
			// Using httpURLConnection
			HttpURLConnection urlConn = (HttpURLConnection) new URL(activeLinks.get(i).getAttribute("href"))
					.openConnection();

			urlConn.connect();
			String response = urlConn.getResponseMessage();
			urlConn.disconnect();

			System.out.println(activeLinks.get(i).getAttribute("href") + " " + response);
		}

	}

	@AfterTest
	public void teardown() {
		driver.close();
		driver = null;
	}

}
