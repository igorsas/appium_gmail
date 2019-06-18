package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import java.util.List;


public class SentActivity extends BaseActivity{

//    @AndroidFindBy(xpath = "")
//    private List<MobileElement> sentMessages;

    public boolean isTitlePresentInSentMessages(String title){
        List<MobileElement> elements = driver.findElements(
                By.xpath("//*[@resource-id='com.google.android.gm:id/list_view']" +
                        "/android.widget.FrameLayout" +
                        "//android.view.View[contains(@content-desc,'" + title + "')]"));
        return !elements.isEmpty();
//        for(MobileElement mobileElement : sentMessages){
//            String text = mobileElement.getAttribute("content-desc");
//            if(text.contains(title)){
//                return true;
//            }
//        }
//        return false;
        //       return sentMessages.stream().map(mobileElement -> mobileElement.getAttribute("content-desc")).anyMatch(text -> text.contains(title));

    }
}
