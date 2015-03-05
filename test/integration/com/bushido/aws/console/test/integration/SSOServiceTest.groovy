package com.bushido.aws.console.test.integration

import grails.test.spock.IntegrationSpec

import static org.hamcrest.Matchers.equalTo
import static org.junit.Assert.assertThat

/**
 * Created by msuarez on 3/3/15.
 */
class SSOServiceTest extends IntegrationSpec {
    def SSOService

    def "should successfully login to sso" () {
        given:
            def username = "matias suarez"
            def password = "ndxpCaZK!"
        when:
            def isLoggedIn = this.SSOService.login(username, password)
        then:
            assertThat(isLoggedIn, equalTo(true))
    }

    def "should fail login to sso due to invalid credentials" () {
        given:
            def username = "matias suarez"
            def password = "Mule1379"
        when:
            def isLoggedIn = this.SSOService.login(username, password)
        then:
            assertThat(isLoggedIn, equalTo(false))
    }

}
