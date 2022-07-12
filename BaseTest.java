package com.veracross.magnus.tests;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import com.veracross.magnus.logs.*;
import com.veracross.magnus.reusableMethods.WebPageOperations;
import com.veracross.magnus.utilities.ConfigFileReader;
import com.veracross.magnus.utilities.FrameworkConfig;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	FrameworkConfig configuration = ConfigFactory.create(FrameworkConfig.class);


	public WebDriver driver;
	protected static WebPageOperations webPageOperations;
	ConfigFileReader configFileReader;

	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void browserSetUp(@Optional("chrome") String browser) throws Exception {

		browserInitialization(browser);

		System.out.println("Before Class method started successfully");

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		Thread.sleep(5000);
		Log.info("driver successfully closed");
	}

	public WebDriver browserInitialization(String browser) throws Exception {

		System.out.println("BeforeClass method execution started");

		//configFileReader = new ConfigFileReader("Configuration");

		String driverPath = configFileReader.getDriverPath(browser);
		if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", driverPath);
			driver = new InternetExplorerDriver();

		}
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			System.out.println("ChromeDriver started Successfully");
		}

		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to Edge.exe
			System.setProperty("webdriver.edge.driver", driverPath);
			// create Edge instance
			driver = new EdgeDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		// webPageOperations = new WebPageOperations();
		WebPageOperations.setWebDriver(driver);
		System.out.println("browser test");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		driver.manage().window().maximize();
		System.out.println("Navigate to Magnus Login URL");
		//driver.get(System.getProperty("TestURL"));
		navigateToLogin(System.getProperty("env"));
//		WebPageOperations.waitForAlertPresent();
//		WebPageOperations.alertAccept();

		System.out.println("Before class method successfully executed");

		return driver;

	}
	public void navigateToLogin(String environment) {
		String env = "";
		if (environment.toLowerCase().contains("test")) {
			env = "Test";
		} else if (environment.toLowerCase().contains("demo")) {
			env = "Demo";
		} else if (environment.toLowerCase().contains("prod")) {
			env = "Prod";
		} else {
			Assert.fail("Could not find the URL for " + environment + " in config.properties");
		}
		System.out.println("Navigating to " + environment + " login page");
		driver.get(System.getProperty(env + ".url"));
	}
	public WebDriver getTargetDriver() {
		if (driver == null) {
			String message = "Driver is null, cannot continue. Application has probably crashed or Driver creation failed!";
			System.out.println(message);
		}
		PageFactory.initElements(driver, this);
		return driver;
	}

}
