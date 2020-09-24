package com.github.sukury47.junit_in_action.chapter07;

import java.util.HashMap;
import java.util.Map;

public class MockAccountManager implements AccountManager {
    private Map<String, Account> accountsByUserId = new HashMap<>();
    
    public void addAccount(String userId, Account account) {
        accountsByUserId.put(userId, account);
    }

    public void addAccount(Account account) {
        accountsByUserId.put(account.getUserId(), account);
    }

    @Override
    public Account findByUserId(String userId) {
        return accountsByUserId.get(userId);
    }
    
    @Override
    public void update(Account account) {
        //do nothing
    }
}
