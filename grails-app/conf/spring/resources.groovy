import org.apache.activemq.ActiveMQConnectionFactory

// Place your Spring DSL code here
beans = {
    jmsConnectionFactory(ActiveMQConnectionFactory) {
        brokerURL = 'vm://localhost'
    }
}
