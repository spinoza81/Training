package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by katarine.dombaeva on 5/3/2018.
 */
public class GroupsPage extends BasePage<GroupsPage> {
    private static final By GROUPS_TABLE_CLASS_BY = By
            .id("test-selenium-group-table");

    private static final By GROUPS_TABLE_ITEM_CLASS_BY = By
            .className("test-selenium-name");

    @FindBy(id = "id_nav.bar.um")
    private WebElement securityButton;

    @FindBy(id = "id_nav.bar.um.groups")
    private WebElement groupsButton;

    @FindBy(className = "test-selenium-add-group")
    private WebElement addGroupButton;

    public GroupsPage(WebDriver driver) {
        super(driver);
    }

    public GroupFormPage addGroup() {
        addGroupButton.click();
        return PageFactory.initElements(this.getDriver(), GroupFormPage.class).waitPageToLoad();
    }

    @Override
    public GroupsPage openPage() {
        groupsButton.click();
        return waitPageToLoad();
    }

    @Override
    public GroupsPage waitPageToLoad() {
        waitToElementsIsVisible(GROUPS_TABLE_CLASS_BY, GROUPS_TABLE_ITEM_CLASS_BY, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/um/groups";
    }


    /**
     * Opens Security button and wits for Groups button to be clickable
     * @return
     */
    public GroupsPage openSecurityGroupsButton() {
    	waitActiveTab();
    	if(!isDropdownOpened(securityButton)) {
            waitToElementClickable(securityButton, SECS_20);
            securityButton.click();
        }
        waitToElementClickable(groupsButton, SECS_20);
        return this;
    }

    /**
     * Waits until the "Add Group" button is clickable in Roles page
     * @return the OrgFormPage reference
     */
    public GroupsPage waitToAddGroupButton() {
        waitToElementClickable(addGroupButton, SECS_20);
        return this;
    }
}
