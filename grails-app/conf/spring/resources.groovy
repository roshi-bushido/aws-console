import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.RedeliveryPolicy

// Place your Spring DSL code here
beans = {
    grailsApplication = ref('grailsApplication')

    jmsRedeliveryPolicy(RedeliveryPolicy){
        maximumRedeliveries = grailsApplication.config.grails.activemq.maxRetries
        initialRedeliveryDelay = grailsApplication.config.grails.activemq.retryDelay
        queue = "*"
    }
    jmsConnectionFactory(ActiveMQConnectionFactory) {
        brokerURL = grailsApplication.config.grails.activemq.url
        redeliveryPolicy = jmsRedeliveryPolicy
    }
}
