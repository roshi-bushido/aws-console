package com.bushido.aws.console.services

import com.bushido.aws.console.RegularInstance
import grails.plugin.jms.*

class AwsWorkerService {
    static exposes = ["jms"]

    def cloudService

//    @Queue(name = "aws_create_queue")
//    def createInstance(message) {
//        def regularInstance = RegularInstance.load(message.regularInstanceId)
//        this.cloudService.createEC2InstanceFrom(regularInstance)
//        print(message)
//    }
}

