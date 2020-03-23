package admin

import auth.User
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class AdminUserController {

    def userService

    def index() {
        List<User> users = userService.list()
        render(view: '/user/index', model: [users: users])
    }
}
