package com.bushido.aws.console

class ApprovedAMI {
    String name
    String awsInstanceId
    String securityGroup
    Boolean isWorkshop

    static constraints = {
        name(nullable: false, unique: true)
        awsInstanceId(nullable: false, unique: true)
        isWorkshop(nullable: false)
        securityGroup(nullable: false)
    }

    static mapping = {
        table("approved_amis")
        cache true
        version false
        name column: 'name', unique: true
        awsInstanceId column: 'aws_instance_id', unique: true
        securityGroup(column: 'aws_security_group')
        isWorkshop column: 'is_workshop', unique: false
    }
}