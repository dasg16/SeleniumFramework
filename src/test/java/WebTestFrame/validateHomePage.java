package WebTestFrame;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import resources.base;

public class validateHomePage extends base {

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest(groups = { "End2End" })
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver has been initialized");

	}

	@BeforeTest
	public void setURL() {

		driver.get(prop.getProperty("url"));

	}

	// Method to verify all the links on HomePage/LandingPage
	@Test(groups = { "End2End" })
	public void allLinksHomePage() throws IOException {

		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();
		int count = lanp.getCountLinks().size();

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		for (int i = 0; i < count; i++) {
			System.out.println(lanp.getCountLinks().get(i).getAttribute("href"));

			if (lanp.getCountLinks().get(i).getAttribute("href") != null
					&& (!(lanp.getCountLinks().get(i).getAttribute("href").contains("tel:")))
					&& (!(lanp.getCountLinks().get(i).getAttribute("href").contains("mailto:")))
					&& (!(lanp.getCountLinks().get(i).getAttribute("href").contains("javascript:"))))
				activeLinks.add(lanp.getCountLinks().get(i));
		}

		for (int i = 0; i < activeLinks.size(); i++) {

			HttpURLConnection conn = (HttpURLConnection) new URL(activeLinks.get(i).getAttribute("href"))
					.openConnection();
			conn.connect();
			String response = conn.getResponseMessage();
			conn.disconnect();

			System.out.println(activeLinks.get(i).getAttribute("href") + " Response is " + response);

		}

	}

	// Method to verify Validated YouTube page link on HomePage
	@Test(enabled = false)
	public void checkLinksToYouTube() throws IOException {

		LandingPage lanp = new LandingPage(driver);
		int count = lanp.getCountLinks().size();

		for (int i = 0; i < count; i++) {
			String test = lanp.getLinks().get(i).getAttribute("href");
			int j = i;
			int numOfTimesYouTube = 0;

			if (test.toLowerCase().contains("youtube")) {
				numOfTimesYouTube = numOfTimesYouTube + 1;

				// Using JavaScript executer to avoid element not clickable on youtube links.
				WebElement element = lanp.getLinks().get(i);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);

				Set<String> abc = driver.getWindowHandles();// 4
				Iterator<String> it = abc.iterator();
				String parentID = it.next();
				String childID = it.next();
				driver.switchTo().window(childID);
				driver.getCurrentUrl();
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/channel/UCgx5SDcUQWCQ_1CNneQzCRw");
				driver.switchTo().window(childID).close();
				driver.switchTo().window(parentID);

				// Thread.sleep(5000L);

				log.info("Validated QAcademy's youtube page link on HomePage");
				i = count;

				i = j + 1;
			}

		}

	}

	@Test
	public void validFeaturedCourses() throws IOException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LandingPage lanp = new LandingPage(driver);

		WebElement FCoursesFrame = lanp.getfeatureCoursesFrame();

		// List of all unique links which points to unique courses.
		int count = FCoursesFrame.findElements(By.tagName("a")).size();
		String test2 = null;
		for (int i = 0; i < count; i++) {
			String test = FCoursesFrame.findElements(By.tagName("a")).get(i).getAttribute("href");

			if (!test.toLowerCase().contains("name=rahul-shetty")) {
				if (test.equals(test2)) {

				}

				else {
					test2 = test;
				}
			}

		}
		Set<String> abc = driver.getWindowHandles();// 4
		Iterator<String> it = abc.iterator();

		while (it.hasNext()) {

			driver.switchTo().window(it.next());

		}

	}

	@Test(enabled = false)
	public void validMissionQuotes() throws IOException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();

		// Scrolling down to the area in the page where the organizational quote is
		// present.
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0,4000)", "");

		WebElement quotesContainer = lanp.getMissionMsg();
		boolean q = quotesContainer
				.findElement(By.xpath("//*[@id=\"about-reviews\"]/div[2]/div/div/div/div[1]/div/div[5]/div/blockquote"))
				.isDisplayed();
		System.out.println(q);

		// // List of all unique links which points to unique quotes.
		// int count = quotesContainer.findElements(By.tagName("span")).size();
		//
		// for (int i = 0; i < count; i++) {
		//
		// String p = quotesContainer.findElements(By.tagName(
		// "We do a lot of testing at work so I was happy to find this course and getup
		// to speed on how to do automated testing using Appium. Excited to put this
		// into practice at work! "))
		// .get(i).getText();
		// System.out.println(p);
		// // Assert.assertEquals(lanp.getMissionMsg().getText(), "Email is already in
		// // use");
		// // log.info("Validated 'Email is already in use' error message");
		// }
	}

	// Method to verify both the links in the image and text for the selenium
	// feature course links to the same URL.
	@Test(groups = { "End2End" })
	public void validFCSelenimLinks() throws IOException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LandingPage lanp = new LandingPage(driver);

		WebElement FCSelenium = lanp.getFeatCourseSelenium();

		String expectedLink = "http://qaclickacademy.usefedora.com/courses/seleniumwebdriverjavatraining";

		int count = FCSelenium.findElements(By.tagName("a")).size();
		for (int i = 0; i < count; i++) {

			if (!(FCSelenium.findElements(By.tagName("a")).get(i).getText().contains("Selenium Webdriver with Java"))
					&& !(FCSelenium.findElements(By.tagName("a")).get(i).getText().contains("by Rahul Shetty"))) {

				String imgLink = FCSelenium.findElements(By.tagName("a")).get(i).getAttribute("href");

				// Verifying if the link in the image is as expected
				Assert.assertEquals(imgLink, expectedLink);
				log.info("Verified the link in the image is as expected");

			}

			if (FCSelenium.findElements(By.tagName("a")).get(i).getText().contains("Selenium Webdriver with Java")) {
				String textLink = FCSelenium.findElements(By.tagName("a")).get(i).getAttribute("href");

				// Verifying if the link in the text is as expected
				Assert.assertEquals(textLink, expectedLink);
				log.info("Verified the link in the text is as expected");
			}

		}

	}

	// Method to verify ratings for the Selenium feature course.
	@Test(groups = { "End2End" })
	public void validFCSelenimRate() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		String abc = lanp.getFCSeleniumRating().getText();
		String alphabets = abc.replaceAll("\n", " ");

		// Verifying if the ratings for the Selenium feature course is as expected
		Assert.assertTrue(alphabets.contains("93 ratings"));
		log.info("Verified the link in the image is as expected");

	}

	// Method to verify number of enrolled students for the Selenium feature course.
	@Test
	public void validFCSeleStudEnrol() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		String abc = lanp.getFCSeleniumRating().getText();
		String alphabets = abc.replaceAll("\n", " ");

		// Verifying if the number of enrolled students is as expected
		Assert.assertTrue(alphabets.contains("2456 students enrolled"));
		log.info("Verified the link in the image is as expected");

	}

	// Method to verify the actual price of the Selenium feature course.
	@Test(groups = { "End2End" })
	public void validFCSeleniumPrice() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		String abc = lanp.getFCSeleniumPrice().getText();
		// Verifying if the actual cost of the course is as expected
		Assert.assertTrue(abc.contains("$ 150.00"));
		log.info("Verified if the actual cost of the course is as expected");

	}

	// Method to verify the price of the Selenium feature course after discount.
	@Test
	public void validFCSeleniumDiscPrice() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		String abc = lanp.getFCSeleniumPrice().getText();
		// Verifying if the discount price is as expected
		Assert.assertTrue(abc.contains("$ 30.00"));
		log.info("Verified if the discount price is as expected");

	}

	// Method to validate if all the topics in the navigation bar actually displayed
	// on the webpage.
	@Test(groups = { "End2End" })
	public void homePNavigationDisplayed() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		log.info("Advertisement Pop-Up Closed");

		Assert.assertTrue(lanp.getNavigationHome().isDisplayed());
		log.info("Home Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationBarCourses().isDisplayed());
		log.info("Course Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationVideos().isDisplayed());
		log.info("Videos Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationInterviewGuide().isDisplayed());
		log.info("Interview Guide Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationPractice().isDisplayed());
		log.info("Practice Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationBlog().isDisplayed());
		log.info("Blog Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationAbout().isDisplayed());
		log.info("About Tab in the Landing page has been verified");

		Assert.assertTrue(lanp.getNavigationContact().isDisplayed());

	}

	// Method to validate Home page link on HomePage's navigation bar
	@Test
	public void validNaviBarHome() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationHome().click();
		// Validate Home page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/index.php");
		log.info("Validated QAcademy's Home page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate Courses page link on HomePage's navigation bar
	@Test(groups = { "Smoke", "End2End" })
	public void validNaviBarCourses() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationBarCourses().click();

		// Validate Courses page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/courses-description.php");
		log.info("Validated QAcademy's Courses page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate Videos page link on HomePage's navigation bar
	@Test(groups = { "End2End" })
	public void validNaviBarVideos() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationVideos().click();

		// Validate Videos page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/videos.php");
		log.info("Validated QAcademy's Videos page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate Interview Guide page link on HomePage's navigation bar
	@Test(groups = { "End2End" })
	public void validNaviBarIntGuides() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationInterviewGuide().click();

		// Validate Interview Guide page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/interview.php");
		log.info("Validated QAcademy's Interview Guide page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate Practice page link on HomePage's navigation bar
	@Test(groups = { "Smoke", "End2End" })
	public void validNaviBarPractice() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationPractice().click();

		// Validate Practice page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/practice.php");
		log.info("Validated QAcademy's Practice page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate Blog page link on HomePage's navigation bar
	@Test(groups = { "End2End" })
	public void validNaviBarBlog() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationBlog().click();

		// Validate Blog page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/blog/");
		log.info("Validated QAcademy's Blog page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate About page link on HomePage's navigation bar
	@Test(groups = { "End2End" })
	public void validNaviBarAbout() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationAbout().click();

		// Validate About page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/about.php");
		log.info("Validated QAcademy's About page link on HomePage navigation bar");
		driver.navigate().back();

	}

	// Method to validate Contact page link on HomePage's navigation bar
	@Test(groups = { "End2End" })
	public void validNaviBarContact() throws IOException {

		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Contact Tab in the Landing page has been verified");
		lanp.getNavigationContact().click();

		// Validate Contact page link on HomePage's navigation bar
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/contact.php");
		log.info("Validated QAcademy's Contact page link on HomePage navigation bar");
		driver.navigate().back();

	}

	@AfterTest(groups = { "End2End" })
	public void teardown() {
		driver.close();
		driver = null;
	}

}
