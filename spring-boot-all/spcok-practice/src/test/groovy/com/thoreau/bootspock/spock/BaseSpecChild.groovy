package com.thoreau.bootspock.spock

/**
 * 2019/3/31 6:08 PM.
 * @author zhaozhou
 */
class BaseSpecChild extends LifecycleBaseSpec {
    def setupSpec() {
        println "child setupSpec"
    }

    def setup() {
        println "child setup"
    }

    def "first feature being tested"() {
        expect: "trivial test"
        println "run test"
        2 == 1 + 1
    }

    def cleanup() {
        println "child cleanup"
    }

    def cleanupSpec() {
        println "child cleanupSpec"
    }
}