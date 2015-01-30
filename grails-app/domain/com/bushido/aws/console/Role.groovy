package com.bushido.aws.console

class Role {

	String authority

	static mapping = {
        table("Roles")
		cache true
        version false
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
