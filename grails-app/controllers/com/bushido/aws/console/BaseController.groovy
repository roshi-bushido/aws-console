package com.bushido.aws.console

import com.google.gson.GsonBuilder

/**
 * Created by msuarez on 12/10/14.
 */
class BaseController {
    private def gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create()

    public void renderJSON(Object o) {
        render(text: gson.toJson(o), contentType: "application/json")
    }
}
