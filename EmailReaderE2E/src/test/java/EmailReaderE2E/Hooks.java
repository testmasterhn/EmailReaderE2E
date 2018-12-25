package EmailReaderE2E;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;

	@Before
	public void BeforeScenario() {
		String webdriver = System.getProperty("browser", "chrome");
		switch (webdriver) {
		case "chrome":
			//System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Driver\\chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=vi-VN");
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			//System.setProperty("webdriver.gecko.driver", "C:\\\\Selenium\\\\Driver\\\\geckodriver.exe");
			FirefoxOptions ffoption = new FirefoxOptions();
			ffoption.addArguments("--lang=vi-VN");
			driver = new FirefoxDriver(ffoption);
			break;
		default:
            throw new RuntimeException("Unsupported webdriver: " + webdriver);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@After
	public void AfterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException platformdontsupportscreenshot) {
				System.err.println(platformdontsupportscreenshot.getMessage());
			}
		}
		driver.quit();
	}
}
