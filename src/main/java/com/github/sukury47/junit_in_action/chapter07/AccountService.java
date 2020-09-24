package com.github.sukury47.junit_in_action.chapter07;

public class AccountService {
    private AccountManager accountManager;
    
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void transfer(String senderId, String receiverId, long amount) {
        Account sender = accountManager.findByUserId(senderId);
        Account receiver = accountManager.findByUserId(receiverId);
        sender.debit(amount);
        receiver.credit(amount);
        accountManager.update(sender);
        accountManager.update(receiver);
    }
}
