package loan

import auth.Role
import grails.gorm.transactions.Transactional

@Transactional
class LoanService {

    def springSecurityService
    def shadowPaymentService
    def sessionFactory

    def list(){
        List<Loan> loans
        def authorities = springSecurityService.currentUser.authorities
        if(authorities.contains(Role.findByAuthority("ROLE_USER"))) {
            def currentSession = sessionFactory.currentSession
            def q = "select loan.* from loan " +
                    "left join loan_request on loan.loan_request_id= loan_request.id " +
                    "left join user on user.id=loan_request.user_id " +
                    "where " +
                    "user.id = '${springSecurityService.currentUser.id}'"
            def data = currentSession.createSQLQuery(q)
            data.addEntity(Loan.class)
            loans = data.list()
        }else{
            loans = Loan.findAll()
        }
        loans
    }

    def static get(Long id){
        Loan.get(id)
    }

    def save(Loan loan){
        Loan savedLoan = loan.save()
        shadowPaymentService.saveShadowPayments(savedLoan)
    }
}
