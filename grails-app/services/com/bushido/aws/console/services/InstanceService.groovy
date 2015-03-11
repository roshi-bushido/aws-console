package com.bushido.aws.console.services

import com.bushido.aws.console.ApprovedAMI
import com.bushido.aws.console.InstanceType
import com.bushido.aws.console.Purpose
import com.bushido.aws.console.RegularInstance
import com.bushido.aws.console.commands.instances.InstanceRequestCommand
import com.bushido.aws.console.domain.InstanceAction
import com.bushido.aws.console.domain.InstanceState
import grails.transaction.Transactional
import org.apache.activemq.command.ActiveMQObjectMessage

import javax.jms.Message
import java.text.SimpleDateFormat

@Transactional
class InstanceService {
    def dateFormatter = new SimpleDateFormat("MM/dd/yyyy")
    def jmsService

    def processCreateRequest(InstanceRequestCommand request) {
        def instances = new ArrayList<RegularInstance>()

        for (def i=0; i < request.amount; i++) {
            RegularInstance instance = new RegularInstance()
            instance.state = InstanceState.PENDING
            instance.purpose = Purpose.load(request.purpose)
            instance.type = InstanceType.load(request.type)
            instance.ami = ApprovedAMI.load(request.amiId)
            instance.owner = request.owner
            instance.name = request.name
            instance.description = request.description
            instance.sfdcOpportunityLink = request.sfdcOpportunityLink
            instance.domain = request.domain
            instance.withElasticIP = Boolean.valueOf(request.withElasticIP)
            instance.withDomain = Boolean.valueOf(request.withDomain)
            instance.startingDate = this.dateFormatter.parse(request.startingDate)
            instance.endingDate = this.dateFormatter.parse(request.endingDate)
            instance.terminationDate = instance.endingDate
            instance.save(failOnError: true, flush: true)
            instances.add(instance)

            Message message = new ActiveMQObjectMessage();
            message.setJMSPriority(instance.id);
            message.setObject([regularInstanceId: instance.id, action: InstanceAction.CREATE.name])

            jmsService.send(queue: "aws_create_queue", message)
        }
        return instances;
    }
}
