package loan

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class RepaymentController {

    def repaymentService

    def index() {
        [model: repaymentService.list()]
    }

    def show(Long id) {
        respond repaymentService.get(id)
    }

    def counts(){
        repaymentService.count()
    }

    def create(){}

    def save(Repayment repayment){
        Repayment saved = repaymentService.save(repayment)
        redirect saved
    }
}
