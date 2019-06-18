package com.igor;

import com.igor.business.LogInBO;
import com.igor.business.MessageBO;
import com.igor.listener.FailureLister;
import com.igor.model.LetterModel;
import com.igor.utils.provider.DriverProvider;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.igor.utils.parser.JsonParser.*;
import static org.testng.Assert.assertTrue;

@Listeners(FailureLister.class)
public class GmailTest {
    private final String MESSAGE_TITLE = UUID.randomUUID().toString();

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        return Stream.of(getUsers()).iterator();
    }

    @Test(dataProvider = "users")
    public void sendEmailTest(String username, String password) throws InterruptedException {
        LogInBO logInBO = new LogInBO();
        MessageBO messageBO = new MessageBO();
        LetterModel letterModel = new LetterModel(getReceiver(), MESSAGE_TITLE, getMessage());
        logInBO.logIn(username, password);
        TimeUnit.SECONDS.sleep(25);
        messageBO.fillFieldsForMessage(letterModel);
        messageBO.sendMessage();
        assertTrue(messageBO.isLetterSent(letterModel));
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
