package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Aleksandr Sarichev
 */
public class LoginPage extends BasePage<LoginPage> {

	@FindBy(id = "login")
	private WebElement loginField;
	@FindBy(id = "password")
	private WebElement passwordField;
	@FindBy(id = "submit")
	private WebElement submitButton;

	private static final By ACTIVITY_TABLE = By.className("sis-portfolio-table");
	private static final By ACTIVITY_TABLE_ITEM = By.className("sis-portfolio-table__link");


	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Fill text in login field
	 * 
	 * @param loginName
	 *            the username
	 * @return the LoginPage object reference
	 */
	public LoginPage fillLogin(String loginName) {
		waitToElementClickable(loginField, SECS_60);
		loginField.sendKeys(loginName);
		return this;
	}

	/**
	 * Fill text in password field
	 * 
	 * @param password
	 *            the user password
	 * @return the LoginPage object reference
	 */
	public LoginPage fillPassword(String password) {
		waitToElementClickable(passwordField, SECS_60);
		passwordField.sendKeys(password);
		return this;
	}

	/**
	 * Click on submit button
	 * 
	 * @return the LoginPage object reference
	 */
	public LoginPage clickSubmit() {
		waitToElementClickable(submitButton, SECS_60);
		submitButton.click();
		return this;
	}

	/**
	 * Login to system
	 * 
	 * @param userName
	 *            the username
	 * @param password
	 *            the password
	 */
	public void loginToSystem(String userName, String password) {
		fillLogin(userName);
		fillPassword(password);
		clickSubmit();
		waitToElementsIsVisible(ACTIVITY_TABLE,ACTIVITY_TABLE_ITEM, SECS_20);
	}

	@Override
	public LoginPage openPage() {
		getDriver().get(getBaseUrl().toString() + getPageUrl());
		return waitPageToLoad();
	}

	@Override
	public LoginPage waitPageToLoad() {
		waitToElementClickable(submitButton, SECS_20);
		return this;
	}

	@Override
	public String getPageUrl() {
		return "/login";
	}

}
