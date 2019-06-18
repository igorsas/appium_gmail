package com.igor;

import com.igor.business.LogInBO;
import com.igor.business.MessageBO;
import com.igor.listener.TestNGListener;
import com.igor.model.LetterModel;
import com.igor.utils.provider.DriverProvider;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static com.igor.utils.parser.JsonParser.*;
import static org.testng.Assert.assertTrue;

@Listeners(TestNGListener.class)
public class GmailTest {
    private final String MESSAGE_TITLE = UUID.randomUUID().toString();

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        return Stream.of(getUsers()).iterator();
    }

    @Test(dataProvider = "users")
    public void sendEmailTest(String username, String password) {
        LogInBO logInBO = new LogInBO();
        MessageBO messageBO = new MessageBO();
        logInBO.logIn(username, password);
        LetterModel letterModel = new LetterModel(getReceiver(), MESSAGE_TITLE, getMessage());
        messageBO.fillFieldsForMessage(letterModel);
        messageBO.sendMessage();
        assertTrue(messageBO.isLetterSent(letterModel), "message was sent");
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
