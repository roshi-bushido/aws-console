package com.bushido.aws.console.test.integration.domain

import com.bushido.aws.console.Purpose
import grails.test.spock.IntegrationSpec

import static org.hamcrest.Matchers.greaterThan
import static org.hamcrest.Matchers.notNullValue
import static org.junit.Assert.assertThat

/**
 * Created by msuarez on 12/11/14.
 */
class DatabaseTest extends IntegrationSpec {

    def "should start container ok" () {
        given:
        when:
            def purposes = Purpose.findAll()
        then:
            assertThat(purposes, notNullValue())
            assertThat(purposes.size(), greaterThan(0))
    }


}
