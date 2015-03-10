package com.bushido.aws.console

class User {
	String username
    String awsClientId
    String awsClientSecret
	boolean hasAWSConfiguration = false

    Set roles = []

    static hasMany = [roles:Role]

	static constraints = {
		username blank: false, unique: true, nullable: false
        awsClientId unique: true, nullable: true
        awsClientSecret nullable: true
	}

	static mapping = {
        table("users")
        version false
        username            column: 'username'
        awsClientId         column: 'aws_client_id'
        awsClientSecret     column: 'aws_client_secret'
        hasAWSConfiguration column: 'has_aws_configuration'
        roles               lazy: false,  joinTable: [name: "user_roles", key: "user_id", column: "role_id"]
	}

    def isAdmin() {
        roles.each {role ->
            if ('ROLE_ADMIN'.equalsIgnoreCase(role.name)) {
                return true
            }
        }
    }
}
