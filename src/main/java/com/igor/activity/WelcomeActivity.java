package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class WelcomeActivity extends BaseActivity {

    private static Logger LOGGER = LogManager.getLogger(WelcomeActivity.class);

    @AndroidFindBy(id = "welcome_tour_got_it")
    private MobileElement gotItButton;

    @AndroidFindBy(id = "setup_addresses_add_another")
    private MobileElement createNewAccoutButton;

    @AndroidFindBy(id = "action_done")
    private MobileElement goToMailButton;

    @AndroidFindBy(id = "setup_addresses_list")
    private MobileElement loggedInUsersList;

    public void clickOnGotItButton()
    {
        LOGGER.info("Clicking on got it button");
        gotItButton.click();
    }

    public void clickOnCreateNewAccoutButton()
    {
        LOGGER.info("clicking on create new account button");
        createNewAccoutButton.click();
    }

    public void clickOnGoToMailButton() {
        LOGGER.info("going to inbox");
        goToMailButton.click();
    }

    public String getLoggedInUsers()
    {
        LOGGER.info("getting all logged in users");
        return loggedInUsersList.getText();
    }
}
