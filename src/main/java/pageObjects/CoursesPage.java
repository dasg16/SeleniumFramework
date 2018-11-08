package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CoursesPage {

	public WebDriver driver;

	By tagNames = By.tagName("a");
	By courseButton = By.xpath("//*[@id=\'homepage\']/header/div[2]/div/nav/ul/li[2]/a");

	public CoursesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public List<WebElement> getLinks() {

		return driver.findElements(tagNames);

	}

	public WebElement getCourseButton() {

		return driver.findElement(courseButton);

	}

}
