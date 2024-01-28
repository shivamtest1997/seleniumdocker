package com.test;

import com.pages.vendorportal.DashboardPage;
import com.pages.vendorportal.LoginPage;
import com.pages.vendorportal.testDataModal.VendorPortalTestData;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import util.Config;
import util.Constants;
import util.JsonUtil;

import java.util.stream.Stream;

public class VendorPortalTest extends AbstractTest{

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private VendorPortalTestData testData;

    @BeforeClass
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        this.testData=JsonUtil.getTestData(testDataPath);
    }

    @Test
    public void login() {
        loginPage.goToVendorPortal(Config.getProperties(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "login")
    public void dashboardTest() throws InterruptedException {

        // finance metrics
        Assert.assertTrue(dashboardPage.isAt());
        Thread.sleep(1000);
        Assert.assertEquals(dashboardPage.monthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.annualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.profitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.availableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchResultHistory(testData.searchKeyword());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0, 1000)");
        Thread.sleep(1000);
        int actualCount=dashboardPage.getSearchResultsCount();
        int expectedCount=testData.searchResultsCount();
        Assert.assertEquals(actualCount,expectedCount);
    }
    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
