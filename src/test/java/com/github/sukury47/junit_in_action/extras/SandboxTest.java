package com.github.sukury47.junit_in_action.extras;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class SandboxTest {
    private User aUser;
    private User bUser;

    @Before
    public void initialize() {
        aUser = new User.Builder().name("constant").build();    
        bUser = new User.Builder().name("constant").build();
    }

    @Test
    public void equal_objects_must_have_same_hashcode() {
        assertEquals(aUser.hashCode(), bUser.hashCode());
    }

    @Test
    public void objects_with_same_hashcode_must_be_inserted_once_in_hash_collection() {
        Set<User> users = new HashSet<>();
        users.add(aUser);
        users.add(bUser);
        assertEquals(2, users.size());
    }

    @Test
    public void objects_with_same_hashcode_could_have_different_memeory_address() {
        assertNotSame(aUser, bUser);
    }
}
