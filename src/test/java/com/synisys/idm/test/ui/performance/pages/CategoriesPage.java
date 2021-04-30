package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Aleksandr Sarichev
 */
public class CategoriesPage extends BasePage<CategoriesPage> {

	public static final By CATEGORIES_TABLE_ITEM_BY = By.className("ng-star-inserted");
	private static final By CATEGORIES_TABLE_BY = By.id("categoriesTableId");

	@FindBy(id = "categoriesTableId")
	private WebElement categoriesTable;

	@FindBy(id = "id_nav.bar.categories")
	private WebElement categoriesButton;

	public CategoriesPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public CategoriesPage openPage() {
		categoriesButton.click();
		return waitPageToLoad();
	}

	@Override
	public CategoriesPage waitPageToLoad() {
		waitToElementsIsVisible(CATEGORIES_TABLE_BY, CATEGORIES_TABLE_ITEM_BY, SECS_20);
		return this;

	}

	@Override
	public String getPageUrl() {
		return "/categories";
	}

	/**
	 * Wait to categories button is clickable in shell
	 * 
	 * @return the CategoriesPage reference
	 */
	public CategoriesPage waitToCategoriesButton() {
		waitActiveTab();
		waitToElementClickable(categoriesButton, SECS_20);
		return this;
	}

}
