package com.bushido.aws.console

class Role {
	String name

	static mapping = {
        table("roles")
		cache true
        version false
        name column: 'name', unique: true
	}

	static constraints = {
		name blank: false, unique: true
	}


    static Collection<Role> getRolesByName(List<String> roleNames) {
        return Role.findAllByNameInList(roleNames);
    }
}
