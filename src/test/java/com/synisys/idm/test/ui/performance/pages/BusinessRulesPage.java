package com.synisys.idm.test.ui.performance.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by aleksandr.sarichev on 5/4/2018.
 */
public class BusinessRulesPage extends BasePage<BusinessRulesPage> {

    @FindBy(id = "title_Business Rules")
    private WebElement businessRulesTab;

    @FindBy(id = "id_nav.bar.businessProcess")
    private WebElement businessProcessButton;


    private static final By RULES_TABLE = By.className("eh-normatives-table--init");
    private static final By RULES_TABLE_ITEM = By.className("eh-normatives-table__link");

    public BusinessRulesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BusinessRulesPage openPage() {
        businessRulesTab.click();
        return waitPageToLoad();
    }

    @Override
    public BusinessRulesPage waitPageToLoad() {
        waitToElementsIsVisible(RULES_TABLE, RULES_TABLE_ITEM, SECS_20);
        return this;
    }

    @Override
    public String getPageUrl() {
        return "/business-process/normatives";
    }

    public BusinessRulesPage openBusinessRulesButton() {
        waitActiveTab();
        waitToElementClickable(businessProcessButton, SECS_20);
        businessProcessButton.click();
        waitToElementClickable(businessRulesTab, SECS_20);
        return this;
    }

}
