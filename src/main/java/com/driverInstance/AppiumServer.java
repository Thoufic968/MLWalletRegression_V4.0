package com.driverInstance;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AppiumServer{

   public AppiumServer(){

   }
      static AppiumDriverLocalService service;
   public static void startServer(){
     /* service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\IGS0502\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
              .withIPAddress("127.0.0.1").usingPort(4723).withStartUpTimeOut(60, TimeUnit.SECONDS).build();

      service.start();*/
    /*  AppiumServiceBuilder builder = new AppiumServiceBuilder();
      builder.withIPAddress ("127.0.0.1")
              .usingPort(4723)
              .usingAnyFreePort()
              .withAppiumJS (new File ("C:\\Users\\IGS0502\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
              .usingDriverExecutable (new File ("C:\\Program Files\\nodejs\\node.exe"))
              .withArgument (() -> "-pa", "/wd/hub")
              .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
              .withArgument (GeneralServerFlag.LOG_LEVEL, "debug");

      service = AppiumDriverLocalService.buildService (builder);*/
      service = AppiumDriverLocalService.
              // buildDefaultService();
                      buildService(new AppiumServiceBuilder()
                      .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                      .withAppiumJS(new File("C:\\Users\\" + System.getProperty("user.name")
                              + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                      .withIPAddress("127.0.0.1").usingPort(4723)
                      //.usingAnyFreePort()
                      .usingDriverExecutable (new File ("C:\\Program Files\\nodejs\\node.exe"))
                      .withArgument(GeneralServerFlag.LOG_LEVEL, "error"));// this is the flag to remove debug
      if (service.isRunning() == true) {
         service.stop();
         //service.start();
      } else {
         service.start();
         System.out.println("[EVENT] Appium Server Started Sucessfully.");
      }
   }

   public static void stopServer(){
      service.stop();
   }
}
