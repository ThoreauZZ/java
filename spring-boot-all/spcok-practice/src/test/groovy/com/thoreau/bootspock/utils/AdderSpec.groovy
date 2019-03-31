package com.thoreau.bootspock.utils

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 2019/3/28 11:53 PM.
 * @author zhaozhou
 */
class AdderSpec extends Specification {
    @Unroll
    // 可以直接这样注解("Adder test #first, #second and #sum (alt2)")
    def "Testing the Adder for #first + #second = #sum"() {
        given: "an adder"
        Adder adder = new Adder()

        expect: "that it calculates the sum of two numbers"
        adder.add(first, second) == sum

        where: "some scenarios are"
        first | second || sum
        1     | 1      || 2
        3     | 2      || 5
        82    | 16     || 98
        3     | -3     || 0
        0     | 0      || 0
    }
    @Unroll
    def "Testing2 the Adder for #first + #second < 0 "() {
        given: "a calculator"
        Adder calc = new Adder()

        expect: "that it calculates the sum of two numbers"
        calc.add(first,second) < 0

        where: "some scenarios are"
        first << (1..10)
        second << (-2..-11)
    }
    @Unroll
    def "Testing3 the Adder for #first + #second < 0 "() {
        given: "a calculator"
        Adder calc = new Adder()

        expect: "that it calculates the sum of two numbers"
        calc.add(first,second) < 0

        where: "some scenarios are"
        first << (1..10)
        second = -11
    }
    @Ignore// 和junit类似
    def "Testing the Adder for #first + #second = #sum "() {
        given: "an adder"
        Adder adder = new Adder()
        expect: "that it calculates the sum of two numbers"
        adder.add(first, second) == sum
        where: "some scenarios are"
        first                            | second                     || sum
        2 + 3                            | 10 - 2                     || new Integer(13).intValue()
        MyInteger.FIVE.getNumber()       | MyInteger.NINE.getNumber() || 14
        IntegerFactory.createFrom("two") | (7 - 2) * 2                || 12
        [1, 2, 3].get(1)                 | 3                          || IntegerFactory.createFrom("five")
        new Integer(5).intValue()        | new String("cat").size()   || MyInteger.EIGHT.getNumber()
        ["hello", "world"].size()        | 5                          || IntegerFactory.createFrom("seven")
    }
}
