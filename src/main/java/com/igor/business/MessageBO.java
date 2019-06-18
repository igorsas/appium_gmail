package com.igor.business;

import com.igor.model.LetterModel;
import com.igor.activity.InBoxActivity;
import com.igor.activity.SentActivity;
import com.igor.activity.NewMessageActivity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MessageBO {
    private static final Logger LOGGER = LogManager.getLogger(MessageBO.class);
    private InBoxActivity inboxActivity;
    private SentActivity sentActivity;
    private NewMessageActivity newMessageActivity;

    public MessageBO() {
        inboxActivity = new InBoxActivity();
        sentActivity = new SentActivity();
        newMessageActivity = new NewMessageActivity();
    }

    public void fillFieldsForMessage(LetterModel letterModel) {
        LOGGER.info("Opening new message activity");
        inboxActivity.clickOnComposeButton();
        LOGGER.info("set receiver");
        newMessageActivity.setReceiverField(letterModel.getReceiver());
        LOGGER.info("set title");
        newMessageActivity.setTitleField(letterModel.getTopic());
        LOGGER.info("set message");
        newMessageActivity.setMessageField(letterModel.getMessage());
    }

    public void sendMessage() {
        LOGGER.info("sending message");
        newMessageActivity.clickToSendButton();
    }

    public boolean isLetterSent(LetterModel letterModel) {
        LOGGER.info("opening sent activity");
        inboxActivity.clickOnNavigationButton();
        inboxActivity.clickOnSentMessageButton();
        LOGGER.info("checking sent activity");
        return sentActivity.isTitlePresentInSentMessages(letterModel.getTopic());
    }
}
