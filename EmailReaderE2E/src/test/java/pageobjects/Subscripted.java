package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Subscripted {
	@FindBy(css="div.popover-dialog div.body-message")
	public WebElement lbActivatedNotify;
	
	public Subscripted(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}
