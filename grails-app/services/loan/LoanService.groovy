package loan

import auth.Role
import grails.gorm.transactions.Transactional

@Transactional
class LoanService {

    def springSecurityService
    def shadowPaymentService

    def list(){
        List<Loan> loans
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
//            loans = Loan.find {loanRequest.user == springSecurityService.currentUser}
        }else{
            loans = Loan.findAll()
        }
        loans
    }

    def save(Loan loan){
        Loan savedLoan = loan.save()
        shadowPaymentService.saveList(savedLoan)
    }
}
