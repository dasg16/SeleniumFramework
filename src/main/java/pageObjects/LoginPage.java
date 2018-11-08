package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;

	By email = By.xpath("//*[@id=\'user_email\']");
	By password = By.xpath("//*[@id=\'user_password\']");
	By loginButton = By.xpath("//*[@id='new_user\']/div[3]/input");
	By loginErrorMsg = By.xpath("/html/body/div/div[1]/div/div/div/div/div[1]/div/div");
	By forgetPwd = By.xpath("//*[@id=\'new_user\']/center/a");

	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getEmail() {
		return driver.findElement(email);

	}

	public WebElement getPassword() {
		return driver.findElement(password);

	}

	public WebElement getLoginButton() {
		return driver.findElement(loginButton);

	}

	public WebElement getLoginErrorMsg() {
		return driver.findElement(loginErrorMsg);

	}

	public WebElement getForgetPwd() {
		return driver.findElement(forgetPwd);

	}

}
