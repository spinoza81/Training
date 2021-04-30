package com.synisys.idm.test.ui.performance.helper;

import net.lightbody.bmp.proxy.BlacklistEntry;

import org.openqa.selenium.Proxy;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;

import java.util.Collection;
import java.util.List;

/**
 * This class is used for create a proxy server instance.
 * 
 * @author Aleksandr Sarichev
 */
public class ProxyServer {

	private BrowserMobProxy browserMobproxy;
	private Proxy seleniumProxy;
	private Integer browserMobpProxyPort;
	private String browserMobProxyHost;
	private Integer browserMobProxyBandwidthLimit;

	public ProxyServer(String bandwidthLimit, Integer browserMobpProxyPort,
			String browserMobProxyHost) {
		this.browserMobproxy = new BrowserMobProxyServer();
		this.browserMobpProxyPort = browserMobpProxyPort;
		this.browserMobProxyHost = browserMobProxyHost;
		if (bandwidthLimit != null && !bandwidthLimit.isEmpty()) {
			this.browserMobProxyBandwidthLimit = Integer
					.parseInt(bandwidthLimit);
			this.browserMobproxy
					.setReadBandwidthLimit(this.browserMobProxyBandwidthLimit);
		}
		this.browserMobproxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,
				CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_CONTENT,
				CaptureType.RESPONSE_HEADERS);
		this.seleniumProxy = new Proxy();
		this.seleniumProxy.setHttpProxy(this.browserMobProxyHost);
		this.seleniumProxy.setSslProxy(this.browserMobProxyHost);
	}

	/**
	 * Stop the browser mob proxy server
	 */
	public void stop() {
		this.browserMobproxy.stop();
	}

	/**
	 * Start the browser mob proxy server
	 */
	public void start() {
		start(this.browserMobpProxyPort);
	}

	/**
	 * Start the proxy server on specified port
	 * 
	 * @param port
	 *            port to listen on
	 */
	public void start(int port) {
		this.browserMobproxy.start(port);
	}

	/**
	 * Get selenium proxy
	 * 
	 * @return the selenium proxy server instance
	 */
	public Proxy getSeleniumProxy() {
		return this.seleniumProxy;

	}

	/**
	 * Get the browser mob proxy server instance
	 * 
	 * @return
	 */
	public BrowserMobProxy getBrowserMobProxyServer() {
		return this.browserMobproxy;
	}

	/**
	 * Starts a new HAR file with the specified archiveName as the page name and
	 * page title.
	 *
	 * @param archiveName
	 *            initial page name of the new HAR file
	 * @return existing HAR file, or null if none exists or HAR capture was
	 *         disabled
	 */
	public Har createHarArchive(String archiveName) {
		return this.browserMobproxy.newHar(archiveName);
	}

	/**
	 * Starts a new HAR file with the default page name (see {@link #newPage()}.
	 * Enables HAR capture if it was not previously enabled.
	 *
	 * @return existing HAR file, or null if none exists or HAR capture was
	 *         disabled
	 */
	public Har createHarArchive() {
		return this.browserMobproxy.newHar();
	}

	/**
	 * Starts a new HAR page using the specified pageName as the page name and
	 * the page title.
	 *
	 * @param pageName
	 *            name of the new page
	 * @return the HAR as it existed immediately after ending the current page
	 */
	public Har createHarPage(String pageName) {
		return this.browserMobproxy.newPage(pageName);
	}

	/**
	 * Ends previously started Har page
	 */
	public void endHarPage() {
		((BrowserMobProxyServer) this.browserMobproxy).endPage();
	}

	/**
	 * Ends Har
	 */
	public void endHar() {
		this.browserMobproxy.endHar();
	}

	/**
	 * Retrieves the current HAR.
	 *
	 * @return current HAR, or null if HAR capture is not enabled
	 */
	public Har getHar() {
		return this.browserMobproxy.getHar();
	}

	/**
	 * Get the traffic from the Har archive
	 * 
	 * @param har
	 *            the name of har archive
	 * @return the responses body size from Har archive
	 */
	public long getTraffic(Har har) {
		long size = 0;
		for (HarEntry entry : har.getLog().getEntries()) {
			size += entry.getResponse().getBodySize();
		}
		return size;
	}

	/**
	 * Replaces any existing blacklist with the specified blacklist.
	 *
	 * @param blacklist
	 *            new blacklist entries
	 */
	public void setBlacklist(Collection<BlacklistEntry> blacklist) {
		browserMobproxy.setBlacklist(blacklist);
	}

	/**
	 * Get the Har archive with entries that have the page.The entries without
	 * page reference remove from archive
	 * 
	 * @return the Har archive with entries that have the page
	 */
	public Har getValidHarArchive() {
		Har harArchive = getHar();
		List<HarEntry> entries = harArchive.getLog().getEntries();
		for (HarEntry entry : entries) {
			if (entry.getPageref() == null) {
				entries.remove(entry);
			}
		}
		return harArchive;
	}

}
