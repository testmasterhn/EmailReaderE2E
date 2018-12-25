package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Subscription {
	public By extraForm = By.cssSelector("div.extra-subscription-form");
	
	@FindBy(css="div.subscription input[type=email]")
	public WebElement txtSubscriberEmail;
	
	@FindBy(css="div.subscription button.next-btn-primary")
	public WebElement btnRegister;
	
	@FindBy(css="div.extra-subscription-form #fullname")
	public WebElement txtFullName;
	
	@FindBy(css="div.extra-subscription-form button.primary")
	public WebElement btnFinish;
	
	public Subscription(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
