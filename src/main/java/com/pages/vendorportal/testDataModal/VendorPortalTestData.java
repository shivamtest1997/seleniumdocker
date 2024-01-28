package com.pages.vendorportal.testDataModal;
//This is record type of class present in java17 and above to call test data
public record VendorPortalTestData(String username,
                                   String password,
                                   String monthlyEarning,
                                   String annualEarning,
                                   String profitMargin,
                                   String availableInventory,
                                   String searchKeyword,
                                   int searchResultsCount) {
}
