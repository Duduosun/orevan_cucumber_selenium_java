================================
Mobile Device Settings
================================
This document covers settings for  
*Mobile Automation on Emulated and Physical Devices, for Native, Hybrid, and Web App  
*Mobile Automation on Cloud using SauceLabs
  


1) Mobile Automation on Emulated and Physical Devices, for Native, Hybrid, and Web App
====================
Machine Configuration
====================
Configure Windows setup: -   
*Java 8 jdk and set up JAVA_HOME environment variable   
*Git  / SVN  and set up Git environment variable
*Maven and set up M2_HOME environment variable   
*Appium Server: AppiumForWindows.zip from https://bitbucket.org/appium/appium.app/downloads/  
*Android sdk and set up ANDROID_HOME environment variable: - Download Android stand-alone SDK Tools (Mandatory) OR Android Studio (optional)  
**Please note by default Android studio downloads latest Android Emulated devices


Framework Setup steps For Emulated Devices
============================
 For the desired android emulated device setup and operating system version e.g Android Kitkat which is 4.4.2, download necessary packages as listed below
 
1)Android SDK manager  
 Open "SDK Manager.exe" located in SDK installed location

Select all checkboxes under "Tools" and "Extras" menu  
Select desired android operating system version which needs to be tested and select all the boxes underneath it. 
 e.g "Android 4.4.2 (API 19)"  
 
Install all these packages 
 
2)Android AVD  
Locate AVD Manager.exe located in SDK installed location

Click on Create a new AVD (Android Virtual Device)
e.g for Android 4.4.2 device
Name: "anyName"
Device: "Nexus 7 (7.02", 1200 * 1920:xhdpi)"  
CPU/ABI: ARM(armeabi-v7a)  
Keyboard: Yes Hardware
Target:  Android 4.4.2 (API level 19)
Skin: HVGA
Camera:  None
Memory: RAM 1024 VM Heap 32
Internal Storage: 200 MiB
SD Card :  Size 100 MiB
Emulation Options : Un check all

    

3) Open "pom.xml" 
Scroll to Profile section : - Choose desired profile e.g "dev" for running locally

Set <browser>appium</browser> (For running Native Apps)
Set <browser>chrome</browser> (For running Web Apps)



            <properties>
                            <!-- Application under test-->
                            <platform>win64</platform>
                            <!--Desired browser to run e.g firefox,chrome,iexplore,phantomjs, appium, sauce -->
                            <browser>appium</browser>
                            <!--Location of Chrome, IE, PhantomJs Drivers installed, normally in your project source code under tools-->
                            <!-- Below is windows example. Linux example  home/dev/src/cucumber_testng_java/tools-->
                            <!-- If set to DEFAULT_PATH, will try to access from default path location valid for FIREFOX Driver-->
                            <driver.root.dir>DEFAULT_PATH</driver.root.dir>
                            <!--To Run parallel Test suite specify the type of Run Files which can be run in parallel -->
                            <testToRun>**/*ATSuite.class</testToRun>
            
            
                        </properties>


