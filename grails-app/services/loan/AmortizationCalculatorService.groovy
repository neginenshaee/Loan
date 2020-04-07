package loan

import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

@Transactional
class AmortizationCalculatorService {

    def calculateMonthlyShare(double mortgageAmount, int months, double interest){
        interest = interest / 1200
        def monthlyPayment = mortgageAmount*[interest*Math.pow((1+interest),months)/((Math.pow((1+interest),months))-1)]
        monthlyPayment

    }

    def calculateTotalInterest(double monthlyPayment, double mortgageAmount, int months){
        monthlyPayment * months - mortgageAmount
    }


    def calculateShadowPayment(double balance, int monthNum, double interestVal){
        List<ShadowPayment> shadowPaymentList = new ArrayList<>()
        def monthly = calculateMonthlyShare(balance,monthNum,interestVal)
        def totalInterest = 0.00;

        Loan loan = new Loan()
        loan.setInterest(interestVal)
        loan.setAmount(balance)
        loan.setMonths(monthNum)
        loan.setMonthlyPayment(monthly)
//        loan.save()
        for(int i=1; i<=monthNum; i++){
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
            totalInterest = totalInterest + minterest;
            balance = balance - principal;
            if(balance<0){
                balance = 0.00
            }
            sp.setBalance(balance)
            sp.setLoan(loan)
//            sp.save()
            shadowPaymentList.add(sp)
        }
//
//        $('#here_table').append(table);
        shadowPaymentList
    }
}