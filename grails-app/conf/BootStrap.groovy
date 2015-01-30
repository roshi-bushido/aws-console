import com.bushido.aws.console.Role
import com.bushido.aws.console.User
import com.bushido.aws.console.UserRole

class BootStrap {

    def init = { servletContext ->
//        def testUser = new User(username: 'admin', enabled: true, password: 'admin').save(failOnError: true);
//        def adminRole = Role.findById(1l);
//
//        def r = new UserRole(user: testUser, role: adminRole).save(failOnError: true);
    }
    def destroy = {
    }
}
