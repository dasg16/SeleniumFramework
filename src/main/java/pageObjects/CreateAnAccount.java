package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAnAccount {

	public WebDriver driver;

	By createAnAccountButton = By.linkText("Create an Account");
	By fullName = By.id("user_name");
	By emailAddress = By.id("user_email");
	By password = By.name("user[password]");
	By confirmPassword = By.name("user[password_confirmation]");
	By checkBoxPolicy = By.id("user_agreed_to_terms");
	By signUpButton = By.xpath("//*[@id=\'new_user\']/center/input");

	By fullNameErrorMsg = By.xpath("//*[@id=\'new_user\']/div[1]/ul/li[1]");
	By emailErorrMsg = By.xpath("//*[@id=\'new_user\']/div[1]/ul/li[2]");
	By pwdNullErrorMsg = By.xpath("//*[@id=\'new_user\']/div[1]/ul/li[3]");
	By pwdLengthErrorMsg = By.xpath("//*[@id=\'new_user\']/div[1]/ul/li[4]");
	By errorMsgs = By.xpath("//*[@id=\'new_user\']/div[1]");

	public CreateAnAccount(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getCreateAccount() {

		return driver.findElement(createAnAccountButton);

	}

	public WebElement getFullName() {

		return driver.findElement(fullName);
	}

	public WebElement getEmailAddess() {

		return driver.findElement(emailAddress);
	}

	public WebElement getPassword() {

		return driver.findElement(password);
	}

	public WebElement getConfirmationPassword() {

		return driver.findElement(confirmPassword);
	}

	public WebElement getCheckBoxPolicy() {

		return driver.findElement(checkBoxPolicy);
	}

	public WebElement getSignUpButton() {

		return driver.findElement(signUpButton);
	}

	public WebElement getFullNameErrorMsg() {

		return driver.findElement(fullNameErrorMsg);
	}

	public WebElement getEmailErrorMsg() {

		return driver.findElement(emailErorrMsg);
	}

	public WebElement getPwdNullErrorMsg() {

		return driver.findElement(pwdNullErrorMsg);
	}

	public WebElement getPwdLengthErrorMsg() {

		return driver.findElement(pwdLengthErrorMsg);
	}

	public WebElement getErrorMsgs() {

		return driver.findElement(errorMsgs);
	}
}
