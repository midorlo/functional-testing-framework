package de.midorlo.testing.framework.page_objects;

import com.lazerycode.selenium.util.Query;

import de.midorlo.testing.framework.DriverBase;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class GoogleHomePage.
 */
public class GoogleHomePage {

    /** The driver. */
    private final RemoteWebDriver driver = DriverBase.getDriver();

    /** The search bar. */
    private Query searchBar = new Query(By.name("q"), driver);
    
    /** The google search. */
    private Query googleSearch = new Query(By.name("btnK"), driver);
    
    /** The im feeling lucky. */
    private Query imFeelingLucky = new Query(By.name("btnI"), driver);

    /**
     * Instantiates a new google home page.
     *
     * @throws Exception the exception
     */
    public GoogleHomePage() throws Exception {
    }

    /**
     * Enter search term.
     *
     * @param searchTerm the search term
     * @return the google home page
     */
    public GoogleHomePage enterSearchTerm(String searchTerm) {
        searchBar.findWebElement().clear();
        searchBar.findWebElement().sendKeys(searchTerm);

        return this;
    }

    /**
     * Submit search.
     *
     * @return the google home page
     */
    public GoogleHomePage submitSearch() {
        googleSearch.findWebElement().submit();

        return this;
    }

    /**
     * Gets the lucky.
     *
     * @return the lucky
     */
    public void getLucky() {
        imFeelingLucky.findWebElement().click();
    }

}