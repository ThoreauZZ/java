package com.thoreauz.bootlearn.utils

import spock.lang.Specification

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
}
