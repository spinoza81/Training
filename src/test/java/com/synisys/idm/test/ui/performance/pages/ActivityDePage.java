package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Aleksandr Sarichev
 */
public class ActivityDePage extends BasePage<ActivityDePage> {

	@FindBy(id = "create_new_activity")
	private WebElement createNewActivity;

	@FindBy(id = "selenium-test-activitystatusid0")
	private WebElement activeRadioButton;

	public ActivityDePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public ActivityDePage openPage() {
		return null;
	}

	@Override
	public ActivityDePage waitPageToLoad() {
		waitToElementClickable(activeRadioButton, SECS_20);
		return this;
	}

	@Override
	public String getPageUrl() {
		return null;
	}

	/**
	 * Create new Household form and waiting that the Household form is loaded
	 *
	 * @return the ActivityDePage reference
	 */
	public ActivityDePage createNewActivity() {
		waitToElementClickable(createNewActivity,SECS_5);
		createNewActivity.click();
		return waitPageToLoad();
	}

	/**
	 * Open create new popup and waiting that the create new household link is
	 * clickable
	 *
	 * @return the ActivityDePage reference
	 */
	public ActivityDePage openCreateNewButton() {
		createNew.click();
		waitToElementClickable(createNewActivity, SECS_20);
		return this;
	}
}
