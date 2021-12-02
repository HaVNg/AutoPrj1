package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Utils;

public class ShoppingCartPage extends BasePage {
	public WebDriver driver;
	public Utils util;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		util= new Utils(this.driver);	
	}

}
