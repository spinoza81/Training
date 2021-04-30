package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by katarine.dombaeva on 5/2/2018.
 */
public class RolesPage extends BasePage<RolesPage>{
    private static final By ROLES_TABLE_CLASS_BY = By
            .id("test-selenium-roles-table");

    private static final By ROLES_TABLE_ITEM_CLASS_BY = By
            .className("test-selenium-name");

    @FindBy(id = "id_nav.bar.um")
    private WebElement securityButton;

    @FindBy(id = "id_nav.bar.um.roles")
    private WebElement rolesButton;

    @FindBy(className = "test-selenium-add-role")
    private WebElement addRoleButton;

    public RolesPage(WebDriver driver) {
        super(driver);
    }


    public RolesFormPage addRole() {
        addRoleButton.click();
        return PageFactory.initElements(this.getDriver(), RolesFormPage.class).waitPageToLoad();
    }

    @Override
    public RolesPage openPage() {
        rolesButton.click();
        return waitPageToLoad();
    }

    @Override
    public RolesPage waitPageToLoad() {
        waitToElementsIsVisible(ROLES_TABLE_CLASS_BY, ROLES_TABLE_ITEM_CLASS_BY, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/um/roles";
    }


    /**
     * Opens Security button and wits for Roles button to be clickable
     * @return
     */
    public RolesPage openSecurityRolesButton() {
    	waitActiveTab();
    	if(!isDropdownOpened(securityButton)) {
            waitToElementClickable(securityButton, SECS_20);
            securityButton.click();
        }
        waitToElementClickable(rolesButton, SECS_20);
        return this;
    }

    /**
     * Waits until the "Add Role" button is clickable in Roles page
     * @return the OrgFormPage reference
     */
    public RolesPage waitToAddRoleButton() {
        waitToElementClickable(addRoleButton, SECS_20);
        return this;
    }
}
