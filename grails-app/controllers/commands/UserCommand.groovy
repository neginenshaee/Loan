package commands

import enums.UserStatus
import grails.validation.Validateable

class UserCommand implements Validateable {
    int id
    String firstName
    String lastName
    String username
    String password
    String email
    String country
    String address
    UserStatus status = UserStatus.CREATED
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        username nullable: false, blank: false
        password nullable: false, blank: false
        email nullable: false, blank: false
        country nullable: false, blank: false
        address nullable: false, blank: false
        status nullable: true
        enabled nullable: true
        accountExpired nullable: true
        accountLocked nullable: true
        passwordExpired nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
