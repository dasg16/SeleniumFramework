package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver;

	By signin = By.xpath("//*[@id=\'homepage\']/header/div[1]/div/nav/ul/li[4]/a/span");
	By navigationHome = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[1]/a");
	By navigationCourses = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[2]/a");
	By navigationVideos = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[3]/a");
	By navigationInterviewGuide = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[4]/a");
	By navigationPractice = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[5]/a");
	By navigationBlog = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[6]/a");
	By navigationAbout = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[7]/a");
	By navigationContact = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[8]/a");
	By closeButton = By.xpath("//*[@id=\'homepage\']/div[5]/div[2]/div/div/div/span/div/div[7]/div/div/div[2]");
	By countLinks = By.tagName("a");
	By featureCoursesFrame = By.xpath("//*[@id='content']/div");
	By countCoursesLinks = By.tagName("a");
	By missionMsg = By.xpath("//*[@id=\'about-reviews\']/div[2]/div/div/div/div[1]/div");
	By featCourseSelenium = By.xpath("//*[@id=\'content\']/div/ul/li[1]/div");
	By FCSeleniumRating = By.xpath("//*[@id=\'content\']/div/ul/li[1]/div/div[1]/p[2]/span[2]");
	By FCSeleniumPrice = By.xpath("//*[@id=\'content\']/div/ul/li[1]/div/div[2]");

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getLogin() {

		return driver.findElement(signin);

	}

	public WebElement getCloseButton() {

		return driver.findElement(closeButton);

	}

	public WebElement getNavigationHome() {

		return driver.findElement(navigationHome);

	}

	public WebElement getNavigationBarCourses() {

		return driver.findElement(navigationCourses);

	}

	public WebElement getNavigationVideos() {

		return driver.findElement(navigationVideos);

	}

	public WebElement getNavigationInterviewGuide() {

		return driver.findElement(navigationInterviewGuide);

	}

	public WebElement getNavigationPractice() {

		return driver.findElement(navigationPractice);

	}

	public WebElement getNavigationBlog() {

		return driver.findElement(navigationBlog);

	}

	public WebElement getNavigationAbout() {

		return driver.findElement(navigationAbout);

	}

	public WebElement getNavigationContact() {

		return driver.findElement(navigationContact);

	}

	public List<WebElement> getCountLinks() {

		return driver.findElements(countLinks);

	}

	public List<WebElement> getLinks() {

		return driver.findElements(countLinks);

	}

	public WebElement getfeatureCoursesFrame() {

		return driver.findElement(featureCoursesFrame);

	}

	public WebElement getCoursesLinks() {

		return driver.findElement(countCoursesLinks);

	}

	public WebElement getMissionMsg() {

		return driver.findElement(missionMsg);

	}

	public WebElement getFeatCourseSelenium() {

		return driver.findElement(featCourseSelenium);

	}

	public WebElement getFCSeleniumRating() {

		return driver.findElement(FCSeleniumRating);

	}

	public WebElement getFCSeleniumPrice() {

		return driver.findElement(FCSeleniumPrice);

	}

}
