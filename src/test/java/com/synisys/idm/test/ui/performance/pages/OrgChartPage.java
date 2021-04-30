package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by katarine.dombaeva on 5/2/2018.
 */
public class OrgChartPage extends BasePage<OrgChartPage>{

    private static final By CONTENT = By.className("um-organization-list-card__content");

    private static final By FIRST_DATA_ROW = By.className("um-organization-list-tree__level-2");

    @FindBy(id = "id_nav.bar.um")
    private WebElement securityButton;

    @FindBy(id = "id_nav.bar.um.organizations")
    private WebElement orgChartButton;

    @FindBy(className =  "test-selenium-add-organization")
    private WebElement addOrganizationButton;

    public OrgChartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrgChartPage openPage() {
        orgChartButton.click();
        return waitPageToLoad();
    }

    @Override
    public OrgChartPage waitPageToLoad() {
        waitToElementsIsVisible(CONTENT, FIRST_DATA_ROW, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/um/organizations";
    }

    /**
     * Opens Security button and wits for OrgChart button to be clickable
     * @return
     */
    public OrgChartPage openSecurityOrgChartButton() {
    	waitActiveTab();
    	if(!isDropdownOpened(securityButton)) {
            waitToElementClickable(securityButton, SECS_20);
            securityButton.click();
        }
        waitToElementClickable(orgChartButton, SECS_20);
        return this;
    }

    /**
     * Waits until the AddOrganization button is clickable in OrgChart page
     * @return the OrgFormPage reference
     */
    public OrgChartPage waitToAddOrganizationButton() {
        waitToElementClickable(addOrganizationButton, SECS_20);
        return this;
    }

    /**
     * Click on AddOrganization button in security/orgChart page and wait OrgFormPage to load
     * @return the OrgFormPage reference
     */
    public OrgFormPage addOrganization() {
        addOrganizationButton.click();
        return PageFactory.initElements(this.getDriver(), OrgFormPage.class).waitPageToLoad();
    }
}
