package loan

import commands.LoanRequestCommand
import exceptions.LoanRequestNotFoundException
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Secured(['ROLE_USER','ROLE_ADMIN'])
class LoanRequestController {

    def loanRequestService
    def amortizationCalculatorService

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

    def edit(Long id) {
        respond loanRequestService.get(id)
    }

    def calculator(){
        render(view: '/loanRequest/calculator', model: [amount: 165000, months: 360, interest: 4.5])
    }

    def save(LoanRequestCommand command) {
        if(command.validate()) {
            LoanRequest loanRequest = loanRequestService.save(command)
            log.info(message(code: 'loanRequest.save.success.message'))
            flash.message = (message(code: 'loanRequest.save.success.message', status: OK))
            redirect loanRequest
        }else{
            log.info(command.errors.toString())
            flash.message = command.errors
            render (view: '/loanRequest/request', model: [amount: params.long('amount') ?: 165000, months: params.int('months') ?:360, interest: params.double('interest') ?: 4.5])
        }
    }

    def cancel(Long id) {
        try {
            loanRequestService.cancel(id)
            log.info (message(code: 'loanRequest.cancel.successful', args: id,status: OK))
            flash.message = (message(code: 'loanRequest.cancel.successful', args: id,status: OK))
            redirect(action: 'show', id: id)
        }catch (LoanRequestNotFoundException e) {
            log.warn(message(code: "loanRequest.not.found.message", id))
            flash.message = (message(code: "loanRequest.not.found.message",status: NOT_FOUND))
            redirect action:"index"
        }
    }

    def confirm(Long id) {
        try {
            loanRequestService.confirm(id)
            log.info (message(code: 'loanRequest.confirm.successful', args: id,status: OK))
            flash.message = (message(code: 'loanRequest.confirm.successful', args: id,status: OK))
            redirect(action: 'show', id: id)
        }catch (LoanRequestNotFoundException e) {
            log.warn(message(code: "loanRequest.not.found.message", id))
            flash.message = (message(code: "loanRequest.not.found.message",status: NOT_FOUND))
            redirect action:"index"
        }
    }

    def search(int max){
        params.max = Math.min(max ?: 50, 100)
        def loanRequests = loanRequestService.search(params)
        if (Objects.isNull(loanRequests) || !loanRequests.iterator().hasNext()) {
            log.info(message(code:'loanRequest.list.empty'))
            flash.message=(message(code:'loanRequest.list.empty',status:NOT_FOUND))
            render(view: '/loanRequest/index', model: [params: params, loans: loanRequests])
        }else {
            log.info("List: ${loanRequests.toString()}")
            render(view: '/loanRequest/index', model: [params: params, loans: loanRequests, loanRequestCount: loanRequests.totalCount])
        }
        return
    }

    def calculate(LoanRequestCommand command){
        if(command.validate(["amount"])) {
            def monthlyPayment = amortizationCalculatorService.calculateMonthlyShare(params.long('amount'), params.int('months'), params.double('interest'))
            def totalInterest = amortizationCalculatorService.calculateTotalInterest(monthlyPayment, params.long('amount'), params.int('months'))
            render template: "amortizationcalc", model: [amount:params.long('amount'), monthlyPayment:monthlyPayment, totalInterest: totalInterest]
        }else{
            flash.message = command.errors
            render (view: '/loanRequest/request', model:[params:params])
        }
    }

    def calculatePayments(){
        List<ShadowPayment> list = amortizationCalculatorService.calculateShadowPayment(params.long('amount'), params.int('months'), params.double('interest'), params.startDate)
        render template: "amortizationschedule", model: [shadowPayments: list, startDate: params.date('startDate', 'MM/dd/YYYY')]
    }

}
