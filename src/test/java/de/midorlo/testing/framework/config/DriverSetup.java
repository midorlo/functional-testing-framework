package de.midorlo.testing.framework.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Interface DriverSetup.
 */
public interface DriverSetup {
    
    /**
     * Gets the web driver object.
     *
     * @param capabilities the capabilities
     * @return the web driver object
     */
    RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities);
}