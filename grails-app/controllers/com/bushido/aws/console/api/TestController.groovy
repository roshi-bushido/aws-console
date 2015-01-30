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

    def login() {
        def user = User.findByUsername("admin")
        request.getSession(true).setAttribute("user", user)
    }

    def user() {
        def iam = this.amazonWebService.getIam()
        renderJSON(iam.getUser())
    }
}
