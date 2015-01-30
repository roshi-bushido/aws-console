package aws.console

import org.springframework.security.access.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class TestController extends BaseController {
    def amazonWebService

    def index() {
        def ec2 = this.amazonWebService.getEc2()
        def instanceList = new ArrayList()
        ec2.describeInstances().getReservations().each { reservation ->
            instanceList.addAll(reservation.getInstances())
        }
        renderJSON(instanceList)
    }

    def user() {
        def iam = this.amazonWebService.getIam()
        renderJSON(iam.getUser())
    }
}
