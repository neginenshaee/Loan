package loan

import auth.User
import enums.Status

class LoanRequest {
    Long amount
    int months
    double interest
    static belongsTo = [user: User]
    Status status
    Date dateCreated
    Date lastUpdated

    static mapping = {
        status enumType: 'identity'
    }

    static constraints = {
        amount()
        months()
        interest()
        status()
        user()
    }


}
