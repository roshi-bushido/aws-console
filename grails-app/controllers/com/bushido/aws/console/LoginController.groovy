package com.bushido.aws.console

class LoginController extends BaseController {

	def index() {
        renderJSON("login index")
	}

    def accessDenied() {
        renderJSON("access denied")
    }

    def authenticate() {
        renderJSON("authenticate")
    }
}
