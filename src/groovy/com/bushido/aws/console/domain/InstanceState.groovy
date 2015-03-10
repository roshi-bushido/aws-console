package com.bushido.aws.console.domain

/**
 * Created by msuarez on 3/10/15.
 */
enum InstanceState {
    PENDING("Pending"),
    RUNNING("Running"),
    STOPPED("Stopped"),
    TERMINATED("Terminated")

    String name

    public InstanceState(String name) {
        this.name = name
    }

    public static List<String> asStringList() {
        return Arrays.asList(PENDING.name, RUNNING.name, STOPPED.name, TERMINATED.name)
    }
}