package WebTestFrame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class validateLogin extends base {

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest(groups = { "Smoke", "End2End" })
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver has been initialized");
		driver.get(prop.getProperty("url"));
	}

	@BeforeClass(groups = { "Smoke", "End2End" })
	public void validPopClose() {
		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();

		lanp.getLogin().click();
	}

	@Test(dataProvider = "getData", groups = { "Smoke", "End2End" })
	public void validEmailPwd(String username, String password) throws IOException {

		LoginPage logp = new LoginPage(driver);

		log.info("Clicked on the loggin button in the home page");

		if ((username == "gourav@gmail.com") && (password == "12345")) {
			logp.getEmail().sendKeys(username);
			logp.getPassword().sendKeys(password);
			log.info("Inserted username and password");

			logp.getLoginButton().click();
			log.info("clicked on the Login button in the login page");

			Assert.assertEquals(logp.getLoginErrorMsg().getText(), "Invalid email or password.");
			log.info("Validate if invalid username password has been displayed");

			logp.getEmail().clear();
			logp.getPassword().clear();
		}

	}

	@DataProvider
	public Object[][] getData() {

		Object[][] data = new Object[3][2];

		data[0][0] = "gourav@gmail.com";
		data[0][1] = "12345";

		data[1][0] = "gourav";
		data[1][1] = "145789";

		data[2][0] = "gourav@";
		data[2][1] = "145789";

		// data[3][0] = "gouravdash.dash6@gmail.com";
		// data[3][1] = "Gourav@123";

		return data;

	}

	@Test(enabled = false)
	public void databaseConnLogin() throws Throwable {

		String host = "localhost";
		String port = "3306";

		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/EmployeePortal"
				+ "?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "Bullshit@123");
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("select * from credentials where scenario='zerobalancecard'");

		while (rs.next()) {
			System.out.println(rs.getString("username"));
			System.out.println(rs.getString("password"));
		}

	}

	// Method for generating "Please include an '@' in the email address. 'gourav'
	// is missing an '@'." toast message.
	// To generate toast when @ has not been used in the email ID.
	@Test(enabled = false) // (dataProvider = "getData")
	public void validEmailAddrs(String username, String password) throws IOException {

		LoginPage logp = new LoginPage(driver);

		if ((username == "gourav") && (password == "145789")) {
			logp.getEmail().sendKeys(username);
			log.info("Typed Invalid email address without entering @");

			// <input autocomplete="off" class="form-control input-hg" type="password"
			// Please include an '@' in the email address. 'gourav' is missing an '@'.
			// name="user[password]" id="user_password">
			logp.getLoginButton().click();
			log.info("clicked on the Login button in the login page");

			WebElement autocomplete = logp.getEmail();
			String abc = autocomplete.findElement(By.tagName("input")).getText();
			// selectOptionWithText("Please include an '@' in the email address. 'gourav' is
			// missing an '@'");
			System.out.println(abc);

			Assert.assertEquals(logp.getLoginErrorMsg().getText(),
					"Please include an '@' in the email address. 'gourav' is missing an '@'.");
			log.info("Validated toast when @ has not been used in the email ID");

		}

		logp.getEmail().clear();
		logp.getPassword().clear();
	}

	// Method for generating "Please enter a part following '@'. 'gourav@' is
	// incomplete." toast message.
	// To generate toast when nothing has been written after @ in the email ID.
	@Test(enabled = false) // (dataProvider = "getData")
	public void validPwd(String username, String password) throws IOException {

		LoginPage logp = new LoginPage(driver);

		if ((username == "gourav@") && (password == "145789")) {
			logp.getEmail().sendKeys(username);
			log.info("Typed Invalid email address without entering anything after @");

			logp.getLoginButton().click();
			log.info("clicked on the Login button in the login page");

			Assert.assertEquals(logp.getLoginErrorMsg().getText(),
					"Please enter a part following '@'. 'gourav@' is incomplete.");
			log.info("Validated toast when nothing has been written after @ in the email ID");
		}

		logp.getEmail().clear();
		logp.getPassword().clear();
	}

	// Method for validating color of error test message.
	@Test(priority = 4)
	public void validErrorColor() throws IOException {

		LoginPage logp = new LoginPage(driver);

		String abc = logp.getLoginErrorMsg().getCssValue("color");

		// Converting RBG color to hex color.
		String hexColor = Color.fromString(abc).asHex();

		// Validate the color of the text in the error message.
		Assert.assertEquals(hexColor, "#c74a47");
		log.info("Validated color of error test message");
	}

	// Method for validating background color of error message.
	@Test(priority = 5)
	public void validErrorBgColor() throws IOException {

		LoginPage logp = new LoginPage(driver);

		String abc = logp.getLoginErrorMsg().getCssValue("background-color");

		// Converting RBG color to hex color.
		String hexColor = Color.fromString(abc).asHex();

		// Validate the background color of error message box.
		Assert.assertEquals(hexColor, "#fbe2e2");
		log.info("Validated background color of error message");

	}

	@Test(priority = 6, groups = { "Smoke" })
	public void validForgetPwd() throws IOException {

		LoginPage logp = new LoginPage(driver);

		logp.getForgetPwd().click();
		String resetPwd = driver.getCurrentUrl();
		// Validate the forget password link in the login page.
		Assert.assertEquals(resetPwd, "https://sso.teachable.com/secure/9521/users/password/new");
		log.info("Validated the forget password link in the login page.");

		driver.navigate().to("http://www.qaclickacademy.com/");

	}

	@AfterTest
	public void teardown() {
		driver.close();
		driver = null;
	}

}
