package aws.console

import grails.test.spock.IntegrationSpec

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat

class Ec2InstanceSpec extends IntegrationSpec {

	public void "test something"() {
		when:
			def instances = Ec2Instance.findAll()
		then:
			assertThat(instances, notNullValue())
	}
}
