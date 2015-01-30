package aws.console

import com.bushido.aws.console.Role
import com.bushido.aws.console.User
import com.bushido.aws.console.UserRole
import com.bushido.aws.console.annotations.Secured
import org.apache.commons.lang.WordUtils

class UserRoleFilters {
    def grailsApplication

    private Object getControllerClassByName(controllerName) {
        def controller = null
        grailsApplication.getControllerClasses().each { c ->
            if (c.name.equalsIgnoreCase(WordUtils.capitalize(controllerName))) {
                controller = c.getClazz()
            }
        }
        return controller;
    }

	def filters = {
		all(controller:'*', action:'*') {
			before = {
                def controller = getControllerClassByName(controllerName)
                def controllerAction = (actionName == null) ? "index" : actionName;
                def securedByController = controller.isAnnotationPresent(Secured)
                def securedByAction = controller.getDeclaredMethod(controllerAction).isAnnotationPresent(Secured)
                def securedAtAll = securedByController || securedByAction

                if (securedAtAll) {
                    def roles = []
                    def isAuthorized = true

                    if (securedByAction) {
                        // method annotation always has precedence over controller annotation
                        def annotation = controller.getDeclaredMethod(controllerAction).getAnnotation(Secured)
                        roles.addAll(annotation.roles())
                    }
                    if (securedByController) {
                        def annotation = controller.getAnnotation(Secured)
                        roles.addAll(annotation.roles())
                    }

                    def domainRoles = Role.getRolesByName(roles);

                    if ( request.getSession()?.getAttribute("user") ) {
                        def user = (User)request.getSession().getAttribute("user")
                        def userRoles = UserRole.findAllByUser(user)
                        def userRolesNames = userRoles.collectNested { it.role.name }

                        domainRoles.each { domainRole ->
                            if (!userRolesNames.contains(domainRole.getName())) {
                                isAuthorized = false
                            }
                        }

                        if (!isAuthorized) {
                            redirect(controller: "login", action: "accessDenied")
                        }
                    } else {
                        redirect(controller: "login", action: "index")
                    }
                }
			}
			after = { Map model ->
				
			}
			afterView = { Exception e ->
				
			}
		}
	}
}
