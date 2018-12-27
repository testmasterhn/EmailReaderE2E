package EmailReaderE2E;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import common.Utility;

public class TestUpload {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://uploadfiles.io/");
		
		driver.findElement(By.id("upload-window")).click();
		
		Utility.uploadFile("C:\\Huong dan.txt");
	}
}
