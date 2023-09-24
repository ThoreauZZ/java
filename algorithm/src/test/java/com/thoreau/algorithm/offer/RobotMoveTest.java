package com.thoreau.algorithm.offer;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * 2023/4/21 19:23.
 *
 * @author zhaozhou
 */
public class RobotMoveTest extends TestCase {
    @Test
    public void test() {
        RobotMove robotMove = new RobotMove();
        //1 1 1
        //1 1 1
        assertEquals(3, robotMove.movingCount2(2, 3, 1));
        assertEquals(2, robotMove.movingCount2(1, 2, 1));
        assertEquals(1, robotMove.movingCount2(1, 2, 0));
        assertEquals(1, robotMove.movingCount2(3, 1, 0));
        System.out.println(robotMove.getNumberSum(124));
    }

}