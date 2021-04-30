package com.synisys.idm.test.ui.performance.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by aleksandr.sarichev on 5/4/2018.
 */
public class SurveysPage extends BasePage<SurveysPage> {

    @FindBy(id = "title_Surveys")
    private WebElement surveyTab;

    @FindBy(id = "id_nav.bar.surveyManagement")
    private WebElement surveyManagementButton;


    private static final By SURVEYS_TABLE = By.className("srv-dashboard-table");
    private static final By SURVEYS_TABLE_ITEM = By.className("srv-dashboard-table__link");

    public SurveysPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SurveysPage openPage() {
        surveyTab.click();
        return waitPageToLoad();
    }

    @Override
    public SurveysPage waitPageToLoad() {
        waitToElementsIsVisible(SURVEYS_TABLE, SURVEYS_TABLE_ITEM, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/survey/management";
    }

    public SurveysPage openSurveysButton() {
        waitActiveTab();
        waitToElementClickable(surveyManagementButton, SECS_20);
        surveyManagementButton.click();
        waitToElementClickable(surveyTab, SECS_20);
        return this;
    }

}
