package loan

import auth.Role
import auth.User
import auth.UserRole
import grails.gorm.transactions.Transactional

class BootStrap {

    def init = { servletContext ->

        def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        println adminRole
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

        def admin = User.findOrSaveWhere(username: 'admin', firstName: 'admin', lastName: 'admin', accountLocked: false, passwordExpired: false, accountExpired: false, password: 'admin')
        def user = User.findOrSaveWhere(username: 'user', firstName: 'user', lastName: 'user', accountLocked: false, passwordExpired: false, accountExpired: false, password: 'user')
        println user

        if (!admin.authorities.contains(adminRole)) {
            UserRole.create(admin, adminRole, true)
        }

        if (!user.authorities.contains(adminRole)) {
            UserRole.create(user, userRole, true)
        }


    }
    def destroy = {
    }
}
