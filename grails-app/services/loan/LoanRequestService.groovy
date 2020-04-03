package loan

import auth.Role
import auth.User
import commands.LoanRequestCommand
import enums.Status
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED

@Transactional
class LoanRequestService {

    def springSecurityService
    def userService
    def generalService
    def sendEmailService

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

    def requestsOfUser(Long id){
        User user = userService.get(id)
        List<LoanRequest> requests = LoanRequest.findAllByUser(user)
        requests
    }

    def static count(){
        LoanRequest.count()
    }

    def static delete(id){
        LoanRequest.get(id).delete()
    }

    def save(LoanRequestCommand command){
        try {
            LoanRequest loanRequest = bindValues(command)
            loanRequest.save()
        }catch(Exception e){
            println e
        }
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
        sendEmailService.sendEmail(
                loanRequest.getUser().getEmail(),
                generalService.getMessage("loan.request.status.update", Status.APPROVED),
                generalService.getMessage("loan.request.approved", loanRequest.amount,loanRequest.dateCreated)
        )
    }

    def reject(Long id){
        LoanRequest loanRequest = LoanRequest.findById(id)
        loanRequest.setStatus(Status.REJECTED)
        loanRequest.save()
        sendEmailService.sendEmail(
                loanRequest.getUser().getEmail(),
                generalService.getMessage("loan.request.status.update", Status.REJECTED),
                generalService.getMessage("loan.request.rejected", loanRequest.amount,loanRequest.dateCreated)
        )

    }

    LoanRequest bindValues(LoanRequestCommand c){
        LoanRequest loanRequest = new LoanRequest()
        loanRequest.setUser(springSecurityService.currentUser)
        loanRequest.setStatus(c.status)
        loanRequest.setDeadline(c.deadline)
        loanRequest.setAmount(c.amount)
        loanRequest.setDateCreated(c.dateCreated)
        loanRequest.setActionDate(c.actionDate)
        loanRequest.setLastUpdated(c.lastUpdated)
        loanRequest.setDescription(c.description)
        loanRequest
    }
}
