package commands

import auth.User
import enums.Status
import grails.validation.Validateable

class LoanRequestCommand implements Validateable {
    Long amount
    Date actionDate = new Date()
    Date deadline
    String description
    static belongsTo = [user: User]
    Status status = Status.REQUESTED
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        amount nullable: false
        deadline nullable: false, min: new Date()
        description nullable: true, blank: true
        status nullable: true
        actionDate nullable: true
        belongsTo nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true


    }
}
