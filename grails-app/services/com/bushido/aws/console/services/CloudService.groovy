package com.bushido.aws.console.services

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model.CreateTagsRequest
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import com.amazonaws.services.ec2.model.DescribeInstancesResult
import com.amazonaws.services.ec2.model.Instance
import com.amazonaws.services.ec2.model.RunInstancesRequest
import com.amazonaws.services.ec2.model.RunInstancesResult
import com.amazonaws.services.ec2.model.Tag
import com.bushido.aws.console.Ec2Instance
import com.bushido.aws.console.InstanceMetadata
import com.bushido.aws.console.RegularInstance
import com.bushido.aws.console.User
import com.bushido.aws.console.domain.InstanceState
import com.google.gson.GsonBuilder
import grails.transaction.Transactional

import java.text.SimpleDateFormat

class CloudService {
    def amazonWebService
    def simpleDateFormatter = new SimpleDateFormat("yyyyMMdd");

    public Boolean areValidCredentials(String clientId, String clientSecret) {
        try {
            AmazonEC2Client ec2 = createClientWith(clientId, clientSecret)
            ec2.describeAccountAttributes();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public RegularInstance createEC2InstanceFrom(RegularInstance instance) {
        AmazonEC2Client ec2 = createClientWith(instance.owner.awsClientId, instance.owner.awsClientSecret)
        Instance createdInstance = null

        if (instance?.instanceMetadata?.awsInstanceId != null && !instance?.instanceMetadata?.awsInstanceId.isEmpty()) {
            createdInstance = this.getAWSInstanceById(ec2, instance.instanceMetadata.awsInstanceId)
        } else {
            def runInstanceRequest = new RunInstancesRequest(
                    imageId: instance.ami.awsInstanceId,
                    minCount: 1,
                    maxCount: 1,
                    instanceType: instance.type.awsInstanceType,
                    securityGroups: Arrays.asList(instance.ami.securityGroup)
            );
            RunInstancesResult result = ec2.runInstances(runInstanceRequest);
            createdInstance = result.reservation.instances.first()
        }

        def instanceMetadata = new InstanceMetadata(awsInstanceId: createdInstance.instanceId).save(flush: true, failOnError: true)
        instance.instanceMetadata = instanceMetadata
        instance.save(flush: true, failOnError: true)


        // add the tags to the instance
        def createTagRequest = new CreateTagsRequest(
                resources: Arrays.asList(createdInstance.instanceId),
                tags: Arrays.asList(
                        new Tag(key: "Name", value: instance.name),
                        new Tag(key: "Creation", value: simpleDateFormatter.format(instance.startingDate)),
                        new Tag(key: "Due", value: simpleDateFormatter.format(instance.endingDate)),
                        new Tag(key: "Owner", value: instance.owner.username),
                        new Tag(key: "Type", value: instance.type.name)
                )
        )
        ec2.createTags(createTagRequest);

        def done = false;
        def maxWaits = 10;
        def waits = 0;

        while (!done && (waits < maxWaits)) {
            def describeInstanceRequest = new DescribeInstanceStatusRequest(instanceIds: Arrays.asList(createdInstance.instanceId))
            DescribeInstanceStatusResult instanceResult = ec2.describeInstanceStatus(describeInstanceRequest)

            def containsStatus = instanceResult.instanceStatuses.isEmpty()
            def isFullyCreated = !containsStatus
            if (isFullyCreated) {
                def recentInstanceStatus = instanceResult.instanceStatuses.first()
                isFullyCreated = recentInstanceStatus.systemStatus.status.equalsIgnoreCase("ok") && recentInstanceStatus.instanceStatus.status.equalsIgnoreCase("ok");
            }

            if (isFullyCreated) {
                def awsInstance = this.getAWSInstanceById(ec2, createdInstance.instanceId)
                this.createEc2Instance(awsInstance)

                instanceMetadata.publicIP = awsInstance.publicIpAddress
                instanceMetadata.privateIP = awsInstance.privateIpAddress
                instanceMetadata.internalDNS = awsInstance.publicDnsName
                instanceMetadata.save(flush: true, failOnError: true)

                instance.state = InstanceState.RUNNING
                instance.save(flush: true, failOnError: true)
                done = true
            } else {
                waits += 1;
                Thread.sleep(2* 60 * 1000)
            }
        }
        return instance;
    }

    static AmazonEC2Client createClientWith(String clientId, String clientSecret) {
        return new AmazonEC2Client(new BasicAWSCredentials(clientId, clientSecret))
    }

    public Instance getAWSInstanceById(AmazonEC2Client ec2, String instanceId) {
        def describeInstanceRequest = new DescribeInstancesRequest(instanceIds: Arrays.asList(instanceId))
        DescribeInstancesResult instanceResult = ec2.describeInstances(describeInstanceRequest)
        return instanceResult.reservations.first().instances.first()
    }

    public Ec2Instance createEc2Instance(Instance ec2) {
        def jsonTransformer = new GsonBuilder().serializeNulls().create()

        def instance = new Ec2Instance (
            instanceId: ec2.instanceId,
            amiId: ec2.imageId,
            state: ec2.state.name.toLowerCase(),
            keyName: " ",
            platform: ec2.platform,
            type: ec2.instanceType,
            securityGroups: jsonTransformer.toJson(ec2.securityGroups).toLowerCase(),
            launchTime: ec2.launchTime,
            lastModificationDate: Calendar.getInstance().getTime(),
            tags: jsonTransformer.toJson(ec2.tags),
            nameTag: ec2.tags.find { it.key.toLowerCase().equals("name") }.value,
            creationDateTag: ec2.tags.find { it.key.toLowerCase().equals("creation") }.value,
            dueDateTag: ec2.tags.find { it.key.toLowerCase().equals("due") }.value,
            ownerTag: ec2.tags.find { it.key.toLowerCase().equals("owner") }.value,
            purposeTag: ec2.tags.find { it.key.toLowerCase().equals("type") }.value,
        ).save(flush: true, failOnError: true)
        return instance;

    }
}
