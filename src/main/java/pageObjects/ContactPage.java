package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage {

	public WebDriver driver;

	private By contactLable = By.xpath("//*[@id=\'contact\']/div/div/div[2]/div[6]/div/h2");
	By address = By.xpath("//*[@id=\'contact\']/div/div/div[2]/div[7]/div[2]");
	By phone = By.linkText("(+1) 323-744-6780");
	By email = By.xpath("//*[@id=\'contact\']/div/div/div[2]/div[11]/div[2]");

	public ContactPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getContactLable() {
		return driver.findElement(contactLable);

	}

	public WebElement getAddress() {
		return driver.findElement(address);

	}

	public WebElement getPhone() {
		return driver.findElement(phone);

	}

	public WebElement getEmail() {
		return driver.findElement(email);

	}

	public By getContactLableBy() {

		return contactLable;

	}

}
