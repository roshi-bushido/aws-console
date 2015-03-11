package com.bushido.aws.console

class Purpose {
    String name

    static constraints = {
        name(nullable: false, unique: true)
    }

    static mapping = {
        table("console_purposes")
        cache true
        version false
        name column: 'name', unique: true
    }

}
