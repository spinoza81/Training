package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by katarine.dombaeva on 5/2/2018.
 */
public class RolesFormPage extends BasePage<RolesFormPage> {
    @FindBy(id = "roleOrganization")
    private WebElement roleOrganization;

    public RolesFormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public RolesFormPage openPage() {
        return null;
    }

    @Override
    public RolesFormPage waitPageToLoad() {
        waitToElementClickable(roleOrganization, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return null;
    }
}
