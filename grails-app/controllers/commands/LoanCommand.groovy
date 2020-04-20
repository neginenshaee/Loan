package commands

import auth.User
import enums.Status
import grails.validation.Validateable
import loan.LoanRequest

class LoanCommand implements Validateable {
    Long id
    Long amount
    int months
    Double interest
    Double monthlyPayment
    static belongsTo = [loanRequest: LoanRequest]
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        amount nullable: false
        months nullable: false
        interest nullable: false
        monthlyPayment nullable: false
        dateCreated nullable: true
        lastUpdated nullable: true
        id nullable: true
    }
}
