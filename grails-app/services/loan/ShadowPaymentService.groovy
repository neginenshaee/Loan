package loan

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import groovy.time.TimeCategory
import org.hibernate.Criteria
import org.hibernate.criterion.CriteriaSpecification

import java.text.SimpleDateFormat

@Transactional
class ShadowPaymentService {

    def dataSource

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

    def findAllBy(){
        ShadowPayment.withCriteria {
            createAlias('Loan', 'l', CriteriaSpecification.LEFT_JOIN)
            isNull 'l.id'
        }

    }

    def sessionFactory

    def getEmailsUsingNativeQuery(){
        Date twoDaysAfter
        Date threeDaysAfter
        use(TimeCategory) {
            twoDaysAfter = new Date() + 2.day
            threeDaysAfter = new Date() + 3.day
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String twoDays= dateFormat.format(twoDaysAfter);
        final String threeDays= dateFormat.format(threeDaysAfter);
        final java.sql.Date tw=  java.sql.Date.valueOf(twoDays);
        final java.sql.Date th=  java.sql.Date.valueOf(threeDays);

        def currentSession = sessionFactory.currentSession
        def q = "select user.email,loan.monthly_payment, payment_date,balance from shadow_payment " +
                "left join loan on shadow_payment.loan_id=loan.id " +
                "left join loan_request on loan.loan_request_id= loan_request.id " +
                "left join user on user.id=loan_request.user_id " +
                "where " +
                "payment_date BETWEEN '${tw}' AND '${th}'"
        def data = currentSession.createSQLQuery(q)
        println q
        List<Object[]> result = data.list()
        return result
    }

    def getEmailsUsingHQL() {
        Date twoDaysAfter
        Date threeDaysAfter
        use(TimeCategory) {
            twoDaysAfter = new Date() + 2.day
            threeDaysAfter = new Date() + 3.day
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String twoDays = dateFormat.format(twoDaysAfter);
        final String threeDays = dateFormat.format(threeDaysAfter);
        final java.sql.Date tw = java.sql.Date.valueOf(twoDays);
        final java.sql.Date th = java.sql.Date.valueOf(threeDays);

        List<Object[]> result = ShadowPayment.executeQuery(
                "select sp from ShadowPayment sp " +
                        "join sp.Loan l" +
                        "join l.loanRequest lr " +
                        "join lr.user u " +
                        "where " +
                        "sp.payment_date BETWEEN '${tw}' AND '${th}'"
        )
        println result
        result
    }

    def getEmailsUsingCriteria(){
        Date twoDaysAfter
        Date threeDaysAfter
        use(TimeCategory) {
            twoDaysAfter = new Date() + 2.day
            threeDaysAfter = new Date() + 3.day
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String twoDays = dateFormat.format(twoDaysAfter);
        final String threeDays = dateFormat.format(threeDaysAfter);
        final java.sql.Date tw = java.sql.Date.valueOf(twoDays);
        final java.sql.Date th = java.sql.Date.valueOf(threeDays);

        List<Object[]> result = ShadowPayment.createCriteria().list(){
            createAlias("ShadowPayment.loan", "loan", Criteria.LEFT_JOIN);
            createAlias("loan.loanRequest", "loanRequest", Criteria.LEFT_JOIN);
            createAlias("loanRequest.user", "user", Criteria.LEFT_JOIN);
            between('ShadowPayment.payment_date',tw ,th)
        }

        println result
        result
    }

    def getEmailsUsingView(){
        def db = new Sql(dataSource)
        List<Object[]> result = db.rows("SELECT * FROM getinfofornotificationemail")
        result
    }
}
