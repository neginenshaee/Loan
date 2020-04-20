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

    def search(int max){
        params.max = Math.min(max ?: 50, 100)
        def users = userService.search(params)
        render(view: '/user/index', model: [params: params, users: users, userCount: users.totalCount])
        return
    }

}

