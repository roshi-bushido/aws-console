package com.bushido.aws.console.services

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.ec2.AmazonEC2Client
import grails.transaction.Transactional

@Transactional
class CloudService {
    def amazonWebService

    public Boolean areValidCredentials(String clientId, String clientSecret) {
        try {
            AmazonEC2Client ec2 = new AmazonEC2Client(new BasicAWSCredentials(clientId, clientSecret))
            ec2.describeAccountAttributes();
            return true;
        } catch (Exception exception) {
            return false;
        }

    }
}
