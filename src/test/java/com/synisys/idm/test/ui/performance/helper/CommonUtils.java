package com.synisys.idm.test.ui.performance.helper;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
	public static final int MAIN_WINDOW_INDEX = 0; 
	
	/**
	 * Switch tobrowser tab window
	 * @param driver
	 * @param tabIndex
	 */
	public static void switchToBrowserTab(WebDriver driver,int tabIndex){
		List<String> handles = new ArrayList<>(driver.getWindowHandles());
		String haldle = handles.get(tabIndex);
		driver.switchTo().window(haldle);
	}
	
	public static void switchToMainTab(WebDriver driver){
		switchToBrowserTab(driver, MAIN_WINDOW_INDEX);
	}
	
	public static void closeCurrentTab(WebDriver driver){
		driver.close();
	}
	
}