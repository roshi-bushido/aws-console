package com.bushido.aws.console.app

import com.bushido.aws.console.BaseController
import com.bushido.aws.console.User
import com.bushido.aws.console.annotations.Secured


@Secured(roles=['ROLE_USER'])
class UserController extends BaseController {
    def cloudService
    static allowedMethods = [updateAwsConfiguration: "POST", index: "GET"]

    def index() {
        User user = this.getLoggedInUser()
        if (!user.getHasAWSConfiguration()) {
            def model = [clientId: '', clientSecret:''];
            render(view: "aws_configuration_update", model: model)
        } else {
            redirect(action: 'myInstances')
            return
        }
    }

    def myInstances() {
        def myInstancesModel = [instanceList: []]
        render(view: "my_instances", model: myInstancesModel)
        return
    }

    def myWorkshops() {
        def myInstancesModel = [instanceList: []]
        render(view: "my_workshops", model: myInstancesModel)
        return
    }

    def myDashboard() {
        def myInstancesModel = [instanceList: []]
        render(view: "my_dashboard", model: myInstancesModel)
        return
    }

    def updateAwsConfiguration() {
        User user = this.getLoggedInUser()
        user.awsClientId = params.clientId
        user.awsClientSecret = params.clientSecret
        user.hasAWSConfiguration = true
        user.merge(flush: true)
        this.updateUserSessionWith(user)

        forward(action: 'myInstances')
        return
    }

    def testConnection() {
        def areValidCredentials = this.cloudService.areValidCredentials(params.clientId, params.clientSecret)
        def testConnectionModel = [validCredentials : areValidCredentials]
        renderJSON(testConnectionModel)
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

}
