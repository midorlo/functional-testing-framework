package de.midorlo.testing.framework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import de.midorlo.testing.framework.DriverBase;
import de.midorlo.testing.framework.page_objects.GoogleHomePage;

// TODO: Auto-generated Javadoc
/**
 * The Class GoogleExampleIT.
 */
public class GoogleExampleIT extends DriverBase {

    /**
     * Page title starts with.
     *
     * @param searchString the search string
     * @return the expected condition
     */
    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

    /**
     * Google cheese example.
     *
     * @throws Exception the exception
     */
    @Test(groups="beispiel")
    public void googleCheeseExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Cheese")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith("Cheese"));

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }

    /**
     * Google milk example.
     *
     * @throws Exception the exception
     */
    @Test(groups="beispiel")
    public void googleMilkExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Milk")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith("Milk"));

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }
}