package admin

import commands.LoanCommand
import grails.plugin.springsecurity.annotation.Secured
import loan.Loan
import loan.LoanRequest
import loan.ShadowPayment

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Secured(['ROLE_ADMIN','ROLE_USER'])
class LoanController {

    def loanService
    def loanRequestService
    def shadowPaymentService

    def index(){
        [loans: loanService.list()]
    }

    def show(Long id) {
        render(view: '/loan/show', model: [loan: loanService.get(id), shadowPayments: shadowPaymentService.getByLoan(loanService.get(id))])
    }

    def create(Long id) {
        LoanRequest lr = loanRequestService.get(id)
        [id: id, amount: lr.amount, months: lr.months, interest: lr.interest]
    }

    def save(LoanCommand command){
        if(command.validate(["amount","months","interest"])) {
            Loan loan = loanService.save(command)
            loanRequestService.end(command.id)
            log.info(message(code: 'loanRequest.end.successful', args: command.id))
            log.info(message(code: 'loan.save.success.message'))
            flash.message = (message(code: 'loan.save.success.message', status: OK))
            redirect loan
        }else {
            log.info(command.errors.toString())
            flash.message = command.errors
            render(view: '/loan/create', model: [amount: command.amount ?: 165000, months: command.months ?: 360, interest: command.interest ?: 4.5])
        }
    }

    def search(int max){
        params.max = Math.min(max ?: 50, 100)
        def loans = loanService.search(params)
        if (Objects.isNull(loans) || !loans.iterator().hasNext()) {
            log.info(message(code:'loan.list.empty'))
            flash.message=(message(code:'loan.list.empty',status:NOT_FOUND))
            render(view: '/loan/index', model: [params: params, loans: loans])
        }else {
            log.info("List: ${loans.toString()}")
            render(view: '/loan/index', model: [params: params, loans: loans, loansCount: loans.totalCount])
        }
        return



    }
}
