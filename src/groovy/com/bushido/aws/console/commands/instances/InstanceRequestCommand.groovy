package com.bushido.aws.console.commands.instances

import grails.validation.Validateable

@Validateable
class InstanceRequestCommand {
    String purpose
    String amiId
    Integer amount
    Boolean withElasticIP
    Boolean withDomain
    String domain
    String type
    String startingDate
    String endingDate
    String name
    String description
    String sfdcOpportunityLink

    static constraints = {
        domain(nullable: true, blank: true, url: true)
    }

}

