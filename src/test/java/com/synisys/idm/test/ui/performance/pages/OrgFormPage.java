package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by katarine.dombaeva on 5/2/2018.
 */
public class OrgFormPage extends BasePage<OrgFormPage> {
    @FindBy(id = "parentOrganization")
    private WebElement parentOrganization;

    public OrgFormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrgFormPage openPage() {
        return null;
    }

    @Override
    public OrgFormPage  waitPageToLoad() {
        waitToElementClickable(parentOrganization, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return null;
    }
}
