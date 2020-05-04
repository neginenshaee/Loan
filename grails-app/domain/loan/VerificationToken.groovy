package loan

import auth.User

import java.sql.Timestamp

class VerificationToken {

    String token
    static belongsTo = [user: User]
    Date expirationDate
    Date dateCreated
    Date lastUpdated


    static constraints = {

    }
}
