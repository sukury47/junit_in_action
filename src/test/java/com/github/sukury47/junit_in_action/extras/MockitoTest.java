package com.github.sukury47.junit_in_action.extras;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    private List<String> mockedList;
    @Spy
    private List<String> spiedList = new ArrayList<>();
    @Captor
    private ArgumentCaptor<String> argCaptor;

    @Test
    public void play_with_mock_annotation() {
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }
    
    @Test
    public void play_with_spy_annotation() {
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        assertEquals(2, spiedList.size());
        Mockito.when(spiedList.size()).thenReturn(100);
        //Mockito.doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());
    }

    @Test
    public void play_with_captor_annotation() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argCaptor.capture());
        assertEquals("one", argCaptor.getValue());
    }
}
