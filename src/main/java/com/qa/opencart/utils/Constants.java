package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final int DEFAULT_TIMEOUT = 5;

//==========LOGIN PAGE =======

	// Login page title
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	// Login block texts
	public static final String LOGIN_BLOCK_HEADING = "Returning Customer";
	public static final String LOGIN_BLOCK_TITLE = "I am a returning customer";
	public static final String LOGIN_BLOCK_EMAIL = "E-Mail Address";
	public static final String LOGIN_BLOCK_PASSWORD = "Password";
	public static final String LOGIN_BLOCK_EMAIL_ATTRIBUTE = "E-Mail Address";
	public static final String LOGIN_BLOCK_PASSWORD_ATTRIBUTE = "Password";
	public static final String PAGE_HEADER = "Your Store";
	// Login error messages
	public static final String LOGIN_ERROR_MSG = "Warning: No match for E-Mail Address and/or Password.";
	public static final String LOGIN_CREDENTIALS_SHEET_NAME = "login";

//==========ACCOUNT PAGE =======
	// Account page title
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final List<String> EXPECTED_ACC_SEC_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

//	public static final ArrayList<String> getExpectedAccountSectionList() {
//		ArrayList<String> accountList = new ArrayList<String>();
//		accountList.add("My Account");
//		accountList.add("My Orders");
//		accountList.add("My Affiliate Account");
//		accountList.add("Newsletter");
//
//		return accountList;
//	}

	// test data
	public static final String PRODUCT_SHEET_NAME = "search";

//=======PRODUCT INFO PAGE =======
	public static final String PRODUCT_NAME = "name";
	public static final String PRODUCT_BRAND = "Brand";
	public static final String PRODUCT_CODE = "Product Code";
	public static final String PRODUCT_REWARD_POINTS = "Reward Points";
	public static final String PRODUCT_AVAILABILITY = "Availability";
	public static final String PRODUCT_PRICE = "Price";
	public static final String PRODUCT_TAX_PRICE = "ExTaxPrice";

	public static final String ADD_TO_CART_SUCCESS_MSG1 = "Success: You have added";
	public static final String ADD_TO_CART_SUCCESS_MSG2 = "to your shopping cart!";

	public static final String PRODUCT_SUBTOTAL = "Sub-Total";
	public static final String PRODUCT_TOTAL = "Total";

	// test data
	public static final String PRODUCT_INFO_MBPRO_SHEET_NAME = "product - MacBook Pro";
	public static final String PRODUCT_INFO_IMAC_SHEET_NAME = "product - iMac";
	public static final String PRODUCT_INFO_HP_SHEET_NAME = "product - HP";

//=======REGISTER PAGE =======
	public static final String REGISTER_PAGE_SHEET_NAME = "register";
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	

// =======SHOPPING CART PAGE =======
	public static final String CART_PAGE_NOT_IN_STOCK_MSG = "Products marked with *** are not available in the desired quantity or not in stock!";
	public static final String CART_PAGE_NUMBER_OF_PRODUCTS = "2";
	public static final String CART_PAGE_NUMBER_OF_PRODUCT_IMG = "2";
	public static final String[] EXPECTED_CART_PRODUCT_MACBOOKPRO = {
			"MacBook Pro *** Reward Points: 12000 Product 18 $2,000.00 $30,000.00" };
	public static final List<String> EXPECTED_CART_PRODUCT_HP = Arrays.asList("HP LP3065", "Delivery Date: 2011-04-22",
			"Reward Points: 11100", "Product 21", " ", "$100.00", "$3,700.00");

}
