package com.igor.activity;

import com.igor.utils.provider.DriverProvider;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public abstract class BaseActivity {
    protected AndroidDriver<MobileElement> driver;

    protected BaseActivity() {
        try {
            driver = DriverProvider.getDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
