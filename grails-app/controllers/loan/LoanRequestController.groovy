package loan

import commands.LoanRequestCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_USER','ROLE_ADMIN'])
class LoanRequestController {

    def loanRequestService

    def index(){
        List<LoanRequest> loans = loanRequestService.list()
        render(view: '/loan/index', model: [loans: loans])
    }

    def show(Long id) {
        render(view: '/loan/show', model: [loanRequest: loanRequestService.get(id)])
    }

    def create() {
        render(view: '/loan/request')
    }

    def save(LoanRequestCommand command) {
        if(command.validate()) {
            try {
                loanRequestService.save(command)
                redirect(view: '/loan/index')
            } catch (ValidationException e) {
                respond command.errors, view: 'create'
            }
        }else{
            flash.message = (command.errors)
            render (view: '/loan/request')
        }
    }

    def edit(Long id) {
        respond loanRequestService.get(id)
    }

    def update(LoanRequest loanRequest) {
        if (loanRequest == null) {
            notFound()
            return
        }

        try {
            loanRequestService.save(loanRequest)
        } catch (ValidationException e) {
            respond loanRequest.errors, view:'edit'
            return
        }

    }

    def calculator(){
        render(view: '/loan/calculator')
    }

    def cancel() {
        loanRequestService.cancel(Long.valueOf(params.loanRequest))
        redirect(view: '/loan/index')
    }


    def repaymentService

    def repayments(Long id){
        def request = repaymentService.getRepaymentsByLoanRequest(loanRequestService.get(id))
        render(view: '/loan/repayments', model: [request: request])
    }

    def select(){
        def id = params.id
        println 'repayment: ' + id
        println 'loan: ' + params.loan
        redirect view: '/loan/index'
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'loanRequest.label', default: 'LoanRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
