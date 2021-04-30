package com.synisys.idm.test.ui.performance;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import com.synisys.idm.test.ui.performance.helper.DriverProvider;
import com.synisys.idm.test.ui.performance.helper.ProxyServer;

/**
 * This is a base class of all test classes.It assures that the driver and proxy
 * server is started when the tests are started and is closed when the tests are
 * finished.
 * 
 * @author Aleksandr Sarichev
 */
public class BaseUIPerformanceTest {
	protected WebDriver driver;
	protected Proxy seleniumProxy;
	protected ProxyServer proxyServer;

	@Before
	public void beforeBase() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, MalformedURLException {
		proxyServer = new ProxyServer(
				System.getProperty("network.speed.limit"),
				Integer.parseInt(System.getProperty("proxy.start.port")),
				System.getProperty("browsermob.proxy.host"));
		proxyServer.start();
		seleniumProxy = proxyServer.getSeleniumProxy();
		driver = new DriverProvider(seleniumProxy).getDriver();
	}
	
	public void afterBase() {
		proxyServer.stop();
		driver.quit();
	}

}
