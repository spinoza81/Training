package com.synisys.idm.test.ui.performance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Aleksandr Sarichev
 */
public class UsersPage extends BasePage<UsersPage> {
	private static final By USERS_TABLE_CLASS_BY = By
			.id("test-selenium-user-table");

	private static final By USERS_TABLE_ITEM_CLASS_BY = By
			.className("test-selenium-name");

	@FindBy(id = "id_nav.bar.um")
	private WebElement securityButton;

	@FindBy(id = "id_nav.bar.um.users")
	private WebElement usersButton;

	@FindBy(className = "test-selenium-add-user")
	private WebElement addUserButton;

	public UsersPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click on AddUser button in security/users page and waiting that the
	 * UserFormPage is loaded
	 * 
	 * @return the UserFormPage reference
	 */
	public UserFormPage addUser() {
		addUserButton.click();
		return PageFactory.initElements(this.getDriver(), UserFormPage.class)
				.waitPageToLoad();
	}

	@Override
	public UsersPage openPage() {
		usersButton.click();
		return waitPageToLoad();
	}

	@Override
	public UsersPage waitPageToLoad() {
		waitToElementsIsVisible(USERS_TABLE_CLASS_BY,
				USERS_TABLE_ITEM_CLASS_BY, SECS_20);
		return this;
	}

	@Override
	public String getPageUrl() {
		return "/um/users";
	}

	/**
	 * Open users section in shell security
	 * 
	 * @return the UsersPage reference
	 */
	public UsersPage openSecurityUsersButton() {
		waitActiveTab();
		if(!isDropdownOpened(securityButton)) {
			waitToElementClickable(securityButton, SECS_20);
			securityButton.click();
		}
		waitToElementClickable(usersButton, SECS_20);
		return this;
	}

	/**
	 * Wait to the Add User button is clickable in Users Page
	 * 
	 * @return the UsersPage reference
	 */
	public UsersPage waitToAddUserButton() {
		waitToElementClickable(addUserButton, SECS_20);
		return this;
	}

}
