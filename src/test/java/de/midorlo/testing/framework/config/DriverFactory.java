package de.midorlo.testing.framework.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static de.midorlo.testing.framework.config.DriverType.FIREFOX;
import static de.midorlo.testing.framework.config.DriverType.valueOf;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Driver objects.
 */
public class DriverFactory {

    /** The driver. */
    private RemoteWebDriver driver;
    
    /** The selected driver type. */
    private DriverType selectedDriverType;

    /** The operating system. */
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    
    /** The system architecture. */
    private final String systemArchitecture = System.getProperty("os.arch");
    
    /** The use remote web driver. */
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    
    /** The proxy enabled. */
    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    
    /** The proxy hostname. */
    private final String proxyHostname = System.getProperty("proxyHost");
    
    /** The proxy port. */
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    
    /** The proxy details. */
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);

    /**
     * Instantiates a new driver factory.
     */
    public DriverFactory() {
        DriverType driverType = FIREFOX;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    /**
     * Gets the driver.
     *
     * @return the driver
     * @throws Exception the exception
     */
    public RemoteWebDriver getDriver() throws Exception {
        if (null == driver) {
            instantiateWebDriver(selectedDriverType);
        }

        return driver;
    }

    /**
     * Gets the stored driver.
     *
     * @return the stored driver
     */
    public RemoteWebDriver getStoredDriver() {
        return driver;
    }

    /**
     * Quit driver.
     */
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Instantiate web driver.
     *
     * @param driverType the driver type
     * @throws MalformedURLException the malformed URL exception
     */
    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        //TODO add in a real logger instead of System.out
        System.out.println(" ");
        System.out.println("Local Operating System: " + operatingSystem);
        System.out.println("Local Architecture: " + systemArchitecture);
        System.out.println("Selected Browser: " + selectedDriverType);
        System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (proxyEnabled) {
            Proxy proxy = new Proxy();
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(proxyDetails);
            proxy.setSslProxy(proxyDetails);
            desiredCapabilities.setCapability(PROXY, proxy);
        }

        if (useRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            desiredCapabilities.setBrowserName(selectedDriverType.toString());
            driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            driver = driverType.getWebDriverObject(desiredCapabilities);
        }
    }
}

