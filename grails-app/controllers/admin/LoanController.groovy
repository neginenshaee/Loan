package admin

import commands.LoanCommand
import grails.plugin.springsecurity.annotation.Secured
import loan.Loan
import loan.LoanRequest
import loan.ShadowPayment

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
            loanService.save(command)
            loanRequestService.end(command.id)
            redirect(view: '/loan/index')
        }else {
            flash.message = command.errors
            render(view: '/loan/create', model: [amount: command.amount ?: 165000, months: command.months ?: 360, interest: command.interest ?: 4.5])
        }

    }
}
