package com.igor.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginActivity extends BaseActivity {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Google']")
    private MobileElement googleEmailVersion;

    @AndroidFindBy(id = "identifierId")
    private MobileElement usernameField;

    @AndroidFindBy(id = "identifierNext")
    private MobileElement identefierNextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Show password']/../following-sibling::android.view.View")
    private MobileElement passwordField;

    @AndroidFindBy(id = "passwordNext")
    private MobileElement passwordNextButton;

    @AndroidFindBy(id = "signinconsentNext")
    private MobileElement signInNextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='ACCEPT']")
    private MobileElement acceptButton;

    public void chooseGoogleEmail(){
        googleEmailVersion.click();
    }

    public void setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
        identefierNextButton.click();
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        passwordNextButton.click();
    }

    public void clickOk(){
        signInNextButton.click();
        acceptButton.click();
    }
}
