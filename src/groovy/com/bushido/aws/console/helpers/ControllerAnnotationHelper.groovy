package com.bushido.aws.console.helpers

import com.bushido.aws.console.annotations.Secured
import java.lang.annotation.Annotation
import org.apache.commons.lang.WordUtils

/**
 * Created by msuarez on 1/30/15.
 */
class ControllerAnnotationHelper {
    private static Map<String, Map<String, List<Class>>> _actionMap = [:]
    private static Map<String, Class> _controllerAnnotationMap = [:]

    /**
     * Find controller annotation information. Called by BootStrap.init().
     */
    static void init(grailsApplication) {
        grailsApplication.controllerClasses.each { controllerClass ->
            def clazz = controllerClass.clazz
            String controllerName = WordUtils.uncapitalize(controllerClass.name)
            mapClassAnnotation clazz, Secured, controllerName

            Map<String, List<Class>> annotatedClosures = findAnnotatedClosures(clazz, Secured)
            if (annotatedClosures) {
                _actionMap[controllerName] = annotatedClosures
            }
        }
    }

    // for testing
    static void reset() {
        _actionMap.clear()
        _controllerAnnotationMap.clear()
    }

    // for testing
    static Map<String, Map<String, List<Class>>> getActionMap() {
        return _actionMap
    }

    // for testing
    static Map<String, Class> getControllerAnnotationMap() {
        return _controllerAnnotationMap
    }

    private static void mapClassAnnotation(clazz, annotationClass, controllerName) {
        if (clazz.isAnnotationPresent(annotationClass)) {
            def list = _controllerAnnotationMap[controllerName] ?: []
            list << annotationClass
            _controllerAnnotationMap[controllerName] = list
        }
    }
	
	public static Annotation getConstructorAnnotation(Class annotationClass, String controllerName) {
        def annotations = _controllerAnnotationMap[controllerName]
        if (annotations && annotations.contains(annotationClass)) {
			def annotation = _controllerAnnotationMap.get(controllerName)
			if (annotation != null) {
				
			}
        }
		return null;
	}
	
	public static Annotation getActionAnnotation(Class annotationClass, String controllerName) {
		
	}

    public static boolean requiresAnnotation(Class annotationClass, String controllerName, String actionName) {

        // see if the controller has the annotation
        def annotations = _controllerAnnotationMap[controllerName]
        if (annotations && annotations.contains(annotationClass)) {
            return true
        }

        // otherwise check the action
        Map<String, List<Class>> controllerClosureAnnotations = _actionMap[controllerName] ?: [:]
        List<Class> annotationClasses = controllerClosureAnnotations[actionName]
        return annotationClasses && annotationClasses.contains(annotationClass)
    }

    private static Map<String, List<Class>> findAnnotatedClosures(
            Class clazz, Class... annotationClasses) {

        // since action closures are defined as "def foo = ..." they're
        // fields, but they end up private
        def map = [:]
        for (field in clazz.declaredFields) {
            def fieldAnnotations = []
            for (annotationClass in annotationClasses) {
                if (field.isAnnotationPresent(annotationClass)) {
                    fieldAnnotations << annotationClass
                }
            }
            if (fieldAnnotations) {
                map[field.name] = fieldAnnotations
            }
        }

        return map
    }
}