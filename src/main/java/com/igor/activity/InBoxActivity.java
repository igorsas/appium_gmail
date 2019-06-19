package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class InBoxActivity extends BaseActivity {
    @AndroidFindBy(id = "compose_button")
    private MobileElement composeButton;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    private MobileElement navigationButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sent']")
    private MobileElement sentMessageButton;

    public void clickOnComposeButton(){
        composeButton.click();
    }

    public void clickOnNavigationButton(){
        navigationButton.click();
    }

    public void clickOnSentMessageButton(){
        sentMessageButton.click();
    }
}
