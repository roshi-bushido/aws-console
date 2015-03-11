package com.bushido.aws.console.services

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model.CreateTagsRequest
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import com.amazonaws.services.ec2.model.DescribeInstancesResult
import com.amazonaws.services.ec2.model.Instance
import com.amazonaws.services.ec2.model.RunInstancesRequest
import com.amazonaws.services.ec2.model.RunInstancesResult
import com.amazonaws.services.ec2.model.Tag
import com.bushido.aws.console.RegularInstance
import com.bushido.aws.console.domain.InstanceState
import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class CloudService {
    def amazonWebService
    def simpleDateFormatter = new SimpleDateFormat("yyyyMMdd");

    public Boolean areValidCredentials(String clientId, String clientSecret) {
        try {
            AmazonEC2Client ec2 = new AmazonEC2Client(new BasicAWSCredentials(clientId, clientSecret))
            ec2.describeAccountAttributes();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Object createEC2InstanceFrom(RegularInstance instance) {
        def clientId = instance.owner.awsClientId
        def clientSecret= instance.owner.awsClientSecret
        AmazonEC2Client ec2 = new AmazonEC2Client(new BasicAWSCredentials(clientId, clientSecret))

        def runInstanceRequest = new RunInstancesRequest(
                imageId: instance.ami.awsInstanceId,
                minCount: 1,
                maxCount: 1,
                instanceType: instance.type.awsInstanceType,
                securityGroups: Arrays.asList(instance.ami.securityGroup)
        );
        RunInstancesResult result = ec2.runInstances(runInstanceRequest);
        Instance createdInstance = result.reservation.instances.first()

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

        while (!done && (maxWaits < waits)) {
            def describeInstanceRequest = new DescribeInstancesRequest(instanceIds: Arrays.asList(createdInstance.instanceId))
            DescribeInstancesResult instanceResult = ec2.describeInstances(describeInstanceRequest)
            def recentInstanceStatus = instanceResult.reservations.first().instances.first()
            if (!recentInstanceStatus.state.name.equalsIgnoreCase(InstanceState.RUNNING.name)) {
                done = true
            } else {
                waits += 1;
                Thread.sleep(1* 60 * 60)
            }
        }

        return true;
    }
}
