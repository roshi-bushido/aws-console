package aws.console

class TestController extends BaseController {
    def amazonWebService

    def index() {
        renderJSON(this.amazonWebService.getEc2().describeReservedInstancesListings())
    }
}
