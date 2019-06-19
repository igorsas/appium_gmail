package com.igor.utils.provider;

import com.igor.utils.parser.JsonParser;
import com.igor.utils.property.Property;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static ThreadLocal<AndroidDriver<MobileElement>> DRIVER_POOL = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> CAPABILITIES_POOL = new ThreadLocal<>();
    private static List<Long> devices;

    private DriverProvider() {
    }

    public static synchronized AndroidDriver<MobileElement> getDriver() throws MalformedURLException {
        if (Objects.isNull(DRIVER_POOL.get())) {
            if(Objects.isNull(devices)){
                devices = new ArrayList<>();
            }
            devices.add(Thread.currentThread().getId());
            setCapabilities();
            initializeDriver();
        }
        return DRIVER_POOL.get();
    }

    private static void initializeDriver() throws MalformedURLException {
        DRIVER_POOL.set(new AndroidDriver<>(new URL(Property.getProperty("url")), CAPABILITIES_POOL.get()));
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(Long.parseLong(Property.getProperty("wait")), TimeUnit.SECONDS);
    }

    private static void setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Property.getProperty("platform"));
        capabilities.setCapability("appPackage", Property.getProperty("path"));
        capabilities.setCapability("appActivity", Property.getProperty("activity"));
        capabilities.setCapability("unicodeKeyboard", Property.getProperty("unicodeKeyboard"));
        capabilities.setCapability("resetKeyboard", Property.getProperty("resetKeyboard"));
        setCapabiliesForNewDevice(capabilities);
        CAPABILITIES_POOL.set(capabilities);
    }

    private static void setCapabiliesForNewDevice(DesiredCapabilities capabilities){
        int index = devices.indexOf(Thread.currentThread().getId());
        LogManager.getLogger(DriverProvider.class).info("INDEX OF CURRENT THREAD ID: " + index);
        String name = JsonParser.getDeviceName(index);
        String version = JsonParser.getDeviceVersion(index);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, name);
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
