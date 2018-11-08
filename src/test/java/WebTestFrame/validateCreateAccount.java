package WebTestFrame;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CreateAnAccount;
import pageObjects.LandingPage;
import resources.base;

//This class has test cases to validate sign up page
public class validateCreateAccount extends base {
	public static Logger log = LogManager.getLogger(base.class.getName());

	// Initialized driver and got the URL.
	@BeforeTest(groups = { "Smoke", "End2End" })
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver has been initialized");
		driver.get(prop.getProperty("url"));
	}

	// Validate create account functionality with valid inputs(that means with new
	// email ID and new details).
	@Test(enabled = false, groups = { "Smoke", "End2End" })
	public void validSignUp() throws IOException {

		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();
		log.info("Clicked on the close button of the pop-up");
		// clicking on login button on landing page
		lanp.getLogin().click();
		log.info("Clicked on the loggin button");

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getCreateAccount().click();
		caa.getFullName().sendKeys("Gourav Das");
		caa.getEmailAddess().sendKeys("gouravdash.dash6@gmail.com");
		caa.getPassword().sendKeys("Gourav@123");
		caa.getConfirmationPassword().sendKeys("Gourav@123");
		caa.getCheckBoxPolicy().click();
		caa.getSignUpButton().click();
		log.info("Clicked Sign Up button with all the required details and first time used email ID");

		// Validate SignUp
		Assert.assertEquals(driver.getCurrentUrl(), "https://qaclickacademy.usefedora.com/");
		log.info("Validated Sign Up successfully");

	}

	// Validate all text boxes(functionalities) on create account page with no
	// inputs or invalid inputs(e.g of invalid input is using the
	// already existing email ID) to generate various error messages.
	// Using Each Choice combinatorial testing technique for each of the error
	// messages.

	// Method for "Email is required" error message.
	@Test(priority = 1, groups = { "Smoke" })
	public void validEmailReqdError() throws IOException {

		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();
		log.info("Clicked on the close button of the pop-up");

		// clicking on login button on landing page
		lanp.getLogin().click();
		log.info("Clicked on the loggin button");

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getCreateAccount().click();
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");
		// Verify that "Email is required" message is shown when user forgets to write
		// the email in the email address text box.
		Assert.assertEquals(caa.getEmailErrorMsg().getText(), "Email is required");
		log.info("Validated 'Email is required' error message");

	}

	// Method for "Email is already in use" error message.
	@Test(priority = 2)
	public void validEmailAdd() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);

		caa.getEmailAddess().sendKeys("gouravdash.dash6@gmail.com");
		log.info("Entered already existing Email ID");
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		// Verify that "Email is already in use" message is shown when user tries to use
		// the already existing email ID to create an account.
		Assert.assertEquals(caa.getEmailErrorMsg().getText(), "Email is already in use");
		log.info("Validated 'Email is already in use' error message");
	}

	// Method for "Name is required" error message.
	@Test(priority = 3)
	public void validFullName() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		// Verify that "Name is required" message is shown when user forgets to write
		// the Name in the Full Name text box.
		Assert.assertEquals(caa.getFullNameErrorMsg().getText(), "Name is required");
		log.info("Validated 'Name is required' error message");
	}

	// In case of password field we have different scenarios. Firstly we have two
	// scenarios as With Password Text and Without Password Text.
	// Then we can further create two more scenario in With Password text scenario:
	// Valid Password Text and Invalid password text based on length of the
	// password.
	// Then again we can further divide Valid Password Text into two more scenarios
	// as Matching With Confirm Password and Not Matching With Confirm Password.

	// Validate "Password is required" error message.
	@Test(priority = 4)
	public void validPwdNull() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		// Verify that "Password is required" message is shown when user forgets to
		// write the password in the password text box.
		Assert.assertEquals(caa.getPwdNullErrorMsg().getText(), "Password is required");
		log.info("Validated 'Password is required' error message");
	}

	// Validate With Password Text by validating both Valid Password Text and
	// Invalid password text based on the length of the password.
	// It has two equivalent classes: five or less than five characters or More than
	// five characters.

	// Validate "Password is too short (minimum is 6 characters)" error message.
	@Test(priority = 5)
	public void validPwdLength() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);

		// Using Boundary Value, Black box technique to test the values at the
		// boundaries of less than equal to five and greater than five equivalent
		// classes.
		// sending only 5 digit value
		caa.getPassword().sendKeys("Goura");
		log.info("Entered 5 digit value in the text field");
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		// Verify that "Password is too short (minimum is 6 characters)" message is
		// shown when user enters Password length at the boundary of length 5.
		Assert.assertEquals(caa.getPwdLengthErrorMsg().getText(), "Password is too short (minimum is 6 characters)");
		log.info("Validate 'Password is too short (minimum is 6 characters)' error message");

		// sending 6 digit value
		caa.getPassword().sendKeys("Gourav");
		log.info("Entered 6 digit value in the text field");
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		WebElement li = caa.getErrorMsgs();
		int count = li.findElements(By.tagName("li")).size();

		for (int i = 0; i < count; i++) {

			// Verify that "Password is too short (minimum is 6 characters)" message is not
			// showing when user enters Password length at the boundary of length 6.
			Assert.assertFalse(li.findElements(By.tagName("li")).get(i).getText()
					.contains("Password is too short (minimum is 6 characters)"));
			log.info("Validated 'Password is too short (minimum is 6 characters)' error message' is not shown");

		}

	}

	// Validating if the confirm password is same as the password. Confirm Password
	// has only one functionality that is to just verify if it is same as the
	// password or not.

	// Validate "Password confirmation doesn't match Password" error message is not
	// present when both the password and confirm password are the same.
	@Test(priority = 6)
	public void validConPwdSame() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);

		caa.getPassword().sendKeys("Gourav@123");
		caa.getConfirmationPassword().sendKeys("Gourav@123");
		log.info("Entered the same value for password and confirm password");
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		WebElement li = caa.getErrorMsgs();
		int count = li.findElements(By.tagName("li")).size();

		for (int i = 0; i < count; i++) {

			// Verify that "Password is too short (minimum is 6 characters)" message is not
			// showing when user enters Password length at the boundary of length 6.
			Assert.assertFalse(li.findElements(By.tagName("li")).get(i).getText()
					.contains("Password confirmation doesn't match Password"));
			log.info("Validated 'Password confirmation doesn't match Password' is not shown");
		}

	}

	// //Validate "Password confirmation doesn't match Password" error message is
	// shown when both the password and confirm password are different.
	// Confirm Password has only one functionality that is to just verify if it is
	// same as
	// the password or not.
	@Test(priority = 7)
	public void validConPwdNotSame() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);

		caa.getPassword().sendKeys("Gourav@123");
		caa.getConfirmationPassword().sendKeys("1234567");
		log.info("Entered different values for password and confirm password");
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		WebElement li = caa.getErrorMsgs();
		int count = li.findElements(By.tagName("li")).size();
		for (int i = 0; i < count; i++) {

			String liName = li.findElements(By.tagName("li")).get(i).getText();

			// Using if statement to run the loop otherwise because of assert true statement
			// loop won't run.
			if (liName == "Password confirmation doesn't match Password") {

				// Verify that "Password is too short (minimum is 6 characters)" message is not
				// showing when user enters Password length at the boundary of length 6.
				Assert.assertTrue(li.findElements(By.tagName("li")).get(i).getText()
						.contentEquals("Password confirmation doesn't match Password"));
				log.info("Validated 'Password confirmation doesn't match Password' error message is shown");
			}

			// Here specifically else part is required to avoid masking of a fault.
			// If I don't write else part then there might be a situation where "Password
			// confirmation doesn't match Password" error message wasn't thrown still my
			// code was running without showing the error.
			else {
				log.error("Validate 'Password confirmation doesn't match Password' error message wasn't shown");
			}

		}

	}

	// Validate "You must agree to the Terms of Use and Privacy Policy" error
	// message.
	@Test(priority = 8)
	public void validPolicyCheckBox() throws IOException {

		// Clicking on create account button on login page
		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getCheckBoxPolicy().click();
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		WebElement li = caa.getErrorMsgs();
		int count = li.findElements(By.tagName("li")).size();
		for (int i = 0; i < count; i++) {

			String liName = li.findElements(By.tagName("li")).get(i).getText();

			if (liName == "You must agree to the Terms of Use and Privacy Policy") {

				// Verify that You must agree to the Terms of Use and Privacy Policy error
				// message was shown.
				Assert.assertTrue(li.findElements(By.tagName("li")).get(i).getText()
						.contentEquals("You must agree to the Terms of Use and Privacy Policy"));
				log.info("Validated You must agree to the Terms of Use and Privacy Policy error message");
			}

			else {
				log.error("You must agree to the Terms of Use and Privacy Policy error message wasn't shown");
			}

		}
	}

	// Method for validating color of error test message.
	@Test(priority = 9)
	public void validErrorColor() throws IOException {

		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		String abc = caa.getFullNameErrorMsg().getCssValue("color");

		// Converting RBG color to hex color.
		String hexColor = Color.fromString(abc).asHex();

		// Validate the color of the text in the error message.
		Assert.assertEquals(hexColor, "#c74a47");
		log.info("Validated color of error test message");
	}

	// Method for validating background color of error message.
	@Test(priority = 10)
	public void validErrorBgColor() throws IOException {

		CreateAnAccount caa = new CreateAnAccount(driver);
		caa.getSignUpButton().click();
		log.info("Clicked on the Sign Up button");

		String abc = caa.getErrorMsgs().getCssValue("background-color");

		// Converting RBG color to hex color.
		String hexColor = Color.fromString(abc).asHex();

		// Validate the background color of error message box.
		Assert.assertEquals(hexColor, "#fbe2e2");
		log.info("Validated background color of error message");
	}

	// Close the driver and set it to null.
	@AfterTest(groups = { "Smoke", "End2End" })
	public void teardown() {
		driver.close();
		driver = null;
	}

}
