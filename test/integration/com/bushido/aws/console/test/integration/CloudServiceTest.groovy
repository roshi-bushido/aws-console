package com.bushido.aws.console.test.integration

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import com.amazonaws.services.ec2.model.DescribeInstancesResult
import com.bushido.aws.console.InstanceMetadata
import com.bushido.aws.console.RegularInstance
import com.bushido.aws.console.domain.InstanceState
import grails.test.spock.IntegrationSpec
import org.junit.Ignore

import static org.hamcrest.Matchers.equalTo
import static org.junit.Assert.assertThat

/**
 * Created by msuarez on 3/3/15.
 */
class CloudServiceTest extends IntegrationSpec {
    def cloudService
    static transactional = false

    def "should create an aws instance" () {
        given:
            def regularInstance = RegularInstance.load(1)
            assertThat(regularInstance.state, equalTo(InstanceState.PENDING))
        when:
            def updateRegularInstance = this.cloudService.createEC2InstanceFrom(regularInstance)
        then:
            assertThat(updateRegularInstance.state, equalTo(InstanceState.RUNNING))
    }

//    def "should update regular instance with metadata" () {
//        given:
//            def regularInstance = RegularInstance.load(1)
//        when:
//            def metadata = new InstanceMetadata(
//                    publicIP: "1.1.1.1",
//                    privateIP: "1.1.1.1",
//                    internalDNS: "www.google.com"
//            ).save(flush: true, failOnError: true)
//            regularInstance.instanceMetadata = metadata
//            regularInstance.save(flush: true, failOnError: true)
//        then:
//            assertThat(regularInstance.instanceMetadata, notNullValue())
//    }

//    def "should get instance status checks" () {
//        given:
//            def instanceId = "i-fa498c0a"
//            def regularInstance = RegularInstance.load(1)
//            AmazonEC2Client ec2 = new AmazonEC2Client(new BasicAWSCredentials(regularInstance.owner.awsClientId, regularInstance.owner.awsClientSecret))
//        when:
//            def describeInstanceRequest = new DescribeInstanceStatusRequest(instanceIds: Arrays.asList(instanceId))
//            DescribeInstanceStatusResult instanceResult = ec2.describeInstanceStatus(describeInstanceRequest)
//            instanceResult.instanceStatuses.first()
//        then:
//            assertThat(instanceResult, notNullValue())
//    }

//    def "should get instance detail" () {
//        given:
//            def instanceId = "i-fa498c0a"
//            def regularInstance = RegularInstance.load(1)
//            AmazonEC2Client ec2 = new AmazonEC2Client(new BasicAWSCredentials(regularInstance.owner.awsClientId, regularInstance.owner.awsClientSecret))
//        when:
//            def describeInstanceRequest = new DescribeInstancesRequest(instanceIds: Arrays.asList(instanceId))
//            DescribeInstancesResult instanceResult = ec2.describeInstances(describeInstanceRequest)
//            def awsInstance = instanceResult.reservations.first().instances.first()
//        then:
//            def metadata = new InstanceMetadata (
//                    publicIP: awsInstance.publicIpAddress,
//                    privateIP: awsInstance.privateIpAddress,
//                    internalDNS: awsInstance.publicDnsName
//            ).save(flush: true, failOnError: true)
//            assertThat(metadata.id, notNullValue())
//    }

}
