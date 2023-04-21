package com.global;

import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import org.apache.xmlbeans.UserType;
import com.epam.ta.reportportal.ws.model.Constants;
import okhttp3.internal.platform.Platform;

public class GlobalData {

    public static final ThreadLocal<String> TESTCASENAME = new ThreadLocal<>();
    public static final String OUTPUT_FOLDER_REPORT = "extentreport/";
    public static final String OUTPUT_FOLDER_LOGS = "logs/";
    public static final String OUTPUT_FOLDER_SCREENSHOTS = "screenshots/";
    public static final String LOGSFILE_EXTENTION = ".txt";
    public static final String FILE_NAME_REPORT_SCREENSHOT = "reportscreenshot";
    public static final String FILE_NAME_REPORT = "extentreport.html";
    public static final String SUITETYPE = System.getProperty("suitetype", "system");
    public static final String GROUP_NAMES = System.getProperty("gname", "");
    //public static final Browser BROWSER = Browser.getBrowser(System.getProperty("browser", "chrome"));
    public static final String EXECUTION_TYPE = System.getProperty("suitetype", "system");
    public static final String BUILD_NAME = System.getProperty("buildversion", "Test");
    public static final String FILEPATH_CONFIG_PROPERTIES_FILE = "globaldata/config";
    public static final String FILEPATH_BROWSERSTACK_CONFIG_FILE = "devicecloud/browserstack";
    public static final String FILEPATH_REMOTEMACHINES_PROPERTIES_FILE = "remote/remotemachines";
    public static final String FILEPATH_LOGINDATA_PRODSANITY_PROPERTIES_FILE = "";
    public static final String FILEPATH_LOGINDATA_PROPERTIES_FILE = "";
    public static final String EXTENTREPORT_DOCUMENTTITLE_WEB = "Web App Automation - Core";
    public static final String EXTENTREPORT_REPORTNAME_WEB = "Web Automation";
    public static  int ELEMENT_TIMEOUT = 20;
    public static int PAGELOAD_TIMEOUT = 60;
    public static int APPIUM_NEW_COMMAND_TIMEOUT = 500;
    public static int DEFAULT_IMPLICITWAIT = 0;
    public static int DOWNLOADING_TIMEOUT = 600;
    public static Platform PLATFORM;
    public static final String ENVIRONMENT = System.getProperty("environment", "live");
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(FILEPATH_CONFIG_PROPERTIES_FILE);
    public static final String LANGUAGE = System.getProperty("language", "english");
    public static final String PARALLELMODE = System.getProperty("parallel", "methods");
    public static final String EXCLUDE_GROUPS = System.getProperty("excludegroup", "");
    public static String ADB_PATH;
    public static String APP_ACTIVITY;
    public static String APP_VERSION;
    public static String APP_PACKAGE;
    public static String APP_COMPLETE_PATH;
    public static String APP_BUNDLEID;
    public static final String ADB_LOCATION = "/platform-tools/adb";
    public static UserType USERTYPE ;
    public static String APPIUM_JS_FILE_PATH;
    public static int NOOFTHREADS = Integer.parseInt(System.getProperty("threads", "20"));
    public static final boolean FLAG_CAPTURESCREENSHOTS = true;
    public static final boolean FLAG_REMOVE_RETRIEDTESTS = true;
    public static Boolean INCOGNITO_MODE =false;
    public static int ELEMENT_TIMEOUT_BROWSERSTACK=10;


    static {
        initGlobalData();
    }

    private static void initGlobalData() {
		/*
		 * initCommonGlobalData(); switch (GlobalData.PLATFORM) {
		 * 
		 * case MWEB: if (GlobalData.BROWSER == Browser.SAFARI) { //
		 * initiOSGloablData(); } else { initAndroidGlobalData(); } break;
		 * 
		 * case ANDROID: initAndroidGlobalData(); break;
		 * 
		 * case IOS: // initiOSGloablData(); break;
		 * 
		 * }
		 */

    }

    private static void initCommonGlobalData() {
        if (System.getProperty("platform", "").length() > 0)
            //PLATFORM = Platform.getPlatform(System.getProperty("platform"));
        //else
            //PLATFORM = Platform.getPlatform(RESOURCE_BUNDLE.getString("PLATFORM"));
        System.out.println("Platform = " + PLATFORM.toString());
        System.out.println("Environment = " + ENVIRONMENT);
        System.out.println("Execution_Type = " + EXECUTION_TYPE);
        System.out.println("Language = " + LANGUAGE);
        if (GROUP_NAMES.length() > 0)
            System.out.println("Group_Names = " + GROUP_NAMES);

        if (EXCLUDE_GROUPS.length() > 0)
            System.out.println("Excluded_Group_Names = " + EXCLUDE_GROUPS);
        /*if (System.getProperty("userType", "").length() > 0)
            USERTYPE = UserType.getUsertype(System.getProperty("usertype"));
        else
            PLATFORM = Platform.getPlatform(RESOURCE_BUNDLE.getString("USERTYPE"));*/
        /*if (System.getenv("APPIUM_JS_FILE") != null)
            APPIUM_JS_FILE_PATH = System.getenv("APPIUM_JS_FILE");
        else
            APPIUM_JS_FILE_PATH = RESOURCE_BUNDLE.getString("APPIUM_JS_FILE");*/

    }

    private static void initAndroidGlobalData() {
        String ANDROID_HOME;
        if (System.getenv("ANDROID_HOME") != null)
            ANDROID_HOME = System.getenv("ANDROID_HOME");
        else
            ANDROID_HOME = RESOURCE_BUNDLE.getString("ANDROID_HOME");
        ADB_PATH = ANDROID_HOME + ADB_LOCATION;
        APP_PACKAGE = RESOURCE_BUNDLE.getString("APP_PACKAGE");
        APP_ACTIVITY = RESOURCE_BUNDLE.getString("APP_ACTIVITY");

        System.out.println("ANDROID_HOME = " + ANDROID_HOME);
        System.out.println("ADB Path = " + ADB_PATH);
        System.out.println("App Package = " + APP_PACKAGE);
        System.out.println("App Activity = " + APP_ACTIVITY);
    }

}
