package auth

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN','ROLE_USER'])
class UserController {

    UserService userService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        println user
        if (user == null) {
            notFound()
            return
        }

        try {
            def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')
            userService.save(user)
            if (!user.authorities.contains(userRole)) {
                UserRole.create(user, userRole, true)
            }
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        User user = userService.get(id)
        println user
        render(view: '/user/edit', model: [user: user])
    }

    def password(Long id) {
        User user = userService.get(id)
        render(view: '/user/password', model: [user: user])
    }

    @Transactional
    def changePassword() {
        String old = params?.old
        String newP = params?.new
        String reP = params?.re
        User user = springSecurityService.currentUser
//        println springSecurityService.encodePassword(old)
//        if (springSecurityService.passwordEncoder.isPasswordValid(user.getPassword(),old,null)){
            if(newP==reP){
                println 'the same!'
                user.setPassword(newP)
//            }
        }
        println user.getPassword()
        user.save(flush: true, failOnError: true)
        redirect(view: '/index')
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
