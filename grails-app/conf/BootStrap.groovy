import com.bushido.aws.console.Role
import com.bushido.aws.console.User
import com.bushido.aws.console.UserRole

class BootStrap {

    def init = { servletContext ->
//        def adminRole = new Role(name: "ROLE_ADMIN").save(failOnError: true)
//        def userRole = new Role(name: "ROLE_USER").save(failOnError: true)
//
//        def user = new User(username: "admin", password: "admin", hasAWSConfiguration: "false")
//
//        new UserRole(user: user, role: adminRole).save(failOnError: true)
//        new UserRole(user: user, role: userRole).save(failOnError: true)


    }

    def destroy = {
    }
}
