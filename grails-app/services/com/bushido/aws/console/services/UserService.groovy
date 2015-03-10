package com.bushido.aws.console.services

import com.bushido.aws.console.Role
import com.bushido.aws.console.User
import com.bushido.aws.console.UserRole
import grails.transaction.Transactional

@Transactional
class UserService {

    @Transactional(readOnly = false)
    public User createUserWith(String username) {
        Role role = Role.findByName("ROLE_USER");
        User user = new User(username: username, hasAWSConfiguration: false)
        user.roles.add(role)
        user = user.save(failOnError: true, flush: true)
        return this.findUserById(user.id);
    }

    public User findUserById(Long id) {
        return User.findById(id);
    }

    public User findUserByUsername(String username) {
        return User.findByUsernameLike(username);
    }

}
