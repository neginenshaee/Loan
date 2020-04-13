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
        if(command.validate()) {
            try {
                User user = userService.save(command)
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                        redirect user
                    }
                    '*' { respond user, [status: CREATED] }
                }
            } catch (ValidationException e) {
                render (command.errors, view: 'create', model: [params: params])
                return
            }
        }else{
            flash.message = command.errors
            render (view: 'create', model: [params: params])
            return
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
        User user
        if(id == null) {
            user = userService.getCurrentUser()
        }else{
            user = userService.get(id)
        }
        params.id = user.getId()
        params.firstName = user.getFirstName()
        params.lastName = user.getLastName()
        params.address = user.getAddress()
        params.country = user.getCountry()
        render(view: '/user/edit', model: [params: params])
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
        if(command.validate(["firstName", "lastName","country", "address"])) {
            try {
                user = userService.update(command)
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user])
                        redirect user
                    }
                '*'{ respond user, [status: OK] }
        }
            } catch (ValidationException e) {
                respond command.errors, view: 'edit'
                return
            }
        }else{
            println 'errors: '+command.errors
            println 'params: ' + params
            flash.message = command.errors
            render (view: 'edit', model: [params: params])
            return
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
