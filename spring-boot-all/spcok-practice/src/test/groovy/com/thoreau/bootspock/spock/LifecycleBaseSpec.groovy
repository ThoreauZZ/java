package com.thoreau.bootspock.spock;

import spock.lang.Specification;

/**
 * 2019/3/29 8:58 PM.
 *
 * @author zhaozhou
 */
class LifecycleBaseSpec extends Specification {
    def setupSpec() {
        println "super setupSpec "
    }

    def setup() {
        println "super setup"
    }
}
