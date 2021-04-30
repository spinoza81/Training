package com.synisys.idm.test.ui.performance.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * Created by aleksandr.sarichev on 5/4/2018.
 */
public class FormManagementPage extends BasePage<FormManagementPage> {

    @FindBy(id = "title_Form Management")
    private WebElement formManagementTab;

    @FindBy(id = "id_nav.bar.configurations")
    private WebElement configurationButton;


    private static final By FORMS_TABLE_ID_BY = By.id("formsTableId");
    private static final By FORMS_TABLE_ITEM = By.className("locked-pages-test");

    public FormManagementPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FormManagementPage openPage() {
        formManagementTab.click();
        return waitPageToLoad();
    }

    @Override
    public FormManagementPage waitPageToLoad() {
        waitToElementsIsVisible(FORMS_TABLE_ID_BY, FORMS_TABLE_ITEM, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/configurations/forms";
    }

    public FormManagementPage openFormManagementButton() {
        waitActiveTab();
        waitToElementClickable(configurationButton, SECS_20);
        configurationButton.click();
        waitToElementClickable(formManagementTab, SECS_20);
        return this;
    }

}
