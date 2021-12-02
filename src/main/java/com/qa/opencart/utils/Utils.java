package com.qa.opencart.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class Utils {
	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger log = Logger.getLogger(Utils.class);

	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Constants.DEFAULT_TIMEOUT);
	}

	/**
	 * This method is used to get a single element
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {

		WebElement element = null;
		try {
			element = driver.findElement(locator);
			log.info("Geting element: " + locator);
		} catch (Exception e) {
			System.out.println("Some exceptions occured while getting the element");
			System.out.println(e.getMessage());
			log.info("Element is not found!");
		}
		return element;
	}

	/**
	 * This method is used to get a list of elements
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getElements(By locator) {

		List<WebElement> elements = driver.findElements(locator);
		log.info("Getting list of elements!");

		return elements;
	}

	/**
	 * This method will wait for title to present and get the page title
	 * 
	 * @param title
	 * @param timeout
	 */
	public String waitForTitleToPresent(String title) {
		this.wait.until(ExpectedConditions.titleContains(title));
		String elementTitle = driver.getTitle();
		log.info("Getting the title: " + elementTitle);
		return elementTitle;
	}

	public String waitForElementToPresentThenGetText(By locator) {
		WebElement element = this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		log.info("Waiting until element: " + locator + " is present");
		return element.getText();
	}

	/**
	 * This method is used to get text of the element
	 * 
	 * @param locator
	 * @return Strings
	 */
	public String doGetText(By locator) {
		String elementText = getElement(locator).getText();
		log.info("Getting text: " + elementText + " from element: " + locator);
		return elementText;
	}

	/**
	 * This method is used to pass value in a web element
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
		log.info("Entering value: " + value);
	}

	/**
	 * This method is used to click on element
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
		log.info("Clicking on: " + locator);
	}

	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
}