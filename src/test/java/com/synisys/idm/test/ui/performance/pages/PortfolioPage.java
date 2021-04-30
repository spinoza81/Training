package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Aleksandr Sarichev
 */
public class PortfolioPage extends BasePage<PortfolioPage> {

	@FindBy(className = "sis-table")
	private WebElement activitiesTable;


	@FindAll(@FindBy(className = "mdl-data-table__cell--non-numeric"))
	private List<WebElement> portfolioItems;



	public PortfolioPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public PortfolioPage openPage() {
		getDriver().get(getBaseUrl().toString() + getPageUrl());
		return waitPageToLoad();
	}

	@Override
	public PortfolioPage waitPageToLoad() {
		waitToElementClickable(activitiesTable, SECS_60);
		waitUntilIgnoringStale((webdriver)->{
    		waitToElementClickable(getTableRowCell(activitiesTable,0,0), SECS_60);
    		return true;
    	});
		return this;
	}

	@Override
	public String getPageUrl() {
		return "/workspace/activities";
	}

	/**
	 * Open the activities from portfolio
	 *
	 * @param activity
	 *            the activity record number
	 * @return the ActivityDetailsPage reference
	 */
	public ActivityDetailsPage openActivityDetailsByRecordNumber(String activity) {
		getPortfolioActivityItem(activity).click();
		return PageFactory.initElements(this.getDriver(),
				ActivityDetailsPage.class).waitPageToLoad();
	}

	private WebElement getPortfolioActivityItem(String activity) {
		for (WebElement item : portfolioItems) {
			if (item.getText().equals(activity)) {
				return item;
			}
		}
		throw new IllegalArgumentException(String.format(
				"Household item with %s does not exist", activity));
	}

	/**
	 * Open first activity's details page from portfolio
	 * @return the ActivityDetailsPage reference
	 */
	public ActivityDetailsPage openFirstActivityDetailsPage() {
		WebElement activityName = getTableRowCell(activitiesTable,0,0);
		activityName.findElement(By.tagName("a")).click();
		return PageFactory.initElements(this.getDriver(),
				ActivityDetailsPage.class).waitPageToLoad();
	}

}
