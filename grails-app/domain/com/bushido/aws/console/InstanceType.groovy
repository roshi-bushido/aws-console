package com.bushido.aws.console

class InstanceType {
    String name
    String awsInstanceType

    static constraints = {
        name(nullable: false, unique: true)
        awsInstanceType(nullable: false, unique: true)
    }

    static mapping = {
        table("console_instance_types")
        cache true
        version false
        name column: 'name', unique: true, length: 255
        awsInstanceType column: 'aws_instance_type', unique: true, length: 255
    }
}
