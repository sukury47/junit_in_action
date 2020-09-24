package com.github.sukury47.junit_in_action.chapter07;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class AccountServiceWithMockitoTest {
    @Test
    public void transfer_must_be_fine() {
        final String senderId = "senderId";
        final String receiverId = "receiverId";

        AccountManager accountManager = mock(AccountManager.class);

        Account senderOne = new Account(senderId, 200);
        Account receiverOne = new Account(receiverId, 100);

        when(accountManager.findByUserId(senderOne.getUserId()))
        .thenReturn(senderOne);

        when(accountManager.findByUserId(receiverOne.getUserId()))
        .thenReturn(receiverOne);

        AccountService service = new AccountService();
        service.setAccountManager(accountManager);
        service.transfer(senderId, receiverId, 100);

        assertEquals(100, senderOne.getBalance());
        assertEquals(200, receiverOne.getBalance());
    }                
}
