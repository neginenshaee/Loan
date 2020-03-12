package admin


import enums.Status
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import loan.LoanRequest

@Secured('ROLE_ADMIN')
class AdminLoanController {

    def index() { }


    @Transactional
    def approve(){
        Long id = Long.valueOf(params.loanRequest)
        LoanRequest loanRequest = LoanRequest.findById(id)
        loanRequest.setStatus(Status.APPROVED)
        loanRequest.save()
    }
}
