package auth

import enums.UserStatus
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
    UserStatus status

    byte[] image;
    String imageName;
    String imageContentType;


    boolean enabled = false
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Date dateCreated
    Date lastUpdated

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static mapping = {
        status enumType: 'identity'
    }

    static constraints = {
        username nullable: false, blank: false, unique: true
        firstName nullable: true
        lastName nullable: true
        email nullable: false, blank: false, unique: true
        status()
        password nullable: false, blank: false, password: false
        country nullable: true
        address nullable: true

        image nullable: true, blank: true, maxSize: 1024 * 1024 * 1; //20MB
        imageName nullable: true, blank: true;
        imageContentType nullable: true, blank: true;

    }

}
