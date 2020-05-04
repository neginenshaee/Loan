package admin

import exceptions.LoanRequestNotFoundException
import grails.plugin.springsecurity.annotation.Secured
import loan.LoanRequest

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Secured('ROLE_ADMIN')
class AdminLoanRequestController {

    def loanRequestService

    def index() { }

    def approve(Long id){
        try {
            loanRequestService.approve(id)
            log.info (message(code: 'loanRequest.approve.successful', args: id,status: OK))
            flash.message = (message(code: 'loanRequest.approve.successful', args: id,status: OK))
            redirect(controller: 'loanRequest',  action: 'show', id: id)
        }catch (LoanRequestNotFoundException e) {
            log.warn(message(code: "loanRequest.not.found.message", id))
            flash.message = (message(code: "loanRequest.not.found.message",status: NOT_FOUND))
            redirect (controller: 'loanRequest', action:"index")
        }
    }

    def reject(Long id){
        try {
            loanRequestService.reject(id)
            log.info (message(code: 'loanRequest.reject.successful', args: id,status: OK))
            flash.message = (message(code: 'loanRequest.reject.successful', args: id,status: OK))
            redirect(controller: 'loanRequest',  action: 'show', id: id)
        }catch (LoanRequestNotFoundException e) {
            log.warn(message(code: "loanRequest.not.found.message", id))
            flash.message = (message(code: "loanRequest.not.found.message",status: NOT_FOUND))
            redirect (controller: 'loanRequest', action:"index")
        }
    }

    def userrequests(Long id){
        List<LoanRequest> requests = loanRequestService.requestsOfUser(id)
        render(view: '/adminLoanRequest/userrequests', model: [requests: requests])
    }

}
