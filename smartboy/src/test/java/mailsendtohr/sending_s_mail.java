package mailsendtohr;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class sending_s_mail {

//	public static void main(String[] args) {
//	
//	System.setProperty("webdriver.gecko.driver","./softwares/geckodriver.exe");
//	WebDriver driver = new FirefoxDriver();
//	
//	driver.get("http://accounts.google.com/v3/signin/challenge/pwd?TL=ALbfvL1b3MMT5TpHgQJiwfCuYoV6iG7HBM1gLhyQZyuSU4eFK89T25HDjSVN_PzE&checkConnection=youtube%3A964%3A1&checkedDomains=youtube&cid=2&continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&dsh=S1459653870%3A1676462768975977&emr=1&flowEntry=ServiceLogin&flowName=GlifWebSignIn&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&ifkv=AWnogHfa7w2omNVGx_0HLwl0SEfxKO0ndeFiNHuJmdFTBCszuI086FWYzDAqMyqS9m78Lj68rI8UsA&osid=1&pstMsg=1&service=mail");
//	
//    driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("sathish.thirumalegowda@gmail.com");
//    
//	WebElement btn = driver.findElement(By.xpath("//span[normalize-space()='Next']"));
//	btn.click();
//	driver.findElement(By.xpath("//a[@class='WpHeLc VfPpkd-mRLv6 VfPpkd-RLmnJb']")).click();
//	
	

	 public static void main(String[] args) {
	  
	  WebDriver driver;
	  
	  System.setProperty("webdriver.chrome.driver", ".softwares/chromedriver.exe");
	  System.setProperty("webdriver.chrome.silentOutput", "true");
	  
	  driver = new ChromeDriver();
	  driver.navigate().to("https://mail.google.com/");
	  
	  driver.manage().window().maximize();
	  
	  // locate "Email or Phone" text input with "name" locator and enter email say --> java.selenium2021@gmail.com

	  driver.findElement(By.name("identifier")).sendKeys("java.selenium2021@gmail.com");
	  
	  // click on "Next" button - This is an xpath example that will be covered in later sessions
	  driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
	  
	 // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	  //locate "Enter your password" text input with "password" locator and enter password say --> JavaSelenium2021
	  WebElement elePassword=driver.findElement(By.name("password"));
	  elePassword.sendKeys("JavaSelenium2021");
	  
	  elePassword.sendKeys(Keys.TAB); 
	  elePassword.sendKeys(Keys.TAB); 
	   
	  // click on "Next" button - This is again an xpath example. 
	  driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
	  
	  //close the driver
	  //driver.close();
	 
	
	
	}

}
