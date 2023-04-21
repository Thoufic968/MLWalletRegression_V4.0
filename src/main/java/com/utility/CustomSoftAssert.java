package com.utility;

import com.aventstack.extentreports.markuputils.ExtentColor;

import com.utility.LoggingUtils;
import org.testng.Assert;
import org.testng.ITestResult;

import java.util.LinkedList;
import java.util.List;

public class CustomSoftAssert extends ExtentReporter {
    static private List<Throwable> m_errors;
    static LoggingUtils logger = new LoggingUtils();
    
    public CustomSoftAssert() {
        m_errors = new LinkedList<>();
    }

    public static void assertTrue(boolean condition, String message) throws Exception {
        try {
            Assert.assertTrue(condition, message);
            logger.info(message + " = <b>Pass</b>");
            ExtentReporter.extentLogger("", message + " = <b>Pass</b>");    
        } catch (Throwable e) {
            logger.error(message + " = <b>Fail</b>");
            ExtentReporter.extentLoggerFail("", message + " = <b>Fail</b>");   
            m_errors.add(e);
        }
    }

    public static void assertContainsIgnoreCase(String completeString, String subString, String message) throws Exception {
        String str = message + "<br><b>Complete String : </b>" + completeString + "<br><b>Substring : </b>" + subString;
        String msg="";
        try {
            msg = message + " -- \nComplete String : " + completeString + ",\nSubstring : " + subString + "\n\n";
            Assert.assertTrue(completeString.toLowerCase().contains(subString.toLowerCase()), msg);
            logger.info(msg);
            ExtentReporter.extentLogger("", msg);          
        } catch (Throwable e) {
            m_errors.add(e);
            logger.error(msg);
            ExtentReporter.extentLoggerFail("", msg);   
        }
    }

    public static void assertEquals(String actual, String expected, String message) throws Exception {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        try {
            Assert.assertEquals(actual, expected, message);
            logger.info(str);
            ExtentReporter.extentLogger("", str);
        } catch (Throwable e) {
            logger.error(str);
            ExtentReporter.extentLoggerFail("", str);   
            m_errors.add(e);
        }
    }

    public static void assertEqualsIgnoreCase(String actual, String expected, String message) throws Exception {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        try {
            Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), message);
            logger.info(str);
            ExtentReporter.extentLogger("", str);
        } catch (Throwable e) {
            logger.error(str);
            ExtentReporter.extentLoggerFail("", str);   
            m_errors.add(e);
        }

    }

    public static void assertEquals(double actual, double expected, String message) throws Exception {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        try {
            Assert.assertEquals(actual, expected, message);
            logger.info(str);
            ExtentReporter.extentLogger("", str);
        } catch (Throwable e) {
            logger.error(str);
            ExtentReporter.extentLoggerFail("", str);   
            m_errors.add(e);
        }
    }

    public static void assertEquals(int actual, int expected, String message) throws Exception {
        String str = message + "<br><b>Actual : </b>" + actual + "<br><b>Expected : </b>" + expected;
        try {
            Assert.assertEquals(actual, expected, message);
            logger.info(str);
            ExtentReporter.extentLogger("", str);
        } catch (Throwable e) {
            logger.error(str);
            ExtentReporter.extentLoggerFail("", str);   
            m_errors.add(e);
        }
    }

    public static void assertContains(String completeString, String subString, String message) throws Exception {
        String str = message + "<br><b>Complete String : </b>" + completeString + "<br><b>Substring : </b>" + subString;
        String errorMessage = message + " -- \nComplete String : " + completeString + "\nSubstring : " + subString + "\n\n";
        try {
            Assert.assertTrue(completeString.contains(subString), errorMessage);
            logger.info(str);
            ExtentReporter.extentLogger("", str);
        } catch (Throwable e) {
            logger.error(str);
            ExtentReporter.extentLoggerFail("", str); 
            m_errors.add(e);
        }
    }

    public static void assertAll() {
        StringBuilder customMessage = new StringBuilder();
        int size = m_errors.size();
        if (size > 0) {
            customMessage.append("Total Assertion Failures = [" + size + "]" + System.lineSeparator());
            int i = 1;
            for (Throwable throwable : m_errors) {
                customMessage.append(System.lineSeparator());
                customMessage.append("Failure " + i + " of " + size + " :" + System.lineSeparator());
                customMessage.append(throwable.getLocalizedMessage());
                customMessage.append(System.lineSeparator());
                i++;
            }
            throw new AssertionError(customMessage);
        }
    }
    
    public static void assertFalse(boolean condition, String message) throws Exception {
        try {
            Assert.assertFalse(condition, message);
            logger.info(message + " = <b>Pass</b>");
            ExtentReporter.extentLogger("", message + " = <b>Pass</b>");
        } catch (Throwable e) {
            logger.error(message + " = <b>Fail</b>");
            ExtentReporter.extentLoggerFail("", message + " = <b>Fail</b>"); 
            m_errors.add(e);
        }
    }

}
