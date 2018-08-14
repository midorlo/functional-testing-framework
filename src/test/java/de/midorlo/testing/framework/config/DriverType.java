package de.midorlo.testing.framework.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Enum DriverType.
 */
public enum DriverType implements DriverSetup {

	/** The firefox. */
	FIREFOX {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            FirefoxOptions options = new FirefoxOptions();
			try {
				System.out.println("EXISTS: " + new File(DriverType.class.getResource(System.getProperty("pathFirefox.61.0.2.portable")).toURI()).exists());
				File binaryFile = new File(DriverType.class.getResource(System.getProperty("pathFirefox.61.0.2.portable")).toURI());
				FirefoxBinary binary = new FirefoxBinary(binaryFile);
	            options.setBinary(binary);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            options.merge(capabilities);
            options.setHeadless(HEADLESS);

            return new FirefoxDriver(options);
        }
    },
/** The firefox portable. */
	FIREFOX_PORTABLE {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            FirefoxOptions options = new FirefoxOptions();
            String binaryLocation = System.getProperty("pathFirefox.61.0.2.portable");
            System.out.println("BINARY:" + binaryLocation);
            
			try {
				System.out.println("EXISTS: " + new File(DriverType.class.getResource(binaryLocation).toURI()).exists());
				File binaryFile = new File(DriverType.class.getResource(binaryLocation).toURI());
				FirefoxBinary binary = new FirefoxBinary(binaryFile);
	            options.setBinary(binary);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            options.merge(capabilities);
            options.setHeadless(HEADLESS);

            return new FirefoxDriver(options);
        }
    },
    
    /** The chrome. */
    CHROME {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            options.setHeadless(HEADLESS);
            options.addArguments("--no-default-browser-check");
            options.setExperimentalOption("prefs", chromePreferences);

            return new ChromeDriver(options);
        }
    },
    
    /** The ie. */
    IE {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.merge(capabilities);
            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

            return new InternetExplorerDriver(options);
        }

        @Override
        public String toString() {
            return "internet explorer";
        }
    },
    
    /** The edge. */
    EDGE {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            EdgeOptions options = new EdgeOptions();
            options.merge(capabilities);

            return new EdgeDriver(options);
        }
    },
    
    /** The safari. */
    SAFARI {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            SafariOptions options = new SafariOptions();
            options.merge(capabilities);

            return new SafariDriver(options);
        }
    },
    
    /** The opera. */
    OPERA {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            OperaOptions options = new OperaOptions();
            options.merge(capabilities);

            return new OperaDriver(options);
        }
    };

    /** The Constant HEADLESS. */
    public final static boolean HEADLESS = Boolean.getBoolean("headless");

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}