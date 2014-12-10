package unit

import spock.lang.Specification

import static org.junit.Assert.assertThat
import static org.hamcrest.Matchers.equalTo

/**
 * Created by msuarez on 12/10/14.
 */

class SampleUnitTest extends Specification {

    def "should pass because is a simple test" () {
        given:
            def application = "application"
        when:
            def wasSuccessful = application.isEmpty()
        then:
            assertThat(wasSuccessful, equalTo(false))
    }
}
