package com.synisys.idm.test.ui.performance.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * Created by aleksandr.sarichev on 5/4/2018.
 */
public class PageManagementPage extends BasePage<PageManagementPage> {

    @FindBy(id = "title_Page Management")
    private WebElement pageManagementTab;

    @FindBy(id = "id_nav.bar.configurations")
    private WebElement configurationButton;


    private static final By PAGE_TABLE = By.className("test-selenium-page-component");
    private static final By PAGE_TABLE_ITEM = By.className("test-selenium-item");

    public PageManagementPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PageManagementPage openPage() {
        pageManagementTab.click();
        return waitPageToLoad();
    }

    @Override
    public PageManagementPage waitPageToLoad() {
        waitToElementsIsVisible(PAGE_TABLE, PAGE_TABLE_ITEM, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/page-management";
    }

    public PageManagementPage openPageManagementButton() {
        waitActiveTab();
        waitToElementClickable(configurationButton, SECS_20);
        configurationButton.click();
        waitToElementClickable(pageManagementTab, SECS_20);
        return this;
    }

}
