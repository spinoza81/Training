package com.synisys.idm.test.ui.performance.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by katarine.dombaeva on 5/4/2018.
 */
public class AnalyticsPage extends BasePage<AnalyticsPage> {
    @FindBy(id = "report-list")
    private WebElement reportList;

    @FindBy(id = "id_nav.bar.analytics")
    private WebElement analyticsButton;

    @FindBy(tagName = "row")
	private List<WebElement> viewItems;

    private static final By REPORT_LIST_TABLE_BY = By.tagName("table");

    public AnalyticsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AnalyticsPage openPage() {
        analyticsButton.click();
        return waitPageToLoad();
    }

    @Override
    public AnalyticsPage waitPageToLoad() {
    	WebElement reportListTable = reportList.findElement(REPORT_LIST_TABLE_BY);
    	waitToElementClickable(reportListTable, SECS_20);
    	waitUntilIgnoringStale((webdriver)->{
    		waitToElementClickable(getTableRowCell(reportListTable,0,0), SECS_20);
    		return true;
    	});
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/page/101";
    }

}
