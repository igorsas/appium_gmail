package com.igor.business;

import com.igor.model.LetterModel;
import com.igor.activity.InBoxActivity;
import com.igor.activity.SentActivity;
import com.igor.activity.NewMessageActivity;

public class MessageBO {
    private InBoxActivity inboxActivity;
    private SentActivity sentActivity;
    private NewMessageActivity newMessageActivity;

    public MessageBO() {
        inboxActivity = new InBoxActivity();
        sentActivity = new SentActivity();
        newMessageActivity = new NewMessageActivity();
    }

    public void fillFieldsForMessage(LetterModel letterModel) {
        inboxActivity.clickOnComposeButton();
        newMessageActivity.setReceiverField(letterModel.getReceiver());
        newMessageActivity.setTitleField(letterModel.getTopic());
        newMessageActivity.setMessageField(letterModel.getMessage());
    }

    public void sendMessage() {
        newMessageActivity.clickToSendButton();
    }

    public boolean isLetterSent(LetterModel letterModel) {
        inboxActivity.clickOnNavigationButton();
        inboxActivity.clickOnSentMessageButton();
        return sentActivity.isTitlePresentInSentMessages(letterModel.getTopic());
    }
}
