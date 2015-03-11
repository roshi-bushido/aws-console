package com.bushido.aws.console

import com.bushido.aws.console.domain.InstanceState

class RegularInstance {
    Purpose purpose
    InstanceType type
    ApprovedAMI ami
    User owner
    InstanceState state

    String name
    String description
    String sfdcOpportunityLink

    String domain
    Boolean withElasticIP
    Boolean withDomain

    Date startingDate
    Date endingDate
    Date terminationDate

    Date dateCreated
    Date lastUpdated

    static constraints = {
        purpose(nullable: false)
        type(nullable: false)
        ami(nullable: false)
        owner(nullable: false)
        withElasticIP(nullable: false)
        withDomain(nullable: false)
        domain(nullable: true, url: true)
        name(nullable: false)
        state(nullable: false)
        description(nullable: true)
        sfdcOpportunityLink(nullable: true)
        startingDate(nullable: false)
        endingDate(nullable: false)
        terminationDate(nullable: false)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    }

    static mapping = {
        table(name: 'console_regular_instances')
        version(false)
        cache(true)
        purpose(column: 'purpose_id')
        type(column: 'instance_type_id')
        ami(column: 'approved_ami_id')
        owner(column: 'owner_id')
        withElasticIP(column: 'has_eip')
        withDomain(column: 'has_domain')
        domain(column: 'domain')
        name(column: 'name')
        state(column: 'state')
        description(column: 'description')
        sfdcOpportunityLink(column: 'sfdc_url')
        startingDate(column: 'date_start')
        endingDate(column: 'date_stop')
        terminationDate(column: 'date_terminate')
        dateCreated(column: 'date_creation')
        lastUpdated(column: 'date_last_modification')
    }
}