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
        List<LoanRequest> loanRequests = loanRequestService.list(params)
        render(view: '/loanRequest/index', model: [loans: loanRequests])
    }

    def show(Long id) {
        render(view: '/loanRequest/show', model: [loanRequest: loanRequestService.get(id)])
    }

    def create() {
        render(view: '/loanRequest/request', model: [amount: 165000, months: 360, interest: 4.5])

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
            flash.message = command.errors
            render (view: '/loanRequest/request', model: [amount: params.long('amount') ?: 165000, months: params.int('months') ?:360, interest: params.double('interest') ?: 4.5])
        }
    }

    def edit(Long id) {
        respond loanRequestService.get(id)
    }

    def search(int max){
        params.max = Math.min(max ?: 50, 100)
        def loanRequests = loanRequestService.search(params)
        render(view: '/loanRequest/index', model: [params: params, loans: loanRequests, loanRequestCount: loanRequests.totalCount])
        return
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
    def calculate(LoanRequestCommand command){
        if(command.validate(["amount"])) {
            def monthlyPayment = amortizationCalculatorService.calculateMonthlyShare(params.long('amount'), params.int('months'), params.double('interest'))
            def totalInterest = amortizationCalculatorService.calculateTotalInterest(monthlyPayment, params.long('amount'), params.int('months'))
            render template: "amortizationcalc", model: [amount:params.long('amount'), monthlyPayment:monthlyPayment, totalInterest: totalInterest]
        }else{
            flash.message = command.errors
            render (view: '/loanRequest/request', model:[params:params])
//            render(view: '/loanRequest/calculator', model: [amount: 165000, months: 360, interest: 4.5])
        }


    }

    def calculatePayments(){
        List<ShadowPayment> list = amortizationCalculatorService.calculateShadowPayment(params.long('amount'), params.int('months'), params.double('interest'), params.startDate)
        render template: "amortizationschedule", model: [shadowPayments: list, startDate: params.date('startDate', 'MM/dd/YYYY')]
    }

    def cancel(Long id) {
        loanRequestService.cancel(id)
        redirect(action: 'show', id: id)
    }

    def confirm(Long id) {
        loanRequestService.confirm(id)
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

}
