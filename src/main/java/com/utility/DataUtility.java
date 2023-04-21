package com.utility;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataUtility {

    @DataProvider(name = "getRegisteredUsers")
    public static Object[] getRegisteredUsers(ITestContext iTestContext) {
        String userType = iTestContext.getCurrentXmlTest().getParameter("registeredUser");
        Object[] data = new Object[userType.split(",").length];
        int counter = 0;
        String[] arr = userType.split(",");
        for (String user : arr) {
            data[counter++] = user;
        }
        return data;
    }

}
