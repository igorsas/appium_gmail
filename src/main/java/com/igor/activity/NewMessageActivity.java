package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NewMessageActivity extends BaseActivity {
    @AndroidFindBy(id = "to")
    private MobileElement receiverField;
    @AndroidFindBy(id = "subject")
    private MobileElement titleField;
    @AndroidFindBy(id = "composearea_tap_trap_bottom")
    private MobileElement messageField;
    @AndroidFindBy(id = "send")
    private MobileElement sendButton;


    public void setReceiverField(String receiver) {
        receiverField.sendKeys(receiver+"");
        driver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    public void setTitleField(String title) {
        titleField.sendKeys(title);
    }

    public void setMessageField(String message) {
        messageField.sendKeys(message);
    }

    public void clickToSendButton() {
        sendButton.click();
    }
}
