package com.github.sukury47.junit_in_action.chapter07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountServiceTest {
    @Test
    public void transfer_must_be_fine() {
        MockAccountManager accountManager = new MockAccountManager();
        
        final String senderId = "senderId";
        final String receiverId = "receiverId";
        
        accountManager.addAccount(new Account(senderId, 200));
        accountManager.addAccount(new Account(receiverId, 100));

        Account sender = accountManager.findByUserId(senderId);
        Account receiver = accountManager.findByUserId(receiverId);

        AccountService service = new AccountService();
        service.setAccountManager(accountManager);
        service.transfer(senderId, receiverId, 100);

        assertEquals(100, sender.getBalance());
        assertEquals(200, receiver.getBalance());        
    }
}
