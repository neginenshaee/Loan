package loan

import auth.Role
import auth.User
import enums.Status

class LoanRequest {
    Long amount
    Date actionDate
    Date deadline
    String description
    static belongsTo = [user: User]
    Status status
    Date dateCreated
    Date lastUpdated

    static mapping = {
        status enumType: 'identity'
    }

    static constraints = {
        amount()
        status()
        description nullable: true
        deadline nullable: true
        user()
        actionDate()
    }


}
