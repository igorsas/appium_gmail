package com.igor.utils.provider;

import com.igor.utils.property.Property;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.igor.utils.constant.Constants.IMPLICIT_WAIT;

public class DriverProvider {
    private static AndroidDriver<MobileElement> driver;
    private static DesiredCapabilities capabilities;

    private DriverProvider() {
    }

    public static AndroidDriver<MobileElement> getDriver() throws MalformedURLException {
        if (Objects.isNull(driver)) {
            setCapabilities();
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() throws MalformedURLException {
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    private static void setCapabilities(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Property.getProperty("platform"));
        capabilities.setCapability("appPackage", Property.getProperty("path"));
        capabilities.setCapability("appActivity", Property.getProperty("activity"));
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");
//        capabilities.setCapability("appWaitActivity", Property.getProperty("activity"));
    }

    public static void quit() {
        driver.quit();
    }
}
