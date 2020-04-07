package loan

import commands.LoanRequestCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_USER','ROLE_ADMIN'])
class LoanRequestController {

    def repaymentService
    def loanRequestService

    def index(){
        List<LoanRequest> loans = loanRequestService.list()
        render(view: '/loanRequest/index', model: [loans: loans])
    }

    def show(Long id) {
        render(view: '/loanRequest/show', model: [loanRequest: loanRequestService.get(id)])
    }

    def create() {
        render(view: '/loanRequest/request')
    }

    def save(LoanRequestCommand command) {
        if(command.validate()) {
            try {
                loanRequestService.save(command)
                redirect(view: '/loanRequest/index')
            } catch (ValidationException e) {
                respond command.errors, view: 'create'
            }
        }else{
            flash.message = (command.errors)
            render (view: '/loanRequest/request')
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
        render(view: '/loanRequest/calculator', model: [amount: 165000, months: 360, interest: 4.5])
    }

    def amortizationCalculatorService
    def calculate(){
        def monthlyPayment = amortizationCalculatorService.calculateMonthlyShare(params.double('mortgageamount'), params.int('month'), params.double('interest'))
        def totalInterest = amortizationCalculatorService.calculateTotalInterest(monthlyPayment, params.double('mortgageamount'), params.int('month') )

        render template: "amortizationcalc", model: [mortgageamount:params.double('mortgageamount'), monthlyPayment:monthlyPayment, totalInterest: totalInterest]

    }

    def calculatePayments(){
        List<ShadowPayment> list = amortizationCalculatorService.calculateShadowPayment(params.double('mortgageamount'), params.int('month'), params.double('interest'))
        render template: "amortizationschedule", model: [shadowPayments: list]
    }

    def cancel(Long id) {
        loanRequestService.cancel(id)
        redirect(action: 'show', id: id)
    }

    def repayments(Long id){
        def request = repaymentService.getRepaymentsByLoanRequest(loanRequestService.get(id))
        render(view: '/loanRequest/repayments', model: [request: request])
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
