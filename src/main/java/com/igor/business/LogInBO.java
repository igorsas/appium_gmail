package com.igor.business;

import com.igor.activity.LoginActivity;
import com.igor.activity.WelcomeActivity;

public class LogInBO {
    private WelcomeActivity welcomeActivity;
    private LoginActivity loginActivity;

    public LogInBO() {
        welcomeActivity = new WelcomeActivity();
        loginActivity = new LoginActivity();
    }

    public void logIn(String username, String password) {
        welcomeActivity.clickOnGotItButton();
        if(!welcomeActivity.getLoggedInUsers().isEmpty()) {
            welcomeActivity.clickOnCreateNewAccoutButton();
            loginActivity.chooseGoogleEmail();
            loginActivity.setUsername(username);
            loginActivity.setPassword(password);
            loginActivity.clickOk();
        }
        welcomeActivity.clickOnGoToMailButton();
    }
}
