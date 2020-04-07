package admin

import grails.plugin.springsecurity.annotation.Secured
import loan.Loan
import loan.LoanRequest

@Secured('ROLE_ADMIN')
class LoanController {

    def loanService
    def loanRequestService
    def amortizationCalculatorService

    def index(){
        [loans: loanService.list()]
    }

    def create(Long id) {
        LoanRequest lr = loanRequestService.get(id)
        [id: id, amount: lr.amount, months: lr.months, interest: lr.interest]
    }


    def save(){
        Loan loan = new Loan()
        loan.setLoanRequest(loanRequestService.get(params.id))
        loan.setAmount(params.double('mortgageamount'))
        loan.setMonths(params.int('month'))
        loan.setInterest(params.double('interest'))
        double monthlyPatment = amortizationCalculatorService.calculateMonthlyShare(params.double('mortgageamount'),params.int('month'),params.double('interest'))

        loan.setMonthlyPayment(monthlyPatment)
        loanService.save(loan)
        loanRequestService.end(params.long('id'))
        redirect(view: '/loan/index')
    }
}
