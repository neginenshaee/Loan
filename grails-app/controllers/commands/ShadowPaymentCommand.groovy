package commands

import auth.User
import enums.Status
import grails.validation.Validateable
import loan.Loan

class ShadowPaymentCommand implements Validateable {
    Date paymentDate
    double principal
    double interest
    double balance
    static belongsTo = [loan: Loan]
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        paymentDate nullable: false
        principal nullable: false
        interest nullable: false
        balance nullable: false
        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
