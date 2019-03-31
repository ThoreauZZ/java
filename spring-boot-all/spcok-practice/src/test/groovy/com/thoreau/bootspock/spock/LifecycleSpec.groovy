package com.thoreau.bootspock.spock;

import spock.lang.Specification;

/**
 * 2019/3/29 8:58 PM.
 *
 * @author zhaozhou
 */
class LifecycleSpec extends Specification {
    def setupSpec() {
        println "Will run only once"
    }
    def setup() {
        println "Will run before EACH feature"
    }
    def "first feature being tested"() {
        expect: "trivial test"
        println "first feature runs"
        2 == 1 +1
    }
    def "second feature being tested"() {
        expect: "trivial test"
        println "second feature runs"
        5 == 3 +2
    }
    def cleanup() {
        println "Will run once after EACH feature"
    }
    def cleanupSpec() {
        println "Will run once at the end"
    }
}
