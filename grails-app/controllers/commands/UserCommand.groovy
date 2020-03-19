package commands

import grails.validation.Validateable

class UserCommand implements Validateable {
    String firstName
    String lastName

    static constraints = {
        firstName size: 3..5
        lastName inList: ['a','b']

    }
}
