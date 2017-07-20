package com.thoreau.springboot.sample.test;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * 17/7/19 下午11:37.
 *
 * @author zhaozhou
 */
public class MockitoSample {

    @Test
    public void mockSample(){
        ArrayList mockList = mock(ArrayList.class);
        //stubbing
        when(mockList.get(0)).thenReturn("first").thenReturn("second");
        assertThat(mockList.get(0)).isEqualTo("first");
        assertThat(mockList.get(0)).isEqualTo("second");

        when(mockList.get(anyInt())).thenReturn("element");
        assertThat(mockList.get(0)).isEqualTo("element");
        mockList.add("third");
        mockList.clear();
        mockList.clear();

        //veryfy 执行次数
        verify(mockList,times(1)).add("third");
        verify(mockList,times(2)).clear();
        verify(mockList,atLeastOnce()).clear();

        doThrow(new RuntimeException()).when(mockList).clear();

    }
}
