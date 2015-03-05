package com.bushido.aws.console.commands.instances

import grails.validation.Validateable

@Validateable
class InstanceRequest {
    String purpose
    String instanceId
    Integer amount
    Boolean withElasticIP
    Boolean withDomain
    String domain
    String type
    Date startingDate
    Date endingDate
    String name
    String description
    String sfdcOpportunityLink

    static constraints = {
        domain(nullable: true, blank: true, url: true)
    }

}

