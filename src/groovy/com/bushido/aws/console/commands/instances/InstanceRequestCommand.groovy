package com.bushido.aws.console.commands.instances

import com.bushido.aws.console.User
import grails.validation.Validateable

@Validateable
class InstanceRequestCommand {
    Integer purpose
    Integer amiId
    Integer type
    Integer amount
    Boolean withElasticIP
    Boolean withDomain
    String domain
    String startingDate
    String endingDate
    String name
    String description
    String sfdcOpportunityLink
    User owner

    static constraints = {
        withElasticIP(nullable: true)
        withDomain(nullable: true)
        domain(nullable: true, blank: true, url: true)
        purpose(nullable: false)
        amiId(nullable: false, blank: false)
        amount(nullable: false, min: 1, max: 100)
        name(nullable: false, minSize: 5, maxSize: 255)
        sfdcOpportunityLink(nullable: true, url: true)
        startingDate(nullable: false)
        endingDate(nullable: false)
        owner(nullable: true)
    }

}

