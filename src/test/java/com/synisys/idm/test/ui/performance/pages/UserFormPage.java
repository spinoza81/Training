package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Aleksandr Sarichev
 */
public class UserFormPage extends BasePage<UserFormPage> {

	@FindBy(id = "user_groups")
	private WebElement userGroup;

	public UserFormPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public UserFormPage openPage() {
		return null;
	}

	@Override
	public UserFormPage waitPageToLoad() {
		waitToElementClickable(userGroup, SECS_20);
		return this;
	}

	@Override
	public String getPageUrl() {
		return null;
	}

}
