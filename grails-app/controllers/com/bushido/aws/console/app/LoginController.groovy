package com.bushido.aws.console.app

import com.bushido.aws.console.BaseController
import com.bushido.aws.console.User

class LoginController extends BaseController {
    def SSOService
    def userService

	def index() {
        User user = (User) getLoggedInUser()
        if (user != null) {
            forward(controller: "user", action: "index")
            return
        } else {
            def loginModel = [username: "", badCredentials: false]
            render (view: "index", model: loginModel)
            return
        }
	}

    def accessDenied() {
        render (view: "access_denied")
    }

    def authenticate() {
        String username = params._username
        String password = params._password
        def ssoUser = this.SSOService.login(username, password)
        if (ssoUser != null) {
            def consoleUser = this.userService.findUserByUsername(username)
            if (consoleUser == null) {
                consoleUser = this.userService.createUserWith(username)
            }
            this.createUserSessionWith(consoleUser)
            redirect(controller: "user", action: "index")
            return
        } else {
            def loginModel = [username: username, badCredentials: true]
            render(view: "index", model: loginModel)
            return
        }
    }
}
