package com.synisys.idm.test.ui.performance.helper;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to create a driver instance
 * 
 * @author Aleksandr Sarichev
 */
public class DriverProvider {
	private static final String IDM_ACCEPTANCE_DRIVER = "idm.acceptance.driver";
	private static final String IDM_ACCEPTANCE_GRID_URL = "idm.acceptance.grid.url";
	private static final String IDM_ACCEPTANCE_BROWSER = "idm.acceptance.browser";
	private static final String IDM_ACCEPTANCE_VERSION = "idm.acceptance.version";
	private static final String IDM_ACCEPTANCE_PLATFORM = "idm.acceptance.platform";

	private WebDriver driver;

	public DriverProvider(Proxy proxy) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, MalformedURLException {

		String driver = System.getProperty(IDM_ACCEPTANCE_DRIVER);
		// Driver is missing: will instantiate Remote Driver
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		if (driver == null) {
			capabilities.setBrowserName(System
					.getProperty(IDM_ACCEPTANCE_BROWSER));
			capabilities.setVersion(System.getProperty(IDM_ACCEPTANCE_VERSION));
			capabilities.setPlatform(Platform.valueOf(System
					.getProperty(IDM_ACCEPTANCE_PLATFORM)));
			capabilities.setCapability("enableVNC", true);
			this.driver = new RemoteWebDriver(new URL(
					System.getProperty(IDM_ACCEPTANCE_GRID_URL)), capabilities);
			((RemoteWebDriver) this.driver)
					.setFileDetector(new LocalFileDetector());

		} else {

			this.driver = Class.forName(driver).asSubclass(WebDriver.class)
					.getConstructor(Capabilities.class)
					.newInstance(capabilities);
		}

		this.driver.manage().window().setSize(new Dimension(1920, 1080));
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}