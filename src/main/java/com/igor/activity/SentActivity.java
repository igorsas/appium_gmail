package com.igor.activity;

import io.appium.java_client.MobileElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;


public class SentActivity extends BaseActivity {

    private static Logger LOGGER = LogManager.getLogger(SentActivity.class);

    public boolean isTitlePresentInSentMessages(String title) {
        LOGGER.info("checking sent letter");
        //id = com.google.android.gm:id/thread_list_view
        List<MobileElement> elements = driver.findElements(
                By.xpath("//*[contains(@resource-id,'com.google.android.gm:id/list_view')]" +
                        "//android.view.View[contains(@content-desc,'" + title + "')]"));
        return !elements.isEmpty();
    }
}
