package com.thoreauz.bootlearn.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * 2019/3/24 2:46 AM.
 *
 * @author zhaozhou
 */
public class FinalClassTest {

    @Test
    public void finalMethod() {
        FinalClass concrete = new FinalClass();
        FinalClass mock = mock(FinalClass.class);
        given(mock.finalMethod()).willReturn("not anymore");
        assertThat(mock.finalMethod()).isNotEqualTo(concrete.finalMethod());
    }
}