package com.synisys.idm.test.ui.performance.pages;

import com.synisys.idm.test.ui.performance.helper.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Aleksandr Sarichev
 */
public class ActivityDetailsPage extends BasePage<ActivityDetailsPage> {

	@FindBy(className = "sis-select__search")
	private WebElement searchField;

	public ActivityDetailsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public ActivityDetailsPage openPage() {
		return null;
	}

	@Override
	public ActivityDetailsPage waitPageToLoad() {
		waitToElementClickable(searchField, SECS_5);
		return this;
	}

	@Override
	public String getPageUrl() {
		return null;
	}

}
