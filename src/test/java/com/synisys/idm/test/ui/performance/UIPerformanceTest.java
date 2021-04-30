package com.synisys.idm.test.ui.performance;

import com.synisys.idm.test.ui.performance.pages.*;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.BlacklistEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * @author Aleksandr Sarichev
 */
public class UIPerformanceTest
		extends BaseUIPerformanceTest {
	private static Har harArchive;
	private LoginPage loginPage;
	private PortfolioPage portfolioPage;
	private ActivityDePage activityDe;
	private UsersPage usersPage;
	private CategoriesPage categoriesPage;
	private OrgChartPage orgChartPage;
	private RolesPage rolesPage;
	private GroupsPage groupsPage;
	private AnalyticsPage analyticsPage;
	private FormManagementPage formManagementPage;
	private PageManagementPage pageManagementPage;
	private SurveysPage surveysPage;
	private BusinessRulesPage businessRulesPage;


	@Before
	public void before() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		portfolioPage = PageFactory.initElements(driver, PortfolioPage.class);
		activityDe = PageFactory.initElements(driver, ActivityDePage.class);
		usersPage = PageFactory.initElements(driver, UsersPage.class);
		orgChartPage = PageFactory.initElements(driver, OrgChartPage.class);
		rolesPage = PageFactory.initElements(driver, RolesPage.class);
		groupsPage = PageFactory.initElements(driver, GroupsPage.class);
		analyticsPage = PageFactory.initElements(driver, AnalyticsPage.class);
		formManagementPage = PageFactory.initElements(driver,FormManagementPage.class);
		categoriesPage = PageFactory.initElements(driver, CategoriesPage.class);
		pageManagementPage = PageFactory.initElements(driver,PageManagementPage.class);
		surveysPage = PageFactory.initElements(driver,SurveysPage.class);
		businessRulesPage = PageFactory.initElements(driver,BusinessRulesPage.class);

		proxyServer.setBlacklist(Collections.singletonList(new BlacklistEntry(
				"https?://k8s-(staging|testing|dev)\\.synisys\\.com/notificationservice/v1/indicata/notifications/count\\?userId=\\d+&status=UNREAD",
				200)));
	}

	@Test
	public void loginPage(){
		proxyServer.createHarArchive("Login Page");
		loginPage.openPage();
		proxyServer.endHarPage();
		//Open Login page in a second time to collect requests with cache
		proxyServer.createHarPage("Login Page With Caches");
		loginPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void orgChartPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		orgChartPage.openSecurityOrgChartButton();
		proxyServer.createHarArchive("Org Chart Page");
		orgChartPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to OrgChartPage, to collect requests with cache
		usersPage.openPage();
		proxyServer.createHarPage("Org Chart Page With Caches");
		orgChartPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void newOrganization(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		orgChartPage.openSecurityOrgChartButton();
		orgChartPage.openPage();
		proxyServer.createHarArchive("New Organization");
		orgChartPage.addOrganization();
		proxyServer.endHarPage();
		//Navigate to another page, to return to New Organization Page, to collect requests with cache
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		orgChartPage.openSecurityOrgChartButton();
		orgChartPage.openPage();
		proxyServer.createHarPage("New Organization With Caches");
		orgChartPage.addOrganization();
		proxyServer.endHarPage();
	}

	@Test
	public void usersPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		usersPage.openSecurityUsersButton();
		proxyServer.createHarArchive("Users Page");
		usersPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		orgChartPage.openPage();
		proxyServer.createHarPage("Users Page With Caches");
		usersPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void newUser(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		usersPage.waitToAddUserButton();
		proxyServer.createHarArchive("New User");
		usersPage.addUser();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		orgChartPage.openPage();
		usersPage.openPage();
		usersPage.waitToAddUserButton();
		proxyServer.createHarPage("New User With Caches");
		usersPage.addUser();
		proxyServer.endHarPage();
	}

	@Test
	public void rolePage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		rolesPage.openSecurityRolesButton();
		proxyServer.createHarArchive("Roles Page");
		rolesPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openPage();
		proxyServer.createHarPage("Roles Page With Caches");
		rolesPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void newRole(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		rolesPage.openSecurityRolesButton();
		rolesPage.openPage();
		rolesPage.waitToAddRoleButton();
		proxyServer.createHarArchive("New Role");
		rolesPage.addRole();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openPage();
		rolesPage.openPage();
		rolesPage.waitToAddRoleButton();
		proxyServer.createHarPage("New Role With Caches");
		rolesPage.addRole();
		proxyServer.endHarPage();
	}


	@Test
	public void groupPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		groupsPage.openSecurityGroupsButton();
		groupsPage.openPage();
		groupsPage.waitToAddGroupButton();
		proxyServer.createHarArchive("New Group");
		groupsPage.addGroup();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openPage();
		groupsPage.openPage();
		groupsPage.waitToAddGroupButton();
		proxyServer.createHarPage("New Group With Caches");
		groupsPage.addGroup();
		proxyServer.endHarPage();
	}

	@Test
	public void newGroup(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		groupsPage.openSecurityGroupsButton();
		proxyServer.createHarArchive("Groups Page");
		groupsPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openPage();
		groupsPage.openSecurityGroupsButton();
		proxyServer.createHarPage("Groups Page With Caches");
		groupsPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void categoriesPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		categoriesPage.waitToCategoriesButton();
		proxyServer.createHarArchive("Categories Page");
		categoriesPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		proxyServer.createHarPage("Categories Page With Caches");
		categoriesPage.openPage();
		proxyServer.endHarPage();
	}


	@Test
	public void formPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		formManagementPage.openFormManagementButton();
		proxyServer.createHarArchive("Forms Management Page");
		formManagementPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		formManagementPage.openFormManagementButton();
		proxyServer.createHarPage("Forms Management Page With Caches");
		formManagementPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void pageManagementPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		pageManagementPage.openPageManagementButton();
		proxyServer.createHarArchive("Page Management Page");
		pageManagementPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		pageManagementPage.openPageManagementButton();
		proxyServer.createHarPage("Page Management Page With Caches");
		pageManagementPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void surveysPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		surveysPage.openSurveysButton();
		proxyServer.createHarArchive("Surveys Page");
		surveysPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		surveysPage.openSurveysButton();
		proxyServer.createHarPage("Surveys Page With Caches");
		surveysPage.openPage();
		proxyServer.endHarPage();
	}

	@Test
	public void businessRulesPage(){
		loginPage.openPage();
		loginPage.loginToSystem("admin", "123456");
		businessRulesPage.openBusinessRulesButton();
		proxyServer.createHarArchive("Business Rules Page");
		businessRulesPage.openPage();
		proxyServer.endHarPage();
		//Navigate to another page, to return to Users Page, to collect requests with cache
		usersPage.openSecurityUsersButton();
		usersPage.openPage();
		businessRulesPage.openBusinessRulesButton();
		proxyServer.createHarPage("Business Rules Page With Caches");
		businessRulesPage.openPage();
		proxyServer.endHarPage();
	}

	@After
	public void after() {
		super.afterBase();
		harArchive = proxyServer.getValidHarArchive();
		Path path = Paths.get("target/ui_perfomance_test" + Math.random() + ".har");
		try {
			harArchive.writeTo(path.toFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
