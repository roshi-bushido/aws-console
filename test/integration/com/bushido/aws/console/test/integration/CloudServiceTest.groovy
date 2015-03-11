package com.bushido.aws.console.test.integration

import com.bushido.aws.console.RegularInstance
import com.bushido.aws.console.domain.InstanceState
import grails.test.spock.IntegrationSpec
import org.junit.Ignore

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.notNullValue
import static org.junit.Assert.assertThat

/**
 * Created by msuarez on 3/3/15.
 */
class CloudServiceTest extends IntegrationSpec {
    def cloudService


    def "should create an aws instance" () {
        given:
            def regularInstance = RegularInstance.load(1)
            assertThat(regularInstance.state, equalTo(InstanceState.PENDING))
        when:
            def result = this.cloudService.createEC2InstanceFrom(regularInstance)
            def updateRegularInstance = RegularInstance.load(1)
        then:
            assertThat(result, notNullValue())
            assertThat(updateRegularInstance.state, equalTo(InstanceState.RUNNING))
    }

}
