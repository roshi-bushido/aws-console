package integration

import grails.test.spock.IntegrationSpec

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat

/**
 * Created by msuarez on 12/11/14.
 */
class AwsTest extends IntegrationSpec {
    def amazonWebService

    def "should get all the users from aws" () {
        given:
            def iam = this.amazonWebService.getIam()
        when:
            def user = iam.getUser().getUser()
        then:
            assertThat(user, notNullValue())
            assertThat(user.getUserName(), equalTo("matias.suarez"))
    }

    def "should login successfully with my aws user" () {
        given:
            def iam = this.amazonWebService.getIam()
        when:
            def users = iam.listUsers()
        then:
            assertThat(users, notNullValue())
    }


}
