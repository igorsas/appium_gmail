package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class NewMessageActivity extends BaseActivity {

    private static Logger LOGGER = LogManager.getLogger(NewMessageActivity.class);

    @AndroidFindBy(id = "to")
    private MobileElement receiverField;
    @AndroidFindBy(id = "subject")
    private MobileElement titleField;
    @AndroidFindBy(id = "composearea_tap_trap_bottom")
    private MobileElement messageField;
    @AndroidFindBy(id = "send")
    private MobileElement sendButton;


    public void setReceiverField(String receiver) {
        LOGGER.info("setting receiver");
        receiverField.sendKeys(receiver+"");
        driver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    public void setTitleField(String title) {
        LOGGER.info("setting title");
        titleField.sendKeys(title);
    }

    public void setMessageField(String message)
    {
        LOGGER.info("setting message");
        messageField.sendKeys(message);
    }

    public void clickToSendButton() {
        LOGGER.info("sending message");
        sendButton.click();
    }
}
