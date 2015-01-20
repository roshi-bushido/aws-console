package aws.console


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Ec2InstanceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Ec2Instance.list(params), model: [ec2InstanceInstanceCount: Ec2Instance.count()]
    }

    def show(Ec2Instance ec2InstanceInstance) {
        respond ec2InstanceInstance
    }

    def create() {
        respond new Ec2Instance(params)
    }

    @Transactional
    def save(Ec2Instance ec2InstanceInstance) {
        if (ec2InstanceInstance == null) {
            notFound()
            return
        }

        if (ec2InstanceInstance.hasErrors()) {
            respond ec2InstanceInstance.errors, view: 'create'
            return
        }

        ec2InstanceInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ec2Instance.label', default: 'Ec2Instance'), ec2InstanceInstance.id])
                redirect ec2InstanceInstance
            }
            '*' { respond ec2InstanceInstance, [status: CREATED] }
        }
    }

    def edit(Ec2Instance ec2InstanceInstance) {
        respond ec2InstanceInstance
    }

    @Transactional
    def update(Ec2Instance ec2InstanceInstance) {
        if (ec2InstanceInstance == null) {
            notFound()
            return
        }

        if (ec2InstanceInstance.hasErrors()) {
            respond ec2InstanceInstance.errors, view: 'edit'
            return
        }

        ec2InstanceInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Ec2Instance.label', default: 'Ec2Instance'), ec2InstanceInstance.id])
                redirect ec2InstanceInstance
            }
            '*' { respond ec2InstanceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Ec2Instance ec2InstanceInstance) {

        if (ec2InstanceInstance == null) {
            notFound()
            return
        }

        ec2InstanceInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Ec2Instance.label', default: 'Ec2Instance'), ec2InstanceInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ec2Instance.label', default: 'Ec2Instance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
