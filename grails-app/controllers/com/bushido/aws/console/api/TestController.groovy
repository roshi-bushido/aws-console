package com.bushido.aws.console.api

import com.bushido.aws.console.BaseController
import com.bushido.aws.console.User
import com.bushido.aws.console.annotations.Secured

class TestController extends BaseController {
    def amazonWebService

    @Secured(roles=['ROLE_ADMIN'])
    def index() {
        def ec2 = this.amazonWebService.getEc2()
        def instanceList = new ArrayList()
        ec2.describeInstances().getReservations().each { reservation ->
            instanceList.addAll(reservation.getInstances())
        }
        renderJSON(instanceList)
    }
}
