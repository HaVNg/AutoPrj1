package com.qa.opencart.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Popup {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public Popup(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Constants.DEFAULT_TIMEOUT);
	}
	
	public boolean isDisplayed(By titleLocator) {
		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
		return title.isDisplayed();	
	}
	
	public void doCancel(By cancelLocator, By titleLocator) {
		if (isDisplayed(titleLocator)) {
			this.wait.until(ExpectedConditions.elementToBeClickable(cancelLocator)).click();
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
		}
	}
	
}
