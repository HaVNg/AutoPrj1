package com.qa.opencart.utils;


import java.util.Properties;


import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;


public class OptionsManager {

	private ChromeOptions co;

	private FirefoxOptions fo;

	private Properties prop;

	

	// pass object of properties file to read config file

	public OptionsManager(Properties prop) {

		this.prop = prop;

	}


	public ChromeOptions getChromeOptions() {

		co = new ChromeOptions();

		

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {

			co.addArguments("--headless");

		} else if (Boolean.parseBoolean(prop.getProperty("incognito"))) {

			co.addArguments("--incognito");	

		}

		return co;

	}

	

	public FirefoxOptions getFirefoxOptions() {

		fo = new FirefoxOptions();

		

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {

			fo.addArguments("--headless");

		} else if (Boolean.parseBoolean(prop.getProperty("incognito"))) {

			fo.addArguments("--incognito");	

		}

		

		return fo;

	}

}
