package commands

import auth.User
import enums.UserStatus
import grails.validation.Validateable

class UserCommand implements Validateable {
    Long id
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

    String repeatPassword
    String oldPassword
    static transients = ['repeatPassword', 'oldPassword']


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

        oldPassword nullable: false, blank: false
        password nullable: false, blank: false
//        , validator: {password, obj ->
//            def password2 = obj.repeatPassword
//            password2 == password ? true : ['userCommand.password.invalid.matchingpasswords']
//        }
        repeatPassword nullable: false, blank: false, validator: {repeatPassword, obj ->
            def password2 = obj.password
            password2 == repeatPassword ? true : ['userCommand.password.invalid.matchingpasswords']
        }
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
        id nullable:  true
    }
}
