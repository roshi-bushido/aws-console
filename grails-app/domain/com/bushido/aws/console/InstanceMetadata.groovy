package com.bushido.aws.console

class InstanceMetadata {
    String awsInstanceId
    String publicIP
    String privateIP
    String internalDNS

    static constraints = {
        publicIP(nullable: true)
        privateIP(nullable: true)
        internalDNS(nullable: true)
        awsInstanceId(nullable: false)
    }

    static mapping = {
        table("console_instance_metadata")
        cache true
        version false
        awsInstanceId column: 'aws_instance_id', unique: true
        publicIP column: 'public_ip_address', unique: true
        privateIP column: 'private_ip_address', unique: true
        internalDNS column: 'internal_dns', unique: true
    }

}
