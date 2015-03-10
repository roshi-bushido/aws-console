package com.bushido.aws.console.app

import com.bushido.aws.console.ApprovedAMI
import com.bushido.aws.console.BaseController
import com.bushido.aws.console.InstanceType
import com.bushido.aws.console.Purpose
import com.bushido.aws.console.annotations.Secured
import com.bushido.aws.console.commands.instances.InstanceRequestCommand

@Secured(roles=['ROLE_USER'])
class InstanceController extends BaseController {
    def instanceService
    static allowedMethods = [createInstance: "POST", newInstance: "GET"]

    private static def getCreateInstanceModel() {
        def model = [
            types: InstanceType.findAll(),
            purposes: Purpose.findAll(),
            amis: ApprovedAMI.findAll(),
        ]
        return model
    }

    def newInstance() {
        def cmd = new InstanceRequestCommand()
        cmd.startingDate = Calendar.getInstance().getTime()
        cmd.withDomain = false
        cmd.withElasticIP = false

        def model = getCreateInstanceModel()
        model.put("instance", cmd)
        render(view: "create_instance", model: model)
        return
    }

    def createInstance(InstanceRequestCommand cmd) {
        if (cmd.hasErrors()) {
            def model = getCreateInstanceModel()
            model.put("instance", cmd)
            render(view: "create_instance", model: model)
            return
        }
        cmd.owner = this.getLoggedInUser()
        this.instanceService.processCreateRequest(cmd)
        redirect(controller: 'user', action: 'myInstances')
        return
    }

}
