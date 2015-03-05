package com.bushido.aws.console.services

import com.bushido.aws.console.Role
import com.bushido.aws.console.User
import com.bushido.aws.console.UserRole
import grails.transaction.Transactional

@Transactional
class UserService {

    public User createUserWith(String username) {
        User user = new User(username: username, hasAWSConfiguration: false)
        user = user.save(failOnError: true, flush: true)

        Role role = Role.findByName("ROLE_USER");
        UserRole userRole = new UserRole(user: user, role: role)
        userRole.save(failOnError: true, flush: true)
        return this.findUserById(user.id);
    }

    public User findUserById(Long id) {
        return User.findById(id);
    }

    public User findUserByUsername(String username) {
        return User.findByUsernameLike(username);
    }

}
