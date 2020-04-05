package commands

import auth.User
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
    boolean enabled = false
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        username nullable: false, blank: false, validator: { val, obj ->
            if (obj.id) {
                if (User.countByUsernameAndIdNotEqual(val,obj.id)) {
                    return "user.already.exist"
                }
            } else {
                if (User.countByUsername(val)) {
                    return "user.already.exist"
                }
            }
        }
        password nullable: false, blank: false
        email nullable: false, blank: false, validator: { val, obj ->
            if (obj.id) {
                if (User.countByEmailAndIdNotEqual(val,obj.id)) {
                    return "user.already.exist"
                }
            } else {
                if (User.countByEmail(val)) {
                    return "user.already.exist"
                }
            }
        }
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
