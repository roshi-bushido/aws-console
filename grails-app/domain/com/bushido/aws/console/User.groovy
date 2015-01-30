package com.bushido.aws.console

class User {
	String username
	String password
    String awsClientId
    String awsClientSecret
    String ldapUserId
	boolean hasAWSConfiguration = false

	static constraints = {
		username blank: false, unique: true, nullable: false
		password blank: false, nullable: false
        awsClientId unique: true, nullable: true
        awsClientSecret nullable: true
        ldapUserId unique: true, nullable: true
	}

	static mapping = {
        table("users")
        version false
        username            column: 'username'
		password            column: 'password'
        ldapUserId          column: 'ldap_user_id'
        awsClientId         column: 'aws_client_id'
        awsClientSecret     column: 'aws_client_secret'
        hasAWSConfiguration column: 'has_aws_configuration'
	}

}
