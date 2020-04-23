package loan

import auth.Role
import auth.User
import commands.LoanRequestCommand
import enums.Status
import enums.UserStatus
import exceptions.LoanRequestNotFoundException
import exceptions.UserNotFoundException
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

    def list(params){
        List<LoanRequest> loans
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
            loans = LoanRequest.findAllByUser(springSecurityService.currentUser,[max: params.max, offset: params.offset])
        }else{
            loans = LoanRequest.findAll([max: params.max, offset: params.offset])
        }
        loans
    }

    def count(){
        List<LoanRequest> loans
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
            loans = LoanRequest.findAllByUser(springSecurityService.currentUser)
        }else{
            loans = LoanRequest.findAll()
        }
        loans.size()
    }

    def requestsOfUser(Long id){
        User user = userService.get(id)
        List<LoanRequest> requests = LoanRequest.findAllByUser(user)
        requests
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
        LoanRequest loanRequest = LoanRequest.get(id)
        if(loanRequest != null) {
            loanRequest.setStatus(Status.CANCELLED)
            loanRequest.save()
        }else {
            throw new LoanRequestNotFoundException(String.valueOf(id), generalService.getMessage("loanRequest.not.found.message",id))
        }
    }

    def confirm(Long id){
        LoanRequest loanRequest = LoanRequest.get(id)
        if(loanRequest != null) {
            loanRequest.setStatus(Status.CONFIRMED)
            loanRequest.save()
        }else {
            throw new LoanRequestNotFoundException(String.valueOf(id), generalService.getMessage("loanRequest.not.found.message",id))
        }
    }

    def end(Long id){
        LoanRequest loanRequest = LoanRequest.findById(id)
        loanRequest.setStatus(Status.ENDED)
        loanRequest.save()
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

    def search(params){
        def result = LoanRequest.createCriteria().list(max: params.max, offset: params.offset){
            if(params.long('searchamount')>=0) {
                ge('amount', params.long('searchamount'))
            }
            if(params.double('searchinterest')>=0) {
                le('interest', params.double('searchinterest'))
            }
            if(params.int('searchmonths')>=0) {
                le('months', params.int('searchmonths'))
            }
            if(params.searchstatus!='') {
                eq('status', Status.valueOf(params.searchstatus))
            }
            order("amount", "asc")
        }
        result
    }

    LoanRequest bindValues(LoanRequestCommand c){
        LoanRequest loanRequest = new LoanRequest()
        loanRequest.setUser(springSecurityService.currentUser)
        loanRequest.setStatus(c.status)
        loanRequest.setInterest(c.interest)
        loanRequest.setAmount(c.amount)
        loanRequest.setDateCreated(c.dateCreated)
        loanRequest.setMonths(c.months)
        loanRequest.setLastUpdated(c.lastUpdated)
        loanRequest
    }
}
