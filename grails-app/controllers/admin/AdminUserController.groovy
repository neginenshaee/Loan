package admin

import auth.User
import exceptions.UserNotFoundException
import grails.plugin.springsecurity.annotation.Secured
import groovy.util.logging.Slf4j

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Slf4j
@Secured('ROLE_ADMIN')
class AdminUserController {


    def userService

    def index(int max) {
        params.max = Math.min(max ?: 50, 100)
        def users = userService.list(params)
        log.info(users.toString())
        if (Objects.isNull(users) || !users.iterator().hasNext()) {
            log.info(message(code:'user.list.empty'))
            flash.message=(message(code:'user.list.empty',status:NOT_FOUND))
            render(view: '/user/index', model: [users: users])
        }else {
            render(view: '/user/index', model: [users: users, userCount: users.totalCount])
        }
        return
    }

    def search(int max){
        params.max = Math.min(max ?: 50, 100)
        def users = userService.search(params)
        log.info(users.toString())
        if (Objects.isNull(users) || !users.iterator().hasNext()) {
            log.info(message(code:'user.list.empty'))
            flash.message=(message(code:'user.list.empty',status:NOT_FOUND))
            render(view: '/user/index', model: [params: params, users: users])
        }else {
            render(view: '/user/index', model: [params: params, users: users, userCount: users.totalCount])
        }
        return
    }

    def delete(Long id){
        try {
            userService.delete(id)
            log.info(message(code: 'log.user.delete.successful'))
            flash.message = (message(code: 'log.user.delete.successful',status: OK))
        } catch (UserNotFoundException e) {
            log.warn(message(code: 'user.not.found.message'))
            flash.message = (message(code: 'user.not.found.message',status: NOT_FOUND))
        }
        redirect action:"index"
    }

}

