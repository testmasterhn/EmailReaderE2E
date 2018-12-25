package EmailReaderE2E;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageobjects.Subscripted;
import pageobjects.Subscription;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.MailReader;
import common.Utility;

public class Stepdefs {
	WebDriver driver;
	Subscription subscriptionPage;
	public Stepdefs()
	{
		this.driver = Hooks.driver;
		this.subscriptionPage = new Subscription(this.driver);
	}
	
	@Given("^I am staying at Testmaster homepage$")
	public void i_am_staying_at_Testmaster_homepage() throws Exception {
		this.driver.get("http://www.testmaster.vn");
		
	}

	@When("^I provide the valid email and it is not to be used before$")
	public void i_provide_the_valid_email_and_it_isn_t_to_be_used_before() throws Exception {
		Utility.scrolled_element_into_view(this.driver, this.subscriptionPage.txtSubscriberEmail);
		this.subscriptionPage.txtSubscriberEmail.sendKeys("mrt.testmailservice@gmail.com");
	}

	@When("^I do subscription to show extra form to provide more personal information$")
	public void i_do_subscription_to_show_extra_form_to_provide_more_personal_information() throws Exception {
		this.subscriptionPage.btnRegister.click();
		WebDriverWait waiter = new WebDriverWait(this.driver, 5);
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(this.subscriptionPage.extraForm));
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.subscriptionPage = new Subscription(this.driver); 
	}

	@When("^I provide required valid information on extra form$")
	public void i_provide_required_valid_information_on_extra_form() throws Exception {
		this.subscriptionPage.txtFullName.sendKeys("Tran Xuan Khanh");
		this.subscriptionPage.btnFinish.click();
	}

	@When("^I wait until Activation Email was sent to my MailBox then open Activation Link$")
	public void i_wait_until_Activation_Email_was_sent_to_my_MailBox_then_open_Activation_Link() throws Exception {
		String emailContent="";
		MailReader mailReader;
		while(true)
		{
			mailReader = new MailReader("mrt.testmailservice@gmail.com", "@admin!@#");
			if(mailReader.messages.length>0)
			{
				emailContent = mailReader.getContent(mailReader.messages[0]);
				System.out.println(emailContent);
			}
			if(emailContent=="")
				Thread.sleep(60000);
			else
				break;
		}
		
		String activationLink = Utility.getHref(emailContent);
		if(!activationLink.isEmpty())
		{
			this.driver.get(activationLink);
			this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}

	@Then("^I should see the message \"([^\"]*)\"$")
	public void i_should_see_the_message(String msg) throws Exception {
		Subscripted subscriptedPage = new Subscripted(this.driver);
		assertEquals(subscriptedPage.lbActivatedNotify.getText(), msg);
		
	}

}