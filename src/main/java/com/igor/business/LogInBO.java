package com.igor.business;

import com.igor.activity.LoginActivity;
import com.igor.activity.WelcomeActivity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogInBO {
    private static final Logger LOGGER = LogManager.getLogger(LogInBO.class);
    private WelcomeActivity welcomeActivity;
    private LoginActivity loginActivity;

    public LogInBO() {
        welcomeActivity = new WelcomeActivity();
        loginActivity = new LoginActivity();
    }

    public void logIn(String username, String password) {
        LOGGER.info("Clicking on got it button");
        welcomeActivity.clickOnGotItButton();
        if(!welcomeActivity.getLoggedInUsers().isEmpty()) {
            LOGGER.info("clicking on create new account button");
            welcomeActivity.clickOnCreateNewAccoutButton();
            LOGGER.info("choosing google email");
            loginActivity.chooseGoogleEmail();
            LOGGER.info("setting username");
            loginActivity.setUsername(username);
            LOGGER.info("setting password");
            loginActivity.setPassword(password);
            LOGGER.info("agreeing with all their rules");
            loginActivity.clickOk();
            LOGGER.info("go to inbox");
        }
        welcomeActivity.clickOnGoToMailButton();
    }
}
