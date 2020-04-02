package admin


import enums.Status
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import loan.LoanRequest

@Secured('ROLE_ADMIN')
class AdminLoanController {

    def loanRequestService

    def index() { }

    def approve(){
        loanRequestService.approve(Long.valueOf(params.loanRequest))
        redirect(controller: 'loanRequest',  action: 'show', id: params.loanRequest)
    }

    def reject(){
        loanRequestService.reject(Long.valueOf(params.loanRequest))
        redirect(controller: 'loanRequest',  action: 'show', id: params.loanRequest)
    }
}
