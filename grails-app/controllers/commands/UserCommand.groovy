package commands

import grails.validation.Validateable

class UserCommand implements Validateable {
    String firstName
    String lastName

    static constraints = {
        firstName nullable: false, blank: false

    }
}
