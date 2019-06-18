package com.igor.utils.provider;

import com.igor.utils.parser.JsonParser;
import com.igor.utils.property.Property;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.igor.utils.constant.Constants.IMPLICIT_WAIT;

public class DriverProvider {
    private static ThreadLocal<AndroidDriver<MobileElement>> DRIVER_POOL = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> CAPABILITIES_POOL = new ThreadLocal<>();
    private static List<Long> devices;

    private DriverProvider() {
    }

    public static AndroidDriver<MobileElement> getDriver() throws MalformedURLException {
        if (Objects.isNull(DRIVER_POOL.get())) {
            devices = new ArrayList<Long>();
            devices.add(Thread.currentThread().getId());
            setCapabilities();
            initializeDriver();
        }
        return DRIVER_POOL.get();
    }

    private static void initializeDriver() throws MalformedURLException {
        DRIVER_POOL.set(new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), CAPABILITIES_POOL.get()));
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    private static void setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Property.getProperty("platform"));
        capabilities.setCapability("appPackage", Property.getProperty("path"));
        capabilities.setCapability("appActivity", Property.getProperty("activity"));
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");
        setCapabiliesForNewDevice(capabilities);
        CAPABILITIES_POOL.set(capabilities);
    }

    private static void setCapabiliesForNewDevice(DesiredCapabilities capabilities){
        String name, version;
        if(Thread.currentThread().getId() == devices.get(0)){
            name = JsonParser.getDeviceName(0);
            version = JsonParser.getDeviceVersion(0);
        }else {
            name = JsonParser.getDeviceName(1);
            version = JsonParser.getDeviceVersion(1);
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, name);
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
