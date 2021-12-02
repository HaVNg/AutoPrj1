package com.qa.opencart.bases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.qa.opencart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager op;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = Logger.getLogger(String.valueOf(BasePage.class));

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize local browser
	 * 
	 * @param browser
	 * @return WebDriver
	 */

	public WebDriver initDriver(Properties prop) {
		String browser = prop.getProperty("browser").trim();
		String browserVersion = prop.getProperty("browserVersion").trim();
		
		op = new OptionsManager(prop);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				log.info("Running test on remote chrome browser");
				initRemoteDriver("chrome", browserVersion);
				log.info("Launching with browser: " + browser + " and browser version: " + browserVersion);
			} else {
				log.info("Running test on local chrome browser");
				tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			}
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				log.info("Running test on remote firefox browser");
				initRemoteDriver("firefox", browserVersion);
				log.info("Launching with browser: " + browser + " and browser version: " + browserVersion);
			} else {
				log.info("Running test on local chrome browser");
				tlDriver.set(new FirefoxDriver(op.getFirefoxOptions()));
			}
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();
	}

	/**
	 * This method is used to initialize remote browser
	 * 
	 * @param browser
	 * @param browserVersion
	 */
	public void initRemoteDriver(String browser, String browserVersion) {
		log.info("Launching remote browser on remote gird: " + browser + " and browser version: " + browserVersion);

		if (browser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("browser", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			cap.setCapability(ChromeOptions.CAPABILITY, op.getChromeOptions());

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("browser", "firefox");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, op.getFirefoxOptions());

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is used to load properties from config.properties
	 * 
	 * @param prop
	 * @return Properties
	 */
	public Properties initProp() {
		prop = new Properties();
		//PropertyConfigurator.configure(System.getProperty("./src/test/resources/logs/app_log.txt"));
		
		
		String path = null;
		String env = System.getProperty("env"); // mvn clean install -Denv="qa" 
				//D: pass command line with the help of Maven. env is env variable.

		try {
			if (env == null) { // null defaults to Prod env
				log.info("Running test on PROD environment");
				path = "./src/test/resources/config/config.properties";
				
			} else {
				switch (env) {
				case "qa":
					log.info("Running test on QA environment");
					path = "./src/test/resources/config/qa.config.properties";
					break;
				case "dev":
					log.info("Running test on DEV environment");
					path = "./src/test/resources/config/dev.config.properties";
					break;
				case "stage":
					log.info("Running test on STAGE environment");
					path = "./src/test/resources/config/stage.config.properties";	
					break;
				default:
					log.info("No environment found!");
					throw new Exception("NO ENVIROMENT FOUND EXCEPTION!");
				}
				
			}

			FileInputStream fis = new FileInputStream(path);
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * This method is used to take screenshot
	 * 
	 * @return String
	 */
	public String getScreenshot() {
		Date d = new Date();
		String path = System.getProperty("user.dir") + "/screenshots/"
				+ d.toString().replaceAll(":", " ").replaceAll("-", " ") + ".png";
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);

		try {
			log.info("Get screenshot of failed test case");
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}


	
	
	
}