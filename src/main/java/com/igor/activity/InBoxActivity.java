package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class InBoxActivity extends BaseActivity {

    private static Logger LOGGER = LogManager.getLogger(InBoxActivity.class);

    @AndroidFindBy(id = "compose_button")
    private MobileElement composeButton;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    private MobileElement navigationButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sent']")
    private MobileElement sentMessageButton;

    public void clickOnComposeButton(){
        LOGGER.info("Opening new message activity");
        composeButton.click();
    }

    public void clickOnNavigationButton(){
        LOGGER.info("clicking on navigation button");
        navigationButton.click();
    }

    public void clickOnSentMessageButton(){
        LOGGER.info("going to sent activity");
        sentMessageButton.click();
    }
}
