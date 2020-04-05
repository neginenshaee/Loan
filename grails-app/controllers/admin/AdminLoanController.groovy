package admin

import auth.User
import enums.Status
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import loan.LoanRequest

@Secured('ROLE_ADMIN')
class AdminLoanController {

    def loanRequestService

    def index() { }

    def approve(Long id){
        loanRequestService.approve(id)
        redirect(controller: 'loanRequest',  action: 'show', id: id)
    }

    def reject(Long id){
        loanRequestService.reject(id)
        redirect(controller: 'loanRequest',  action: 'show', id: id)
    }

    def userrequests(Long id){
        List<LoanRequest> requests = loanRequestService.requestsOfUser(id)
        render(view: '/loan/userrequests', model: [requests: requests])
    }

}
