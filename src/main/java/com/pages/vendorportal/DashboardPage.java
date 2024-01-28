package com.pages.vendorportal;

import com.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;
    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;
    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;
    @FindBy(id = "available-inventory")
    private WebElement availableInventory;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInput;
    @FindBy(id = "dataTable_info")
    private WebElement searchResultCount;
    @FindBy(xpath = "//img[@class='img-profile rounded-circle']")
    private WebElement imageProfile;
    @FindBy(linkText = "Logout")
    private WebElement logout;
    @FindBy(xpath = "//div[@class='modal-content']//*[text()='Logout']")
    private WebElement modalLogoutBtn;

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(annualEarningElement));
        return annualEarningElement.isDisplayed();
    }

    public String monthlyEarning() {
        return monthlyEarningElement.getText();
    }

    public String annualEarning() {
        return annualEarningElement.getText();
    }

    public String profitMargin() {
        return profitMarginElement.getText();
    }

    public String availableInventory() {
        return availableInventory.getText();
    }

    public void searchResultHistory(String keyword) {
        searchInput.sendKeys(keyword);
    }
    /*
            Showing 1 to 10 of 32 entries (filtered from 99 total entries)
            arr[0] = "Showing"
            arr[1] = "1"
            arr[2] = "to"
            arr[3] = "10"
            arr[4] = "of"
            arr[5] = "32"
            ...
            ...
         */
    public int getSearchResultsCount(){
        String resultsText = this.searchResultCount.getText();
        String[] arr = resultsText.split(" ");
        // if we do not have 5th item, it would throw exception.
        // that's fine. we would want our test to fail anyway in that case!
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }
    public void logout()
    {
        imageProfile.click();
        wait.until(ExpectedConditions.visibilityOf(logout));
        logout.click();
        wait.until(ExpectedConditions.visibilityOf(modalLogoutBtn));
        modalLogoutBtn.click();
    }

}
