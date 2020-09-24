package com.github.sukury47.junit_in_action.chapter07;

public interface AccountManager {
    Account findByUserId(String userId);
    void update(Account account);    
}
