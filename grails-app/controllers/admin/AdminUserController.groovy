package admin

import auth.User
import grails.plugin.springsecurity.annotation.Secured
import groovy.util.logging.Slf4j

@Slf4j
@Secured('ROLE_ADMIN')
class AdminUserController {


    def userService

    def index() {
        log.error "$controllerName: $actionName In index of userController"
        log.debug "$controllerName: $actionName In index of userController"
        log.info "$controllerName: $actionName In index of userController"
        log.trace "$controllerName: $actionName In index of userController"
        log.warn "$controllerName: $actionName In index of userController"
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

