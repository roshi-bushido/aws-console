package com.bushido.aws.console.commands.instances

import grails.validation.Validateable

/**
 * Created by msuarez on 3/4/15.
 */

@Validateable
class WorkshopRequest {
    String instanceId
    String sfdcOpportunityLink
    String namePreffix
    Date startingDate
    Date endingDate

    static constraints = {
    }

}

