package com.thoreau.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class ByteBufWriteTest {
    @Test
    public void testByteBuf() {
        // 4 byte
        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.directBuffer().alloc().buffer(1, 4);
        buffer.writeInt(299999999);
        buffer.writerIndex();
        assertEquals(4, buffer.writerIndex());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testByteBufException() {
        // 1 byte
        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.directBuffer().alloc().buffer(1, 3);
        buffer.writeInt(1);
        buffer.writerIndex();
        assertEquals(4, buffer.writerIndex());
    }
}
