package loan

import auth.Role
import enums.Status
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED

@Transactional
class LoanRequestService {

    def springSecurityService

    def static get(id){
        LoanRequest.get(id)
    }

    def list(){
        List<LoanRequest> loans
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
            loans = LoanRequest.findAllByUser(springSecurityService.currentUser)
        }else{
            loans = LoanRequest.findAll()
        }
        loans
    }

    def static count(){
        LoanRequest.count()
    }

    def static delete(id){
        LoanRequest.get(id).delete()
    }

    def save(LoanRequest loanRequest){
        loanRequest.setUser(springSecurityService.currentUser)
        loanRequest.setStatus(Status.REQUESTED)
        loanRequest.setActionDate(new Date())
        loanRequest.save()
    }

    def cancel(Long id){
        LoanRequest loanRequest = LoanRequest.findById(id)
        loanRequest.setStatus(Status.CANCELLED)
        loanRequest.save()
        println loanRequest
    }

    def approve(Long id){
        LoanRequest loanRequest = LoanRequest.findById(id)
        loanRequest.setStatus(Status.APPROVED)
        loanRequest.save()
    }
}
