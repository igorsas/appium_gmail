package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomeActivity extends BaseActivity {

    @AndroidFindBy(id = "welcome_tour_got_it")
    private MobileElement gotItButton;

    @AndroidFindBy(id = "setup_addresses_add_another")
    private MobileElement createNewAccoutButton;

    @AndroidFindBy(id = "action_done")
    private MobileElement goToMailButton;

    @AndroidFindBy(id = "setup_addresses_list")
    private MobileElement loggedInUsersList;

    public void clickOnGotItButton(){
        gotItButton.click();
    }

    public void clickOnCreateNewAccoutButton(){
        createNewAccoutButton.click();
    }

    public void clickOnGoToMailButton() {
        goToMailButton.click();
    }

    public String getLoggedInUsers(){
        return loggedInUsersList.getText();
    }
}
