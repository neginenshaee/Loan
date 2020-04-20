package loan

import auth.Role
import commands.LoanCommand
import enums.Status
import grails.gorm.transactions.Transactional
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class LoanService {

    def springSecurityService
    def shadowPaymentService
    def amortizationCalculatorService
    def loanRequestService
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

    def save(LoanCommand command){
        Loan loan = new Loan()
        loan.setLoanRequest(loanRequestService.get(command.id))
        loan.setAmount(command.amount)
        loan.setMonths(command.months)
        loan.setInterest(command.interest)
        double monthlyPatment = amortizationCalculatorService.calculateMonthlyShare(command.amount, command.months, command.interest)

        loan.setMonthlyPayment(monthlyPatment)

        Loan savedLoan = loan.save()
        shadowPaymentService.saveShadowPayments(savedLoan)
    }

    def search(params){
        def result = Loan.createCriteria().list(max: params.max, offset: params.offset){
//            createAlias('loanRequest', 'lr', CriteriaSpecification.LEFT_JOIN)
//            createAlias('user', 'u', CriteriaSpecification.LEFT_JOIN)
            if(params.long('searchamount')>=0) {
                ge('amount', params.long('searchamount'))
            }
            if(params.double('searchinterest')>=0) {
                le('interest', params.double('searchinterest'))
            }
            if(params.int('searchmonths')>=0) {
                le('months', params.int('searchmonths'))
            }
//            like('u.username',params.searchusername)
            if(params.double('searchmonthlypayments')>=0) {
                ge('monthlyPayment', params.double('searchmonthlypayments'))
            }
            order("amount", "asc")
        }
        result
    }
}
