import org.apache.activemq.ActiveMQConnectionFactory

// Place your Spring DSL code here
beans = {
    grailsApplication = ref('grailsApplication')

    jmsConnectionFactory(ActiveMQConnectionFactory) {
        brokerURL = grailsApplication.config.grails.activemq.url
    }
}
