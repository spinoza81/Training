package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author Aleksandr Sarichev
 */
public abstract class BasePage<T extends BasePage<T>> {
	
	private static final String OPENED_DROPDOWN_CLASS = "sis-sidebar__dropdown-menu--expanded";
	private static final By ACTIVE_TAB = By.className("selenium-test__nav-item--active");

	@FindBy(id = "createnew")
	protected WebElement createNew;

	protected static final long SECS_5 = 5;
	protected static final long SECS_20 = 20;
	protected static final long SECS_60 = 60;
	protected WebDriver driver;
	private URL baseUrl;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		try {
			this.baseUrl = new URL(System.getProperty("idm.acceptance.url"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get webdriver
	 *
	 * @return the webdriver instance
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Wait until the element is clickable
	 *
	 * @param element          the webelement
	 * @param timeOutInSeconds The timeout in seconds when an expectation is called
	 */
	public void waitToElementClickable(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait until the child elements as a part of parent element to be visible
	 *
	 * @param locator          parent element locator
	 * @param sub_locator      child element locator
	 * @param timeOutInSeconds The timeout in seconds when an expectation is called
	 */
	public void waitToElementsIsVisible(By locator, By sub_locator,
										long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(
				locator, sub_locator));
	}


	/**
	 * Returns true if the dropdown is opened and false otherwise
	 *
	 * @param button dropdown button
	 * @return true or false
	 */
	public boolean isDropdownOpened(WebElement button) {
		return button.getAttribute("class").contains(OPENED_DROPDOWN_CLASS);
	}


	/**
	 * Get the tests base url
	 *
	 * @return the tests base url
	 */
	public URL getBaseUrl() {
		return this.baseUrl;
	}

	public abstract String getPageUrl();

	public abstract T openPage();

	public abstract T waitPageToLoad();

	/**
	 * This method get  table's column by provided webElement of table, rowNumber and columnNumber
	 *
	 * @param webElement   webElement of table
	 * @param rowNumber    row number of table
	 * @param columnNumber column number of table
	 * @return WebElement of column by provided row number and column number
	 */
	public WebElement getTableRowCell(WebElement webElement, int rowNumber, int columnNumber) {
		WebElement table = webElement.findElement(By.tagName("tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		List<WebElement> cells = rows.get(rowNumber).findElements(By.tagName("td"));
		return cells.get(columnNumber);
	}

	/**
	 * Waits for predicate to be true or time elapsed. Can be useful in cases when underneath action can have stale
	 * elements due to some implementation issues or specifics.
	 *
	 * @param predicate        a predicate to check condition upon
	 * @param timeOutInSeconds time in seconds to keep retrying
	 */
	public void waitUntilIgnoringStale(Predicate<WebDriver> predicate, long timeOutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), timeOutInSeconds);

		webDriverWait.ignoring(StaleElementReferenceException.class).until(predicate);
	}

	public void waitUntilIgnoringStale(Predicate<WebDriver> predicate) {
		waitUntilIgnoringStale(predicate, SECS_5);
	}


	public void customWait(Predicate<WebDriver> predicate, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(predicate);
	}

	public void customWait(Predicate<WebDriver> predicate) {
		customWait(predicate, SECS_20);
	}
	
	public void waitToElement(By elementBy, long period) {
		(new WebDriverWait(getDriver(), period)).until(ExpectedConditions.presenceOfElementLocated(elementBy));
	}
	
	public void waitActiveTab() {
		waitToElement(BasePage.ACTIVE_TAB, SECS_20);
	}
	
}
