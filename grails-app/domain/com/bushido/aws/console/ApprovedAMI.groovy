package com.bushido.aws.console

class ApprovedAMI {
    String name
    String awsInstanceId
    Boolean isWorkshop

    static constraints = {
        name(nullable: false, unique: true)
        awsInstanceId(nullable: false, unique: true)
        isWorkshop(nullable: false)
    }

    static mapping = {
        table("approved_amis")
        cache true
        version false
        name column: 'name', unique: true
        awsInstanceId column: 'aws_instance_id', unique: true
        isWorkshop column: 'is_workshop', unique: false
    }
}