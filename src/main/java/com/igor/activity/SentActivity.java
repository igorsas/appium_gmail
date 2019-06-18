package com.igor.activity;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;


public class SentActivity extends BaseActivity {

    public boolean isTitlePresentInSentMessages(String title) {
        List<MobileElement> elements = driver.findElements(
                By.xpath("//*[@resource-id='com.google.android.gm:id/list_view']" +
                        "/android.widget.FrameLayout" +
                        "//android.view.View[contains(@content-desc,'" + title + "')]"));
        return !elements.isEmpty();
    }
}
