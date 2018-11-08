package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PracticePage {

	public WebDriver driver;

	By radioButtonTitle = By.xpath("//*[@id=\'radio-btn-example\']/fieldset/legend");
	By radioButtonCommonPath = By.name("radioButton");

	By suggessionTxtBoxTitle = By.xpath("//*[@id=\'select-class-example\']/fieldset/legend");
	By suggessionTxtBoxPath = By.xpath("//*[@id=\'autocomplete\']");

	By dropdownTitle = By.xpath("/html/body/div[1]/div[3]/fieldset/legend");
	By dropdownCommonPath = By.xpath("//*[@id=\'dropdown-class-example\']");
	By dropdownOption1 = By.xpath("//*[@id=\'dropdown-class-example\']/option[2]");
	By dropdownOption2 = By.xpath("//*[@id=\'dropdown-class-example\']/option[3]");
	By dropdownOption3 = By.xpath("//*[@id=\'dropdown-class-example\']/option[4]");

	By checkboxTitle = By.xpath("//div[@id=\'checkbox-example\']/fieldset/legend");
	By checkboxOnePath = By.xpath("//*[@id=\"checkBoxOption1\"]");
	By checkboxTwoPath = By.id("checkBoxOption2");
	By checkboxThreePath = By.id("checkBoxOption3");

	By childWindowTitle = By.xpath("/html/body/div[2]/div[1]/fieldset/legend");
	By childWindowPath = By.xpath("//*[@id=\'openwindow\']");

	By switchTabTitle = By.xpath("/html/body/div[2]/div[2]/fieldset/legend");
	By tabPath = By.xpath("//*[@id=\'opentab\']");

	By alertTitle = By.xpath("/html/body/div[2]/div[3]/fieldset/legend");
	By alertTextBox = By.xpath("//*[@id=\'name\']");
	By alertButton = By.xpath("//*[@id=\'alertbtn\']");
	By alertConfirmButton = By.xpath("//*[@id=\'confirmbtn\']");

	By isDisplayedTitle = By.xpath("/html/body/div[3]/div[2]/fieldset/legend");
	By hideButton = By.xpath("//*[@id=\'hide-textbox\']");
	By showButton = By.xpath("//*[@id=\'show-textbox\']");
	By isDisplayedElement = By.xpath("//*[@id=\'displayed-text\']");

	By tableTitle = By.xpath("/html/body/div[3]/div[1]/fieldset/legend");
	By instColCommonPath = By.xpath("//*[@id=\'user_email\']");
	By courseColCommonPath = By.xpath("//*[@id=\'user_email\']");
	By priceColCommonPath = By.xpath("//*[@id=\'user_email\']");

	By mouseHoverTitle = By.xpath("/html/body/div[4]/div/fieldset/legend");
	By mouseHoverButton = By.xpath("//*[@id=\'mousehover\']");
	By mouseHoverTopButton = By.xpath("/html/body/div[4]/div/fieldset/div/div/a[1]");
	By mouseHoverReloadButton = By.xpath("/html/body/div[4]/div/fieldset/div/div/a[2]");

	By iframeTitle = By.xpath("//*[@id=\'user_email\']");
	By frameCommonPath = By.xpath("//*[@id=\'user_password\']");

	public PracticePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getRadioButtonTitle() {
		return driver.findElement(radioButtonTitle);

	}

	public List<WebElement> getRadioButtonCommonPath() {
		return driver.findElements(radioButtonCommonPath);

	}

	public WebElement getSuggessionTxtBoxTitle() {
		return driver.findElement(suggessionTxtBoxTitle);

	}

	public WebElement getSuggessionTxtBoxPath() {
		return driver.findElement(suggessionTxtBoxPath);

	}

	public WebElement getDropdownTitle() {
		return driver.findElement(dropdownTitle);

	}

	public WebElement getDropdownCommonPath() {
		return driver.findElement(dropdownCommonPath);

	}

	public WebElement getDropdownOption1() {
		return driver.findElement(dropdownOption1);

	}

	public WebElement getDropdownOption2() {
		return driver.findElement(dropdownOption2);

	}

	public WebElement getDropdownOption3() {
		return driver.findElement(dropdownOption3);

	}

	public WebElement getCheckboxTitle() {
		return driver.findElement(checkboxTitle);

	}

	public WebElement getCheckboxOnePath() {
		return driver.findElement(checkboxOnePath);

	}

	public WebElement getCheckboxTwoPath() {
		return driver.findElement(checkboxTwoPath);

	}

	public WebElement getCheckboxThreePath() {
		return driver.findElement(checkboxThreePath);

	}

	public WebElement getChildWindowTitle() {
		return driver.findElement(childWindowTitle);

	}

	public WebElement getChildWindowPath() {
		return driver.findElement(childWindowPath);

	}

	public WebElement getSwitchTabTitle() {
		return driver.findElement(switchTabTitle);

	}

	public WebElement getTabPath() {
		return driver.findElement(tabPath);

	}

	public WebElement getAlertTitle() {
		return driver.findElement(alertTitle);

	}

	public WebElement getAlertTextBox() {
		return driver.findElement(alertTextBox);

	}

	public WebElement getAlertButton() {
		return driver.findElement(alertButton);

	}

	public WebElement getAlertConfirmButton() {
		return driver.findElement(alertConfirmButton);

	}

	public WebElement getIsDisplayedTitle() {
		return driver.findElement(isDisplayedTitle);

	}

	public WebElement getHideButton() {
		return driver.findElement(hideButton);

	}

	public WebElement getShowButton() {
		return driver.findElement(showButton);

	}

	public WebElement getIsDisplayedElement() {
		return driver.findElement(isDisplayedElement);

	}

	public WebElement getTableTitle() {
		return driver.findElement(tableTitle);

	}

	public WebElement getInstColCommonPath() {
		return driver.findElement(instColCommonPath);

	}

	public WebElement getCourseColCommonPath() {
		return driver.findElement(courseColCommonPath);

	}

	public WebElement getPriceColCommonPath() {
		return driver.findElement(priceColCommonPath);

	}

	public WebElement getMouseHoverTitle() {
		return driver.findElement(mouseHoverTitle);

	}

	public WebElement getMouseHoverButton() {
		return driver.findElement(mouseHoverButton);

	}

	public WebElement getMouseHoverTopButton() {
		return driver.findElement(mouseHoverTopButton);

	}

	public WebElement getMouseHoverReloadButton() {
		return driver.findElement(mouseHoverReloadButton);

	}

	public WebElement getIframeTitle() {
		return driver.findElement(iframeTitle);

	}

	public WebElement getFrameCommonPath() {
		return driver.findElement(frameCommonPath);

	}

}
