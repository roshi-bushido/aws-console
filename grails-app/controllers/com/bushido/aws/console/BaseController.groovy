package com.bushido.aws.console

import com.google.gson.GsonBuilder

/**
 * Created by msuarez on 12/10/14.
 */
class BaseController {
    private def gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create()

    protected def createUserSessionWith(User user) {
        request.getSession(true)
        this.updateUserSessionWith(user)
    }

    protected void updateUserSessionWith(User user) {
        session['user'] = user
    }

    public void renderJSON(Object o) {
        render(text: gson.toJson(o), contentType: "application/json")
    }

    protected User getLoggedInUser() {
        return (User) session['user']
    }
}
