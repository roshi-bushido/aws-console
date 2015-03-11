package com.bushido.aws.console.domain

/**
 * Created by msuarez on 3/10/15.
 */
enum InstanceAction {
    CREATE("create"),
    STOP("stop"),
    TERMINATE("terminate")

    String name

    public InstanceAction(String name) {
        this.name = name
    }

}