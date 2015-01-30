package com.bushido.aws.console

class UserRole implements Serializable {
	User user
	Role role

	static constraints = {
	}

	static mapping = {
        table("user_roles")
		id composite: ['role', 'user']
		version false
	}
}
