package WebTestFrame;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.PracticePage;
import resources.base;

public class ValidatePractice extends base {

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest(groups = { "Smoke", "End2End" })
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver has been initialized");
		driver.get(prop.getProperty("url"));
		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();

	}

	@BeforeClass(groups = { "Smoke", "End2End" })
	public void validPracticeLink() throws IOException, Exception {

		// counting total number of links in the landing page.
		LandingPage lanp = new LandingPage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		int count = lanp.getCountLinks().size();

		for (int i = 0; i < count; i++) {

			String test = lanp.getLinks().get(i).getAttribute("href");

			if (test.toLowerCase().contains("practice")) {
				lanp.getLinks().get(i).click();
				// Thread.sleep(5000L);

				log.info("Validated Practice page link on HomePage");
				i = count;// Doubt! Why isn't this working without this statement?

			}

		}

		Assert.assertEquals(driver.getTitle(), "Practice Page");
		log.info("Moved into Practice page");

	}

	@Test(priority = 1, groups = { "End2End" })
	public void validPracticeRadio() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getRadioButtonTitle().getText(), "Radio Button Example");
		log.info("Verified radio button title tag");

		int count = pracP.getRadioButtonCommonPath().size();

		for (int i = 0; i < count; i++) {

			pracP.getRadioButtonCommonPath().get(i).click();

		}
	}

	@Test(priority = 2, groups = { "End2End" })
	public void validSuggessionTxtBox() {

		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getSuggessionTxtBoxTitle().getText(), "Suggession Class Example");
		log.info("Verified Suggestion Class Textbox title tag");

		pracP.getSuggessionTxtBoxPath().click();

		Actions a = new Actions(driver);

		a.moveToElement(pracP.getSuggessionTxtBoxPath()).click().keyDown(Keys.SHIFT).sendKeys("hello").build()
				.perform();
		// a.moveToElement(pracP.getSuggessionTxtBoxPath()).contextClick().build().perform();
		// a.moveToElement(pracP.getSuggessionTxtBoxPath()).click().build().perform();

	}

	@Test(groups = { "End2End" })
	public void validPracticeDropdown() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getDropdownTitle().getText(), "Dropdown Example");
		log.info("Verified dropdown button title tag");

		// Using JavaScriptExecutor because otherwise the links are not clickable. Using
		// explicit wait ExpectedConditions.elementToBeClickable worked for a while.
		// WebElement element = pracP.getDropdownCommonPath();
		Select s = new Select(pracP.getDropdownCommonPath());

		s.selectByValue("option1");
		// Validate dropdown option 1
		Assert.assertTrue(pracP.getDropdownOption1().isSelected());
		log.info("Validated dropdown option 1");

		// Here selecting inex 2 instead of 1 because in 0th index we have just select
		// option.
		s.selectByIndex(2);
		// Validate dropdown option 2
		Assert.assertTrue(pracP.getDropdownOption2().isSelected());
		log.info("Validated dropdown option 2");

		s.selectByVisibleText("Option3");
		// Validate dropdown option 3
		Assert.assertTrue(pracP.getDropdownOption3().isSelected());
		log.info("Validated dropdown option 3");

	}

	@Test(priority = 4, groups = { "End2End" })
	public void validPracticeCheckbox() {
		PracticePage pracP = new PracticePage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Assert.assertEquals(pracP.getCheckboxTitle().getText(), "Checkbox Example");
		log.info("Verified dropdown button title tag");

		String checkboxLable = pracP.getCheckboxTitle().getText();

		// Validate checkbox lable
		Assert.assertEquals(checkboxLable, "Checkbox Example");
		log.info("Verified checkbox lable");

		pracP.getCheckboxOnePath().click();
		// Validate checkbox option one is selected
		Assert.assertTrue(pracP.getCheckboxOnePath().isSelected());
		log.info("Validate checkbox option one is selected");

		pracP.getCheckboxTwoPath().click();
		// Validate checkbox option two is selected
		Assert.assertTrue(pracP.getCheckboxTwoPath().isSelected());
		log.info("Validate checkbox option two is selected");

		pracP.getCheckboxThreePath().click();
		// Validate checkbox option three is selected
		Assert.assertTrue(pracP.getCheckboxThreePath().isSelected());
		log.info("Validate checkbox option three is selected");

	}

	@Test(priority = 5, groups = { "Smoke", "End2End" })
	public void validPracticeSwitchWindow() {
		PracticePage pracP = new PracticePage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Assert.assertEquals(pracP.getChildWindowTitle().getText(), "Switch Window Example");
		log.info("Verified Switch Window title tag");

		pracP.getChildWindowPath().click();

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);

		driver.switchTo().window(childID).close();
		driver.switchTo().window(parentID);

	}

	@Test(priority = 6, groups = { "End2End" })
	public void validPracticeSwitchTab() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getSwitchTabTitle().getText(), "Switch Tab Example");
		log.info("Verified Switch Window title tag");

		pracP.getTabPath().click();

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
		driver.switchTo().window(childID).close();
		driver.switchTo().window(parentID);

	}

	@Test(priority = 7, groups = { "End2End" })
	public void validPracticeSwitchAlert() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getAlertTitle().getText(), "Switch To Alert Example");
		log.info("Verified Switch Window title tag");

		pracP.getAlertTextBox().sendKeys("Gourav");
		pracP.getAlertButton().click();
		String msg = driver.switchTo().alert().getText();

		Assert.assertEquals(msg, "Hello GOURAV, share this practice page and share your knowledge");

		driver.switchTo().alert().accept();
		pracP.getAlertConfirmButton().click();
		driver.switchTo().alert().dismiss();

	}

	@Test(priority = 8, groups = { "End2End" })
	public void validPracticeElementDisplayed() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getIsDisplayedTitle().getText(), "Element Displayed Example");
		log.info("Verified Element Displayed title tag");

		// Validate hide button
		pracP.getHideButton().click();
		Assert.assertFalse(pracP.getIsDisplayedElement().isDisplayed());
		log.info("Validated hide button");

		// Validate show button
		pracP.getShowButton().click();
		Assert.assertTrue(pracP.getIsDisplayedElement().isDisplayed());
		log.info("Validated show button");
	}

	@Test(priority = 9, groups = { "End2End" })
	public void validPracticeWebTable() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getAlertTitle().getText(), "Switch To Alert Example");
		log.info("Verified Switch Window title tag");

		pracP.getAlertTextBox().sendKeys("Gourav");
		pracP.getAlertButton().click();
		String msg = driver.switchTo().alert().getText();

		Assert.assertEquals(msg, "Hello Gourav, share this practice page and share your knowledge");

		driver.switchTo().alert().accept();
		pracP.getAlertConfirmButton().click();
		driver.switchTo().alert().dismiss();

	}

	@Test(priority = 10, groups = { "End2End" })
	public void validPracMouseHovrTop() {
		PracticePage pracP = new PracticePage(driver);

		Assert.assertEquals(pracP.getMouseHoverTitle().getText(), "Mouse Hover Example");
		log.info("Verified Mouse Hover title tag");

		Actions a = new Actions(driver);
		a.moveToElement(pracP.getMouseHoverButton()).build().perform();
		a.moveToElement(pracP.getMouseHoverTopButton()).click().build().perform();
		log.info("Verified Mouse Hovertop button");

	}

	@Test(priority = 11, groups = { "End2End" })
	public void validPracMouseHovrReload() {
		PracticePage pracP = new PracticePage(driver);

		Actions a = new Actions(driver);

		a.moveToElement(pracP.getMouseHoverButton()).build().perform();
		a.moveToElement(pracP.getMouseHoverReloadButton()).click().build().perform();
		log.info("Verified Mouse Hovertop reload");

	}

	@AfterTest(groups = { "Smoke", "End2End" })
	public void teardown() {
		driver.quit();
		driver = null;
	}

}
