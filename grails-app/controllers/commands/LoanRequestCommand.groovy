package commands

import auth.User
import enums.Status
import grails.validation.Validateable

class LoanRequestCommand implements Validateable {
    Long amount
    int months
    double interest
    static belongsTo = [user: User]
    Status status = Status.REQUESTED
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        amount nullable: false
        months nullable: false
        interest nullable: false
        status nullable: true
        belongsTo nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true


    }
}
