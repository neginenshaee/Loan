package loan

import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

@Transactional
class ShadowPaymentService {

    def saveShadowPayments(Loan loan){
        def monthly = loan.monthlyPayment
        def interestVal = loan.interest
        def balance = loan.amount
        for(int i=1; i<=loan.months; i++){
            ShadowPayment sp = new ShadowPayment()
            Date paymentDate
            use(TimeCategory) {
                paymentDate = new Date() + i.month
            }
            sp.setPaymentDate(paymentDate)
            def minterest = balance * interestVal / 1200.00;
            def principal = monthly - minterest;
            sp.setPrincipal(principal)
            sp.setInterest(minterest)
            balance = balance - principal;
            if(balance<0){
                balance = 0.00
            }
            sp.setBalance(balance)
            sp.setLoan(loan)
            sp.save()
        }
    }
}
