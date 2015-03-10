
package com.bushido.aws.console.services

import grails.transaction.Transactional
import org.apache.directory.api.ldap.model.exception.LdapException
import org.apache.directory.ldap.client.api.LdapConnection
import org.apache.directory.ldap.client.api.LdapNetworkConnection

@Transactional
class SSOService {
    def grailsApplication
    private Integer port
    private String host
    private String baseCN

    String getBaseCN() {
        if (this.baseCN == null) {
            this.baseCN = grailsApplication.config.grails.ldap.baseCN
        }
        return this.baseCN

    }

    Integer getPort() {
        if (this.port == null) {
            this.port = Integer.valueOf(grailsApplication.config.grails.ldap.port)
        }
        return this.port
    }

    String getHost() {
        if (this.host == null) {
            this.host = grailsApplication.config.grails.ldap.host
        }
        return this.host
    }

    def login(String username, String password) {
        try {
            def ldapUsername = "CN=$username," + this.getBaseCN()
            LdapConnection connection = new LdapNetworkConnection(this.getHost(), this.getPort());
            connection.bind(ldapUsername, password)
            if (connection.isAuthenticated()) {
                def entry = connection.lookup(ldapUsername)
                connection.unBind();
                connection.close();
                return entry
            } else {
                return null
            }
        } catch (LdapException e) {
            return null
        }
    }

}
