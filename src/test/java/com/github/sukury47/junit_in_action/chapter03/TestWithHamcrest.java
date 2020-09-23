package com.github.sukury47.junit_in_action.chapter03;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestWithHamcrest {
    private List<String> values;

    @Before
    public void setUpList() {
        values = List.of("one", "two", "three");
    }

    @Test
    public void test_could_be_more_easy_with_hamcrest() {
        assertThat(values, hasItem(anyOf(equalTo("one"), equalTo("two"))));
    }
}
