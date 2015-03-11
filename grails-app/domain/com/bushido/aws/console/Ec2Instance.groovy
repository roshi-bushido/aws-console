package com.bushido.aws.console

class Ec2Instance {
    String instanceId
    String amiId
    String state
    String keyName
    String platform
    String type
    String tags
    String securityGroups
    String nameTag
    String dueDateTag
    String ownerTag
    String purposeTag
    String creationDateTag

    Date   launchTime
    Date   lastModificationDate

    static constraints = {

    }

    static mapping = {
        table('instance')
        version(false)
        id                      (column: 'id', sqlType: "int")
        instanceId              (column: 'instance_id')
        amiId                   (column: 'ami_id')
        state                   (column: 'instance_state')
        keyName                 (column: 'key_name')
        platform                (column: 'platform')
        type                    (column: 'instance_type')
        tags                    (column: 'instance_tags', sqlType: "text")
        securityGroups          (column: 'instance_security_groups', sqlType: "text")
        nameTag                 (column: 'tag_name')
        dueDateTag              (column: 'tag_due_date')
        ownerTag                (column: 'tag_owner')
        purposeTag              (column: 'tag_type')
        creationDateTag         (column: 'tag_creation_date')
        launchTime              (column: 'instance_launch_time')
        lastModificationDate    (column: 'audit_last_modification_date')
    }
}
