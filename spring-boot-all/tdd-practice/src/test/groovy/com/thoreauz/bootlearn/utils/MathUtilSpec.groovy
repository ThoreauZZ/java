package com.thoreauz.bootlearn.utils

import spock.lang.Specification
import spock.lang.Unroll

/**
 * 2019/3/24 11:24 AM.
 * @author zhaozhou
 */

class MathUtilSpec extends Specification {
    def "maximum of two numbers"() {
        expect:
        MathUtil.maxLong(a as long, b as long) == c

        where:
        a | b   || c
        1 | 2   || 2
        -1| 0   || 0
        200|-22 || 200
    }
    @Unroll
    def "minimum of #a and #b is #c"() {
        expect:
        MathUtil.min(a, b) == c

        where:
        a | b || c
        3 | 7 || 3
        5 | 4 || 4
        9 | 9 || 9
    }
}
