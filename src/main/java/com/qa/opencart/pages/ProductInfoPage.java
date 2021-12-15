package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.bases.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Utils;

import io.qameta.allure.Step;

public class ProductInfoPage extends BasePage {
	private WebDriver driver;
	private Utils util;

	private By productHeader = By.cssSelector("div#content h1");
	// private By productImages = By.cssSelector("ul.thumbnails img");
	private By productImages = By.cssSelector("li.image-additional a.thumbnail");

	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");	
	private By addToCartMsg = By.xpath("//div[text()='Success: You have added ']");
	private By cartInfoButton = By.cssSelector("span#cart-total");
	private By productLabelInCartButton = By.cssSelector("table.table-bordered strong");
	private By productValueInCartButton = By.cssSelector("table.table-bordered td.text-right +td");
	private By productInfoInCartButton = By.xpath("(//table[@class='table table-bordered'])[1]//tr");
	private By viewCartButton = By.cssSelector("p.text-right i.fa-shopping-cart");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new Utils(this.driver);
	}
	
	@Step("Get Product Info page title after searching a product")
	public String getProductInfoPageTitle(String productName) {
		return util.waitForTitleToPresent(productName);	
	}
	
	@Step("Get searched product images count")
	public int getProductImagesCount() {
		return util.getElements(productImages).size();
	}

	@Step("Get the product header text")
	public String getProductHeaderText() {
		return util.doGetText(productHeader);
	}

	@Step("Get metadata of the searched product")
	public Map<String, String> getProductInfo() {
		Map<String, String> productInfoMap = new HashMap<>();
		productInfoMap.put(Constants.PRODUCT_NAME, getProductHeaderText().trim());

		List<WebElement> productMetadataList = util.getElements(productMetaData);
		log.info("Total product metadata list: " + productMetadataList.size());

		for (WebElement e : productMetadataList) {
			String[] productMetadata = e.getText().split(":");
			String metaKey = productMetadata[0].trim();
			String metaValue = productMetadata[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}

		List<WebElement> priceList = util.getElements(productPriceData);
		log.info("Total product price list: " + priceList.size());

		String price = priceList.get(0).getText().substring(1).trim();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].substring(2).trim();
		productInfoMap.put(Constants.PRODUCT_PRICE, price);
		productInfoMap.put(Constants.PRODUCT_TAX_PRICE, exTaxPrice);

		return productInfoMap;
	}

	@Step("Add quantity of the product to cart")
	public void selectProductQuantity(String qty) {
		util.doSendKeys(quantity, qty);
	}
	

	@Step("Get text message returned when adding a product to cart")
	public String getAddToCartSuccessText(String qty) {	
		selectProductQuantity(qty);
		util.doClick(addToCartBtn);
		return util.waitForElementToPresentThenGetText(addToCartMsg);
	}
	

	@Step("Get product metadata in cart info button")
	public Map<String, String> getProductInfoInCartButton() {
		
		util.doClick(cartInfoButton);
		Map<String, String> productInfoCartButtonMap = new HashMap<>();
		List<WebElement> productList = util.getElements(productInfoInCartButton);

		productList.stream().forEach(info -> productInfoCartButtonMap.put(info.getText().split(" ")[0].trim(), info.getText().split(" ")[1].trim()));
		return productInfoCartButtonMap;
	}
	

	@Step("Navigating to View Cart page")
	public ShoppingCartPage navigateToViewCartPage() {
		util.doClick(cartInfoButton);
		util.doClick(viewCartButton);
		return new ShoppingCartPage(driver);
	}
}
	
	
	
	

