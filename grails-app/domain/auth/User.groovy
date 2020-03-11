package auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String firstName
    String lastName
    String username
    String password
    String email
    String country
    String address
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Date dateCreated
    Date lastUpdated

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: false
        username nullable: false, blank: false, unique: true
        firstName nullable: true
        lastName nullable: true
        email nullable: false, blank: false, unique: true
        country nullable: true
        address nullable: true
    }

    static mapping = {
    }
}
