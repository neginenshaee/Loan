package auth

import commands.UserCommand
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN','ROLE_USER'])
class UserController {

    def userService

    def index() {

    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(UserCommand command) {
        User user
        if(command.validate()) {
            try {
                user = userService.save(command)
            } catch (ValidationException e) {
                respond command.errors, view: 'create'
                return
            }

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                    redirect user
                }
                '*' { respond user, [status: CREATED] }
            }
        }else{
            flash.message = command.errors
            respond command.errors, view: 'create'
        }
    }

    def confirm(String token){
        User u = userService.confirmUser(token)
        if(u==null){
            flash.message = "Not Recognized"
        }
        redirect(action: 'show', id: u.id)
    }

    def edit(Long id) {
        if(id == null) {
            render(view: '/user/edit', model: [user: userService.getCurrentUser()])
        }else{
            render(view: '/user/edit', model: [user: userService.get(id)])
        }
    }

    def password(Long id) {
        User user = userService.get(id)
        render(view: '/user/password', model: [user: user])
    }

    def changePassword() {
        userService.updatePassword(params?.old, params?.new, params?.re)
        redirect(view: '/index')
    }

    def update(UserCommand command) {
        User user
        println command.validate()
        println command.errors
        if(command.validate()) {
            try {
                user = userService.update(command)
            } catch (ValidationException e) {
                respond user.errors, view: 'edit'
                return
            }
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        println(id)
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

    def onChange(Long id){
        boolean status = Boolean.parseBoolean(params.status);
        User user = userService.get(Long.valueOf(params.id))
        user.setEnabled(status)
        userService.update(user)
        render user
    }

    def userloanrequest(Long id){
        redirect(controller: 'adminLoanRequest',  action: 'userrequests', id: id)
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
