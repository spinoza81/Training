package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by katarine.dombaeva on 5/3/2018.
 */
public class GroupFormPage extends BasePage<GroupFormPage>{
    @FindBy(id = "groupOrganization")
    private WebElement groupOrganization;

    public GroupFormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GroupFormPage openPage() {
        return null;
    }

    @Override
    public GroupFormPage waitPageToLoad() {
        waitToElementClickable(groupOrganization, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return null;
    }
}
